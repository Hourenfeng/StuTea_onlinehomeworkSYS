package com.two.vue_serve.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.mapper.ResourceMapper;
import com.two.vue_serve.po.Resource;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.po.TeacherClazz;
import com.two.vue_serve.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @javax.annotation.Resource
    private ResourceMapper resourceMapper;
    @Override
    public PageInfo<Resource> findAllResources(int pageNum, int pageSize, Resource resource) {
        PageInfo<Resource> pageInfo=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Resource> resources=resourceMapper.findAllResources(resource);
        pageInfo = new PageInfo<>(resources);
        return pageInfo;
    }

    @Override
    public int deleteResource(Resource resource) {
        return resourceMapper.deleteResource(resource);
    }

    @Override
    public int addResource(Resource resource) {
        return resourceMapper.addResource(resource);
    }

    @Override
    public int updateResource(Resource resource) {
        return resourceMapper.updateResource(resource);
    }

    @Override
    public List<Teacher> findAllTeacher(Teacher teacher) {
        return resourceMapper.findAllTeacher(teacher);
    }

    @Override
    public PageInfo<Resource> findByClazzId(int pageNum, int pageSize, Resource resource) {
        PageInfo<Resource> pageInfo=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Resource> resources=resourceMapper.findByClazzId(resource);
        pageInfo = new PageInfo<>(resources);
        return pageInfo;
    }

    @Override
    public PageInfo<Resource> findStuResourcesByClazzId(int pageNum, int pageSize, Resource resource) {
        PageInfo<Resource> pages=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Resource> tus=resourceMapper.findStuResourcesByClazzId(resource);
        pages = new PageInfo<>(tus);
        return pages;
    }
}
