<template>
	<el-dialog
	  title="编辑"
	  v-model="dialogEditShomework">
			<el-form :model="form" >
			<el-form-item label="作业题目" >
			<el-select v-model="form.homeworkId" placeholder="请选择作业题目">
				<el-option v-for="item in homework" 
				:key="item.content" 
				:label="item.content" 
				:value="item.homeworkId"
				>
			</el-option>
			</el-select>
			</el-form-item>	
			<el-form-item label="学生姓名" >
			<el-select v-model="form.studentId" placeholder="请选择学生姓名">
				<el-option v-for="item in student" 
				:key="item.loginName" 
				:label="item.loginName" 
				:value="item.studentId"
				>
			</el-option>
			</el-select>
			</el-form-item>
			<el-form-item label="课程名称" >
			<el-select v-model="form.courseId" placeholder="请选择课程名称">
				<el-option v-for="item in course" 
				:key="item.courseName" 
				:label="item.courseName" 
				:value="item.courseId"
				>
			</el-option>
			</el-select>
			</el-form-item>
			<el-form-item label="作业内容">
			<el-input v-model="form.content" autocomplete="off"></el-input>
			</el-form-item>
			</el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogEditShomework = false,clearForm()">取 消</el-button>
	      <el-button type="primary" @click="EditShomework()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<el-dialog
	  title="添加"
	  v-model="dialogAddShomework">
			<el-form :model="form" >
			<el-form-item label="作业题目" >
			<el-select v-model="form.homeworkId" placeholder="请选择作业题目">
				<el-option v-for="item in homework" 
				:key="item.content" 
				:label="item.content" 
				:value="item.homeworkId"
				>
			</el-option>
			</el-select>
			</el-form-item>	
			<el-form-item label="学生姓名" >
			<el-select v-model="form.studentId" placeholder="请选择学生姓名">
				<el-option v-for="item in student" 
				:key="item.loginName" 
				:label="item.loginName" 
				:value="item.studentId"
				>
			</el-option>
			</el-select>
			</el-form-item>
			<el-form-item label="课程名称">
			<el-select v-model="form.courseId" placeholder="请选择课程名称">
				<el-option v-for="item in course" 
				:key="item.courseName" 
				:label="item.courseName" 
				:value="item.courseId"
				>
			</el-option>
			</el-select>
			</el-form-item>
			<el-form-item label="作业内容">
			<el-input v-model="form.content" autocomplete="off"></el-input>
			</el-form-item>
			</el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogAddShomework = false">取 消</el-button>
	      <el-button type="primary" @click="addShomework()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<div id="container">
		<div id="search">
			<el-form :inline="true">
				<el-form-item>
					<el-select v-model="shomeworks.studentId" placeholder="请选择学生姓名">
						<el-option v-for="item in student" 
						:key="item.loginName" 
						:label="item.loginName" 
						:value="item.studentId"
						>
					</el-option>
					</el-select>
				</el-form-item>
				<el-form-item>
					<el-select v-model="shomeworks.courseId" placeholder="请选择课程名称">
						<el-option v-for="item in course" 
						:key="item.courseName" 
						:label="item.courseName" 
						:value="item.courseId"
						>
					</el-option>
					</el-select>					
				</el-form-item>
				<el-form-item>
				        <el-button  @click="submitSearch()">搜索</el-button>
				        <el-button  @click="clearForm()">清空</el-button>
				</el-form-item>
			</el-form>
		</div>
		<div id="addHomework">
			<el-button type="primary" class="el-icon-search"
			@click="dialogAddShomework=true">
			  添加课程
			</el-button>
		</div>
		<div id="show_table">
			<el-table :data="shomeworks">
				<el-table-column label="学生姓名"
				prop="student.loginName"></el-table-column>
				<el-table-column label="提交日期"
				prop="date"></el-table-column>
				<el-table-column label="课程名称"
				prop="course.courseName"></el-table-column>
				<el-table-column label="作业内容"
				prop="content"></el-table-column>
				<el-table-column
				      fixed="right"
				      label="操作"
					  >
				      <template #default="scope">
				        <el-button @click="handleClick(scope.row)" type="primary" 
						icon="el-icon-edit" size="small">编辑</el-button>
				        <el-button type="danger" 
						icon="el-icon-delete" size="small"
						@click="deleteShomework(scope.row.stuUploadId)">删除</el-button>
				      </template>
				    </el-table-column>
			</el-table>
			<div id="page_content">
			   <el-pagination
			   					 background
			   					layout="total,sizes,prev, pager, next,jumper"
			   					:total="pageTotal"
			   					:page-sizes="[2,4,6,8]"
			   					:page-size="pageSize"
			   					@size-change="handleSizeChange"
			   					 @current-change="handleCurrentChange"
			   					 :current-change="pageNum"
			   					 >
			   					</el-pagination>
			
			</div>
		</div>
	</div>
</template>

<script>
	import moment from "moment";
	export default{
		name:'Shomeworks',
		data(){
			return{
				student:[],
				shomeworks:[],
				course:[],
				homework:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4,
				form:{
					stuUploadId:'',
					homeworkId:'',
					studentId:'',
					date:'',
					courseId:'',
					content:'',
				},
				dialogAddShomework:false,
				dialogEditShomework:false,
			}
		},
		methods:{
			submitSearch(){
				this.pageNum=1;
				this.init();
			},
			clearForm(){
				this.form={
					stuUploadId:'',
					homeworkId:'',
					studentId:'',
					date:'',
					courseId:'',
					content:'',
				};
				this.init();
			},
			EditShomework(){
				let str = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
				var postData = this.$qs.stringify({
					stuUploadId:this.form.stuUploadId,
					homeworkId:this.form.homeworkId,
					studentId:this.form.studentId,
					date:str,
					courseId:this.form.courseId,
					content:this.form.content,
				});
				this.$axios
				.post("stuhomework/updateStuHomework",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(er=>{
					console.log(er);
				});
				this.dialogEditShomework=false;
			},
			handleClick(row){
				this.form=row;
				this.dialogEditShomework=true;
			},
			deleteShomework(row)
			{
				this.$confirm('删除操作, 是否继续?', '提示', {
				         confirmButtonText: '确定',
				         cancelButtonText: '取消',
				         type: 'warning'
				       }).then(() => {
											var postData=this.$qs.stringify({
												stuUploadId:row
											});
											this.$axios
											.post("stuhomework/deleteStuHomework",postData)
											.then(rst=>{
												this.init();
											})
											.catch(er=>{
												console.log(er);
											});
				         this.$message({
				           type: 'success',
				           message: '删除成功!'
				         });
				       }).catch(() => {
				         this.$message({
				           type: 'info',
				           message: '已取消删除'
				         });
				       });
			},
			addShomework(){
				
				let str = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
				var postData = this.$qs.stringify({
					homeworkId:this.form.homeworkId,
					studentId:this.form.studentId,
					date:str,
					courseId:this.form.courseId,
					content:this.form.content,
				});
				this.$axios
				.post("stuhomework/addStuHomework",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(er=>{
					console.log(er);
				});
				this.dialogAddShomework=false;
			},
			findStuName(){
				this.student.pageNum=this.pageNum;
				this.student.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.student);
				this.$axios
				.post("student/findAllStudents",param)
				.then(rst=>{
					this.student=rst.data.list;
					console.log(rst.data);
				})
				.catch(ex=>{
					console.log(ex);
				})
			},
			findHomeworkName(){
				this.homework.pageNum=this.pageNum;
				this.homework.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.homework);
				this.$axios
				.post("teahomework/findAllTeaHomeworks",param)
				.then(rst=>{
					this.homework=rst.data.list;
				})
				.catch(ex=>{
					console.log(ex);
				})
			},
			findCourseName(){
				this.$axios
				.post("teacher/findAllCourse")
				.then(rst=>{
					this.course=rst.data;
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
			init(){
				this.shomeworks.pageNum=this.pageNum;
				this.shomeworks.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.shomeworks);
				this.$axios
				.post("stuhomework/findAllStuHomeworks",param)
				.then(rst=>{
					console.log(rst);
					this.shomeworks=rst.data.list;
					this.pageTotal=rst.data.total;
				}).catch(ex=>{
					console.log(ex);
				});
			}
		},
		created() {
			this.findHomeworkName();
			this.findStuName();
			this.findCourseName();
			this.shomeworks.pageNum=this.pageNum;
			this.shomeworks.pageSize=this.pageSize;
			var param=this.$qs.stringify(this.shomeworks);
			this.$axios
			.post("stuhomework/findAllStuHomeworks",param)
			.then(rst=>{
				console.log(rst);
				this.shomeworks=rst.data.list;
				this.pageTotal=rst.data.total;
			}).catch(ex=>{
				console.log(ex);
			});
		}
	}
</script>

<style>
</style>
