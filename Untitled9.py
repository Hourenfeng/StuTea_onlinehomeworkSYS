#!/usr/bin/env python
# coding: utf-8

# In[112]:


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
def getBody(filepath):
    with open(filepath, 'rb') as f:
        imgfile = f.read()
    data = {'image': str(base64.b64encode(imgfile), 'utf-8')}
    return data


# In[113]:


def expression_parser(word_string):
    if(len(word_string.split('='))!=2):
        return ''
    elif word_string.split('=')[1]=='':
        return '×'
    else:
        string=word_string.replace('×','*')
        string=string.replace('÷','/')
        calculated_mode=string.split('=')[0]
        stu_result=string.split('=')[1]
        stu_result= int(float(stu_result))
        real_result=caculator(calculated_mode)
        if '=' in string and string[0]!= '=' and string[-1]!='=' and stu_result==real_result:
            return '√'
        elif '=' in string and string[0]!= '=' and string[-1]!='=' and stu_result!=real_result:
            return '×'
        else:
            return ''


# In[114]:


def caculator(eq):
    format_list = eq_format(eq) # 把字符串变成格式化列表形式
    s_eq = simplify(format_list) # 去括号，得到无括号的一个格式化列表
    ans = calculate(s_eq)    # 计算最终结果
    if len(ans) == 2:      # 判断最终结果为正数还是负数
        ans = -float(ans[1])
    else:
        ans = float(ans[0])
    return ans
def eq_format(eq):
    format_list = re.findall('[\d\.]+|\(|\+|\-|\*|\/|\)',eq)
    return format_list
def simplify(format_list):
  
  
    bracket = 0   # 用于存放左括号在格式化列表中的索引
    count = 0
    for i in format_list:
        if i == '(':
            bracket = count
        elif i == ')':
            temp = format_list[bracket + 1 : count]
    # print(temp)
            new_temp = calculate(temp)
            format_list = format_list[:bracket] + new_temp + format_list[count+1:]
            format_list = change(format_list,bracket)   # 解决去括号后会出现的-- +- 问题
            return simplify(format_list)      # 递归去括号
        count = count + 1
    return format_list  
def calculate(s_eq):
    if '*' or '/' in s_eq:
        s_eq = remove_multiplication_division(s_eq)
    if '+' or '-' in s_eq:
        s_eq = remove_plus_minus(s_eq)
    return s_eq
def remove_multiplication_division(eq):
    count = 0
    for i in eq:
        if i == '*':
            if eq[count+1] != '-':
                eq[count-1] = float(eq[count-1]) * float(eq[count+1])
                del(eq[count])
                del(eq[count])
            elif eq[count+1] == '-':
                eq[count] = float(eq[count-1]) * float(eq[count+2])
                eq[count-1] = '-'
                del(eq[count+1])
                del(eq[count+1])
            eq = change(eq,count-1)
            return remove_multiplication_division(eq)
        elif i == '/':
            if eq[count+1] != '-':
                eq[count-1] = float(eq[count-1]) / float(eq[count+1])
                del(eq[count])
                del(eq[count])
            elif eq[count+1] == '-':
                eq[count] = float(eq[count-1]) / float(eq[count+2])
                eq[count-1] = '-'
                del(eq[count+1])
                del(eq[count+1])
            eq = change(eq,count-1)
            return remove_multiplication_division(eq)
        count = count + 1
    return eq
def remove_plus_minus(eq):
    count = 0
    if eq[0] != '-':
        sum = float(eq[0])
    else:
        sum = 0.0
    for i in eq:
        if i == '-':
            sum = sum - float(eq[count+1])
        elif i == '+':
            sum = sum + float(eq[count+1])
        count = count + 1
    if sum >= 0:
        eq = [str(sum)]
    else:
        eq = ['-',str(-sum)]
    return eq
def change(eq,count):
    if eq[count] == '-':
        if eq[count-1] == '-':
            eq[count-1] = '+'
            del eq[count]
        elif eq[count-1] == '+':
            eq[count-1] = '-'
            del eq[count]
    return eq

def identify(url):
    auth = oss2.Auth('LTAI5t9rtS8p2qmryN9jvjc1', 'X3V6C3RfMBrMDahoU3VsqtmgyrhgWz')
    bucket = oss2.Bucket(auth, 'https://oss-cn-qingdao.aliyuncs.com', 'zjy123456')
    object_name = url.replace('https://zjy123456.oss-cn-qingdao.aliyuncs.com/','')
    url = bucket.sign_url('GET', object_name, 60, slash_safe=True)
    original_file='D:\\shixi\\math.jpg'
    result = bucket.get_object_with_url_to_file(url, original_file)

    _image = Image.open(original_file)
    draw = ImageDraw.Draw(_image)
    language = "cn|en"
    location = "true"
    # headers=getHeader(language, location) 图像识别
    r = requests.post(URL, headers=getHeader(language,location), data=getBody(original_file))
    result_json= json.loads(r.content)
    data = result_json["data"]["block"]
    text_font = ImageFont.truetype('c:/windows/fonts/Verdana.ttf', 20)
    for words_result in data[0]['line']:
        location = words_result['location']
        rect =(location['top_left']['x'],location['top_left']['y'],location['right_bottom']['x'],location['right_bottom']['y'])
        draw.rectangle(rect,None,'red')
        draw.text((location['right_bottom']['x']+10,location['top_left']['y']+5),expression_parser(words_result['word'][0]['content']),(255,0,0),font=text_font)
    _image.save('result.jpg','jpeg')
    encoded = base64.b64encode(open('result.jpg', 'rb').read()).decode('ascii')
    return encoded


# In[115]:


if __name__=="__main__":
    url = sys.argv[1]
    encoded = identify(url)
    print(encoded)


# In[ ]:




