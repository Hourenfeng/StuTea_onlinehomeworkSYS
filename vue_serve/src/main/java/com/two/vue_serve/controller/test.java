package com.two.vue_serve.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@CrossOrigin
@RestController
@RequestMapping("/mathOrc")
public class test {
    @RequestMapping("/imageBase64")
    public String ImageBase64(String url){
        String[] arguments = new String[] {"python","C:\\Users\\ZJY\\Untitled9.py",url};
        //这里构建要在cmd中输入的参数，第二个参数表示.py文件的路径，
        // 第二个之后的参数都表示要传给.py文件的参数，可以根据.py文件的需求写
        String result="";
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            //这个方法相当于在cmd中输入 pyh D:/ccc/
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;

            result=in.readLine();
            
            in.close();
            int re = process.waitFor();
            //因为是process这个进程调用.py文件， 所以要将当前进程阻塞等待.py文件执行完毕，
            // 若果.py文件成功运行完毕，此方法将返回0，代表执行成功
            System.out.println(re);
            //执行成功的话这里会打印一个0，否则会打印1，2或者其他数字
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @RequestMapping("/answerTest")
    public String Answer_test(String answer,String homework){
        String[] arguments = new String[] {"python","C:\\Users\\ZJY\\test.py",answer,homework};
        //这里构建要在cmd中输入的参数，第二个参数表示.py文件的路径，
        // 第二个之后的参数都表示要传给.py文件的参数，可以根据.py文件的需求写
        String result="";
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            //这个方法相当于在cmd中输入 pyh D:/ccc/
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;

            result=in.readLine();

            in.close();
            int re = process.waitFor();
            //因为是process这个进程调用.py文件， 所以要将当前进程阻塞等待.py文件执行完毕，
            // 若果.py文件成功运行完毕，此方法将返回0，代表执行成功
            System.out.println(re);
            //执行成功的话这里会打印一个0，否则会打印1，2或者其他数字
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
