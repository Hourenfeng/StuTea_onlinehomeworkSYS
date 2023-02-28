<template>
  <el-row>
    <el-col>
      <el-menu
        class="el-menu-vertical-demo"
           background-color="#ffffff"
           text-color="black" router>
            <el-submeu index="1" >
            <el-menu-item-group>
				<el-menu-item index="/studataAnalyse">数据分析</el-menu-item>
              <el-menu-item index="/studenthkList">作业列表</el-menu-item>
			  <el-menu-item index="/studentResources">资源管理</el-menu-item>
			  
            </el-menu-item-group>
          </el-submeu>
        </el-menu>
    </el-col>
  </el-row>
</template>

<style>
  .el-menu-vertical-demo{
    height: 645px
  }
</style>

<script>
  export default {
    name: 'LeftMenu',
    };
</script>