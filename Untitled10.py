#!/usr/bin/env python
# coding: utf-8

# In[83]:


# -*- coding: utf-8 -*-
from urllib import parse
import base64
import json
import jsonpath
import hashlib
import time
import requests
from PIL import Image, ImageDraw, ImageFont
import numpy as np
import re
import oss2
import sys
import base64
import cv2
import math
import imageio
from scipy import misc, ndimage

import sys
import uuid
import importlib
importlib.reload(sys)


YOUDAO_URL = 'https://openapi.youdao.com/cut_question'
APP_KEY = '63230d360e0117dd'
APP_SECRET = 'YUhiDDcDcqEMWNlr3KIkVOIZSVtHTndk'

language = "cn|en"
location = "true"
text_font = ImageFont.truetype('c:/windows/fonts/Verdana.ttf', 50)
auth = oss2.Auth('LTAI5t9rtS8p2qmryN9jvjc1', 'X3V6C3RfMBrMDahoU3VsqtmgyrhgWz')
bucket = oss2.Bucket(auth, 'https://oss-cn-qingdao.aliyuncs.com', 'zjy123456')
answer_file='answer.jpg'
homework_file='homework.jpg'

# OCR手写文字识别接口地址
URL = "http://webapi.xfyun.cn/v1/service/v1/ocr/handwriting"
# 应用APPID(必须为webapi类型应用,并开通手写文字识别服务,参考帖子如何创建一个webapi应用：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=36481)
APPID = "627bdaeb"
# 接口密钥(webapi类型应用开通手写文字识别后，控制台--我的应用---手写文字识别---相应服务的apikey)
API_KEY = "e2710243e05e3ac1aa1c95f4e9db927a"
def getHeader(language,location):
    curTime = str(int(time.time()))
    param = "{\"language\":\""+language+"\",\"location\":\""+location+"\"}"
    paramBase64 = base64.b64encode(param.encode('utf-8'))

    m2 = hashlib.md5()
    str1 = API_KEY + curTime + str(paramBase64, 'utf-8')
    m2.update(str1.encode('utf-8'))
    checkSum = m2.hexdigest()
	# 组装http请求头
    header = {
        'X-CurTime': curTime,
        'X-Param': paramBase64,
        'X-Appid': APPID,
        'X-CheckSum': checkSum,
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
    }
    return header

headers=getHeader(language, location)

def getBody(filepath):
    with open(filepath, 'rb') as f:
        imgfile = f.read()
    data = {'image': str(base64.b64encode(imgfile), 'utf-8')}
    return data
def truncate(q):
    if q is None:
        return None
    q_utf8 = q.decode("utf-8")
    size = len(q_utf8)
    return q_utf8 if size <= 20 else q_utf8[0:10] + str(size) + q_utf8[size - 10:size]



def encrypt(signStr):
    hash_algorithm = hashlib.sha256()
    hash_algorithm.update(signStr.encode('utf-8'))
    return hash_algorithm.hexdigest()


def do_request(data):
    headers = {'Content-Type': 'application/x-www-form-urlencoded'}
    return requests.post(YOUDAO_URL, data=data, headers=headers)


# In[85]:


def expression_parser(word_string):
    return '√'


# In[86]:


#图像矫正
def Direction_correction(file_path):
    img_path = file_path
    img = cv2.imread(img_path)
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    #cv2.Canny(src, thresh1, thresh2) 进行canny边缘检测
    # src表示输入的图片，
    #thresh1表示最小阈值，
    #thresh2表示最大阈值，用于进一步删选边缘信息
    #例如：50，70 梯度像素>70: 强边缘像素；50<梯度像素<70:弱边缘像素； 梯度像素<50：被抑制
    #apertureSize 指边缘检测器的大小。canny使用非极大值抑制的方法来判断弱边缘像素是不是该图像的边缘信息。
    # 这个方法使得canny不像sobel算法一样，使得canny对于图像的边缘信息定位准确，而又不像laplacian算子那样对图像的噪声十分敏感。
    edges = cv2.Canny(gray, 50, 150, apertureSize=3)
    # 霍夫变换
    #rho = x cos (theta) + y sin (theta)
    lines = cv2.HoughLines(edges, 1, np.pi / 180, 0)
    rotate_angle = 0
    for rho, theta in lines[0]:
        a = np.cos(theta)
        b = np.sin(theta)
        x0 = a * rho
        y0 = b * rho
        x1 = int(x0 + 1000 * (-b))
        y1 = int(y0 + 1000 * (a))
        x2 = int(x0 - 1000 * (-b))
        y2 = int(y0 - 1000 * (a))
        if x1 == x2 or y1 == y2:
            continue
        t = float(y2 - y1) / (x2 - x1)
        rotate_angle = math.degrees(math.atan(t))
        if rotate_angle > 45:
            rotate_angle = -90 + rotate_angle
        elif rotate_angle < -45:
            rotate_angle = 90 + rotate_angle
    rotate_img = ndimage.rotate(img, rotate_angle)
    imageio.imsave(file_path, rotate_img)
#获取答案
def get_answer(data):
    for result_line in data[0]['line']:
        for result_word in result_line['word']:
            findall_1 = re.findall(r'[（](.*?)[)]', result_word['content'])
            findall_2 = re.findall(r'[(](.*?)[)]', result_word['content'])
            findall_3 = re.findall(r'[（](.*?)[）]', result_word['content'])
            findall_4 = re.findall(r'[(](.*?)[）]', result_word['content'])
            if(len(findall_1)!=0):
                return findall_1[0]
            elif(len(findall_2)!=0):
                return findall_2[0]
            elif(len(findall_3)!=0):
                return findall_3[0]
            elif(len(findall_4)!=0):
                return findall_4[0]
    return None
def get_teacher_answer(imgPath):
    str=[]
    result = get_result(imgPath)
    for i in range(0, len(result['Result']['regions'])):
        imgsave = imgPath.replace(".jpg","") + "_%d.png"%i 
        coordinate = result['Result']['regions'][i]['boundingBox'].split(',')
        coordinate_result = (int(coordinate[0]),int(coordinate[1]),int(coordinate[4]),int(coordinate[5])) 
        image = Image.open(imgPath)
        region = image.crop(coordinate_result)
        region.save(imgsave)
        #文字识别
        answer_r = requests.post(URL, headers=getHeader(language,location), data=getBody(imgsave))
        answer_json= json.loads(answer_r.content)
        answer_data = answer_json["data"]["block"]
        
        str.append(get_answer(answer_data))
    return str

def get_result(imgPath):
    f = open(imgPath, 'rb')  # 二进制方式打开图文件
    q = base64.b64encode(f.read())  # 读取文件内容，转换为base64编码
    f.close()

    data = {}
    data['imageType'] = '1'
    data['q'] = q
    data['docType'] = 'json'
    data['signType'] = 'v3'
    curtime = str(int(time.time()))
    data['curtime'] = curtime
    salt = str(uuid.uuid1())
    signStr = APP_KEY + truncate(q) + salt + curtime + APP_SECRET
    sign = encrypt(signStr)
    data['appKey'] = APP_KEY
    data['salt'] = salt
    data['sign'] = sign
    
    response = do_request(data)
    result = json.loads(bytes.decode(response.content))
    return result


# In[109]:


def connect(answer,homework,imgPath):
    #获取文件到本地
    #answer="https://zjy123456.oss-cn-qingdao.aliyuncs.com/zjy123456/2022-04-20/answer1.jpg"
    
    object_name = answer.replace('https://zjy123456.oss-cn-qingdao.aliyuncs.com/','')
    answer = bucket.sign_url('GET', object_name, 60, slash_safe=True)
    result_1 = bucket.get_object_with_url_to_file(answer, answer_file)
    Direction_correction(answer_file)

    #homework="https://zjy123456.oss-cn-qingdao.aliyuncs.com/zjy123456/2022-04-20/homework1.jpg"
    
    object_name = homework.replace('https://zjy123456.oss-cn-qingdao.aliyuncs.com/','')
    homework = bucket.sign_url('GET', object_name, 60, slash_safe=True)
    result_2 = bucket.get_object_with_url_to_file(homework, homework_file)
    Direction_correction(homework_file)
    #获取老师上传图片的答案
    teacher_answer = get_teacher_answer(answer_file)
    
    
    result = get_result(imgPath)
    _image = Image.open(imgPath)
    draw = ImageDraw.Draw(_image)
    for i in range(0, len(result['Result']['regions'])):
        imgsave = imgPath.replace(".jpg","") + "_%d.png"%i 
        coordinate = result['Result']['regions'][i]['boundingBox'].split(',')
        coordinate_result = (int(coordinate[0]),int(coordinate[1]),int(coordinate[4]),int(coordinate[5]))       
        rect =(int(coordinate[0]),int(coordinate[1]),int(coordinate[4]),int(coordinate[5]))
        image = Image.open(imgPath)
        region = image.crop(coordinate_result)
        region.save(imgsave)
        
        answer_r = requests.post(URL, headers=getHeader(language,location), data=getBody(imgsave))
        answer_json= json.loads(answer_r.content)
        answer_data = answer_json["data"]["block"]
        
        answer = get_answer(answer_data)
        if(answer!=None):
            answer=answer.replace('.', '')
        if(answer=='X'):
            answer='×'
        
        if(teacher_answer[i]=='X'):
            teacher_answer[i]='×'
        if(teacher_answer[i]!=None):
            teacher_answer[i]=teacher_answer[i].replace('.', '')
        
        if(teacher_answer[i]==None or answer==None):
            draw.rectangle(rect,None,'blue')
        elif(answer==teacher_answer[i]):
            draw.rectangle(rect,None,'green')
            draw.text((int(coordinate[4])+5,int(coordinate[1])),'√','#00FF00',font=text_font)
        else:
            draw.rectangle(rect,None,'red')
            draw.text((int(coordinate[4])+5,int(coordinate[1])),'×',(255,0,0),font=text_font)
    _image.save('result.jpg','jpeg')
    encoded = base64.b64encode(open('result.jpg', 'rb').read()).decode('ascii')
    return encoded


# In[ ]:


if __name__=="__main__":
    answer = sys.argv[1]
    homework = sys.argv[2]
    encoded = connect(answer,homework,homework_file)
    print(encoded)

