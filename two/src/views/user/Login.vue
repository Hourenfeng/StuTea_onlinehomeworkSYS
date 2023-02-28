<template>
	<div >
		<login-and-regist-main>
			<div class="right-content">
				<h2 class="title"><span >账号登录</span></h2>
				<div class="login-tab">
					<el-form :model="loginForm" :rules="rules" ref="loginForm" class="login">	
					<el-form-item prop="radio" class="registRole">
					  <el-button :style="{borderColor: borderType ? '#4285f4':null}" icon="el-icon-teacher" @click="changeRole('老师')">老师</el-button>
					  <el-button :style="{borderColor: borderType ? null:'#4285f4'}" icon="el-icon-student" @click="changeRole('学生')">学生</el-button>
					</el-form-item>
						<el-form-item prop="loginName" >
							<el-input  type="text"
							placeholder="请输入账号"
							v-model="loginForm.loginName"
							></el-input>
						</el-form-item>
						<el-form-item prop="password" >
							<el-input type="password"
							v-model="loginForm.password"
							placeholder="请输入密码"
							autocomplete="off"></el-input>
						</el-form-item>
					</el-form>
					
					<div>
						<el-button type="primary" 
						 @click="submitlogin" style="width: 100%;">登录</el-button>
						 <div class="loginBottom"> 还没有账号？ <el-button type="text" @click="goRegist">去注册</el-button>
						 </div>
						 <div class="qr-box">
						     <div class="qr-code">
						     </div>
						 </div>
					</div>
				</div>
			</div>
		</login-and-regist-main>
	</div>
</template>

<script>
	import LoginAndRegistMain from "../../components/loginAndRegistMain";
	export default{
		 components:{LoginAndRegistMain},
		name:'Login',
		data() {
			return{
				borderType:true,
				radio:1,					
				loginForm:{
					loginName:'',
					password:'',
					role:"老师",
				},
				rules:{
				    userName: [{required:true,message:'请输入用户名！',trigger:'blur'}],
				    password: [{required:true,message:'请输入密码！',trigger:'blur'}]
				},
			}
		},
		created() {
			document.onkeydown = (e) => {
			      e = window.event || e
			      if (this.$route.path === '/login' && e.keyCode === 13) this.submitlogin()  // submitLoginForm() 为登录函数
			    }

		},
		methods:{
			changeRole(role){
			  this.loginForm.role=role;
			  if (role==='老师')
			    {
					this.radio = 1
					this.borderType=true
				}
			  else
			    {
					this.borderType=false
					this.radio = 2
				}
			},
			goRegist(){
			    this.$router.push("/Regist")
			},
			submitlogin(){
				this.$refs.loginForm.validate((valid)=>{
					if(valid){
						if(this.radio=='1'){
							var param = this.$qs.stringify(this.loginForm);
							this.$axios
							.post("teacher/login",param)
							.then(list=>{
								if(list.data){
									console.log(list);
									sessionStorage.setItem("teacher",JSON.stringify(list.data));
									this.$router.push("/teaHomepage");
								}
								else{
									console.log(list);
									alert("账号或密码错误，登录失败！");
								}
							})
						}
						else
						{
							var param = this.$qs.stringify(this.loginForm);
							this.$axios
							.post("student/login",param)
							.then(list=>{
								if(list.data){
									console.log(list);
									sessionStorage.setItem("student",JSON.stringify(list.data));
									this.$router.push("/studataAnalyse");
								}
								else{
									console.log(list);
									alert("账号或密码错误，登录失败！");
								}
							})
						}
					}
					else{
						this.$message.error('请输入所有字段');
						return false;
					}
				})
			}
		}
	}
</script>

<style scoped>
	.registRole .el-icon-teacher {
	  background: url('../../../public/img/teacher.6674873d.svg');
	  width: 34px;
	  height: 34px;
	  float: left;
	}
	.registRole .el-icon-student {
	  background: url('../../../public/img/student.c11b6951.svg');
	  width: 34px;
	  height: 34px;
	  float: left;
	}
	.registRole .el-icon-user:before {
	  visibility: hidden;
	}
	.registRole .el-button {
	  color: #000;
	  background-color: #fff;
	  border-color: #fff;
	  width: 205px;
	  height: 60px;
	  text-align: center;
	  line-height: 35px;
	}
	.registRole .el-form-item__content {
	  line-height: 0px;
	  position: relative;
	  font-size: 14px;
	  font-family: MicrosoftYaHei;
	  text-align: center;
	  color: #3c4043;
	  line-height: 24px;
	  letter-spacing: 1px;
	}
	.registRole{
	  display: flex;
	  justify-content: space-between;
	}
	.title{
		padding: 18px 0;
	}
	.logo-box {
	    position: absolute;
	    top: 48px;
	    left: 62px;
	    cursor: pointer;
	}
	
	
	.right-content {
	    width: 431px;
	    padding: 0 35px;
	    background: #fff;
	    border: 1px solid #eee;
	    box-shadow: 0 0 61px 0 rgba(85,108,144,.07);
	}
	.title {
	    position: relative;
	    text-align: center;
	}
	.tabs-nav {
	    justify-content: center;
	    position: relative;
	    padding-bottom: 9px;
	    margin-bottom: 12px;
	    display: flex;
	}
	
	
	.el-menu--horizontal>.el-menu-item {
	    float: left;
	    height: 50px;
	    margin: 0;
	    border-bottom: 4px solid transparent;
	    color: #909399;
	}
	.el-checkbox {
	    color: #606266;
	    font-weight: 500;
	    cursor: pointer;
	    -moz-user-select: none;
	    -ms-user-select: none;
	    float: left;
	}
	.el-button--text {
	    color: #409EFF;
	    background: 0 0;
	    padding-top: 0;
	    padding-bottom: 25px;
	    padding-right: 0;
	    float: right;
	}
	li, ul {
	    list-style-type: none;
	}
	.login.el-input__inner {
	    -webkit-appearance: none;
	    background-color: #FFF;
	    background-image: none;
	    border-radius: 4px;
	    border: 1px solid #DCDFE6;
	    box-sizing: border-box;
	    color: #606266;
	    display: inline-block;
	    font-size: inherit;
	    height: 48px;
	    line-height: 40px;
	    outline: 0;
	    padding: 0 15px;
	    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
	    width: 100%;
	}
	flex-between {
	    display: flex;
	    align-items: center;
	}
	.el-checkbox__input {
	    cursor: pointer;
	    outline: 0;
	    line-height: 1;
	    vertical-align: middle;
	}
	.el-checkbox__label {
	    display: inline-block;
	    padding-left: 10px;
	    line-height: 19px;
	    font-size: 14px;
	}
	.loginBottom{
	    padding-top: 10px;
	    float: right;
	}
	.loginBottom .el-button--text{
	    color: #409EFF;
	    background: 0 0;
	    padding-top: 3px;
	    padding-bottom: 25px;
	    padding-right: 0;
	    float: right;
	}
	
	.margin-top {
	    margin-top: 12px;
	}
	.font14 {
	    font-size: 14px;
	}
	.qr-code {
	    position: relative;
	    height: 70px;
	    width: 70px;
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    cursor: pointer;
	    transform: rotateY(180deg);
	}
	.login-footer-img {
	    position: absolute;
	    bottom: 100px;
	}

</style>
