package com.two.vue_serve.controller;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/oss")
public class OSSController {
        //阿里云 学生端上传拍照作业到服务器
        @RequestMapping("/policy")
        public Map<String,String> policy(){
            String accessId = "LTAI5tGxzrKzfRsNYUKLF5mD";
            String accessKey = "aOlYDUIqb7LD9AHBtozIvOBVezk7a0";
            // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
            String endpoint = "oss-cn-qingdao.aliyuncs.com";
            // 填写Bucket名称，例如examplebucket。
            String bucket = "zjy123456";
            // 填写Host名称，格式为https://bucketname.endpoint。
            String host = "https://"+bucket+"."+endpoint;

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(new Date());
            // 设置上传到OSS文件的前缀，可置空此项。置空后，文件将上传至Bucket的根目录下。
            String dir = "zjy123456/"+date+"/";

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKey);
            try {
                long expireTime = 30;
                long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
                Date expiration = new Date(expireEndTime);
                // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
                PolicyConditions policyConds = new PolicyConditions();
                policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
                policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

                String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
                byte[] binaryData = postPolicy.getBytes("utf-8");
                String encodedPolicy = BinaryUtil.toBase64String(binaryData);
                String postSignature = ossClient.calculatePostSignature(postPolicy);

                Map<String, String> respMap = new LinkedHashMap<String, String>();
                respMap.put("accessid", accessId);
                respMap.put("policy", encodedPolicy);
                respMap.put("signature", postSignature);
                respMap.put("dir", dir);
                respMap.put("host", host);
                respMap.put("expire", String.valueOf(expireEndTime / 1000));
                // respMap.put("expire", formatISO8601Date(expiration));
                return respMap;
            } catch (Exception e) {
                // Assert.fail(e.getMessage());
                System.out.println(e.getMessage());
            } finally {
                ossClient.shutdown();
            }
            return null;
        }
}


