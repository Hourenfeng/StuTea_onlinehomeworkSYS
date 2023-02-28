<template>
	<el-container id="container">
		<StudentHeader></StudentHeader>
		<el-container>
			<el-aside width="200px">
				<StuLeftMenu></StuLeftMenu>
			</el-aside>
			<el-main id="main">
				<el-breadcrumb v-if="this.$router.currentRoute.value.path!='/studentHomepage'"
				>
				  <el-breadcrumb-item 
				v-for="item in breadcrumbList"
				:to="{path:item.path}"
				:key="item.path">
				{{item.meta.title}}
				</el-breadcrumb-item>
				</el-breadcrumb>
				<div class="homeWelcome" v-show="this.$router.currentRoute.path!='/studentHomepage'">
					<h1 v-if="this.$router.currentRoute.value.path=='/studentHomepage'">欢迎来到学生的主页</h1>
				</div>
				<router-view/>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	import StudentHeader from '@/views/common/StudentHeader'; 
	import StuLeftMenu from '@/views/common/Stu_LeftMenu';
	export default{
		components:{StudentHeader,StuLeftMenu},
		name:'Tea_Homepage',
		data() {
			return{
				student:{
					
				}
			}
		},
		created() {
			this.student=JSON.parse(sessionStorage.getItem("student"));
			console.log(this.student);
		},
		mounted() {
			console.log(this.$route);
		},
		computed:{
			breadcrumbList(){
				return this.$route.matched;
			}
		}
	}
</script>

<style scoped>

	#container{
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
	}
	#main{
		width: 100%;
		flex: 1;
	}
	#aside{
		background-color: #071b2e;
	}
	.homeWelcome{
		text-align: center;
		font-size: 30px;
		font-family: 华文楷体;
		color: #409eff;
	}
</style>
