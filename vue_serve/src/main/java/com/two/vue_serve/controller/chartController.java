package com.two.vue_serve.controller;


import com.two.vue_serve.po.Student;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.service.chartService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/chart")

public class chartController {
    @Resource
    chartService cs;

    @RequestMapping("/barChart")
    List<Map<Object,Object>> barChart(Teacher teacher){
        return cs.barChart(teacher);
    }


    @Resource
    chartService cf;
    @RequestMapping("/twoChart")
    List<Map<Object,Object>> twoChart(Teacher teacher){
        return cf.twoChart(teacher);
    }



    @Resource
    chartService cd;
    @RequestMapping("/fourChart")
    List<Map<Object,Object>> fourChart(Teacher teacher){
        return cd.fourChart(teacher);
    }


    //学生详情页里的第一个图表
    @Resource
    chartService cg;
    @RequestMapping("/fiveChart")
    List<Map<Object,Object>> fiveChart(Student student){ return cg.fiveChart(student); }

    //学生页面
    @Resource
    chartService ch;
    @RequestMapping("/sixChart")
    List<Map<Object,Object>> sixChart(Student student){ return ch.sixChart(student); }
    //语文
    @Resource
    chartService cq;
    @RequestMapping("/sixaChart")
    List<Map<Object, Object>> sixaChart(Student student) { return cq.sixaChart(student); }
    //英语
    @Resource
    chartService cw;
    @RequestMapping("/sixbChart")
    List<Map<Object, Object>> sixbChart(Student student) { return cw.sixbChart(student); }
    //学生页面第二个图表
    @Resource
    chartService cj;
    @RequestMapping("/sevenChart")
    List<Map<Object,Object>> sevenChart(Student student){ return cj.sevenChart(student); }

    //学生页面第三个表
    @Resource
    chartService ck;
    @RequestMapping("/eightChart")
    List<Map<Object,Object>> eightChart(Student student){ return ck.eightChart(student); }
    //学生页面第四个图表
    @Resource
    chartService cl;
    @RequestMapping("/nineChart")
    List<Map<Object,Object>> nineChart(Student student){ return cl.nineChart(student); }

}
