package com.two.vue_serve.mapper;

import com.two.vue_serve.po.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class studentMapperTest {
    @Resource
    private StudentMapper studentMapper;

    @Test
    @Transactional
    void save(){
        Student student=new Student();
        student.setLoginName("cs");
        student.setPassword("123456");
        student.setClazzId(1);
        int row=studentMapper.addStudent(student);
        assertEquals(1,row);
        System.out.println(row);
    }
}
