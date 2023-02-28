<template>
<el-upload
  class="upload-demo"
  :action="objData.host"
  :file-list="fileList"
  :before-upload="ossPolicy"
  :data="objData"
  list-type="picture"
  multiple
  drag>
  <el-button  type="primary">点击上传</el-button>
  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过4MB</div>
</el-upload>
</template>

<script>
  export default {
	  name:'UploadHomework',
    data() {
      return {
		  url:'',
		objData:{
			OSSAccessKeyId:'',
			policy:'',
			Signature:'',
			key:'',
			host:'',
			dir:''
		}
	  };
    },
    methods: {
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
					resolve(true);
				})
				.catch(ex=>{
					console.log(ex);
					reject(false);
				})
			})
		},
    }
  }
</script>

<style>
</style>
