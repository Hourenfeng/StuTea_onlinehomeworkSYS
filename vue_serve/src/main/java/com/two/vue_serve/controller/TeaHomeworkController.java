package com.two.vue_serve.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.TeaUpload;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.po.TeacherClazz;
import com.two.vue_serve.service.TeaHomeworkService;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/teahomework")
public class TeaHomeworkController {
    @Resource
    private TeaHomeworkService teaHomeworkService;
    @RequestMapping("/findAllTeacher")
    public List<Teacher> findAllTeacher(Teacher teacher)
    {return teaHomeworkService.findAllTeacher(teacher);}
//    查看所有老师上传作业
    @RequestMapping("/findAllTeaHomeworks")
    public PageInfo<TeaUpload> findAllTeaHomeworks(int pageNum,int pageSize,TeaUpload teaUpload)
    {
        return teaHomeworkService.findAllTeaHomework(pageNum, pageSize, teaUpload);
    }
    @RequestMapping("/findByTeacherId")
    public PageInfo<TeaUpload> findByTeacherId(int pageNum,int pageSize,TeaUpload teaUpload)
    {
        return teaHomeworkService.findByTeacherId(pageNum, pageSize, teaUpload);
    }
//    添加老师上传作业
    @RequestMapping("/addTeaHomework")
    public int addTeaHomework(TeaUpload teaUpload)
    {
        return teaHomeworkService.addTeaHomework(teaUpload);
    }
//    删除老师上传作业
    @RequestMapping("/deleteTeaHomework")
    public int deleteTeaHomework(TeaUpload teaUpload)
    {
        return teaHomeworkService.deleteTeaHomework(teaUpload);
    }
//    修改老师上传作业
    @RequestMapping("/updateTeaHomework")
    public int updateTeaHomework(TeaUpload teaUpload)
    {
        return teaHomeworkService.updateTeaHomework(teaUpload);
    }
    @RequestMapping("/findByClazzId")
    public PageInfo<TeaUpload> finByClazzId(int pageNum,int pageSize,TeaUpload teaUpload)
    {
        return teaHomeworkService.findstuTeaHomeworkByClazzId(pageNum, pageSize, teaUpload); }
}
