package com.two.vue_serve.service;

import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.School;

public interface SchoolService {
    PageInfo<School> findAllSchools(int pageNum,int pageSize,School school);

    int addSchool(School school);

    int deleteSchool(School school);

    int updateSchool(School school);
}
