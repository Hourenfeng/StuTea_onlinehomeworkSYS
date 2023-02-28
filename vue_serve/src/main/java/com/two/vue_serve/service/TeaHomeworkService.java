package com.two.vue_serve.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.TeaUpload;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.po.TeacherClazz;

import java.util.List;

public interface TeaHomeworkService {
    PageInfo<TeaUpload> findAllTeaHomework(int pageNum,int pageSize,TeaUpload teaUpload);

    int addTeaHomework(TeaUpload teaUpload);

    int deleteTeaHomework(TeaUpload teaUpload);

    int updateTeaHomework(TeaUpload teaUpload);

    List<Teacher> findAllTeacher(Teacher teacher);

    PageInfo<TeaUpload> findByTeacherId(int pageNum,int pageSize,TeaUpload teaUpload);

    PageInfo<TeaUpload> findstuTeaHomeworkByClazzId(int pageNum,int pageSize,TeaUpload teaUpload);
}
