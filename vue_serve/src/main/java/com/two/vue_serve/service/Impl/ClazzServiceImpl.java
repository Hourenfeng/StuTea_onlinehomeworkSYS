package com.two.vue_serve.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.vue_serve.mapper.ClazzMapper;
import com.two.vue_serve.po.Clazz;
import com.two.vue_serve.service.ClazzService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Resource
    private ClazzMapper clazzMapper;
    @Override
    public PageInfo<Clazz> findAllClazzs(int pageNum, int pageSize, Clazz clazz) {
        PageInfo<Clazz> pages=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Clazz> clazzes=clazzMapper.findAllClazzs(clazz);
        pages=new PageInfo<>(clazzes);
        return pages;
    }

    @Override
    public int deleteClazz(Clazz clazz) {
        return clazzMapper.deleteClazz(clazz);
    }

    @Override
    public int addClazz(Clazz clazz) {
        return clazzMapper.addClazz(clazz);
    }

    @Override
    public int updateClazz(Clazz clazz) {
        return clazzMapper.updateClazz(clazz);
    }


}
