package com.two.vue_serve.po;


public class StuUpload {

  private Integer stuUploadId;
  private Integer homeworkId;
  private Integer studentId;
  private String date;
  private Integer courseId;
  private String content;
  private Integer score;
  private Integer correct;

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getCorrect() {
    return correct;
  }

  public void setCorrect(Integer correct) {
    this.correct = correct;
  }

  private String clazzName;
  private String title;
  private String loginName;
  private String courseName;
  private Student student;
  private Course course;
  private Teacher teacher;
  private Integer teacherId;
  private Integer submit;

  public Integer getSubmit() {
    return submit;
  }

  public void setSubmit(Integer submit) {
    this.submit = submit;
  }

  public String getClazzName() {
    return clazzName;
  }

  public void setClazzName(String clazzName) {
    this.clazzName = clazzName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public Integer getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(Integer teacherId) {
    this.teacherId = teacherId;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Integer getStuUploadId() {
    return stuUploadId;
  }

  public void setStuUploadId(Integer stuUploadId) {
    this.stuUploadId = stuUploadId;
  }


  public Integer getHomeworkId() {
    return homeworkId;
  }

  public void setHomeworkId(Integer homeworkId) {
    this.homeworkId = homeworkId;
  }


  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
  }


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }


  public Integer getCourseId() {
    return courseId;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
