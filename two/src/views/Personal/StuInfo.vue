<template>
  <div id="container">
    <template v-if="JSON.stringify(student)!='[]'">
      <el-form >
        <el-form-item label="学生姓名：">
          {{student[0].loginName}}
        </el-form-item>
        <el-form-item label="所在班级：">
          {{this.student[0].clazz.clazzName}}
        </el-form-item>
        <el-form-item label="授课教师：">
          {{this.student[0].teacher.loginName}}
        </el-form-item>
        <el-form-item label="学校：">
          {{this.student[0].school.schoolName}}
        </el-form-item>
      </el-form>
    </template>
  </div>
</template>

<script>
export default {
  name: "StuInfo",
  data(){
    return{
      student:[],
      student1:'',
      a:[],
    }
  },
  methods:{
    init(){
      var param = this.$qs.stringify({
        studentId:this.student1.studentId
      })

      this.$axios.post("student/studentInfo",param)
          .then(rst=>{
            this.student=rst.data;
            console.log(rst);
          })
          .catch(ex=>{
            console.log(ex);
          })
    }
  },
  created() {
    this.student1=JSON.parse(sessionStorage.getItem("student"));
    this.init();
    console.log(this.student1);
  },
  mounted() {
  }
}
</script>

<style scoped>

</style>