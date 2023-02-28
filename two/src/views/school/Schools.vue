<template>
	<el-dialog
	  title="编辑"
	  v-model="dialogEditSchool">
			<el-form :model="form" >
			<el-form-item label="学校名称">
				<el-input v-model="form.schoolName" autocomplete="off"></el-input>
			</el-form-item>
			</el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogEditSchool = false,clearForm()">取 消</el-button>
	      <el-button type="primary" @click="EditSchool()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<el-dialog
	  title="添加"
	  v-model="dialogAddSchool">
			<el-form :model="form" >
			<el-form-item label="学校名称" >
			<el-input v-model="form.schoolName" autocomplete="off"></el-input>
			</el-form-item>	
			</el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogAddSchool = false">取 消</el-button>
	      <el-button type="primary" @click="addSchool()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<div id="container">
		<div id="search">
			<el-form :inline="true" ref="search_form">
				<el-form-item>
					<el-input v-model="schools.schoolName" placeholder="请输入学校名称"></el-input>
				</el-form-item>
				<el-form-item>
				            <el-button  @click="submitSearch()">查询</el-button>
				            <el-button  @click="clearForm()">清空</el-button>
				</el-form-item>
			</el-form>
		</div>
		<div id="addUser">
			<el-button type="primary" @click="dialogAddSchool = true">添加学校</el-button>
		</div>
		<div id="show_table">
			<el-table :data="schools">
				<el-table-column label="序号"
				prop="schoolId"></el-table-column>
				<el-table-column label="学校名称"
				prop="schoolName"></el-table-column>
				<el-table-column
				      fixed="right"
				      label="操作"
					  >
				      <template #default="scope">
				        <el-button @click="handleClick(scope.row)" type="primary" 
						icon="el-icon-edit" size="small">编辑</el-button>
				        <el-button type="danger" 
						icon="el-icon-delete" size="small"
						@click="deleteSchool(scope.row.schoolId)">删除</el-button>
				      </template>
				    </el-table-column>
			</el-table>
		</div>
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
</template>

<script>
	export default
	{
		name:'Schools',
		data(){
			return{
				schools:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4,
				form:{
					schoolId:'',
					schoolName:''
				},
				dialogAddSchool:false,
				dialogEditSchool:false
			}
		},
		methods:{
			submitSearch(){
				this.pageNum=1;
				this.init();
			},
			handleClick(row){
				this.dialogEditSchool=true;
				this.form=row;
			},
			clearForm(){
				this.form={
					schoolId:'',
					schoolName:''
				};
				this.init();
			},
			EditSchool(){
				var postData = this.$qs.stringify({
					schoolId:this.form.schoolId,
					schoolName:this.form.schoolName
				});
				this.$axios
				.post("school/updateSchool",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(er=>{
					console.log(er);
				});
				this.dialogEditSchool=false;
			},
			addSchool()
			{
				var postData = this.$qs.stringify({
					schoolName:this.form.schoolName
				});
				this.$axios
				.post("school/addSchool",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(er=>{
					console.log(er);
				});
				this.dialogAddSchool=false;
			},
			deleteSchool(row)
			{
				this.$confirm('删除操作, 是否继续?', '提示', {
				         confirmButtonText: '确定',
				         cancelButtonText: '取消',
				         type: 'warning'
				       }).then(() => {
											var postData=this.$qs.stringify({
												schoolId:row
											});
											this.$axios
											.post("school/deleteSchool",postData)
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
				this.schools.pageNum=this.pageNum;
				this.schools.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.schools);
				this.$axios
				.post("school/findAllSchools",param)
				.then(rst=>{
					console.log(rst);
					this.schools=rst.data.list;
					this.pageTotal=rst.data.total;
				}).catch(ex=>{
					console.log(ex);
				});
			}
		},
		created() {
			this.schools.pageNum=this.pageNum;
			this.schools.pageSize=this.pageSize;
			var param=this.$qs.stringify(this.schools);
			this.$axios
			.post("school/findAllSchools",param)
			.then(rst=>{
				console.log(rst);
				this.schools=rst.data.list;
				this.pageTotal=rst.data.total;
			}).catch(ex=>{
				console.log(ex);
			});
		}
	}
</script>

<style>
</style>
