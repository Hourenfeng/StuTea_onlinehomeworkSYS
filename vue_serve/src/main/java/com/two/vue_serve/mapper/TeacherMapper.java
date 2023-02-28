package com.two.vue_serve.mapper;

import com.two.vue_serve.dto.TeacherDto;
import com.two.vue_serve.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherMapper {
    List<Teacher> findAllTeacher(Teacher teacher);

    int deleteByTeacherId(Teacher teacher);

    int addTeacher(Teacher teacher);

    int updateTeacher(Teacher teacher);

    Teacher findByTeacherId(Integer teacherId);

    List<Course> findAllCourse(Course course);

    Teacher login(Teacher teacher);

    List<Clazz> findAllTeaClazzs(Teacher teacher);//查找所有教的班级

    List<Student> findAllTeaClaStu(Teacher teacher);//查找所教班级的所有学生

    List<Clazz> findClazzByParam(Teacher teacher);//搜索班级

    List<Student> findClaStuByParam(Teacher teacher);//搜索学生

    int addClaStu(Teacher teacher);

    List<Teacher> teacherInfo(Teacher teacher);
    //修改密码
    int editPwd(@Param("npwd")String npwd, @Param("opwd")String opwd, @Param("teacherId")String teacherId);
}
