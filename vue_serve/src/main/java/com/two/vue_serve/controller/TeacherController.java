package com.two.vue_serve.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.*;
import com.two.vue_serve.service.TeacherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    @RequestMapping("/login")
    public Teacher login(Teacher teacher)
    {
        return teacherService.login(teacher);
    }
//    查找所有老师
    @RequestMapping(path = "/findAllTeacher")
    public PageInfo<Teacher> findAllTeacher(int pageNum, int pageSize, Teacher teacher)
    {
        PageInfo<Teacher> teacher1=null;
        teacher1=teacherService.findAllTeacher(pageNum, pageSize, teacher);
        return teacher1;
    }
//    删除老师
    @RequestMapping("/deleteTeacher")
    public int deleteTeacher(Teacher teacher)
    {
        return teacherService.deleteTeacher(teacher);
    }
//    修改老师
    @RequestMapping("/updateTeacher")
    public int updateTeacher(Teacher teacher)
    {
        return teacherService.updateTeacher(teacher);
    }
//    添加老师
    @RequestMapping("/addTeacher")
    public int addTeacher(Teacher teacher)
    {
        return teacherService.addTeacher(teacher);
    }
    @RequestMapping("/findTeacherById")
    public Teacher findTeacherById(Integer teacherId)
    {
        Teacher teacher=null;
        teacher=teacherService.doFindTeacherById(teacherId);
        return teacher;
    }
    //查找所有课程  需要显示老师所教课程
    @RequestMapping("/findAllCourse")
    public List<Course> findAllCourse(Course course)
    {
        return teacherService.findAllCourse(course);
    }

    //根据页面传过来的用户名/ID查找管理的班级
    @RequestMapping("/findAllTeaClazzs")
    public PageInfo<Clazz> findAllTeaClazzs(int pageNum,int pageSize,Teacher teacher){
        PageInfo<Clazz> clazzPageInfo=null;
        clazzPageInfo=teacherService.findAllTeaClazzs(pageNum,pageSize,teacher);
        return clazzPageInfo;
    }

    //根据页面传过来的用户ID和班级ID查找学生
    @RequestMapping("/findAllTeaClaStu")
    public PageInfo<Student> findAllTeaClaStu(int pageNum,int pageSize,Teacher teacher){
        PageInfo<Student> studentPageInfo=null;
        studentPageInfo=teacherService.findAllTeaClaStu(pageNum,pageSize,teacher);
        return studentPageInfo;
    }

    //根据页面传过来的老师ID和班级名
    @RequestMapping("/findClazzByParam")
    public PageInfo<Clazz> findClazzByParam(int pageNum,int pageSize,Teacher teacher){
        PageInfo<Clazz> clazzPageInfo=null;
        clazzPageInfo=teacherService.findClazzByParam(pageNum,pageSize,teacher);
        return clazzPageInfo;
    }

    //根据页面传过来的老师ID和班级名
    @RequestMapping("/findClaStuByParam")
    public PageInfo<Student> findClaStuByParam(int pageNum,int pageSize,Teacher teacher){
        PageInfo<Student> studentPageInfo=null;
        studentPageInfo=teacherService.findClaStuByParam(pageNum,pageSize,teacher);
        return studentPageInfo;
    }

    //在学生管理页面添加学生
    @RequestMapping("/addClaStu")
    public int addClaStu(Teacher teacher)
    {
        return teacherService.addClaStu(teacher);
    }

    @RequestMapping("/teacherInfo")
    public List<Teacher> teacherInfo(Teacher teacher){ return teacherService.teacherInfo(teacher);}

    @RequestMapping("/doupdatepwd")
    public int doupdatepwd(String npwd,String opwd,String teacherId){
        int row=0;
        row=teacherService.doupdatepwd(npwd, opwd, teacherId);
        return row;
    }
}
