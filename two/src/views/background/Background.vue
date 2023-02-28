<template>
	<el-container id="container">
		<el-header id="header">
			<el-row class="row-bg" justify="space-between">
				<el-col :span="6">
					<div class="grid-content bg-purple">
						<h1 id="houtai">后台管理</h1>
					</div>
				</el-col>
				<el-col :span="1" class="hidden-md-and-down">
					<div class="grid-content bg-purple">
							<el-button type="info" circle>
								退出
							</el-button>
					</div>
				</el-col>
			</el-row>
		</el-header>
		<el-container>
			<el-aside width="200px" id="aside">
				<el-menu
					:router="true"
					:unique-opened="true">
				<el-sub-menu index="1">
					<template #title>
						<i class="el-icon-user"></i>
						教师管理
					</template>
					<el-menu-item index="/teachers">教师信息导入</el-menu-item>
					<el-menu-item index="/teacher">教师信息管理</el-menu-item>
				</el-sub-menu>
				<el-menu-item index="/students">学生信息管理</el-menu-item>
				<el-menu-item index="/resources">资源管理</el-menu-item>
				<el-sub-menu index="2">
					<template #title>
						<i class="el-icon-user"></i>
						作业管理
					</template>
					<el-menu-item index="/shomeworks">学生提交作业</el-menu-item>
					<el-menu-item index="/thomeworks">教师上传作业</el-menu-item>
				</el-sub-menu>
				<el-menu-item index="/schools">学校信息管理</el-menu-item>
			</el-menu>
			</el-aside>
			<el-main id="main">
				<router-view></router-view>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	export default{
		name:'Background'
	}
</script>

<style scoped="scoped">
	#houtai{
		margin: 12px;
		color: #e3e3e3;
		font-family: 黑体;
		font-size: 30px;
	}
	#container{
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
	}
	#header{
		background-color: #071b2e;
	}
	#main{
		width: 100%;
		flex: 1;
		display: flex;
	}
	#aside{
		background-color: #071b2e;
	}
</style>
