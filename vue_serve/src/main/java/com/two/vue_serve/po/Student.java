package com.two.vue_serve.po;


public class Student {

  private Integer studentId;
  private String loginName;
  private String password;
  private Integer clazzId;
  private Clazz clazz;
  private String phoneNum;
  private Integer hwNum;

  private School school;
  private TeacherClazz teacherClazz;
  private Teacher teacher;


  public Integer getHwNum() {
    return hwNum;
  }

  public void setHwNum(Integer hwNum) {
    this.hwNum = hwNum;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public Clazz getClazz() {
    return clazz;
  }

  public void setClazz(Clazz clazz) {
    this.clazz = clazz;
  }

  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
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


  public Integer getClazzId() {
    return clazzId;
  }

  public void setClazzId(Integer clazzId) {
    this.clazzId = clazzId;
  }

  public School getSchool() {
    return school;
  }

  public void setSchool(School school) {
    this.school = school;
  }

  public TeacherClazz getTeacherClazz() {
    return teacherClazz;
  }

  public void setTeacherClazz(TeacherClazz teacherClazz) {
    this.teacherClazz = teacherClazz;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }
}
