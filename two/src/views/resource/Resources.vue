<template>
	<el-dialog
	  title="编辑"
	  v-model="dialogEditResource">
			<el-form :model="form" >
			<el-form-item label="作者名称" >
			<el-select v-model="form.teacherId" placeholder="请选择标签">
				<el-option v-for="item in teacher" 
				:key="item.loginName" 
				:label="item.loginName" 
				:value="item.teacherId"
				>
			</el-option>
			</el-select>
			</el-form-item>
			<el-form-item label="内容">
				<el-input v-model="form.resource" autocomplete="off"></el-input>
			</el-form-item>
			</el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogEditResource = false,clearForm()">取 消</el-button>
	      <el-button type="primary" @click="EditResource()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<el-dialog
	  title="添加"
	  v-model="dialogAddResource">
			<el-form :model="form" >
			<el-form-item label="上传教师" >
			<el-select v-model="form.teacherId" placeholder="请选择标签">
				<el-option v-for="item in teacher" 
				:key="item.loginName" 
				:label="item.loginName" 
				:value="item.teacherId"
				>
			</el-option>
			</el-select>
			</el-form-item>
			<el-form-item label="资源内容">
			<el-input v-model="form.resource" autocomplete="off"></el-input>
			</el-form-item>
			</el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogAddResource = false">取 消</el-button>
	      <el-button type="primary" @click="addResource()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<div id="container">
		<div id="search">
			<el-form :inline="true">
				<el-form-item>
					<el-select v-model="resources.teacherId" placeholder="请选择查询教师">
						<el-option v-for="item in teacher" 
						:key="item.loginName" 
						:label="item.loginName" 
						:value="item.teacherId"
						>
					</el-option>
					</el-select>
				</el-form-item>
				<el-form-item>
					<el-input v-model="resources.resource" style="width: 160px;" placeholder="资源内容" clearable=""></el-input>					
				</el-form-item>
				<el-form-item>
				        <el-button  @click="submitSearch()">搜索</el-button>
				        <el-button  @click="clearForm()">清空</el-button>
				</el-form-item>
			</el-form>
		</div>
		<div id="addResource">
			<el-button type="primary" @click="dialogAddResource = true">添加资源</el-button>
		</div>
	<div id="show_table">
			<el-table :data="resources">
				<el-table-column label="作者名称" prop="teacher.loginName">
				</el-table-column>
				<el-table-column label="上传日期" prop="date">
				</el-table-column>
				<el-table-column label="内容" prop="resource">
				</el-table-column>
				<el-table-column
				      fixed="right"
				      label="操作"
					  >
				      <template #default="scope">
				        <el-button @click="handleClick(scope.row)" type="primary" 
						icon="el-icon-edit" size="small">编辑</el-button>
				        <el-button type="danger" 
						icon="el-icon-delete" size="small"
						@click="deleteResource(scope.row.resourceId)">删除</el-button>
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
		name:'Resources',
		data(){
			return{
				resources:[],
				teacher:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4,
				form:{
					resourceId:'',
					teacherId:'',
					date:'',
					resource:''
				},
				dialogEditResource:false,
				dialogAddResource:false,
			}
		},
		methods:{
			submitSearch(){
				this.pageNum=1;
				this.init();
			},
			clearForm(){
				this.form={
					resourceId:'',
					teacherId:'',
					date:'',
					resource:''
				};
				this.init();
			},
			EditResource(){
				var postData = this.$qs.stringify({
					resourceId:this.form.resourceId,
					teacherId:this.form.teacherId,
					date:this.form.date,
					resource:this.form.resource
				});
				this.$axios
				.post("resource/updateResource",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(er=>{
					console.log(er);
				});
				this.dialogEditResource=false;
			},
			handleClick(row){
				this.dialogEditResource=true;
				this.form=row;
			},
			findTeacherName(){
				this.$axios
				.post("resource/findAllTeacher")
				.then(rst=>{
					this.teacher=rst.data;
					console.log(rst.data);
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
				this.resources.pageNum=this.pageNum;
				this.resources.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.resources);
				this.$axios
				.post("resource/findAllResources",param)
				.then(rst=>{
					console.log(rst);
					this.resources=rst.data.list;
					this.pageTotal=rst.data.total;
				}).catch(ex=>{
					console.log(ex);
				});
			},
			pageData(curr){
				this.pageNum=curr;
				this.init();
			},
			addResource(){
				let str = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
				console.log(str);
				var postData = this.$qs.stringify({
					teacherId:this.form.teacherId,
					date:str,
					resource:this.form.resource
				});
				this.$axios
				.post("resource/addResource",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(er=>{
					console.log(er);
				});
				this.dialogAddResource=false;
			},
			deleteResource(row){
				this.$confirm('删除操作, 是否继续?', '提示', {
				         confirmButtonText: '确定',
				         cancelButtonText: '取消',
				         type: 'warning'
				       }).then(() => {
											var postData=this.$qs.stringify({
												resourceId:row
											});
											this.$axios
											.post("resource/deleteResource",postData)
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
		},
		created() {
			this.findTeacherName();
			this.resources.pageNum=this.pageNum;
			this.resources.pageSize=this.pageSize;
			var param=this.$qs.stringify(this.resources);
			this.$axios
			.post("resource/findAllResources",param)
			.then(rst=>{
				console.log(rst);
				this.resources=rst.data.list;
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
