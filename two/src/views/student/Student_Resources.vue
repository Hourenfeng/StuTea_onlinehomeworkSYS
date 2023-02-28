<template>
	<div id="container">
		<el-container>
			<el-main>
				
				<template v-if="this.$router.currentRoute.value.path=='/studentResources'">
					<div id="search">
						<el-form :inline="true">
							<el-form-item>
								<el-input
								 v-model="resources.resource" 
								 style="width: 160px;"
								  placeholder="资源内容" 
								  clearable=""></el-input>					
							</el-form-item>
							<el-form-item>
							        <el-button  @click="submitSearch()">搜索</el-button>
									<el-button  @click="init()">清空</el-button>
							</el-form-item>
							<el-form-item  style="float:right">
								<el-select v-model="resources.courseId" clearable 
								placeholder="请选择课程"
								 @change="searchSelect">
									<el-option v-for="item in course"
									:key="item.courseName"
									:label="item.courseName"
									:value="item.courseId"
									></el-option>
								</el-select>
							</el-form-item>
						</el-form>
					</div>
					<el-table :data="resources">
						<el-table-column label="上传教师" prop="teacher.loginName">
						</el-table-column>
						<el-table-column label="上传日期" prop="date">
						</el-table-column>
						<el-table-column label="学科" prop="course.courseName">
						</el-table-column>	
						<el-table-column label="内容" prop="resource">
						</el-table-column>
						<el-table-column
								fixed="right"
							      label="操作"
								  >
							<template #default="scope">
								<el-button 
								size="small" 
								type="primary" @click="showFile(scope.row.url)">
									预览
								</el-button>
								<el-button size="small" type="primary"
								@click="download(scope.row.url)">
									下载
								</el-button>
						      </template>
						</el-table-column>	
						</el-table>
					<div id="page_content">
							<el-pagination
							 background
							layout="total,sizes,prev, pager, next,jumper"
							:total="pageTotal"
							:page-sizes="[4,6,8,10]"
							:page-size="pageSize"
							@size-change="handleSizeChange"
							@current-change="handleCurrentChange"
							:current-change="pageNum"
							>
					</el-pagination>
					</div>
				</template>
				<template v-else>
					<router-view/>
				</template>
			</el-main>
		</el-container>
	</div>
</template>

<script>
	export default{
		name:'Student_Resources',
		data() {
			return{
				resources:[],
				course:[],
				url:'',
				pageNum:1,
				pageTotal:0,
				pageSize:10,
			}
		},
		created() {
			this.student=JSON.parse(sessionStorage.getItem("student"));
			this.init();
			this.findCourseName();
		},
		methods:{
			 searchSelect(val){
			    this.init();
			  },
			findCourseName(){
				this.$axios
				.post("teacher/findAllCourse")
				.then(rst=>{
					this.course=rst.data;
					console.log(rst.data);
				})
				.catch(ex=>{
					console.log(ex);
				})
			},
			submitSearch(){
				this.pageNum=1;
				this.init();
			},
			download(row){
				window.open(row, '_blank');
			},
			init(){
				this.resources.clazzId=this.student.clazzId;
				this.resources.pageNum=this.pageNum;
				this.resources.pageSize=this.pageSize;
				var param = this.$qs.stringify(this.resources)
				this.$axios
				.post("resource/findStuResourcesByClazzId",param)
				.then(rst=>{
					this.resources=rst.data.list;
					this.pageTotal=rst.data.total;
					console.log(rst.data.list);
				})
				.catch(ex=>{
					console.log(ex);
				})
			},
			handleCurrentChange(val)
						{
							console.log(`当前页:${val}`);
							this.pageNum=val;
							this.init();
						},
						handleSizeChange(val){
							console.log(`每页${val}条`);
							this.pageSize=val;
							this.init();
						},
			showFile(row){
				let routeUrl = this.$router.resolve({
				
				path: "/filePreview",
				
				query: {url:row}
				
				});
				
				window.open(routeUrl.href, '_blank');
			},				
			
		}
	}
</script>

<style>
</style>
