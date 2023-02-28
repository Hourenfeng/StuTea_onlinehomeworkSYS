package com.two.vue_serve.mapper;

import com.two.vue_serve.po.School;
import com.two.vue_serve.po.Student;
import com.two.vue_serve.po.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SchoolMapper {
    List<School> findAllSchools(School school);

    int deleteSchool(School school);

    int addSchool(School school);

    int updateSchool(School school);

}
