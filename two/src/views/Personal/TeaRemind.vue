<template>
  <div id="container">
      <el-container>
<!--        <el-header>-->
<!--          <div id="postSearch">-->
<!--            <el-form :inline="true" :model="student" class="demo-form-inline">-->
<!--              <el-form-item label="搜索学生">-->
<!--                <el-input placeholder="请输入学生姓名" v-model="student.stuloginName" ></el-input>-->
<!--              </el-form-item>-->
<!--              <el-form-item>-->
<!--                <el-button type="primary" v-on:click="">查询</el-button>-->
<!--                <el-button  @click="">清空</el-button>-->
<!--              </el-form-item>-->
<!--            </el-form>-->
<!--          </div>-->
<!--        </el-header>-->
        <el-main>
          <el-table :data="result" style="width: 100%">
            <el-table-column fixed prop="date" label="申请时间"  />
            <el-table-column prop="student.loginName" label="申请学生"  />
            <el-table-column prop="content" label="申请内容"  />
            <el-table-column fixed="right" label="Operations">
              <template #default="scope">
                <el-button type="primary" size="small" @click="tongyi(scope.row)">同意申请</el-button>
                <el-button type="danger" size="small" @click="jujue(scope.row)">拒绝申请</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div id="page_content">
            <el-pagination
                background
                layout="total,sizes,prev, pager, next,jumper"
                :total="pageTotal"
                :page-sizes="[4,6,8,10]"
                :page-size="pageSize"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-change="pageNum"
            >
            </el-pagination>
          </div>

        </el-main>
      </el-container>
  </div>
</template>

<script>
export default{
  name:'TeaRemind',
  data() {
    return{
      num:'',
      result:[],
      student: {},
      teacher:[],
      pageNum:1,
      pageTotal:0,
      pageSize:10,
      stuUploadId:'',
      newsubmit:''
    }
  },
  created() {
    this.teacher=JSON.parse(sessionStorage.getItem("teacher"));
    this.init()
  },
  methods:{
    handleCurrentChange(val)
    {
      console.log(`当前页:${val}`);
      this.pageNum=val;
      this.init();
    },
    handleSizeChange(val){
      console.log(`每页${val}条`);
      this.pageSize=val;
      this.init();
    },
    handleCommand(command){
      this.commands=command;
      if(command==2){
        console.log("2!!");
        this.init();
      }
      if(command==1){
        console.log("1!!");
        this.findSubmitState(1);
      }
      if(command==0){
        console.log("0!!");
        this.findSubmitState(0);
      }
    },
    init() {
      this.result.pageNum=this.pageNum;
      this.result.pageSize=this.pageSize;
      this.result.teacherId = this.teacher.teacherId;
      var param = this.$qs.stringify(this.result);
      this.$axios
          .post("/stuhomework/findTeaRemind", param)
          .then(rst => {
            this.result = rst.data.list;
            this.pageTotal = rst.data.total;
          })
          .catch(ex => {
            console.log(ex);
          })
    },
    tongyi(row){
      console.log(row.stuUploadId);
      this.newsubmit=0;
      var param1={
        newsubmit:this.newsubmit,
        stuUploadId:row.stuUploadId}
      param1=this.$qs.stringify(param1);
      this.$axios
          .post("/stuhomework/deleteStuHomework",param1)
          .then(rst=>{
            if(rst.data==1){
              this.success();
              console.log(rst);
            }
            else{
              this.error();
              console.log(param1);
              console.log(rst);
            }
          })

    },
    jujue(row){
      this.newsubmit=3;
      var param1={
        'newsubmit':this.newsubmit,
        'stuUploadId':row.stuUploadId}
      param1=this.$qs.stringify(param1);
      this.$axios
          .post("/stuhomework/upDateSubmit",param1)
          .then(rst=>{
            if(rst.data==1){
              this.$message({
                message: '已拒绝重做！',
                type: 'success'
              });
            }
            else{
              this.$message({
                message: '系统错误，拒绝失败！',
                type: 'error'
              });
            }
          })

    },
    error() {
      this.$message({
        message: '批准失败！',
        type: 'error'
      });
    },
    success() {
      this.$message({
        message: '批准成功！',
        type: 'success'
      });
    },
    // tongyi(){
    //   var submit0=0;
    //   param1=this.$qs.stringify(param1);
    //   this.$axios
    //       .post("stuhomework/upLoadSubmit",param1)
    //       .then(rst=>{
    //         if(rst.data==1){
    //               this.$message({
    //                 message: '请求通过成功！',
    //                 type: 'success'
    //               })
    //           console.log(rst);
    //         }else{
    //           this.$message({
    //             message: '请求通过失败！',
    //             type: 'error'
    //           })
    //         }
    //       })
    // },
    // jujue(){
    //   //通过不同的数字表明不同的状态
    //   var param1=1;
    //   param1=this.$qs.stringify(param1);
    //   this.$axios
    //       .post("stuhomework/upLoadSubmit",param)
    //       .then(rst=>{
    //         if(rst.data==1){
    //           this.$message({
    //             message: '拒绝通过成功！',
    //             type: 'success'
    //           })
    //           console.log(rst);
    //         }else{
    //           this.$message({
    //             message: '拒绝通过失败！',
    //             type: 'error'
    //           })
    //         }
    //       })
    // }
  }
}
</script>

<style scoped>

</style>
