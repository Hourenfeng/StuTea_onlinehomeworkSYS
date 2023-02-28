package com.two.vue_serve.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.controller.TeaHomeworkController;
import com.two.vue_serve.mapper.TeaHomeworkMapper;
import com.two.vue_serve.po.TeaUpload;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.po.TeacherClazz;
import com.two.vue_serve.service.TeaHomeworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeaHomeworkServiceImpl implements TeaHomeworkService {
    @Resource
    private TeaHomeworkMapper teaHomeworkMapper;
    @Override
    public PageInfo<TeaUpload> findAllTeaHomework(int pageNum, int pageSize,TeaUpload teaUpload) {
        PageInfo<TeaUpload> pages=null;
        PageHelper.startPage(pageNum, pageSize);
        List<TeaUpload> tus=teaHomeworkMapper.findAllTeaHomework(teaUpload);
        pages = new PageInfo<>(tus);
        return pages;
    }

    @Override
    public int addTeaHomework(TeaUpload teaUpload) {
        return teaHomeworkMapper.addTeaHomework(teaUpload);
    }

    @Override
    public int deleteTeaHomework(TeaUpload teaUpload) {
        return teaHomeworkMapper.deleteTeaHomework(teaUpload);
    }

    @Override
    public int updateTeaHomework(TeaUpload teaUpload) {
        return teaHomeworkMapper.updateTeaHomework(teaUpload);
    }

    @Override
    public List<Teacher> findAllTeacher(Teacher teacher) {
        return teaHomeworkMapper.findAllTeacher(teacher);
    }

    @Override
    public PageInfo<TeaUpload> findByTeacherId(int pageNum, int pageSize,TeaUpload teaUpload) {
        PageInfo<TeaUpload> pages=null;
        PageHelper.startPage(pageNum, pageSize);
        List<TeaUpload> tus=teaHomeworkMapper.findByTeacher(teaUpload);
        pages = new PageInfo<>(tus);
        return pages;
    }

    @Override
    public PageInfo<TeaUpload> findstuTeaHomeworkByClazzId(int pageNum, int pageSize, TeaUpload teaUpload) {
        PageInfo<TeaUpload> pages=null;
        PageHelper.startPage(pageNum, pageSize);
        List<TeaUpload> tus=teaHomeworkMapper.findTeaHomeworkByClazzId(teaUpload);
        pages = new PageInfo<>(tus);
        return pages;
    }


}
