package com.two.vue_serve.mapper;

import com.two.vue_serve.po.Clazz;
import com.two.vue_serve.po.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
//    查找所有课程
    List<Clazz> findAllClazzs(Clazz clazz);
//删除课程
    int deleteClazz(Clazz clazz);
//添加课程
    int addClazz(Clazz clazz);
//修改课程
    int updateClazz(Clazz clazz);


}
