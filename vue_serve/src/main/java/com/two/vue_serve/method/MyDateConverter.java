package com.two.vue_serve.method;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义格式转换器
 * Stirng 转为Date
 */
@Component
public class MyDateConverter implements Converter<String,Date> {


    @Override
    public Date convert(String s) {
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date date=f.parse(s);
            return date;
        }catch (Exception ex){
        }
        return null;
    }
}