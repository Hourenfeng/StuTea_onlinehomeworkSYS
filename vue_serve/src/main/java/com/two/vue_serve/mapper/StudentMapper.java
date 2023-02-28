package com.two.vue_serve.mapper;

import com.two.vue_serve.po.Clazz;
import com.two.vue_serve.po.StuUpload;
import com.two.vue_serve.po.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.w3c.dom.ls.LSException;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> findAllStudent(Student student);

    int deleteStudent(Student student);

    int addStudent(Student student);

    int updateStudent(Student student);

    List<Clazz> findAllClazz(Clazz clazz);


    @Select("SELECT COUNT(*) FROM student where phoneNum = #{phoneNum} and password = #{password}")
    int loginStudent(String phoneNum, String password);

    Student login(Student student);

    //查看学生所有作业
    List<StuUpload> findAllStuHomework(Student student);
	//修改密码
    int editPwd(@Param("npwd")String npwd, @Param("opwd")String opwd, @Param("studentId")String studentId);

    List<Student> studentInfo(Student student);}
