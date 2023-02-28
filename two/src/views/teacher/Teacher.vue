<template>
	<el-dialog title="编辑" v-model="dialogFormUpdate">
		<el-form :model="form" >
			<el-input  v-model="form.teacherId" type="hidden"/>
			<el-form-item label="教师姓名">
				<el-input v-model="form.loginName" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="教师密码">
				<el-input v-model="form.password" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="所教课程">
				<el-select v-model="form.courseId" placeholder="请选择课程">
					<el-option v-for="item in course"
					:key="item.courseName"
					:label="item.courseName"
					:value="item.courseId"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="手机号">
				<el-input v-model="form.phoneNum" autocomplete="off"></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="dialogFormUpdate = false,clearForm()">取消</el-button>
				<el-button type="primary" @click="updateTeacher()">确认</el-button>
			</span>
		</template>
	</el-dialog>
	
	<el-dialog title="添加老师" v-model="dialogFormVisible">
		<el-form :model="form">
			<el-form-item label="教师姓名">
				<el-input v-model="form.loginName" autocomplete="off">
				</el-input>
			</el-form-item>
			<el-form-item label="密码">
				<el-input v-model="form.password" autocomplete="off">
				</el-input>
			</el-form-item>
			<el-form-item label="所教课程">
				<el-select v-model="form.courseId" placeholder="请选择课程">
					<el-option v-for="item in course"
					:key="item.courseName"
					:label="item.courseName"
					:value="item.courseId"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="手机号">
				<el-input v-model="form.phoneNum" autocomplete="off">
				</el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="dialogFormVisible=false">取消</el-button>
				<el-button type="primary" @click="addTeacher()">确定</el-button>
			</span>
		</template>
	</el-dialog>
	
	
	<div id="container">
		<div id="search">
			<el-form :inline="true">
				<el-form-item>
					<el-input v-model="teacher.loginName" style="width: 160px;" placeholder="教师姓名" clearable=""></el-input>					
				</el-form-item>
				<el-form-item>
					<el-select v-model="teacher.courseId" placeholder="请选择课程">
						<el-option v-for="item in course"
						:key="item.courseName"
						:label="item.courseName"
						:value="item.courseId"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item>
					<el-input v-model="teacher.phoneNum" placeholder="请选择手机号" autocomplete="off">
					</el-input>
				</el-form-item>
				<el-form-item>
				            <el-button  @click="submitSearch()">搜索</el-button>
				            <el-button  @click="clearForm()">清空</el-button>
				        </el-form-item>
			</el-form>
		</div>
		<div id="addTeacher">
			<el-button type="primary" @click="dialogFormVisible=true">添加老师</el-button>
		</div>
	<div id="show_table">
		<el-table :data="teacher">
			<el-table-column label="教师名称" prop="loginName">
			</el-table-column>
			<el-table-column label="授课名称" prop="course.courseName">
			</el-table-column>
			<el-table-column label="联系方式" prop="phoneNum">
			</el-table-column>
			<el-table-column fixed="right" label="操作">
				<template #default="scope">
					<el-button @click="handleClick(scope.row)" type="primary"
					icon="el-icon-edit" size="small">编辑</el-button>
					<el-button type="danger"
					icon="el-icon-delete" size="small"
					@click="deleteTeacher(scope.row.teacherId)">删除</el-button>
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
	export default{
		name:'Teacher',
		data(){
			return{
				teacher:[],
				course:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4,
				dialogFormVisible:false,
				dialogFormUpdate:false,
				form:{
					teacherId:'',
					loginName:'',
					password:'',
					type:'',
					courseId:'',
					phoneNum:'',
					
				},
				formLabelWidth: '120px',
			}
		},
		methods:{
			submitSearch(){
				this.pageNum=1;
				this.init();
				console.log(this.form);
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
			handleClick(row) {
					console.log(row);
					this.dialogFormUpdate=true;
					this.form=row;
			},
			updateTeacher(){
				this.teacher.pageNum=this.pageNum;
				this.teacher.pageSize=this.pageSize;
				var postData = this.$qs.stringify({
					teacherId:this.form.teacherId,
					loginName:this.form.loginName,
					password:this.form.password,
					type:this.form.type,
					courseId:this.form.courseId,
					phoneNum:this.form.phoneNum
				});
				console.log(postData);
				this.$axios
				.post("teacher/updateTeacher",postData)
				.then(rst=>{
					this.teacher.pageNum=this.pageNum;
					this.teacher.pageSize=this.pageSize;
					var param=this.$qs.stringify(this.teacher);
					this.$axios
					.post("teacher/findAllTeacher",param)
					.then(rst=>{
						console.log(rst);
						this.teacher=rst.data.list;
						this.pageTotal=rst.data.total;
					}).catch(ex1=>{
						console.log(ex1);
					});
				}).catch(ex=>{
					console.log(ex);
				});
				this.dialogFormUpdate=false;
			},
			init(){
				this.teacher.pageNum=this.pageNum;
				this.teacher.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.teacher);
				this.$axios
				.post("teacher/findAllTeacher",param)
				.then(rst=>{
					this.teacher=rst.data.list;
					this.pageTotal=rst.data.total;
				}).catch(ex=>{
					console.log(ex);
				});
			},
			pageData(curr){
				this.pageNum=curr;
				this.init();
			},
			deleteTeacher(row){
				this.$confirm('删除操作，是否确定?','提示',{
					confirmButtonText:'确定',
					cancelButtonText:'取消',
					type:'warning'
				}).then(()=>{
					console.log(row);
					var postData=this.$qs.stringify({
						teacherId:row
					});
					this.$axios
					.post("teacher/deleteTeacher",postData)
					.then(rst=>{
						this.teacher.pageNum=this.pageNum;
						this.teacher.pageSize=this.pageSize;
						var param = this.$qs.stringify(this.teacher);
						this.$axios
						.post("teacher/findAllTeacher",param)
						.then(rst=>{
							console.log(rst);
							this.teacher=rst.data.list;
							this.pageTotal=rst.data.pageTotal;
						}).catch(ex1=>{
							console.log(ex1);
						});
					})
					.catch(ex=>{
						console.log(ex);
					})
					this.$message({
						type:'success',
						message:'删除成功！'
					});
				}).catch(()=>{
					this.$message({
						type:'info',
						message:'已取消删除'
					});
				});
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
			clearForm(){
				this.form={
					teacherId:'',
					loginName:'',
					password:'',
					type:'',
					courseId:'',
					phoneNum:'',
				};
				this.init();
			},
			addTeacher(){
				var postData = this.$qs.stringify({
					teacherid:this.form.teacherId,
					loginName:this.form.loginName,
					password:this.form.password,
					type:'0',
					courseId:this.form.courseId,
					phoneNum:this.form.phoneNum
				});
				this.$axios
				.post("teacher/addTeacher",postData)
				.then(rst=>{
					this.teacher.pageNum=this.pageNum;
					this.teacher.pageSize=this.pageSize;
					var param = this.$qs.stringify(this.teacher);
					this.$axios
					.post("teacher/findAllTeacher",param)
					.then(rst=>{
						console.log(rst);
						this.teacher=rst.data.list;
						this.pageTotal=rst.data.total;
					}).catch(ex1=>{
						console.log(ex1);
					});
				}).catch(ex=>{
					console.log(ex);
				})
				this.dialogFormVisible=false
			},
		},
		
		created(){
			this.findCourseName();
			this.teacher.pageNum=this.pageNum;
			this.teacher.pageSize=this.pageSize;
			var param=this.$qs.stringify(this.teacher);
			this.$axios
			.post("teacher/findAllTeacher",param)
			.then(rst=>{
				console.log(rst);
				this.teacher=rst.data.list;
				this.pageTotal=rst.data.total;
			}).catch(ex=>{
				console.log(ex);
			});
		}
	}
</script>

<style>
</style>
