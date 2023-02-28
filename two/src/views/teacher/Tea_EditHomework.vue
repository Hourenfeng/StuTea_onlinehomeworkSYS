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

					</el-form>
					
					<div style="padding: 10px">
					    <el-button type="primary" @click="EditThomework()"
					    >修 改</el-button>
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
				thomeworkId:'',
				thomework:'',
				form:{
					homeworkId:'',
					teacherId:'',
					date:'',
					title:'',
					content:''					
				},
			}
		},
		mounted() {
		    tinymce.init({});
		},
		
		created() {
			this.teacher=JSON.parse(sessionStorage.getItem("teacher"));
			this.thomework=JSON.parse(sessionStorage.getItem("thomework"));
			this.form.homeworkId=this.thomework.homeworkId;
			this.form.teacherId=this.thomework.teacherId;
			this.form.title=this.thomework.title;
			this.form.content=this.thomework.content;
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
			EditThomework(){
				let str = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
				console.log(str);
				console.log(this.form.content);
				var postData = this.$qs.stringify({
					homeworkId:this.form.homeworkId,
					teacherId:this.form.teacherId,
					date:str,
					title:this.form.title,
					content:this.form.content
				});
				this.$axios
				.post("teahomework/updateTeaHomework",postData)
				.then(rst=>{
					if(rst.status==200)
					{
						this.$message.success("修改成功！")
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
