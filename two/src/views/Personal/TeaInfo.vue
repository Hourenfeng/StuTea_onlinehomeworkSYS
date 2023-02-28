<!-- <template>
  <div id="container">
    <el-form ref="show_content">
      <el-form-item label="教师姓名：">
        {{teacher.loginName}}
      </el-form-item>
      <el-form-item label="学校：">
		
      </el-form-item>
      <el-form-item label="教学班级：">

      </el-form-item>
      <el-form-item label="教学课程：">

      </el-form-item>
      <el-form-item label="电话号码：">
        {{teacher.phoneNum}}
      </el-form-item>
    </el-form>
  </div>
</template> -->
<template>
    <div>
      <el-row :gutter="20" style="margin-top:30px;margin-left: 30px;">
        <el-col :span="8">
            <div class="grid-content">
                 <el-card class="box-card">
					<div slot="header" class="clearfix" style="font-size: 24px;margin-bottom: 10px;">
						<span>个人中心</span>
					</div>
					<el-divider></el-divider>
					<div class="name-role" style="font-size: 20px;">
						<span class="sender">身份 - 老师</span>  
					</div>
					<div class="registe-info">
						<span class="registe-info">
							注册时间：
							<!-- <li class="el-icon-time"></li> -->
							<el-icon><Timer /></el-icon>
							2020/4/10 9:40:33
						</span>
					</div>
					<el-divider></el-divider>
					<div class="personal-relation">
						<div class="relation-item">性别:  <div style="float: right; padding-right:20px;">男</div></div>
					</div>
					<div class="personal-relation">
						<div class="relation-item">昵称:  <div style="float: right; padding-right:20px;">磊子</div></div> 
					</div>
					<div class="personal-relation">
						<div class="relation-item">所属学校:  <div style="float: right; padding-right:20px;">中国石油大学附属小学</div></div>      
					</div>
				</el-card>
			</div>
        </el-col>
		<el-col :span="16">
			<div class="grid-content2" v-if="this.tocorrect==0">
				<el-card class="box-card">
					<div slot="header" class="clearfix" style="font-size: 24px;margin-bottom: 30px;">
						<span>基本资料</span>
					</div>
					<el-divider></el-divider>
					<div >
						<!-- <el-form >
							<el-form-item label="教师姓名：" class="items">
								<span style="font-size: 18px;">{{teacher.loginName}}</span>
							</el-form-item>
							<el-form-item label="手机号" prop="phone" class="items">
								<span style="font-size: 18px;">{{teacher.phoneNum}}</span>
							</el-form-item>
							<el-form-item label="首页链接" prop="homeUrl" class="items">
								<span style="font-size: 18px;">{{dataForm.homeUrl}}</span>
							</el-form-item>
						</el-form> -->
						<div class="personal-relation">
							<div class="relation-item">教师姓名:  <div style="float: right; padding-right:20px;">{{teacher.loginName}}</div></div>
						</div>
						<div class="personal-relation">
							<div class="relation-item">手机号:  <div style="float: right; padding-right:20px;">{{teacher.phoneNum}}</div></div> 
						</div>
						<div class="personal-relation">
							<div class="relation-item">负责班级:  <div style="float: right; padding-right:20px;">一年二班</div></div>      
						</div>
						<div class="personal-relation">
							<div class="relation-item">教学课程:  <div style="float: right; padding-right:20px;">语文</div></div>      
						</div>
					</div>
					<div slot="footer" class="dialog-footer">
					          <el-button size="mini" type="primary" v-on:click="correct()">修改</el-button>
					</div>
				</el-card>
			</div>
			<div class="grid-content2" v-if="this.tocorrect==1">
				<el-card class="box-card">
					<div slot="header" class="clearfix" style="font-size: 24px;margin-bottom: 30px;">
						<span>基本资料</span>
					</div>
					<el-divider></el-divider>
					<div >
						<el-form label-width="80px" v-model="dataFrom" size="small" label-position="right">
							<el-form-item label="教师姓名：" class="items">
								<el-input  auto-complete="off" v-model="teacher.loginName"></el-input>
							</el-form-item>
							<el-form-item label="手机号 :" prop="phone" class="items">
								<el-input  auto-complete="off" v-model="teacher.phoneNum"></el-input>
							</el-form-item>
							<el-form-item label="负责班级:" prop="homeUrl" class="items">
								<el-input  auto-complete="off" v-model="teacher.clazz"></el-input>
							</el-form-item>
							<el-form-item label="教学课程：" prop="homeUrl" class="items">
								<el-input  auto-complete="off" v-model="teacher.courseId"></el-input>
							</el-form-item>
						</el-form>
					</div>
					<div slot="footer" class="dialog-footer">
					          <el-button size="mini" type="primary">确认</el-button>
							  <el-button size="mini" type="warning" v-on:click="close()">关闭</el-button>
					</div>
				</el-card>
			</div>
        </el-col>
      </el-row>
    </div>
</template>
<script>
	import { Timer } from '@element-plus/icons-vue'
export default {
	
	components: {
		Timer
	},
  name: "TeaInfo",
  data(){
    return{
		tocorrect:0,
      teacher:{
      },
      info:{},
	  dataForm:{
	          nickName: '超级管理员',
	          phone: '173567777777',
	          homeUrl: 'http://www.baidu.com'
	        }
    }
  },
  methods:{
    init() {
      var param=this.$qs.stringify(this.teacher)
      this.$axios.post("/teacher/findAllCourse",param)
      .then(rst => {
        console.log(rst);
        this.info=rst.data.list;
      }).catch(ex=>{
        console.log(ex);
      });
    },
	correct(){
		this.tocorrect=1
	},
	close(){
		this.tocorrect=0
	}
  },
  created() {
    this.teacher=JSON.parse(sessionStorage.getItem("teacher"));
    console.log(this.teacher);
  },
  mounted() {
  }
}
</script>

<style>
//卡片样式
 .text {
    font-size: 24px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 100%;
	border-radius: 4px;
  }
//文本样式区
.items.el-form-item__label{
	font-size: 18px;
}
  .name-role {
    font-size: 16px;
    padding: 5px;
   text-align:center;
  }
   .sender{
      text-align:center;
    }
.registe-info{
  text-align: center;
  padding-top:10px;
}
.personal-relation {
  font-size: 18px;
  padding: 0px 5px 15px;
  margin-right: 1px;
  width: 100%;
  text-align: justify;
}

.relation-item {
  padding: 12px;
   
}
.dialog-footer{
  padding-top:10px ;
  padding-left: 10%;
}
//布局样式区
   .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
	margin-left: 60px;
  }
  .grid-content2 {
    border-radius: 4px;
    min-height: 36px;
  	margin-left: 200px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>