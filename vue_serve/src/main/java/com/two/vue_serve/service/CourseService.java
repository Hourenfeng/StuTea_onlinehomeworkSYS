package com.two.vue_serve.service;

import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.Course;

import java.util.List;

public interface CourseService {
    PageInfo<Course> findAllCourses(Integer pageNum,Integer pageSize,Course course);

    int deleteCourse(Course course);

    int addCourse(Course course);

    int updateCourse(Course course);

    List selectCource();
}
