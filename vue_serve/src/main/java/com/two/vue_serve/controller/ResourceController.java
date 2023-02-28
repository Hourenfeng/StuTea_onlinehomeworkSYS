package com.two.vue_serve.controller;

import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.po.TeacherClazz;
import com.two.vue_serve.service.ResourceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Resource
    private ResourceService resourceService;
    //查找所有资源
    @RequestMapping("/findAllResources")
    public PageInfo<com.two.vue_serve.po.Resource> findAllResources(int pageNum, int pageSize, com.two.vue_serve.po.Resource resource)
    {
        PageInfo<com.two.vue_serve.po.Resource> res=null;
        res=resourceService.findAllResources(pageNum, pageSize, resource);
        return res;
    }
    @RequestMapping("/findAllTeacher")
    public List<Teacher> findAllTeacher(Teacher teacher)
    {
        return resourceService.findAllTeacher(teacher);
    }
    //添加资源
    @RequestMapping("/addResource")
    public int addResource(com.two.vue_serve.po.Resource resource)
    {
        return resourceService.addResource(resource);
    }
    //删除资源
    @RequestMapping("/deleteResource")
    public int deleteResource(com.two.vue_serve.po.Resource resource)
    {
        return resourceService.deleteResource(resource);
    }
//    修改资源
    @RequestMapping("/updateResource")
    public int updateResource(com.two.vue_serve.po.Resource resource)
    {
        return resourceService.updateResource(resource);
    }
    @RequestMapping("/findByClazzId")
    public PageInfo<com.two.vue_serve.po.Resource> findByClazzId(int pageNum, int pageSize, com.two.vue_serve.po.Resource resource)
    {
        PageInfo<com.two.vue_serve.po.Resource> res=null;
        res=resourceService.findByClazzId(pageNum, pageSize, resource);
        return res;
    }
    @RequestMapping("/findStuResourcesByClazzId")
    public PageInfo<com.two.vue_serve.po.Resource> findStuResourcesByClazzId(int pageNum, int pageSize, com.two.vue_serve.po.Resource resource)
    {
        return resourceService.findStuResourcesByClazzId(pageNum, pageSize,resource);
    }
}
