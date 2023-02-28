package com.two.vue_serve.mapper;

import com.two.vue_serve.po.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<Course> findAllCourse(Course course);

    int addCourse(Course course);

    int deleteCourse(Course course);

    int updateCourse(Course course);

    List<Course> selectCourse();
}
