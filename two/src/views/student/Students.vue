<template>
	<el-dialog title="添加学生" v-model="dialogFormVisible">
			<el-form :model="form">
				<el-form-item label="学生姓名">
					<el-input v-model="form.loginName" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="学生密码">
					<el-input v-model="form.password" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="学生班级">
					<el-select v-model="form.clazzId" placeholder="请选择班级">
						<el-option v-for="item in clazz"
						:key="item.clazzName"
						:label="item.clazzName"
						:value="item.clazzId"></el-option>
					</el-select>
				</el-form-item>
			</el-form>
			<template #footer>
				<span  class="dialog-footer">
					<el-button @click="dialogFormVisible=false">取消</el-button>
					<el-button type="primary" @click="addStudent()">确定</el-button>
				</span>
			</template>
		</el-dialog>
	<el-dialog title="编辑" v-model="dialogFormUpdate">
		<el-form :model="form" >
			<el-input  v-model="form.studentId" type="hidden"/>
			<el-form-item label="学生姓名">
				<el-input v-model="form.loginName" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="学生密码">
				<el-input v-model="form.password" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="学生班级">
				<el-select v-model="form.clazzId" placeholder="请选择班级">
					<el-option v-for="item in clazz"
					:key="item.clazzName"
					:label="item.clazzName"
					:value="item.clazzId"></el-option>
				</el-select>
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="dialogFormUpdate = false,clearForm()">取消</el-button>
				<el-button type="primary" @click="updateStudent()">确认</el-button>
			</span>
		</template>
	</el-dialog>
<div id="container">
	<div id="search">
		<el-form :inline="true">
			<el-form-item>
				<el-input v-model="students.loginName" style="width: 160px;" placeholder="学生姓名" clearable=""></el-input>					
			</el-form-item>
			<el-form-item>
				<el-select v-model="students.clazzId" placeholder="请选择班级">
					<el-option v-for="item in clazz"
					:key="item.clazzName"
					:label="item.clazzName"
					:value="item.clazzId"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-input v-model="students.phoneNum" style="width: 160px;" placeholder="学生手机号" clearable=""></el-input>					
			</el-form-item>
			<el-form-item>
			            <el-button  @click="submitSearch()">搜索</el-button>
			            <el-button  @click="clearForm()">清空</el-button>
			        </el-form-item>
		</el-form>
	</div>
	<div id="addStudent">
		<el-button type="primary" @click="dialogFormVisible = true">添加学生</el-button>
	</div>
	<div id="show_table">
		<el-table :data="students">
			<el-table-column label="姓名"
			prop="loginName"></el-table-column>
						<el-table-column label="手机号"
						prop="phoneNum"></el-table-column>
			<el-table-column label="班级"
			prop="clazz.clazzName"></el-table-column>
			<el-table-column
			      fixed="right"
			      label="操作"
				  >
			      <template #default="scope">
			        <el-button @click="handleClick(scope.row)" type="primary" 
					icon="el-icon-edit" size="small">编辑</el-button>
			        <el-button type="danger" 
					icon="el-icon-delete" size="small"
					@click="deleteStudent(scope.row.studentId)">删除</el-button>
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
		name:'Students',
		data(){
			return{
				students:[],
				clazz:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4,
				dialogFormVisible:false,
				dialogFormUpdate:false,
				form:{
					phoneNum:'',
					studentId:'',
					loginName:'',
					password:'',
					clazzId:'',
				},
				formLabelWidth: '120px',
			}
		},
		methods:{
			submitSearch(){
				this.pageNum=1;
				this.init();
			},
			handleClick(row){
				this.dialogFormUpdate=true;
				this.form=row;
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
			deleteStudent(row)
			{
				this.$confirm('删除操作, 是否继续?', '提示', {
				         confirmButtonText: '确定',
				         cancelButtonText: '取消',
				         type: 'warning'
				       }).then(() => {
											var postData=this.$qs.stringify({
												studentId:row
											});
											this.$axios
											.post("student/deleteStudent",postData)
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
			findAllClazz(){
				this.$axios
				.post("student/findAllClazz")
				.then(rst=>{
					this.clazz=rst.data;
					console.log(rst.data);
				})
				.catch(ex=>{
					console.log(ex);
				})
			},
			updateStudent(){
				var postData = this.$qs.stringify({
					studentId:this.form.studentId,
					loginName:this.form.loginName,
					password:this.form.password,
					clazzId:this.form.clazzId
				});
				this.$axios
				.post("student/updateStudent",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(ex=>{
					console.log(ex);
				});
				this.dialogFormUpdate=false;
			},
			addStudent(){
				var postData = this.$qs.stringify({
					loginName:this.form.loginName,
					password:this.form.password,
					clazzId:this.form.clazzId
				});
				this.$axios
				.post("student/addStudent",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(ex=>{
					console.log(ex);
				})
				this.dialogFormVisible=false;
			},
			clearForm(){
				this.form={
					studentId:'',
					loginName:'',
					password:'',
					clazzId:'',
				};
				this.init();
			},
			init(){
				this.students.pageNum=this.pageNum;
				this.students.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.students);
				this.$axios
				.post("student/findAllStudents",param)
				.then(rst=>{
					console.log(rst);
					this.students=rst.data.list;
					this.pageTotal=rst.data.total;
				}).catch(ex=>{
					console.log(ex);
				});
			},
		},
		created() {
			this.findAllClazz();
			this.students.pageNum=this.pageNum;
			this.students.pageSize=this.pageSize;
			var param=this.$qs.stringify(this.students);
			this.$axios
			.post("student/findAllStudents",param)
			.then(rst=>{
				console.log(rst);
				this.students=rst.data.list;
				this.pageTotal=rst.data.total;
			}).catch(ex=>{
				console.log(ex);
			});
		}
	}
</script>

<style>
	#container{
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
	}
</style>
