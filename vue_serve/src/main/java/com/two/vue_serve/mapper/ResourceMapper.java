package com.two.vue_serve.mapper;

import com.github.pagehelper.PageInfo;
import com.two.vue_serve.po.Resource;
import com.two.vue_serve.po.Teacher;
import com.two.vue_serve.po.TeacherClazz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper {
    List<Resource> findAllResources(Resource resource);

    int deleteResource(Resource resource);

    int addResource(Resource resource);

    int updateResource(Resource resource);

    List<Teacher> findAllTeacher(Teacher teacher);

    List<Resource> findByClazzId(Resource resource);

    List<Resource> findStuResourcesByClazzId(Resource resource);
}
