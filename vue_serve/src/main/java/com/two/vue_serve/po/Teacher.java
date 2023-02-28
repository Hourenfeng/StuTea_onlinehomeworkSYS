package com.two.vue_serve.po;


public class Teacher {

  private Integer teacherId;
  private String loginName;
  private String password;
  private Integer type;
  private Integer courseId;
  private String phoneNum;
  private Course course;
  private TeacherClazz teacherClazz;
  private  Clazz clazz;
  private Integer clazzId;
  private String clazzName;
  private String stuloginName;

  public Clazz getClazz() {
    return clazz;
  }

  public void setClazz(Clazz clazz) {
    this.clazz = clazz;
  }

  public TeacherClazz getTeacherClazz() {
    return teacherClazz;
  }

  public void setTeacherClazz(TeacherClazz teacherClazz) {
    this.teacherClazz = teacherClazz;
  }

  public String getStuloginName() {
    return stuloginName;
  }

  public void setStuloginName(String stuloginName) {
    this.stuloginName = stuloginName;
  }

  public String getClazzName() {
    return clazzName;
  }

  public void setClazzName(String clazzName) {
    this.clazzName = clazzName;
  }

  public Integer getClazzId() {
    return clazzId;
  }

  public void setClazzId(Integer clazzId) {
    this.clazzId = clazzId;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Integer getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(Integer teacherId) {
    this.teacherId = teacherId;
  }


  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }


  public Integer getCourseId() {
    return courseId;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }


  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

}
