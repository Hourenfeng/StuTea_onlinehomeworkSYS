<template>
<el-header style="
	height: 65px;
	line-height: 20px;
	background-color: rgba(10, 35, 54, 100);
	">
        <el-row>
               <el-col :span="4">
                 <div class="logo">
       				  <a href="/main">Ant Motion</a>
       		  </div>
               </el-col>
       		<el-col :span="15"></el-col>
               <el-col :span="5">
       <el-menu
       :default-active="src"
                     mode="horizontal"
                     @select="handleSelect"
                     class="el-menu-demo"
                     background-color="rgba(10, 35, 54, 100)"
                     text-color="#fff">
       	<el-menu-item>
       		<router-link to="/background">后台</router-link>
       	</el-menu-item>
       	<el-menu-item index="#">帮助</el-menu-item>
       	<el-menu-item index="#">登录</el-menu-item>
       	<el-menu-item index="#">注册</el-menu-item>
       </el-menu>
               </el-col>
             </el-row>
    </el-header>
</template>

<script>
	export default {
		name:'Header',
	    data() {    
	      return {
	      }
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
