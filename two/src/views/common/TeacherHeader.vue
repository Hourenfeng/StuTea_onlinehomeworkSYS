<template>
<el-header style="
	height: 80px;
	line-height: 20px;
	background-color: rgba(10, 35, 54, 100);
	">
        <el-row class="row-bg" justify="space-between" style="background-color:rgba(10, 35, 54, 100) ;">
               <el-col :span="4">
                 <div class="logo">
       				  <a href="/teadataAnalyse">Ant Motion</a>
       		  </div>
               </el-col>
       		<el-col :span="14"></el-col>
               <el-col :span="3">

                </el-col>
               			   <el-col :span="3">
               				   <div >
               					   <a href="/TeaPersonal" style="color: white;text-align: center;
               								   line-height: 60px;">
               						   <h5 id="five">欢迎你：{{teacher.loginName}}老师</h5>
               					   </a>
               				   </div>
               			   </el-col>
             </el-row>
 </el-header>
</template>

<script>
	export default {
		name:'TeacherHeader',
	    data() {    
	      return {
			  teacher:{
				  
			  }
	      }
	    },
		created() {
			this.teacher=JSON.parse(sessionStorage.getItem("teacher"));
			console.log(this.teacher);
		}
	  };
</script>

<style>
.logo{
	left: 0px;
	top:0px;
	position: absolute;
	width: 259px;
	height: 60px;
}
.logo a{
	position: absolute;
	top: 0px;
	display: block;
	width: 259px;
	height: 60px;
	background: url(../../assets/1.png) no-repeat;
	font-size: 0;
}
.el-container{
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
}
/* .el-main{
	background: url(../../assets/背景.png);
	background-position: center top;
	background-attachment: fixed;
	background-size: cover;
} */
.el-header{
	top: 0px;
	left: 0px;
}
</style>
