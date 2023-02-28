package com.two.vue_serve.mapper;

import com.two.vue_serve.po.Clazz;
import com.two.vue_serve.po.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class teacherMapperTest {
    @Resource
    private TeacherMapper teacherMapper;

    @Test
    @Transactional
    void getAllUserInfoByParam() {
        Teacher teacher=new Teacher();
        teacher.setTeacherId(2);
        List<Clazz> clazzList=teacherMapper.findAllTeaClazzs(teacher);
        System.out.println(clazzList);
    }
}
