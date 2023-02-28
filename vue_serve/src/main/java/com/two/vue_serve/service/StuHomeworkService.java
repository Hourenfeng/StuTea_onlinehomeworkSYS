package com.two.vue_serve.service;


import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.StuUpload;

import java.util.List;

public interface StuHomeworkService {
    PageInfo<StuUpload> findAllStuHomework(int pageNum,int pageSize,StuUpload stuUpload);

    int addStuHomework(StuUpload stuUpload);

    int deleteStuHomework(StuUpload stuUpload);

    int updateStuHomework(StuUpload stuUpload);

    String findSubmitByStudentId(StuUpload stuUpload);

    PageInfo<StuUpload> findByHomeworkId(int pageNum,int pageSize,StuUpload stuUpload);

    PageInfo<StuUpload> findByHomeworkIdByState(int pageNum,int pageSize,StuUpload stuUpload);

    PageInfo<StuUpload> findTeaRemind(int pageNum,int pageSize,StuUpload stuUpload);

    int upDateSubmit(String newsubmit,String stuUploadId);

    int WriteAgain(String studentId,String homeworkId,String newsubmit);
}
