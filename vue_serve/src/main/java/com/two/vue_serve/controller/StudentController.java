package com.two.vue_serve.controller;

import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.Clazz;
import com.two.vue_serve.po.StuUpload;
import com.two.vue_serve.po.Student;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.service.StudentsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentsService studentsService;
//    查找所有学生
    @RequestMapping(path = "/findAllStudents")
    public PageInfo<Student> findAllStudent(int pageNum,int pageSize,Student student)
    {
        PageInfo<Student> stu = null;
        stu= studentsService.findAllStudent(pageNum,pageSize,student);
        return stu;
    }
    @RequestMapping("/findAllClazz")
    public List<Clazz> findAllClazz(Clazz clazz)
    {return studentsService.findAllClazz(clazz);}
//    删除学生
    @RequestMapping("/deleteStudent")
    public int deleteStudent(Student student)
    {
        return studentsService.deleteStudent(student);
    }
//    添加学生
    @RequestMapping("/addStudent")
    public int addStudent(Student student)
    {
        return studentsService.addStudent(student);
    }
//    修改学生
    @RequestMapping("/updateStudent")
    public int updateStudent(Student student)
    {
        return studentsService.updateStudent(student);
    }

//   小程序登录
    @RequestMapping("/loginStudent")
    public int loginStudent(String phoneNum,String password) {
        return studentsService.loginStudent(phoneNum, password);
    }
    @RequestMapping("/login")
    public Student  login(Student student)
    {
        return studentsService.login(student);
    }


	@RequestMapping("/doupdatepwd")
    public int doupdatepwd(String npwd,String opwd,String studentId){
        int row=0;
        row=studentsService.doupdatepwd(npwd, opwd, studentId);
        return row;
    }
    //查看学生所有作业
    @RequestMapping("/findAllStuHomework")
    public PageInfo<StuUpload> findAllStuHomework(int pageNum, int pageSize, Student student){
        PageInfo<StuUpload> stuUploadPageInfo=null;
        stuUploadPageInfo=studentsService.findAllStuHomework(pageNum,pageSize,student);
        return stuUploadPageInfo;
    }
	@RequestMapping("/studentInfo")
    public List<Student> studentInfo(Student student){ return studentsService.studentInfo(student);}}
