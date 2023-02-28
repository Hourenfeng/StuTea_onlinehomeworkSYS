<template>
	<div id="container">
		<login-and-regist-main>
			<div id="logincontainer">
					<el-form :model="regForm" :rules="rules">
						<h3 class="logintitle">注册</h3>
						<el-radio v-model="radio" label="1">我是老师</el-radio>
						  <el-radio v-model="radio" label="2">我是学生</el-radio>
						<stuReg v-if="radio==2"></stuReg>
						<teaReg v-if="radio==1"></teaReg>
					</el-form>
			</div>
		</login-and-regist-main>
		
	</div>
</template>

<script>
	  import LoginAndRegistMain from "../../components/loginAndRegistMain";
	import stuReg from '@/views/user/StuReg';
	import teaReg from '@/views/user/TeaReg';
	export default{
		components:{stuReg,teaReg,LoginAndRegistMain},
		name:'UserReg',
		data(){
			
			return{
				radio:'0',	
				regForm:{
					
				}
			}
		},

	}
</script>

<style>
	#logincontainer{
	    position:absolute;
	        /*定位方式绝对定位absolute*/
	        top:50%;
	        left:50%;
	        /*顶和高同时设置50%实现的是同时水平垂直居中效果*/
	        transform:translate(-50%,-50%);
	        /*实现块元素百分比下居中*/
	        width:450px;
	        padding:50px;
	        /*背景颜色为黑色，透明度为0.8*/
	        box-sizing:border-box;
	        /*box-sizing设置盒子模型的解析模式为怪异盒模型，
	        将border和padding划归到width范围内*/
	        box-shadow: 0px 15px 25px rgba(0,0,0,.5);
	        /*边框阴影  水平阴影0 垂直阴影15px 模糊25px 颜色黑色透明度0.5*/
	        border-radius:15px;
	        /*边框圆角，四个角均为15px*/
	}
</style>
