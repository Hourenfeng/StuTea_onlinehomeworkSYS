package com.two.vue_serve.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.mapper.CourseMapper;
import com.two.vue_serve.po.Course;
import com.two.vue_serve.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Override
    public PageInfo<Course> findAllCourses(Integer pageNum, Integer pageSize, Course course) {
        PageInfo<Course> pages=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Course> courses=courseMapper.findAllCourse(course);
        pages= new PageInfo<>(courses);
        return pages;
    }

    @Override
    public int deleteCourse(Course course) {
        return courseMapper.deleteCourse(course);
    }

    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public List selectCource() {
        return courseMapper.selectCourse();
    }


}
