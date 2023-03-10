package com.two.vue_serve.controller;

import com.two.vue_serve.method.FileUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/engOrc")
public class ImagecorrectwritingV3Demo {

    private static Logger logger = LoggerFactory.getLogger(ImagecorrectwritingV3Demo.class);

    private static final String YOUDAO_URL = "https://openapi.youdao.com/correct_writing_image";

    private static final String APP_KEY = "1ee463a460ace742";

    private static final String APP_SECRET = "NsCTIZa3OseULaQL8wE27ERE2oMTAfl4";

    @RequestMapping("/imageRecognition")
    public String ImameCorrectWriting(String url) throws IOException {
        Map<String,String> params = new HashMap<String,String>();

        byte[] imageByteArray = FileUtil.read2ByteArray(url);
        String imageBase64 = new String(org.apache.commons.codec.binary.Base64.encodeBase64(imageByteArray), "UTF-8");
        String salt = String.valueOf(System.currentTimeMillis());
        String grade = "elementary";
        params.put("q", imageBase64);
        params.put("signType", "v3");
        params.put("grade", grade);
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        params.put("curtime", curtime);
        String signStr = APP_KEY + truncate(imageBase64) + salt + curtime + APP_SECRET;
        String sign = getDigest(signStr);
        params.put("appKey", APP_KEY);
        params.put("salt", salt);
        params.put("sign", sign);
        params.put("signType", "v3");
        String result = requestForHttp(YOUDAO_URL,params);
        return result;
    }

    public static String requestForHttp(String url,Map<String,String> params) throws IOException {
        String result = "";

        /** ??????HttpClient */
        CloseableHttpClient httpClient = HttpClients.createDefault();

        /** httpPost */
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String,String>> it = params.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            paramsList.add(new BasicNameValuePair(key,value));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList,"UTF-8"));
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try{
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity,"UTF-8");
            EntityUtils.consume(httpEntity);
        }finally {
            try{
                if(httpResponse!=null){
                    httpResponse.close();
                }
            }catch(IOException e){
                logger.info("## release resouce error ##" + e);
            }
        }
        return result;
    }

    /**
     * ??????????????????
     */
    public static String getDigest(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes();
        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String loadAsBase64(String imgFile)
    {//???????????????????????????????????????????????????????????????Base64????????????

        File file = new File(imgFile);
        if(!file.exists()){
            logger.error("???????????????");
            return null;
        }
        InputStream in = null;
        byte[] data = null;
        //????????????????????????
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //???????????????Base64??????
        System.out.println(Base64.getEncoder().encodeToString(data));
        return Base64.getEncoder().encodeToString(data);//??????Base64?????????????????????????????????
    }

    public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        String result;
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }
}