package com.two.vue_serve.po;


public class Resource {

  private Integer resourceId;
  private Integer teacherId;
  private String date;
  private String resource;
  private String url;
  private Teacher teacher;
  private Course course;
  private TeacherClazz teacherClazz;
  private int clazzId;
  private int courseId;

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public int getClazzId() {
    return clazzId;
  }

  public void setClazzId(int clazzId) {
    this.clazzId = clazzId;
  }

  public TeacherClazz getTeacherClazz() {
    return teacherClazz;
  }

  public void setTeacherClazz(TeacherClazz teacherClazz) {
    this.teacherClazz = teacherClazz;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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

  public Integer getResourceId() {
    return resourceId;
  }

  public void setResourceId(Integer resourceId) {
    this.resourceId = resourceId;
  }


  public Integer getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(Integer teacherId) {
    this.teacherId = teacherId;
  }


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }


  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

}
