package com.two.vue_serve.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.Clazz;
import com.two.vue_serve.po.Course;
import com.two.vue_serve.service.ClazzService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/clazz")
public class ClazzController {
    @Resource
    private ClazzService clazzService;
//    查询所有班级
    @RequestMapping("/findAllClazzs")
    public PageInfo<Clazz> findAllClazzs(int pageNum,int pageSize,Clazz clazz)
    {
        return clazzService.findAllClazzs(pageNum, pageSize, clazz);
    }
//    删除班级
    @RequestMapping("/deleteClazz")
    public int deleteClazz(Clazz clazz)
    {return clazzService.deleteClazz(clazz);}
//    添加班级
    @RequestMapping("/addClazz")
    public int addClazz(Clazz clazz)
    {return clazzService.addClazz(clazz);}
//    修改班级
    @RequestMapping("/updateClazz")
    public int updateClazz(Clazz clazz)
    {return clazzService.updateClazz(clazz);}


}
