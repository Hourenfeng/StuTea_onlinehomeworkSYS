package com.two.vue_serve.service;


import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.Clazz;

import java.util.List;

public interface ClazzService {
//    查找所有课程
    PageInfo<Clazz> findAllClazzs(int pageNum,int pageSize,Clazz clazz);
//删除课程
    int deleteClazz(Clazz clazz);
//添加课程
    int addClazz(Clazz clazz);
//修改课程
    int updateClazz(Clazz clazz);

}
