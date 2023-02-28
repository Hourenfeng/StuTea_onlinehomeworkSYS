package com.two.vue_serve.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.mapper.SchoolMapper;
import com.two.vue_serve.po.School;
import com.two.vue_serve.service.SchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Resource
    private SchoolMapper schoolMapper;
    @Override
    public PageInfo<School> findAllSchools(int pageNum, int pageSize, School school) {
        PageInfo<School> pages=null;
        PageHelper.startPage(pageNum, pageSize);
        List<School> schools=schoolMapper.findAllSchools(school);
        pages=new PageInfo<>(schools);
        return pages;
    }

    @Override
    public int addSchool(School school) {
        return schoolMapper.addSchool(school);
    }

    @Override
    public int deleteSchool(School school) {
        return schoolMapper.deleteSchool(school);
    }

    @Override
    public int updateSchool(School school) {
        return schoolMapper.updateSchool(school);
    }
}
