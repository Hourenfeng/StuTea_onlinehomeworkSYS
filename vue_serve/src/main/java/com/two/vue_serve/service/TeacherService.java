package com.two.vue_serve.service;


import com.github.pagehelper.PageInfo;
import com.two.vue_serve.dto.TeacherDto;
import com.two.vue_serve.po.*;

import java.util.List;

public interface TeacherService {
    PageInfo<Teacher> findAllTeacher(int pageNum, int pageSize, Teacher teacher);

    int deleteTeacher(Teacher teacher);

    int addTeacher(Teacher teacher);

    int updateTeacher(Teacher teacher);

    Teacher doFindTeacherById(Integer teacherId);

    List<Course> findAllCourse(Course course);

    Teacher login(Teacher teacher);

    PageInfo<Clazz> findAllTeaClazzs(int pageNum, int pageSize, Teacher teacher);

    PageInfo<Student> findAllTeaClaStu(int pageNum, int pageSize, Teacher teacher);

    PageInfo<Clazz> findClazzByParam(int pageNum, int pageSize, Teacher teacher);

    PageInfo<Student> findClaStuByParam(int pageNum, int pageSize, Teacher teacher);

    int addClaStu(Teacher teacher);

    List<Teacher> teacherInfo(Teacher teacher);

    //修改密码
    int doupdatepwd(String npwd,String opwd,String teacherId);
}
