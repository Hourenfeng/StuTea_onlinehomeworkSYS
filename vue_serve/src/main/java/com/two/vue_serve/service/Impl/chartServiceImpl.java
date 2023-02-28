package com.two.vue_serve.service.Impl;

import com.two.vue_serve.mapper.chartDataMapper;
import com.two.vue_serve.po.Student;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.service.chartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class chartServiceImpl implements chartService {
    @Resource
    chartDataMapper cdm;
    @Override
    public List<Map<Object, Object>> barChart(Teacher teacher) {
        return cdm.barChart(teacher);
    }

    @Resource
    chartDataMapper cdf;
    @Override
    public List<Map<Object, Object>> twoChart(Teacher teacher) {
        return cdf.twoChart(teacher);
    }

    @Resource
    chartDataMapper  cdd;
    @Override
    public List<Map<Object, Object>> fourChart(Teacher teacher) {
        return cdd.fourChart(teacher);
    }

    //学生详情页里的第一个图表
    @Resource
    chartDataMapper  cdg;
    @Override
    public List<Map<Object, Object>> fiveChart(Student student) {
        return cdg.fiveChart(student);
    }

    //学生页面图表
    @Resource
    chartDataMapper  cdh;
    @Override
    public List<Map<Object, Object>> sixChart(Student student) {
        return cdh.sixChart(student);
    }

    //语文
    @Resource
    chartDataMapper  cdq;
    @Override
    public List<Map<Object, Object>> sixaChart(Student student) {
        return cdq.sixaChart(student);
    }
    //英语
    @Resource
    chartDataMapper  cdw;
    @Override
    public List<Map<Object, Object>> sixbChart(Student student) {
        return cdw.sixbChart(student);
    }
    //学生页面第二个图表
    @Resource
    chartDataMapper  cdj;
    @Override
    public List<Map<Object, Object>> sevenChart(Student student) {
        return cdj.sevenChart(student);
    }

    //学生页面第三个图表
    @Resource
    chartDataMapper  cdk;
    @Override
    public List<Map<Object, Object>> eightChart(Student student) {
        return cdk.eightChart(student);
    }

    //学生页面第四个图表
    @Resource
    chartDataMapper  cdl;
    @Override
    public List<Map<Object, Object>> nineChart(Student student) {
        return cdl.nineChart(student);
    }
}
