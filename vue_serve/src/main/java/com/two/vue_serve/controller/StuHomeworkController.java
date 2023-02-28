package com.two.vue_serve.controller;

import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.StuUpload;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.service.StuHomeworkService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/stuhomework")
public class StuHomeworkController {
    @Resource
    private StuHomeworkService stuHomeworkService;
//    查找所有学生作业
    @RequestMapping("/findAllStuHomeworks")
    public PageInfo<StuUpload> findAllStuHomeworks(int pageNum,int pageSize,StuUpload stuUpload){
        return stuHomeworkService.findAllStuHomework(pageNum, pageSize, stuUpload);
    }
    @RequestMapping("/findByHomeworkId")
    public PageInfo<StuUpload> findByHomeworkId(int pageNum,int pageSize,StuUpload stuUpload)
    {
        return stuHomeworkService.findByHomeworkId(pageNum, pageSize, stuUpload);
    }
    //根据页面传的状态来查找学生作业
    @RequestMapping("/findByHomeworkIdByState")
    public PageInfo<StuUpload> findByHomeworkIdByState(int pageNum,int pageSize,StuUpload stuUpload){
        return stuHomeworkService.findByHomeworkIdByState(pageNum,pageSize,stuUpload);
    }
//    添加学生作业
    @RequestMapping("/addStuHomework")
    public int addStuHomework(StuUpload stuUpload)
    {
        return stuHomeworkService.addStuHomework(stuUpload);
    }
    @RequestMapping("/findSubmitByStudentId")
    public String findSubmitByStudentId(StuUpload stuUpload)
    {return stuHomeworkService.findSubmitByStudentId(stuUpload);}
    //    删除学生作业
    @RequestMapping("/deleteStuHomework")
    public int deleteStuHormwork(StuUpload stuUpload)
    {
        return stuHomeworkService.deleteStuHomework(stuUpload);
    }
//    修改学生作业
    @RequestMapping("/updateStuHomework")
    public int updateStuHomework(StuUpload stuUpload)
    {
        return stuHomeworkService.updateStuHomework(stuUpload);
    }

    @RequestMapping("/findTeaRemind")
    public PageInfo<StuUpload> findTeaRemind(int pageNum,int pageSize,StuUpload stuUpload){ return stuHomeworkService.findTeaRemind(pageNum,pageSize,stuUpload);}

    @RequestMapping("/upDateSubmit")
    public int upDateSubmit(String newsubmit,String stuUploadId){
        int row=0;
        row=stuHomeworkService.upDateSubmit(newsubmit, stuUploadId);
        return row;
    }

    @RequestMapping("/WriteAgain")
    public int WriteAgain(String studentId,String homeworkId,String newsubmit){
        int row=0;
        row=stuHomeworkService.WriteAgain(studentId,homeworkId,newsubmit);
        return row;
    }
}
