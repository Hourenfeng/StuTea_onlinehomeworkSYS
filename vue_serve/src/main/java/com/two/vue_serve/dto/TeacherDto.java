package com.two.vue_serve.dto;

import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.po.Clazz;


public class TeacherDto extends Teacher {
    private String course_name;
    private Integer teacherId;
    private Integer clazzId;


    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    @Override
    public Integer getTeacherId() {
        return teacherId;
    }

    @Override
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }
}
