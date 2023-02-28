<template>
	<div v-if="showDoc == true">
						<iframe frameborder="0" :src="'https://view.officeapps.live.com/op/view.aspx?src=' + this.fileUrl" width='100%'
						 style="height: 100vh;">
						</iframe>
					</div>
					<div v-else-if="showPdf == true" style="justify-content: center; align-items: center">
						<embed :src="pdfUrl" style="width: 100%; height: 100vh" />
					</div>
					<div v-else-if="showImg == true" style="justify-content: center; align-items: center;min-width: 40px;min-height: 40px;">
						<img :src="imagesUrl" alt="" style="width: 100%;height: auto;">
					</div>
</template>

<script>
	import pdf from "vue3-pdf"; //引入vue-pdf  显示服务器pdf文件
	
	export default {
		components: {
	    	pdf,
	  	},
	  	data() {
	    return {
					showDoc: false,
			  		showPdf: false,
			  		showImg: false,
		      		fileUrl: "",
		      		imagesUrl: "",
		      		pdfUrl: "",
					url:'https://zjy9823.oss-cn-qingdao.aliyuncs.com/zjy9823/2022-04-12/3w.jpg',
		    };
		 },
		 methods:{
			closePreviewClick() {
				if (this.showDoc == true) {
					this.showDoc = false
				} else if (this.showPdf == true) {
					this.showPdf = false
				} else if (this.showImg == true) {
					this.showImg = false
				}
			},
		},
		created() {
			this.url=this.$route.query.url;
			let type = this.url;
			console.log(this.url);
				this.fileUrl = this.url;
				if (type.indexOf("doc") != -1 || type.indexOf("docx") != -1 || type.indexOf("xsl") != -1 || type.indexOf("xlsx") !=
					-1 || type.indexOf("ppt") != -1) {
					this.fileWidth = '80%'
					this.showDoc = true
				} else if (type.indexOf("pdf") != -1) {
					this.fileWidth = '80%'
					this.pdfUrl = this.url;
					this.showPdf = true
				} else if (type.indexOf("jpg") != -1 || type.indexOf("png") != -1 || type.indexOf("jpeg") != -1) {
					this.showImg = true
					this.imagesUrl = this.url;
					let image = new Image();
					image.src = this.url;
					image.onload = () => {
						this.fileWidth = image.width + 'px'
					};
				}else {
					this.$message("当前文件暂不支持预览")
				}
		}
	  	
	}

</script>

<style>
</style>
