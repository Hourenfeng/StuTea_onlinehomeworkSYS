package com.two.vue_serve.service;

import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.Clazz;
import com.two.vue_serve.po.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class teacherServiceTest {
    @Resource
    private TeacherService teacherService;
    @Test
    void findAllTeaClazzs() {
        Teacher teacher=new Teacher();
        teacher.setTeacherId(2);
        PageInfo<Clazz> pageInfo=teacherService.findAllTeaClazzs(1,3,teacher);
        System.out.println(pageInfo);
    }
}
