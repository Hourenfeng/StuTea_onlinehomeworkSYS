<template>
    <div class="common-layout">
      <el-container>

        <el-header>

            <el-row>
              <el-col :span="4">
                <el-menu
                  class="el-menu-demo"
                  mode="horizontal"
                  @select="handleSelect"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b"
                  router
              >
                <el-menu-item index="/teadataAnalyse">首页</el-menu-item>
                </el-menu>
              </el-col>
              <el-col :span="17"></el-col>
              <el-col :span="3">
                <el-button type="info" @click="tuichu">退出登录</el-button>
              </el-col>
            </el-row>

        </el-header>

        <el-container>
          <el-aside width="200px">
            <el-menu
              class="el-menu-vertical-demo"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b"
              router
          >
            <el-menu-item index="/TeaInfo">
              <span slot="title">个人信息</span>
            </el-menu-item>
            <el-menu-item index="/TeaRemind">
              <span slot="title">消息提醒</span>
            </el-menu-item>
            <el-menu-item index="/TeaPassword">
              <span slot="title">修改密码</span>
            </el-menu-item>
          </el-menu></el-aside>
          <el-main><router-view></router-view></el-main>
        </el-container>
        </el-container>


    </div>
  </template>


<script>
export default {
  name: "TeaPersonal",
  data(){
    return{

    }
  },
  created() {

  },
  mounted() {

  },
  methods:{
    tuichu(){
      sessionStorage.removeItem("teacher");
      location.href = "/login";
    }

  }
}
</script>

<style scoped>
.el-header{
  background-color: #545c64;
}
body>.el-container{
  height: 100%;
}
body,html{
  height: 100%;
}
.el-button{
  margin: 15px;
}
</style>