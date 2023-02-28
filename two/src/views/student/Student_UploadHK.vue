<template>
	<div id="container">
		<el-container>
			<template v-if="this.$router.currentRoute.value.path=='/studenthkList/studentUploadHK'">
				<el-main>
					<el-descriptions title="作业信息" border>
						<el-descriptions-item label="状态" >
						      <el-tag  v-if="this.submit==1" type="success" >已提交</el-tag>
							  <el-tag  v-else type="danger" >未提交</el-tag>
						    </el-descriptions-item>
					    <el-descriptions-item label="发布教师">{{this.thomework.teacher.loginName}}</el-descriptions-item>
					    <el-descriptions-item label="作业标题">{{this.thomework.title}}</el-descriptions-item>
					    <el-descriptions-item label="作业内容" >
							<template #default="scope">
								<dl v-html="this.thomework.content"></dl>
							</template>
						</el-descriptions-item>
						
					</el-descriptions>
					<el-divider >
						
					</el-divider>
					<template v-if="this.submit!=1">
						<el-upload class="avatar-uploader"
						  v-model="Addfrom.url" 
						  action=""
						  :file-list="fileList"
						  :auto-upload="false"
						  :multiple = true 
						  :before-upload="handleBeforeUpload" 
						  :limit="8"
						  drag>
						  <!-- <i v-else class="el-icon-plus avatar-uploader-icon" style="font-size:50px"></i> -->
						  <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
							<div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过4MB</div>
						</el-upload>

						<el-button type="primary" @click="upload()">确认上传</el-button>
						
					</template>
				</el-main>
			</template>
		</el-container>
	</div>
</template>

<script>
	import {client} from '@/router/oss.js'
		import moment from "moment";
	export default{
		name:'Student_UploadHk',
		data() {
			return{
				fileList: [],
				imageList:[],
				Addfrom:{
				  url:''
				},
				thomework:'',
				shomework:'',
				student:'',
				objData:{
					OSSAccessKeyId:'',
					policy:'',
					Signature:'',
					key:'',
					host:'',
					dir:''
				},
				fileName:'',
				submit:0
			}
		},
		created() {
			this.student=JSON.parse(sessionStorage.getItem("student"));
			this.thomework=JSON.parse(sessionStorage.getItem("thomework"));
			this.findSubmit();
		},
		methods:{
			getTime () {
			  var Time = new Date()
			  var year = Time.getFullYear()
			  var month = Time.getMonth() + 1
			  var date = Time.getDate()
			  if (month < 10) {
			        month = "0" + month;
			      }
				if (date < 10) {
				      month = "0" + month;
				    }  
			  var time = year + '-' +month +'-'+ date 
			  return time
			},
			handleBeforeUpload(file) {
				const isJPEG = file.name.split('.')[1] === 'jpeg';
				const isJPG = file.name.split('.')[1] === 'jpg';
				const isPNG = file.name.split('.')[1] === 'png';
				const isWEBP = file.name.split('.')[1] === 'webp';
				const isGIF = file.name.split('.')[1] === 'gif';
				const isLt500K = file.size / 1024 / 1024 / 1024 / 1024 < 4;
				if (!isJPG && !isJPEG && !isPNG && !isWEBP && !isGIF) {
					this.$message.error('上传图片只能是 JPEG/JPG/PNG 格式!');
				}
				if (!isLt500K) {
					this.$message.error('单张图片大小不能超过 4mb!');
				}
				return (isJPEG || isJPG || isPNG || isWEBP || isGIF) && isLt500K;
			},

			uploadURL() {
			for(var i =0;i<this.fileList.length;i++){
			  let fileName = "zjy123456" + '/' +
			  this.getTime()+ '/'+ 
			  this.fileList[i].raw.name ; //定义唯一的文件名
			  //定义唯一的文件名，打印出来的uid其实就是时间戳
			  
			  this.imageList.push('https://zjy123456.oss-cn-qingdao.aliyuncs.com/' + fileName)
			  client.multipartUpload(fileName, this.fileList[i].raw,{
			    progress: function(percentage, cpt) {
			      console.log('打印进度',percentage)
			    }
			  }).then((res)=>{
				  console.log('拼接')
			    //上传成功之后，回显操作，拼接桶名+域名+文件名
			    
			  })
			 
			}
		},
			// ossPolicy(file){
			// 	return new Promise((resolve,reject)=>{
			// 		this.$axios
			// 		.post("oss/policy")
			// 		.then(rst=>{
			// 			this.objData.OSSAccessKeyId=rst.data.accessid;
			// 			this.objData.policy=rst.data.policy;
			// 			this.objData.Signature=rst.data.signature;
			// 			this.objData.dir=rst.data.dir;
			// 			this.objData.host=rst.data.host;
			// 			this.objData.key=rst.data.dir+"${filename}";
			// 			this.fileName=file.name;
			// 			console.log(this.fileName);
			// 			resolve(true);
			// 		})
			// 		.catch(ex=>{
			// 			console.log(ex);
			// 			reject(false);
			// 		})
			// 	})
			// },
			findSubmit(){
					
				var param = this.$qs.stringify({
					studentId:this.student.studentId,
					homeworkId:this.thomework.homeworkId
				});
				this.$axios
				.post("/stuhomework/findSubmitByStudentId",param)
				.then(rst=>{
					this.submit=rst.data;
				})
				.catch(ex=>{
					console.log(ex);
				})
			},
			upload(){
				
				if(this.submit==1)
				{
					this.$message.error("已经提交");
				}
				else{
					this.uploadURL();
					console.log('看看是否在拼接前打印了');
					console.log(this.imageList);
					let imageurl = this.imageList.join(",");
					console.log(imageurl);
					let str = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
					var param = this.$qs.stringify({
						homeworkId:this.thomework.homeworkId,
						studentId:this.student.studentId,
						date:str,
						content:imageurl,
						courseId:this.thomework.course.courseId,
						submit:1,
					});
					this.$axios
					.post("stuhomework/addStuHomework",param)
					.then(rst=>{
						if(rst.status==200){
							console.log(rst);
							this.$message.success("提交成功");
							this.submit=1;
						}
					})
					.catch(er=>{
						console.log(er);
					});
				}
			}
		}
	}
</script>

<style scoped>
	.homeworkContent >>.el-form-item--default{
		text-align: center;
		font-family: 华文楷体;
		font-size: 30px;
	}

</style>
