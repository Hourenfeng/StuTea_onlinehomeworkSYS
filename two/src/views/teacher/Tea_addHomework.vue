<template>
		<el-container>
			<el-main>
				<el-page-header @back="goBack"  title="返回作业列表">
					</el-page-header>
				<el-form :model="form" style="width:1200px">
					<el-form-item label="作业标题" >
						<el-input v-model="form.title"></el-input>
					</el-form-item>
					<el-form-item label="作业内容" >
						<editor  v-model="form.content" :init="editorInit" v-bind="$attrs" v-on="inputListeners">
						</editor>
					</el-form-item>
					<el-form-item label="上传答案">
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
					</el-form-item>
					</el-form>
					
					<div style="padding: 10px">
					    <el-button type="primary" @click="addThomework()"
					    >发 布</el-button>
					</div>
			</el-main>
		</el-container>
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
	
	export default{
		components:{Editor},
		data() {
			return {
				fileList: [],
				imageList:[],
				Addfrom:{
				  url:''
				},
				objData:{
					OSSAccessKeyId:'',
					policy:'',
					Signature:'',
					key:'',
					host:'',
					dir:''
				},
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
				fileName:'',
				picUrl:'',
				teacher:[],
				form:{
					homeworkId:'',
					teacherId:'',
					date:'',
					title:'',
					content:'',
					answer:''
				},
			}
		},
		mounted() {
		    tinymce.init({});
		},
		created() {
			this.teacher=JSON.parse(sessionStorage.getItem("teacher"));
		},
		methods:{
			goBack(){
				this.$router.go(-1);
			},
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
			addThomework(){
				this.uploadURL();
				let imageurl = this.imageList.join(",");
				console.log(imageurl);
				let str = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
				console.log(str);
				var postData = this.$qs.stringify({
					teacherId:this.teacher.teacherId,
					date:str,
					title:this.form.title,
					content:this.form.content,
					answer:imageurl
				});
				this.$axios
				.post("teahomework/addTeaHomework",postData)
				.then(rst=>{
					if(rst.status==200)
					{
						this.$message.success("发布成功！")
					}
				})
				.catch(er=>{
					console.log(er);
				});
			},
		}
	}
</script>

<style>
</style>
