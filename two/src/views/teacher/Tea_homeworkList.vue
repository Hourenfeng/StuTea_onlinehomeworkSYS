<template>
	<div id="container">
		<template v-if="this.$router.currentRoute.value.path=='/teahklist'">
			<el-container>
				<el-main>
					<div id="search">
						<el-form :inline="true">
							<el-form-item>
								<el-input v-model="thomeworks.title" style="width: 160px;" placeholder="作业标题" clearable=""></el-input>					
							</el-form-item>
							<el-form-item>
							            <el-button  @click="submitSearch()">搜索</el-button>
							            <el-button  @click="clearForm()">清空</el-button>
							</el-form-item>
							<el-form-item  style="float:right">
								<el-button type="primary" class="el-icon-search"
								@click="addThomework()">
								  发布作业
								</el-button>
							</el-form-item>
						</el-form>
					</div>
					<el-table :data="thomeworks">
						<el-table-column prop="date" label="发布时间"></el-table-column>
						<el-table-column  prop="title" label="作业标题">
							
						</el-table-column>
						<el-table-column>
							<template #default="scope">
								<el-button @click="lookHKList(scope.row)">查看作业提交情况</el-button>
							</template>
						</el-table-column>
						<el-table-column>
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
		</template>
		<template v-else>
			<router-view/>
		</template>
	</div>
</template>

<script>
	
	import tinymce from 'tinymce/tinymce'
	import Editor from '@tinymce/tinymce-vue'
    import 'tinymce/themes/silver/theme'
    import 'tinymce/icons/default/icons'
    import 'tinymce/themes/silver'
    import 'tinymce/plugins/image'
    import 'tinymce/plugins/link'
    import 'tinymce/plugins/code'
    import 'tinymce/plugins/table'
    import 'tinymce/plugins/lists'
    import 'tinymce/plugins/wordcount'
	import {client} from '@/router/oss.js'
	import moment from "moment";
	import TeacherHeader from '@/views/common/TeacherHeader';
	export default{
		name:'Tea_homeworkList',
		components:{TeacherHeader,Editor},
		data() {
			return{
				editorInit:{
				    language_url: '/tinymce/lang/zh-Hans.js',
				    language: 'zh-Hans',
				    skin_url: '/tinymce/skins/ui/oxide',
				    height: 450,
				    width:1000,
					 image_title: false,
				    plugins: 'link lists image code table wordcount contextmenu',
				    toolbar:
				        'bold italic underline strikethrough | fontsizeselect | forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist | outdent indent blockquote | undo redo | link unlink image code | removeformat',
				    branding: false,
					menubar: true,
					images_upload_base_path: this.picUrl,
					        // 图片本地上传方法  点击上传后执行的事件
					images_upload_handler: (blobInfo, success, failure) => {
					          var filename = blobInfo.filename()
					          var index = filename.lastIndexOf('.')
					          var suffix = filename.substring(index + 1, filename.length)
							  console.log(filename)
							  console.log(index)
							  console.log(suffix)
					          // 重构文件名 加上时间序列防止重名
					          filename =  "zjy123456" + '/' +  this.getTime() + '/'+ this.getTime_second() + '.' + suffix
					          client.multipartUpload(filename, blobInfo.blob()).then(function (result) {
					            if (result.res.requestUrls) {
					              console.log('返回结果', result)
					              success(result.res.requestUrls[0].split('?')[0])
					            }
					          }).catch(function (err) {
					            console.log(err)
					          })
					        }
				},
				picUrl:'',
				teacher:[],
				thomeworks:[],
				thomework:[],
				pageNum:1,
				pageTotal:0,
				pageSize:10,				
				form:{
					homeworkId:'',
					teacherId:'',
					date:'',
					title:'',
					content:''					
				},
				homework:{
					stuUploadId:'',
					homeworkId:'',
					studentId:'',
					date:'',
					courseId:'',
					content:'',
					student:'',
					course:'',
				},
				dialogEditThomework:false,
				dialogAddThomework:false,
			}
		},
		mounted() {
		    tinymce.init({});
		},

		methods:{
			getTime_second () {
				//给文件另起名
			      var Time = new Date()
			      var year = Time.getFullYear()
			      var month = Time.getMonth() + 1
			      var date = Time.getDate()
			      var hours = Time.getHours()
			      var minute = Time.getMinutes()
			      var seconds = Time.getSeconds()
			      var time = year + month + date + hours + minute + seconds
			      return time
			    },
			    getTime () {
			      var Time = new Date()
			      var year = Time.getFullYear()
			      var month = Time.getMonth() + 1
			      var date = Time.getDate()
				  if (month < 10) {
				        month = "0" + month;
				      }
			      var time = year + '-' +month +'-'+ date 
			      return time
			    },
			submitSearch(){
				this.pageNum=1;
				this.initz();
			},
			clearForm(){
				this.thomeworks.content=null;
				this.form={
					homeworkId:'',
					teacherId:'',
					date:'',
					content:''
				};
				this.initz();
			},
			handleClick(row){
				sessionStorage.setItem("thomework",JSON.stringify(row));
				this.$router.push("/teahklist/editHomework");
			},
			
			addThomework(){
				this.$router.push("/teahklist/addHomework");
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
												this.initz();
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
							this.initz();
						},
						handleSizeChange(val){
							console.log(`每页${val}条`);
							this.pageSize=val;
							this.initz();
						},			
			lookHKList(row)
			{
				sessionStorage.setItem("homework",JSON.stringify(row));
				this.$router.push("/teahklist/stuhkList");
			},
			initz(){
				this.thomeworks.teacherId=this.teacher.teacherId;
				this.thomeworks.pageNum=this.pageNum;
				this.thomeworks.pageSize=this.pageSize;
				var param = this.$qs.stringify(this.thomeworks)
				this.$axios
				.post("teahomework/findByTeacherId",param)
				.then(rst=>{
					this.thomeworks=rst.data.list;
					this.pageTotal=rst.data.total;
					console.log(rst.data.list);
				})
				.catch(ex=>{
					console.log(ex);
				})
			},

		},
		created() {
			this.teacher=JSON.parse(sessionStorage.getItem("teacher"));
			this.initz();
			console.log(this.teacher);
		},

	}
</script>

<style scoped>
	
</style>
