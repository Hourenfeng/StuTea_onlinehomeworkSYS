<template>
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
	<el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
		<el-button @click="see">查看</el-button>
	<el-row>
		<el-col :span="3">
			
		</el-col>
		<el-col :span="18">
			<el-card v-for="(item,index) in imageList" :key="index">
				 <img :src="item" class="avatar" :alt="item">
			</el-card>
		</el-col>
	</el-row>
				

</template>
<script>	
	import moment from "moment";
  import {client} from '@/router/oss.js'
  export default {
    data() {
      return {
        fileList: [],
        imageList:[],
		
        Addfrom:{
          url:''
        }
      };
    },
    methods: {
			see(){

				//数组变字符串  存入数据库
				let permission = this.imageList.join(",");
				console.log(permission);
				//字符串变数组 取数据库url显示图片
				let b = permission.split(',');
				console.log(b);
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
      submitUpload() {
        this.uploadURL();
      },
      handleChange(file, fileList){
        this.fileList = fileList
        console.log(this.fileList);
      },

			uploadURL() {
        for(var i =0;i<this.fileList.length;i++){
          let fileName = "zjy123456" + '/' +
		  this.getTime()+ '/'+ 
		  this.fileList[i].raw.name ; //定义唯一的文件名
          //定义唯一的文件名，打印出来的uid其实就是时间戳
          client.multipartUpload(fileName, this.fileList[i].raw,{
            progress: function(percentage, cpt) {
              console.log('打印进度',percentage)
            }
          }).then((res)=>{
            console.log('这个是啥',res)
            //上传成功之后，回显操作，拼接桶名+域名+文件名
            this.imageList.push('https://zjy123456.oss-cn-qingdao.aliyuncs.com/' + fileName)
          })
         
        }
			},

    }
  }
</script>

<style >

</style>
