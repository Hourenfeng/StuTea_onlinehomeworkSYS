<template>
<el-form :model="regForm" :rules="rules" ref="regForm">
				<el-form-item label="邀请码" prop="clazzId">
					<el-input type="text" v-model="regForm.clazzId"
					placeholder="请输入邀请码" class="input"></el-input>
				</el-form-item>
				<el-form-item label="手机号" prop="phoneNum">
					<el-input type="text" v-model="regForm.phoneNum"
					placeholder="请输入手机号" class="input"></el-input>
				</el-form-item>		
				<el-form-item label="用户名" prop="loginName">
					<el-input type="text" v-model="regForm.loginName"
					placeholder="请输入用户名" class="input"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="password">
					<el-input type="password" v-model="regForm.password"
					placeholder="请输入密码" class="input"></el-input>
				</el-form-item>
				<el-form-item  label="再次输入密码：" prop="checkPass">
				        <el-input type="password" v-model="regForm.checkPass"
				                  class="input" placeholder="请再次输入密码"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" class="loginbutton" @click="submitreg">注册</el-button>
				</el-form-item>
</el-form>
</template>

<script>
	export default{
		name:'StuReg',
		data() {
		let reg=/^(((13[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(17[3-8]{1})|(18[0-9]{1})|(19[0-9]{1})|(14[5-7]{1}))+\d{8})$/;
			var validatePass2 = (rule, value, callback) => {
			    if (value === '') {
			        callback(new Error('请再次输入密码'));
			    } else if (value !== this.regForm.password) {
			        callback(new Error('两次输入密码不一致!'));
			    } else {
			        callback();
			    }
			};
			var validatePass3 = (rule,value,callback)=>{
				if (value === '') {
							callback(new Error('手机号不能为空'))
					  } else if (!reg.test(value)) {
							callback(new Error('手机号格式错误'))
						} else {
							callback()
						}
			};
			var validateName = (rule, value, callback) => {
			
			    let reg=/^[\u2E80-\u9FFF]+$/;//Unicode编码中的汉字范围
			
			    if (value && value.length > 1) {
			
			        if(!reg.test(value)){
			
			            callback([new Error('姓名输入不合法')]);
			
			        }else{
			
			            callback();
			
			        }
			
			    }else if(value.length == 0){
			
			        callback(new Error('请输入姓名'));
			
			    } else {
			
			        callback(new Error('姓名输入不合法'));
			
			    }
			
			}			
			return{
				regForm:{
					phoneNum:'',
					clazzId:'',					
					checkPass: '',
					loginName:'',
					password:''
				},
				rules:{
				    clazzId: [{required:true,message:'请输入密码！',trigger:'blur'}],
				    loginName: [{validator:validateName,trigger:'blur'}],
				    password: [{message:'请输入密码！',trigger:'blur'}],
					phoneNum:[{validator: validatePass3,trigger:'blur'}],
					checkPass: [{ validator: validatePass2, trigger: 'blur' }]
				},
			}
		},
		methods:{
			submitreg(){
				this.$refs.regForm.validate((valid)=>{
					if(valid)
					{
						alert("注册成功");
					}
					else
					{
						alert("注册失败");
					}
				});
			}
		},

	}
</script>

<style>
</style>
