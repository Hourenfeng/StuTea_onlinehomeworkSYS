<template>
	<el-dialog
	  title="添加"
	  v-model="dialogAddResource">
			<el-form :model="form" >
			<el-form-item label="资源内容">
			<el-input v-model="form.resource" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="上传文件">
				<el-upload
				  class="upload-demo"
				  :action="objData.host"
				  :file-list="fileList"
				  :before-upload="ossPolicy"
				  :on-exceed="handleExceed"
				  :data="objData"
				  list-type="picture"
				   multiple
				   drag>
				<div class="el-upload__text"><em style="margin-right: 10px">选择文件</em>或者将文件拖入框内</div>
				  <div slot="tip" class="el-upload__tip">提示：支持上传文件格式：doc，docx，ppt，pptx，xls，xlsx，pdf，单个文件大小在50M以内。</div>
				</el-upload>
			</el-form-item>
			</el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogAddResource = false,clearForm()">取 消</el-button>
	      <el-button type="primary" @click="addResource()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<div id="container">
		<el-container>
			<el-main>
		<div id="search">
			<el-form :inline="true">
				<el-form-item>
					<el-input v-model="resources.resource" style="width: 160px;" placeholder="资源内容" clearable=""></el-input>					
				</el-form-item>
				<el-form-item>
				        <el-button  @click="submitSearch()">搜索</el-button>
				        <el-button  @click="clearForm()">清空</el-button>
				</el-form-item>
				<el-form-item  style="float:right">
					<el-button type="primary" class="el-icon-search"
					@click="dialogAddResource=true">
					  上传资源
					</el-button>
				</el-form-item>
			</el-form>
		</div>
					<el-table :data="resources">
						<el-table-column label="上传日期" prop="date">
						</el-table-column>
						<el-table-column label="内容" prop="resource">
						</el-table-column>
						<el-table-column label="学科" prop="course.courseName">
						</el-table-column>
						<el-table-column
								fixed="right"
							      label="操作"
								  >
							<template #default="scope">
								<el-button size="small"  @click="showFile(scope.row.url)">
									预览
								</el-button>
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
							:page-sizes="[4,6,8,10]"
							:page-size="pageSize"
							@size-change="handleSizeChange"
							@current-change="handleCurrentChange"
							:current-change="pageNum"
							>
					</el-pagination>
					</div>
					
				</el-main>
		</el-container>
	</div>
</template>

<script>
	import pdf from "vue-pdf";
	import moment from "moment";
	export default{
		components:{pdf},
		name:'Tea_Resources',
		data() {
			return{
				resources:[],
				teacher:[],
				url:'',
				pageNum:1,
				pageTotal:0,
				pageSize:10,				
				form:{
					resourceId:'',
					date:'',
					resource:''
				},
				fileName:'',
				Resource:{
				filename:'',
				},
				objData:{
					OSSAccessKeyId:'',
					policy:'',
					Signature:'',
					key:'',
					host:'',
					dir:''
				},
				dialogEditResource:false,
				dialogAddResource:false,
			}
		},
		methods:{
			handleExceed(e) {
			      // 判断是否只能上传一个文件，超过提示报错
			      console.log(e);
			      this.$notify.error({
			        title: "错误",
			        message: "只能上传一个文件哦",
			      });
			    },
			showFile(row){
				console.log(row);
				let routeUrl = this.$router.resolve({
				
				path: "/filePreview",
				
				query: {
					url:row
				}
				
				});
				
				window.open(routeUrl.href, '_blank');
			},
		ossPolicy(file){
			return new Promise((resolve,reject)=>{
				this.$axios
				.post("oss/policy")
				.then(rst=>{
					this.objData.OSSAccessKeyId=rst.data.accessid;
					this.objData.policy=rst.data.policy;
					this.objData.Signature=rst.data.signature;
					this.objData.dir=rst.data.dir;
					this.objData.host=rst.data.host;
					this.objData.key=rst.data.dir+"${filename}";
					console.log(this.objData.host+'/'+this.objData.dir+encodeURIComponent(file.name));
					this.fileName=file.name;
					resolve(true);
				})
				.catch(ex=>{
					console.log(ex);
					reject(false);
				})
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
			addResource(){
				let str = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
				console.log(str);
				var postData = this.$qs.stringify({
					teacherId:this.teacher.teacherId,
					date:str,
					resource:this.form.resource,
					url:this.objData.host+'/'+this.objData.dir+encodeURIComponent(this.fileName)
					
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
			submitSearch(){
				this.pageNum=1;
				this.init();
			},
			clearForm(){
				this.form={
					resourceId:'',
					date:'',
					resource:''
				};
				this.init();
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
			init(){
				this.resources.teacherId=this.teacher.teacherId;
				this.resources.pageNum=this.pageNum;
				this.resources.pageSize=this.pageSize;
				var param = this.$qs.stringify(this.resources)
				this.$axios
				.post("resource/findAllResources",param)
				.then(rst=>{
					this.resources=rst.data.list;
					this.pageTotal=rst.data.total;
					console.log(rst.data.list);
				})
				.catch(ex=>{
					console.log(ex);
				})
			}
		},
		created() {
			this.teacher=JSON.parse(sessionStorage.getItem("teacher"));
			this.init();
		}
	}
</script>

<style>
	
</style>
