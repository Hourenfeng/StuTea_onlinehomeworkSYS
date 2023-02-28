package com.two.vue_serve.service;

import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.Resource;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.po.TeacherClazz;

import java.util.List;

public interface ResourceService {
    PageInfo<Resource> findAllResources(int pageNum,int pageSize,Resource resource);

    int deleteResource(Resource resource);

    int addResource(Resource resource);

    int updateResource(Resource resource);

    List<Teacher> findAllTeacher(Teacher teacher);

    PageInfo<Resource> findByClazzId(int pageNum,int pageSize,Resource resource);

    PageInfo<Resource> findStuResourcesByClazzId(int pageNum, int pageSize,Resource resource);
}
