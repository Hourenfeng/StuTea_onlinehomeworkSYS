package com.two.vue_serve.controller;

import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.School;
import com.two.vue_serve.service.SchoolService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/school")
public class SchoolController {
    @Resource
    private SchoolService schoolService;
//    查找所有学校
    @RequestMapping("/findAllSchools")
    public PageInfo<School> findAllSchools(int pageNum,int pageSize,School school)
    {
        return schoolService.findAllSchools(pageNum, pageSize, school);
    }
//    添加学校
    @RequestMapping("/addSchool")
    public int addSchool(School school)
    {
        return schoolService.addSchool(school);
    }
//    删除学校
    @RequestMapping("/deleteSchool")
    public int deleteSchool(School school)
    {
        return schoolService.deleteSchool(school);
    }
//    修改学校
    @RequestMapping("/updateSchool")
    public int updateSchool(School school)
    {
        return schoolService.updateSchool(school);
    }
}
