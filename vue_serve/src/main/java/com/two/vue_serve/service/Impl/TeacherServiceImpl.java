package com.two.vue_serve.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.mapper.TeacherMapper;
import com.two.vue_serve.po.Clazz;
import com.two.vue_serve.po.Course;
import com.two.vue_serve.po.Student;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Override
    public PageInfo<Teacher> findAllTeacher(int pageNum, int pageSize, Teacher teacher) {
        PageInfo<Teacher> pages=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Teacher> teachers=teacherMapper.findAllTeacher(teacher);
        pages=new PageInfo<>(teachers);
        return pages;
    }

    @Override
    public int deleteTeacher(Teacher teacher) {

        return teacherMapper.deleteByTeacherId(teacher);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public Teacher doFindTeacherById(Integer teacherId) {
        Teacher teacher=null;
        teacher=teacherMapper.findByTeacherId(teacherId);
        return teacher;
    }

    @Override
    public List<Course> findAllCourse( Course course) {
            return teacherMapper.findAllCourse(course);

    }

    @Override
    public Teacher login(Teacher teacher) {
        return teacherMapper.login(teacher);
    }

    @Override
    public PageInfo<Clazz> findAllTeaClazzs(int pageNum, int pageSize,Teacher teacher) {
        PageInfo<Clazz> pages=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Clazz> teachers=teacherMapper.findAllTeaClazzs(teacher);
        pages=new PageInfo<>(teachers);
        return pages;
    }

    @Override
    public PageInfo<Student> findAllTeaClaStu(int pageNum, int pageSize, Teacher teacher) {
        PageInfo<Student> pageInfo=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Student> students=teacherMapper.findAllTeaClaStu(teacher);
        pageInfo=new PageInfo<>(students);
        return pageInfo;

    }

    @Override
    public PageInfo<Clazz> findClazzByParam(int pageNum, int pageSize, Teacher teacher) {
        PageInfo<Clazz> pages=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Clazz> teachers=teacherMapper.findClazzByParam(teacher);
        pages=new PageInfo<>(teachers);
        return pages;

    }

    @Override
    public PageInfo<Student> findClaStuByParam(int pageNum, int pageSize, Teacher teacher) {
        PageInfo<Student> pageInfo=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Student> students=teacherMapper.findClaStuByParam(teacher);
        pageInfo=new PageInfo<>(students);
        return pageInfo;
    }

    @Override
    public int addClaStu(Teacher teacher) {
        return teacherMapper.addClaStu(teacher);
    }

    @Override
    public List<Teacher> teacherInfo(Teacher teacher) {
        return teacherMapper.teacherInfo(teacher);
    }

    @Override
    public int doupdatepwd(String npwd, String opwd, String teacherId) {
        int row=0;
        row=teacherMapper.editPwd(npwd, opwd, teacherId);
        return row;
    }
}
