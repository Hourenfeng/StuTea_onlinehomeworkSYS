<template>
	<div id="container">
		<el-container>
			<el-main>
				<el-page-header @back="goBack" content="详情页面" title="返回作业列表">
				</el-page-header>
				<div class="demo-image__preview">
					<el-descriptions title="作业信息"  >
						<el-descriptions-item label="状态:" >
						      <el-tag  v-if="homework.correct==1" type="success" >已批改</el-tag>
							  <el-tag  v-else type="danger" >未批改</el-tag>
						    </el-descriptions-item>			
					</el-descriptions>
				<el-form >
					<el-form-item>
						<template v-if="JSON.stringify(imageList)!='[]'">
							<el-card :body-style="{ padding: '0px' }">
								<div v-for="(item,index) in imageList" :key="index" style="text-align: center;width: 75%;height: 75%;display: inline-block;">
									 <img :src="item"  :alt="item" 
									 style="width: 100%;height: 100%;margin: 0 auto;"/>
								</div>
								<div style="text-align: center;width: 75%;height: 75%;display: inline-block;">
									<img :src="'data:image/jpeg;base64,'+base64" alt=""
									style="width: 100%;height: 100%;margin: 0 auto;">
								</div>
							</el-card>
						</template>
					</el-form-item>
				</el-form>
				<el-form>
					<template v-if="homework.correct==0">
						
						<el-form-item>
							<template #default="scope" v-if="this.homework.courseName=='英语'">
								<el-button @click="englishOrc(this.homework.content)" 
								v-if="this.homework.courseName=='英语'">英语作业自动批改</el-button>
							</template>
						</el-form-item>
							
						<el-form-item>
							<template #default="scope" v-if="this.homework.courseName=='数学'">
								<el-button @click="mathOrc(this.homework.content)">数学作业批改</el-button>
							</template>
						</el-form-item>
						<!-- <el-form-item>
							<el-button 
							@click="identifyImage(this.homework.content)">识图</el-button>
						</el-form-item> -->
						<el-form-item>
							<template #default="scope" v-if="this.teacher_homework.answer!=null">
								<el-button
								@click="answerComparison(this.homework.content)">自动批改</el-button>
							</template>
							
						</el-form-item>
						<el-form-item>
								<el-table v-if="JSON.stringify(keys)!='[]'" :data="keys" border
								>
									<el-table-column label="得分" prop="score"  width='200px'
									align="center">
									</el-table-column>
									<el-table-column label="评语" prop="advice"
									align="center">
									</el-table-column>
								</el-table>
						</el-form-item>
						<el-form-item v-if="JSON.stringify(text1)!='{}'" v-for="i in this.text1.line.length">
								 
									 <span v-for="k in this.text1.line[i-1].word.length" style="
									 text-align: center;display:inline-block
									 font-family: simsun;font-size: 16px;
									 ">
									 {{text1.line[i-1].word[k-1].content}}&#8194;
									 </span>
								 
									 
								 
						</el-form-item>
						<el-form-item>
							<el-table v-if="JSON.stringify(sentsFeedback)!='[]'" :data="sentsFeedback" border>
								<el-table-column label="原句" prop="rawSent"
								align="center">
								</el-table-column>
								<el-table-column label="修改后" prop="correctedSent"
								align="center">
								</el-table-column>
								<el-table-column label="错误原因" prop="sentFeedback"
								align="center">
								</el-table-column>
							</el-table>
						</el-form-item>
						<el-form-item>
							<el-button @click="pigai()">手动批改</el-button>
						</el-form-item>
						<el-form-item label="打分:">
							<el-input v-model="homework.score"></el-input>
						</el-form-item>
						<el-form-item>
							<template #default="scope">
								<el-button type="primary" @click="Correct()">提交批改</el-button>
							</template>
						</el-form-item>
					</template>
				</el-form>
				</div>
			</el-main>
		</el-container>
	</div>
</template>

<script>
	import imgDraw from '@/views/imgDraw/imgDraw'
	import TeacherHeader from '@/views/common/TeacherHeader';
	export default{
		components:{TeacherHeader,imgDraw},
		data() {
			return{
				url:'https://zjy9823.oss-cn-qingdao.aliyuncs.com/zjy9823/2022-04-09/4.jpg',
				homework:{
					
				},
				teacher_homework:{
					
				},
				imageList:[],
				text1:{
					
				},
				bg:'',
				essayAdvice:{},
				result:{},
				sentsFeedback:[],
				keys:[],
				base64:''
			}
		},
		created() {
			this.teacher_homework=JSON.parse(sessionStorage.getItem("homework"));
			console.log(this.teacher_homework);
			this.homework=JSON.parse(sessionStorage.getItem("one_homework"));
			
			this.imageList=this.homework.content.split(',');
			console.log(this.imageList);
		},
		methods:{
			pigai(){
							let routeUrl = this.$router.resolve({
								path:"/tdraw",
								query:{url:this.homework.content}
							})
							window.open(routeUrl.href,'_blank')
						},
			Correct(){
				var postData = this.$qs.stringify({
					loginName:this.homework.loginName,
					stuUploadId:this.homework.stuUploadId,
					content:this.homework.content,
					score:this.homework.score,
					correct:1
				});
				
				this.$axios
				.post("stuhomework/updateStuHomework",postData)
				.then(rst=>{
					this.$message.success("批改成功！")
					
				}).catch(ex=>{
					console.log(ex);
				});
			},
			goBack(){
				this.$router.go(-1);
			},
			answerComparison(row){
				var params=this.$qs.stringify({
					answer:this.teacher_homework.answer,
					homework:row
				})
				this.$axios
				.post("mathOrc/answerTest",params)
				.then(rst=>{
					this.base64=rst.data;
				})
				.catch(ex=>{
					console.log(ex)
				})
			},
			mathOrc(row){
				var params=this.$qs.stringify({
					url:row
				})
				this.$axios
				.post("mathOrc/imageBase64",params)
				.then(rst=>{
					this.base64=rst.data;
				})
				.catch(ex=>{
					console.log(ex);
				})
			},
			englishOrc(row){
				var params=this.$qs.stringify({
					url:row
				})
				this.$axios
				.post("engOrc/imageRecognition",params)
				.then(rst=>{
					this.result=rst.data.Result;
					this.sentsFeedback=rst.data.Result.essayFeedback.sentsFeedback
					this.homework.score=this.result.totalScore
					for (let i =0; i < 1;i++)
					{
						this.keys[i]={
							score:this.result.totalScore,
							advice:this.result.essayAdvice
						}
					}
				})
				.catch(ex=>{
					console.log(ex);
				})
			},
			identifyImage(row){
				var params=this.$qs.stringify({
					url:row
				})
				this.$axios
				.post("orc/imageRecognition",params)
				.then(rst=>{
					this.text1=rst.data.data.block[0];
					console.log(this.text1.line.length);
					console.log(rst.data.data.block[0].line);
				})
				.catch(ex=>{
					console.log(ex);
				})
			}
			
		}
	}
</script>

<style scoped>
.rightullidiv {
    width: 100%;
    /*background: #f2f2f2;*/
    display: flex;
    justify-content: center;
    align-items: center;
    .rightulliimg {
      max-width: 100%;
      max-height: 700px;
    }
  }

</style>
