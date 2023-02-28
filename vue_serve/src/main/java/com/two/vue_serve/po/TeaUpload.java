package com.two.vue_serve.po;


public class TeaUpload {

  private Integer homeworkId;
  private Integer teacherId;
  private String date;
  private String title;
  private String content;
  private String answer;
  private int clazzId;

  private Teacher teacher;
  private Course course;
  private TeacherClazz teacherClazz;

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public Integer getHomeworkId() {
    return homeworkId;
  }

  public void setHomeworkId(Integer homeworkId) {
    this.homeworkId = homeworkId;
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


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
