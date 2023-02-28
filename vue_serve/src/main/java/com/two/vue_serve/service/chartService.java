package com.two.vue_serve.service;

import com.two.vue_serve.po.Student;
import com.two.vue_serve.po.Teacher;

import java.util.List;
import java.util.Map;

public interface chartService {

//    List<Map<Object,Object>> barChart(Teacher teacher);
//    List<Map<Object,Object>> twoChart(Teacher teacher);
//
//    List<Map<Object,Object>> fourChart(Teacher teacher);

    //老师页面第一个
    List<Map<Object,Object>> barChart(Teacher teacher);
    //老师页面第二个
    List<Map<Object,Object>> twoChart(Teacher teacher);
    //老师页面第四个
    List<Map<Object,Object>> fourChart(Teacher teacher);

    //学生详情页里的第一个图表
    List<Map<Object,Object>> fiveChart(Student student);

    //学生页面图表
    List<Map<Object,Object>> sixChart(Student student);

    List<Map<Object,Object>> sixaChart(Student student);
    List<Map<Object,Object>> sixbChart(Student student);
    //学生第二个表
    List<Map<Object,Object>> sevenChart(Student student);
    //学生第三个表
    List<Map<Object,Object>> eightChart(Student student);
    //学生第四个表
    List<Map<Object,Object>> nineChart(Student student);
}
