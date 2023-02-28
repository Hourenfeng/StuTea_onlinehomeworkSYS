package com.two.vue_serve.mapper;

import com.two.vue_serve.po.Student;
import com.two.vue_serve.po.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface chartDataMapper {
    @Select("SELECT avg(a.score) scoreavga,min(a.score) scoremina,max(a.score) scoremaxa\n" +
            "FROM\n" +
            "\n" +
            "(SELECT student.studentId,stu_upload.score\n" +
            "FROM stu_upload,student,teacher,teacher_clazz\n" +
            "where stu_upload.studentId=student.studentId and stu_upload.courseId=teacher.courseId\n" +
            "and teacher_clazz.clazzId=student.clazzId\n" +
            "and student.studentId=stu_upload.studentId\n" +
            "and teacher_clazz.teacherId=teacher.teacherId\n" +
            "and teacher.teacherId=#{teacherId})a")

    List<Map<Object, Object>> barChart(Teacher teacher);

    @Select("SELECT count(case when a.score>=0 and a.score<20 then a.studentId end) sa,\n" +
            "       count(case when a.score>=20 and a.score<40 then a.studentId end) sb,\n" +
            "       count(case when a.score>=40 and a.score<60 then a.studentId end) sc,\n" +
            "       count(case when a.score>=60 and a.score<80 then a.studentId end) sd,\n" +
            "       count(case when a.score>=80 and a.score<100 then a.studentId end) se\n" +
            "FROM\n" +
            "(SELECT student.studentId,stu_upload.score\n" +
            "FROM stu_upload,student,teacher,teacher_clazz\n" +
            "where stu_upload.studentId=student.studentId and stu_upload.courseId=teacher.courseId\n" +
            "and teacher_clazz.clazzId=student.clazzId\n" +
            "and student.studentId=stu_upload.studentId\n" +
            "and teacher_clazz.teacherId=teacher.teacherId\n" +
            "and teacher.teacherId=#{teacherId})a")


    List<Map<Object,Object>> twoChart(Teacher teacher);





    @Select("SELECT count(a.studentId) doita,count(studentId)-count(a.studentId) nodoita\n" +
            "FROM\n" +
            "(SELECT student.studentId,stu_upload.submit\n" +
            "FROM stu_upload,student,teacher,teacher_clazz\n" +
            "where stu_upload.studentId=student.studentId and stu_upload.courseId=teacher.courseId\n" +
            "and teacher_clazz.clazzId=student.clazzId\n" +
            "and student.studentId=stu_upload.studentId\n" +
            "and teacher_clazz.teacherId=teacher.teacherId\n" +
            "and teacher.teacherId=#{teacherId}\n" +
            "and submit>0)a")

    List<Map<Object,Object>> fourChart(Teacher teacher);


//    @Select("SELECT SUM(a.score) a1,avg(a.score) scoreavg,min(a.score) scoremin,max(a.score) scoremax\n" +
//            "FROM\n" +
//            "(SELECT student.studentId,stu_upload.score\n" +
//            "FROM stu_upload,student,teacher,teacher_clazz\n" +
//            "where stu_upload.studentId=student.studentId and stu_upload.courseId=teacher.courseId\n" +
//            "and teacher_clazz.clazzId=student.clazzId\n" +
//            "and student.studentId=stu_upload.studentId\n" +
//            "and teacher_clazz.teacherId=teacher.teacherId\n" +
//            "and teacher.teacherId=#{teacherId}\n" +
//            "and student.studentId=#{studentId})a")
//
//    List<Map<Object, Object>> fiveChart(Teacher teacher,Student student);

    //学生详情页里的第一个图表
    @Select("SELECT b.loginName,count(b.homeworkId) b1,avg(b.score) scoreavgde,min(b.score) scoreminde,max(b.score) scoremaxde \n" +
            "            FROM \n" +
            "                 (SELECT student.studentId,student.loginName,stu_upload.score,stu_upload.homeworkId\n" +
            "                  FROM stu_upload,student\n" +
            "                  where stu_upload.loginName=student.loginName\n" +
            "                  and student.loginName=#{loginName})b")
    List<Map<Object, Object>> fiveChart(Student student);


    //学生页面  数学
    @Select("SELECT avg(c.score) scoreavg,min(c.score) scoremin,max(c.score) scoremax\n" +
            "FROM\n" +
            "\n" +
            "(SELECT student.studentId,stu_upload.score\n" +
            "FROM stu_upload,student\n" +
            "where stu_upload.studentId=student.studentId \n" +
            "and student.studentId=stu_upload.studentId\n" +
            "and stu_upload.courseId=1\n"+
            "and student.studentId=#{studentId})c\n")
    List<Map<Object, Object>> sixChart(Student student);

    //语文
    @Select("SELECT avg(c.score) scoreavg2,min(c.score) scoremin2,max(c.score) scoremax2\n" +
            "FROM\n" +
            "\n" +
            "(SELECT student.studentId,stu_upload.score\n" +
            "FROM stu_upload,student\n" +
            "where stu_upload.studentId=student.studentId \n" +
            "and student.studentId=stu_upload.studentId\n" +
            "and stu_upload.courseId=2\n"+
            "and student.studentId=#{studentId})c\n")
    List<Map<Object, Object>> sixaChart(Student student);
    //英语
    @Select("SELECT avg(c.score) scoreavg3,min(c.score) scoremin3,max(c.score) scoremax3\n" +
            "FROM\n" +
            "\n" +
            "(SELECT student.studentId,stu_upload.score\n" +
            "FROM stu_upload,student\n" +
            "where stu_upload.studentId=student.studentId \n" +
            "and student.studentId=stu_upload.studentId\n" +
            "and stu_upload.courseId=3\n"+
            "and student.studentId=#{studentId})c\n")
    List<Map<Object, Object>> sixbChart(Student student);

    //学生页面第二个图
    @Select("SELECT count(case when a.score>=0 and a.score<20 then a.studentId end) s1,\n" +
            "count(case when a.score>=20 and a.score<40 then a.studentId end) s2,\n" +
            "count(case when a.score>=40 and a.score<60 then a.studentId end) s3,\n" +
            "count(case when a.score>=60 and a.score<80 then a.studentId end) s4,\n" +
            "count(case when a.score>=80 and a.score<100 then a.studentId end) s5\n" +
            "FROM \n" +
            "(SELECT student.studentId,stu_upload.score\n" +
            "FROM stu_upload,student\n" +
            "where stu_upload.studentId=student.studentId \n" +
            "and student.studentId=stu_upload.studentId\n" +
            "and student.studentId=#{studentId})a\n")
    List<Map<Object, Object>> sevenChart(Student student);
    //学生页面第三个图表
    @Select("SELECT sum(if(b.submit>0,1,0)) doit,count(submit)-sum(if(b.submit>0,1,0)) nodoit\n" +
            "from(\n" +
            "SELECT submit\n" +
            "FROM(\n" +
            "SELECT homeworkId,studentId,submit\n" +
            "from stu_upload\n" +
            "WHERE studentId=#{studentId}\n" +
            "UNION\n" +
            "SELECT tea_upload.homeworkId,student.studentId,0 as submit\n" +
            "from tea_upload,student,teacher_clazz\n" +
            "WHERE student.studentId=#{studentId} \n" +
            "and student.clazzId=teacher_clazz.clazzId \n" +
            "and teacher_clazz.teacherId=tea_upload.teacherId)a\n" +
            "GROUP BY a.homeworkId)b")
    List<Map<Object, Object>> eightChart(Student student);

    //学生页面第四个图表
    @Select("SELECT count(case when a.submit=1 then a.studentId end) sub,\n" +
            "\t\t\t\tcount(case when a.submit>1 then a.studentId end) subup\n" +
            "FROM \n" +
            "            (SELECT student.studentId,stu_upload.score,stu_upload.submit\n" +
            "            FROM stu_upload,student\n" +
            "            where stu_upload.studentId=student.studentId \n" +
            "            and student.studentId=stu_upload.studentId\n" +
            "            and student.studentId=#{studentId})a")
    List<Map<Object, Object>> nineChart(Student student);
}
