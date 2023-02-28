package com.two.vue_serve.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.mapper.StuHomeworkMapper;
import com.two.vue_serve.po.StuUpload;
import com.two.vue_serve.service.StuHomeworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StuHomeworkServiceImpl implements StuHomeworkService {

    @Resource
    private StuHomeworkMapper stuHomeworkMapper;
    @Override
    public PageInfo<StuUpload> findAllStuHomework(int pageNum, int pageSize, StuUpload stuUpload) {
        PageInfo<StuUpload> pages=null;
        PageHelper.startPage(pageNum, pageSize);
        List<StuUpload> shs= stuHomeworkMapper.findAllStuHomework(stuUpload);
        pages = new PageInfo<>(shs);
        return pages;
    }

    @Override
    public int addStuHomework(StuUpload stuUpload) {
        return stuHomeworkMapper.addStuHomework(stuUpload);
    }

    @Override
    public int deleteStuHomework(StuUpload stuUpload) {
        return stuHomeworkMapper.deleteStuHomework(stuUpload);
    }

    @Override
    public int updateStuHomework(StuUpload stuUpload) {
        return stuHomeworkMapper.updateStuHomework(stuUpload);
    }

    @Override
    public String findSubmitByStudentId(StuUpload stuUpload) {
        return stuHomeworkMapper.findSubmitByStudentId(stuUpload);
    }

    @Override
    public PageInfo<StuUpload> findByHomeworkId(int pageNum,int pageSize,StuUpload stuUpload) {
        PageInfo<StuUpload> pages=null;
        PageHelper.startPage(pageNum, pageSize);
        List<StuUpload> shs= stuHomeworkMapper.findAllByHomeworkId(stuUpload);
        pages = new PageInfo<>(shs);
        return pages;
    }

    @Override
    public PageInfo<StuUpload> findByHomeworkIdByState(int pageNum, int pageSize, StuUpload stuUpload) {
        PageInfo<StuUpload> pageInfo=null;
        PageHelper.startPage(pageNum,pageSize);
        List<StuUpload> stuUploads=stuHomeworkMapper.findByHomeworkIdByState(stuUpload);
        pageInfo=new PageInfo<>(stuUploads);
        return pageInfo;

    }

    @Override
    public PageInfo<StuUpload> findTeaRemind(int pageNum, int pageSize,StuUpload stuUpload) {
        PageInfo<StuUpload> pageInfo=null;
        PageHelper.startPage(pageNum,pageSize);
        List<StuUpload> stuUploads=stuHomeworkMapper.findTeaRemind(stuUpload);
        pageInfo=new PageInfo<>(stuUploads);
        return pageInfo;
    }

    @Override
    public int upDateSubmit(String newsubmit, String stuUploadId) {
        int row=0;
        row=stuHomeworkMapper.upDateSubmit(newsubmit,stuUploadId);
        return row;
    }

    @Override
    public int WriteAgain(String studentId, String homeworkId, String newsubmit) {
        int row=0;
        row=stuHomeworkMapper.WriteAgain(studentId,homeworkId,newsubmit);
        return row;
    }
}
