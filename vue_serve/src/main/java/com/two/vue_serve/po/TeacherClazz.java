package com.two.vue_serve.po;


public class TeacherClazz {

  private Integer teaNum;
  private Integer teacherId;
  private Integer clazzId;
  private Teacher teacher;
  private TeaUpload teaUpload;
  private Course course;
  private Resource resource;

  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public TeaUpload getTeaUpload() {
    return teaUpload;
  }

  public void setTeaUpload(TeaUpload teaUpload) {
    this.teaUpload = teaUpload;
  }

  public Integer getTeaNum() {
    return teaNum;
  }

  public void setTeaNum(Integer teaNum) {
    this.teaNum = teaNum;
  }


  public Integer getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(Integer teacherId) {
    this.teacherId = teacherId;
  }


  public Integer getClazzId() {
    return clazzId;
  }

  public void setClazzId(Integer clazzId) {
    this.clazzId = clazzId;
  }

}
