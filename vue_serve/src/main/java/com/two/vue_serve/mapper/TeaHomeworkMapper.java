package com.two.vue_serve.mapper;

import com.two.vue_serve.po.TeaUpload;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.po.TeacherClazz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TeaHomeworkMapper {
    List<TeaUpload> findAllTeaHomework(TeaUpload teaUpload);

    int addTeaHomework(TeaUpload teaUpload);

    int deleteTeaHomework(TeaUpload teaUpload);

    int updateTeaHomework(TeaUpload teaUpload);

    List<Teacher> findAllTeacher(Teacher teacher);

    List<TeaUpload> findByTeacher(TeaUpload teaUpload);

    List<TeaUpload> findTeaHomeworkByClazzId(TeaUpload teaUpload);
}
