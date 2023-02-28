package com.two.vue_serve.controller;

import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.Course;
import com.two.vue_serve.service.CourseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;
//    查找所有课程

    @RequestMapping("/findAllCourses")
    public PageInfo<Course> findAllCourses(Integer pageNum,Integer pageSize,Course course)
    {
        return courseService.findAllCourses(pageNum, pageSize, course);
    }
//    添加课程
    @RequestMapping("/addCourse")
    public int addCourse(Course course)
    {
        return courseService.addCourse(course);
    }
//    删除课程
    @RequestMapping("/deleteCourse")
    public int deleteCourse(Course course)
    {
        return courseService.deleteCourse(course);
    }
//    修改课程
    @RequestMapping("/updateCourse")
    public int updateCourse(Course course)
    {return courseService.updateCourse(course);}

    @RequestMapping("/findallCourse")
    public List<Course> selectCourse(){
        return courseService.selectCource();
    }
}
