package com.two.vue_serve.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.mapper.StudentMapper;
import com.two.vue_serve.po.Clazz;
import com.two.vue_serve.po.StuUpload;
import com.two.vue_serve.po.Student;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.service.StudentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public PageInfo<Student> findAllStudent(int pageNum, int pageSize, Student student) {
        PageInfo<Student> pageInfo=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Student> students=studentMapper.findAllStudent(student);
        pageInfo=new PageInfo<>(students);
        return pageInfo;
    }

    @Override
    public int deleteStudent(Student student) {
        return studentMapper.deleteStudent(student);
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public List<Clazz> findAllClazz(Clazz clazz) {
        return studentMapper.findAllClazz(clazz);
    }

    @Override

    public int loginStudent(String phoneNum, String password) {
        return studentMapper.loginStudent(phoneNum, password);
    }
    public Student login(Student student) {
        return studentMapper.login(student);
    }

    @Override
    public PageInfo<StuUpload> findAllStuHomework(int pageNum, int pageSize, Student student) {
        PageInfo<StuUpload> pageInfo=null;
        PageHelper.startPage(pageNum,pageSize);
        List<StuUpload> homeworks=studentMapper.findAllStuHomework(student);
        pageInfo=new PageInfo<>(homeworks);
        return pageInfo;

    }
	@Override
    public List<Student> studentInfo(Student student) {
        return studentMapper.studentInfo(student);
    }

    @Override
    public int doupdatepwd(String npwd, String opwd, String studentId) {
        int row=0;
        row=studentMapper.editPwd(npwd, opwd, studentId);
        return row;
    }}
