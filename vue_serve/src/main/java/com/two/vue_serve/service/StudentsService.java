package com.two.vue_serve.service;

import com.github.pagehelper.PageInfo;
import com.two.vue_serve.mapper.StudentMapper;
import com.two.vue_serve.po.Clazz;
import com.two.vue_serve.po.StuUpload;
import com.two.vue_serve.po.Student;
import com.two.vue_serve.po.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


public interface StudentsService {

    PageInfo<Student> findAllStudent(int pageNum, int pageSize,Student student);

    int deleteStudent(Student student);

    int addStudent(Student student);

    int updateStudent(Student student);

    List<Clazz> findAllClazz(Clazz clazz);

    int loginStudent(String phoneNum, String password);

    Student login(Student student);
	PageInfo<StuUpload> findAllStuHomework(int pageNum, int pageSize,Student student);
    List<Student> studentInfo(Student student);
    //ÐÞ¸ÄÃÜÂë
    int doupdatepwd(String npwd,String opwd,String studentId);
}
