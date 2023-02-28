<template>
	<el-dialog title="编辑"
	v-model="dialogEditThomework">
	<el-form
	:model="form">
	<el-form-item label="作业内容" >
	<el-input v-model="form.content" autocomplete="off"></el-input>
	</el-form-item>
	</el-form>
		
		<template #footer>
		  <span class="dialog-footer">
		    <el-button @click="dialogEditThomework = false,clearForm()">取 消</el-button>
		    <el-button type="primary" @click="EditThomework()">确 定</el-button>
		  </span>
		</template>
	</el-dialog>
	<el-dialog
	  title="添加"
	  v-model="dialogAddThomework">
			<el-form :model="form" >
			<el-form-item label="老师姓名" >
			<el-select v-model="form.teacherId" placeholder="请选择标签">
				<el-option v-for="item in teacher" 
				:key="item.loginName" 
				:label="item.loginName" 
				:value="item.teacherId"
				>
			</el-option>
			</el-select>
			</el-form-item>
			<el-form-item label="作业内容" >
			<el-input v-model="form.content" autocomplete="off"></el-input>
			</el-form-item>
			</el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogAddThomework = false">取 消</el-button>
	      <el-button type="primary" @click="addThomework()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<div id="container">
		<div id="search">
			<el-form :inline="true">
				<el-form-item>
					<el-select v-model="thomeworks.teacherId" placeholder="请选择查询教师">
						<el-option v-for="item in teacher" 
						:key="item.loginName" 
						:label="item.loginName" 
						:value="item.teacherId"
						>
					</el-option>
					</el-select>
				</el-form-item>
				<el-form-item>
					<el-input v-model="thomeworks.content" style="width: 160px;" placeholder="上传作业内容" clearable=""></el-input>					
				</el-form-item>
				<el-form-item>
				        <el-button  @click="submitSearch()">搜索</el-button>
				        <el-button  @click="clearForm()">清空</el-button>
				</el-form-item>
			</el-form>
		</div>
		<div id="addHomework">
			<el-button type="primary" class="el-icon-search"
			@click="dialogAddThomework=true">
			  添加作业
			</el-button>
		</div>
		<div id="show_table">
			<el-table :data="thomeworks">
				<el-table-column label="老师姓名"
				prop="teacher.loginName"></el-table-column>
				<el-table-column label="日期"
				prop="date"></el-table-column>
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
						@click="deleteThomework(scope.row.homeworkId)">删除</el-button>
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
		name:'Thomeworks',
		data(){
			return{
				thomeworks:[],
				teacher:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4,
				form:{
					homeworkId:'',
					teacherId:'',
					date:'',
					content:''
				},
				dialogAddThomework:false,
				dialogEditThomework:false
			}
		},
		methods:{
			submitSearch(){
				this.pageNum=1;
				this.init();
			},
			handleClick(row){
				this.form=row;
				this.dialogEditThomework=true;
			},
			deleteThomework(row){
				this.$confirm('删除操作, 是否继续?', '提示', {
				         confirmButtonText: '确定',
				         cancelButtonText: '取消',
				         type: 'warning'
				       }).then(() => {
											var postData=this.$qs.stringify({
												homeworkId:row
											});
											this.$axios
											.post("teahomework/deleteTeaHomework",postData)
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
			EditThomework(){
				let str = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
				console.log(str);
				var postData = this.$qs.stringify({
					homeworkId:this.form.homeworkId,
					teacherId:this.form.teacherId,
					date:str,
					content:this.form.content
				});
				this.$axios
				.post("teahomework/updateTeaHomework",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(er=>{
					console.log(er);
				});
				this.dialogEditThomework=false;
			},
			addThomework(){
				let str = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
				console.log(str);
				var postData = this.$qs.stringify({
					teacherId:this.form.teacherId,
					date:str,
					content:this.form.content
				});
				this.$axios
				.post("teahomework/addTeaHomework",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(er=>{
					console.log(er);
				});
				this.dialogAddThomework=false;
			},
			findTeacherName(){
				this.$axios
				.post("teahomework/findAllTeacher")
				.then(rst=>{
					this.teacher=rst.data;
					console.log(rst.data);
				})
				.catch(ex=>{
					console.log(ex);
				})
			},
			clearForm(){
				this.thomeworks.content=null;
				this.form={
					homeworkId:'',
					teacherId:'',
					date:'',
					content:''
				};
				this.init();
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
				this.thomeworks.pageNum=this.pageNum;
				this.thomeworks.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.thomeworks);
				this.$axios
				.post("teahomework/findAllTeaHomeworks",param)
				.then(rst=>{
					console.log(rst);
					this.thomeworks=rst.data.list;
					this.pageTotal=rst.data.total;
				}).catch(ex=>{
					console.log(ex);
				});
			}
		},
		created() {
			this.findTeacherName();
			this.thomeworks.pageNum=this.pageNum;
			this.thomeworks.pageSize=this.pageSize;
			var param=this.$qs.stringify(this.thomeworks);
			this.$axios
			.post("teahomework/findAllTeaHomeworks",param)
			.then(rst=>{
				console.log(rst);
				this.thomeworks=rst.data.list;
				this.pageTotal=rst.data.total;
			}).catch(ex=>{
				console.log(ex);
			});
		}
	}
</script>

<style>
</style>
