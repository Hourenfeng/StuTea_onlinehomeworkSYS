<template>
  <div>
    <login-and-regist-main>
      <div class="right-content">
        <h2 class="title"><span data-v-5be217e7="">注册账号</span></h2>
        <div class="login-tab">	
          <el-form :model="registForm" status-icon :rules="rules"  ref="registForm" class="registForm">
			<el-form-item>
			  <p>选择身份</p>
			</el-form-item>
			<el-form-item prop="role" class="registRole">
			  <el-button :style="{borderColor: borderType ? '#4285f4':null}" icon="el-icon-teacher" @click="changeRole('老师')">老师</el-button>
			  <el-button :style="{borderColor: borderType ? null:'#4285f4'}" icon="el-icon-student" @click="changeRole('学生')">学生</el-button>
			</el-form-item>
            <el-form-item prop="phoneNum">
              <el-input type="text" v-model="registForm.phoneNum" placeholder="请输入手机号" autocomplete="off" clearable></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input type="password" v-model="registForm.password" @focus.capture.native='changePasswordTip(true)'  @blur.capture.native='changePasswordTip(false)' autocomplete="off" placeholder="请输入密码" clearable></el-input>
              <div style="position: absolute">
                <password-prompt :password="registForm.password" :isShowTip = 'isShowTip'></password-prompt>
              </div>
            </el-form-item>
            <el-form-item prop="confirmPassword">
              <el-input type="password" v-model="confirmPassword" autocomplete="off" placeholder="请再次输入密码确认" clearable></el-input>
            </el-form-item>
            
            <el-form-item prop="loginName">
              <el-input type="text" v-model="registForm.loginName" autocomplete="off" placeholder="请输入姓名" clearable></el-input>
            </el-form-item>

            <el-form-item v-if="registForm.role==='学生'" prop="clazzId">
              <el-input type="password" v-model="registForm.clazzId" autocomplete="off" placeholder="请输入邀请码" clearable></el-input>
            </el-form-item>
            <el-form-item prop="codeText">
              <el-row :span="24">
                <el-col :span="12">
                  <el-input type="text" v-model="code" autocomplete="off" placeholder="请输入验证码" clearable></el-input>
                </el-col>
                <el-col :span="12">
                  <div class="login-code" @click="refreshCode">
                    <!--验证码组件-->
                    <s-identify :identifyCode="identifyCode"></s-identify>
                  </div>
                </el-col>
              </el-row>
            </el-form-item>
          </el-form>
          <div>
            <el-button type="primary" style="width: 100%" @click="submit">注册</el-button>
            <div class="registBottom">已有账户? <el-button type="text" @click="goLogin">去登录</el-button>
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
  import LoginAndRegistMain from "@/components/loginAndRegistMain";
  import SIdentify from "@/components/SIdentify";
  import PasswordPrompt from "@/components/PasswordPrompt";
  export default {
    name: "Regist",
    components:{PasswordPrompt, SIdentify, LoginAndRegistMain},
    data () {
		let reg=/^(((13[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(17[3-8]{1})|(18[0-9]{1})|(19[0-9]{1})|(14[5-7]{1}))+\d{8})$/;
		var validatePass3 = (rule,value,callback)=>{
			if (value === '') {
						callback(new Error('手机号不能为空'))
				  } else if (!reg.test(value)) {
						callback(new Error('手机号格式错误'))
					} else {
						callback()
					}
		};
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.confirmPassword !== '') {
            this.$refs.registForm.validateField('confirmPassword');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (this.confirmPassword !== this.registForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      const validateCode = (rule, value, callback) => {
        if(this.code === ''){
          callback(new Error('请输入验证码'));
        }
        else if (this.identifyCode !== this.code) {
          this.code = ''
          this.refreshCode()
          callback(new Error('请输入正确的验证码'))
        } else {
          callback()
        }
      };
      return {
        identifyCodes: "1234567890",
        identifyCode: "",
        code:"",//text框输入的验证码
        confirmPassword:"",
        borderType:true,
        isShowTip:false,
        registForm: {
			type:0,
          phoneNum: "",
          password: "",
          role:"老师",
          loginName:"",
		  clazzId:"",
        },
        rules: {
          phoneNum: [{validator: validatePass3,trigger:'blur'}],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ],
          confirmPassword: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          codeText: [
            { validator: validateCode, trigger: 'blur',required: true}
          ],
        },
       }
    },
    methods: {
      returnHome(){
        this.$router.push("/home")
      },
      changeRole(role){
        this.registForm.role=role;
        if (role==='老师')
          this.borderType = true
        else
          this.borderType = false
      },
      submit() {
        this.$refs.registForm.validate((valid) => {
          if (valid) {
            if(this.borderType){
				var param = this.$qs.stringify(this.registForm);
				this.$axios
				.post("teacher/addTeacher",param)
				.then(successResponse=>{
					if(successResponse.status===200){
					    console.log(successResponse);
						this.$message.success("注册成功！");
					    this.$router.push({path:'/login'});
					}else{
					    console.log(successResponse);
					    alert("注册失败，请重新输入用户名！");
					}
				})
				.catch(ex=>{
					alert("注册失败！");
					console.log(ex);
				})
			}
			else{
				var param = this.$qs.stringify(this.registForm);
				this.$axios
				.post("student/addStudent",param)
				.then(successResponse=>{
					if(successResponse.status==200){
						console.log(successResponse);
						this.$message.success("注册成功！");
						this.$router.push({path:'/login'});
					}
					else
					{
						console.log(successResponse)
						alert("注册失败，请重新输入用户名！");
					}
				}).catch(ex=>{
				        alert("注册失败！");
				        console.log(ex);							
				})
			}
          } else {
            this.$message({
              message: '请输入所有字段',
              type: 'error'
            });
            return false
          }
        });
      },
      goLogin(){
        this.$router.push("/login")
      },
      //验证码
      randomNum(min, max) {
         return Math.floor(Math.random() * (max - min) + min);
      },

      refreshCode() {
             this.identifyCode = "";
             this.makeCode(this.identifyCodes, 4);
       },
      makeCode(o, l) {
         for (let i = 0; i < l; i++) {
              this.identifyCode += this.identifyCodes[
                         this.randomNum(0, this.identifyCodes.length)
               ];
           }
         console.log(this.identifyCode);
     },
      changePasswordTip(isShow) {
        if (isShow) {
          this.isShowTip = true;
        } else {
          this.isShowTip = false;
        }
      }
    },
    mounted(){
          this.identifyCode = "";
           this.makeCode(this.identifyCodes, 4);
    },
    created() {
      this.refreshCode();
    }
  }
</script>
<style scoped>
  .regist {
    z-index: 0;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    transform: scale(1);
    background: transparent;
    transition: transform .3s ease-out 299ms,background .3s ease-in-out 0ms;
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
  .content {
    display: flex;
    justify-content: space-between;
  }
  .left {
    margin-right: -100px;
  }
  .registBottom{
    padding-top: 10px;
    float: right;
  }
  .el-button--text{
    color: #409EFF;
    background: 0 0;
    padding-top: 4px;
    padding-bottom: 25px;
    padding-right: 0;
    float: right;
    font-size: 15px;
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
  .login-tab p{
    font-weight: 700;
    font-size: 16px;
    float: left;
    margin-block-start: 0px;
    margin-block-end: 0px;
  }
  .registRole{
    display: flex;
    justify-content: space-between;
  }
  .login-code{
    cursor: pointer;
  }
</style>
<style scoped>
  .login-tab .el-input__inner {
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
  .registRole .el-button {
    color: #000;
    background-color: #fff;
    border-color: #fff;
    width: 205px;
    height: 60px;
    text-align: center;
    line-height: 35px;
  }

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
  .login-code .s-canvas canvas {
    margin-top: 1px;
    margin-left: 8px;
    height: 48px;
  }
</style>