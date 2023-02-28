package com.two.vue_serve.mapper;

import com.two.vue_serve.po.StuUpload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StuHomeworkMapper {
    //查找所有学生作业 （需要多表关联查询）
    List<StuUpload> findAllStuHomework(StuUpload stuUpload);

    int addStuHomework(StuUpload stuUpload);

    int deleteStuHomework(StuUpload stuUpload);

    int updateStuHomework(StuUpload stuUpload);

    List<StuUpload> findAllByHomeworkId(StuUpload stuUpload);


    List<StuUpload> findByHomeworkIdByState(StuUpload stuUpload);

    String findSubmitByStudentId(StuUpload stuUpload);

    List<StuUpload> findTeaRemind(StuUpload stuUpload);

    int upDateSubmit(@Param("newsubmit")String newsubmit,@Param("stuUploadId")String stuUploadId);

    int WriteAgain(@Param("studentId")String studentId,@Param("homeworkId")String homeworkId,@Param("newsubmit")String newsubmit);
}
