<template>
	<div id="container">
		<el-container>
			<template v-if="this.$router.currentRoute.value.path=='/studenthkList'">
				<el-main>
					<div id="show_table">
						<el-table :data="thomeworks">
							<el-table-column label="老师姓名"
							prop="teacher.loginName"></el-table-column>
							<el-table-column label="发布日期"
							prop="date"></el-table-column>
							<el-table-column label="科目"
							prop="course.courseName"></el-table-column>
							<el-table-column label="作业标题"
							prop="title"></el-table-column>							
							<el-table-column fixed="right" label="操作">
								<template #default="scope">
									<el-button @click="handleClick(scope.row)" type="primary"
									icon="el-icon-edit" size="small">提交作业</el-button>
                  <el-button @click="writeagain(scope.row)" type="danger"
                             icon="el-icon-edit" size="small">申请重做</el-button>
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
					</div>
				</el-main>
			</template>
			<template v-else>
				<router-view/>
			</template>
		</el-container>
	</div>
</template>

<script>
	export default{
		name:'Student_homeworkList',
		data() {
			return{
				student:[],
				thomeworks:[],
				course:[],
				homework:[],
				pageNum:1,
				pageTotal:0,
				pageSize:8,
				form:{
					stuUploadId:'',
					homeworkId:'',
					studentId:'',
					date:'',
					courseId:'',
					content:'',
				},
        newsubmit:'',
				dialogAddShomework:false,
				dialogEditShomework:false,
			}

		},
		created() {
			this.student=JSON.parse(sessionStorage.getItem("student"));
			this.findCourseName();
			this.init();
			console.log(this.student);
		},
		methods:{
			handleClick(row)
			{
				sessionStorage.setItem("thomework",JSON.stringify(row));
				this.$router.push("/studenthkList/studentUploadHK");
			},
      writeagain(row){
        this.newsubmit=2;
        var param1=this.$qs.stringify({
			studentId:this.student.studentId,
			homeworkId:row.homeworkId,
			newsubmit:this.newsubmit
		})
        this.$axios
            .post("stuhomework/WriteAgain",param1)
            .then(rst=>{
              if(rst.data==1){
                this.$message({
                  message: '已成功申请重做！',
                  type: 'success'
                });
              }
              else{
                console.log(param1);
                console.log(rst);
                this.$message({
                  message: '系统错误，申请失败！',
                  type: 'error'
                });
              }
            })
      },
			init(){
				this.thomeworks.pageNum=this.pageNum;
				this.thomeworks.pageSize=this.pageSize;
				this.thomeworks.clazzId=this.student.clazzId;
				console.log(this.student.clazzId);
				var param=this.$qs.stringify(this.thomeworks);
				this.$axios
				.post("teahomework/findByClazzId",param)
				.then(rst=>{
					console.log(rst);
					this.thomeworks=rst.data.list;
					this.pageTotal=rst.data.total;
				}).catch(ex=>{
					console.log(ex);
				});
			},
			submitSearch(){
				this.pageNum=1;
				this.init();
			},
			clearForm(){
				this.form={
					stuUploadId:'',
					homeworkId:'',
					studentId:'',
					date:'',
					courseId:'',
					content:'',
				};
				this.init();
			},
			
			findCourseName(){
				this.$axios
				.post("teacher/findAllCourse")
				.then(rst=>{
					this.course=rst.data;
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

		}
	}
</script>

<style>
</style>
