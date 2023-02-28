<template>
	<el-dialog title="添加学生"
	  v-model="dialogAddStudent">
			<el-form :model="addStudent">
			<el-form-item label="学生姓名" >
			<el-input v-model="addStudent.stuloginName" autocomplete="off"></el-input>
			</el-form-item>
			</el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogAddStudent = false">取 消</el-button>
	      <el-button type="primary" @click="addStudentSubmit()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<div class="studentControl">
		<el-container>
			<el-main>
				<template v-if="this.$router.currentRoute.value.path=='/clazzControl/clazzStuControl'">
				<el-page-header @back="goBack" content="详情页面" title="返回班级页面">
					</el-page-header>
				<div id="postSearch">
					<el-form :inline="true" :model="student" class="demo-form-inline">
						<el-form-item label="搜索学生">
							<el-input placeholder="请输入学生姓名" v-model="student.stuloginName" ></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" v-on:click="findByParam()">查询</el-button>
							<el-button  @click="clearForm()">清空</el-button>
						</el-form-item>
						<el-form-item  style="float:right">
							<el-button type="primary" class="el-icon-search"
							@click="dialogAddStudent=true">
							  添加学生
							</el-button>
						</el-form-item>
					</el-form>
				</div>
				<div id="studentTable">
					<div id="show_table">
						<el-table :data="students">
							<el-table-column label="学生姓名" prop="loginName"></el-table-column>
							<el-table-column label="学生提交作业次数" prop="hwNum"></el-table-column>
							<el-table-column label="操作">
								<template #default="scope">
									<el-button v-on:click="viewStu(scope.row)" type="info" plain>查看学习情况</el-button>
								</template>
							</el-table-column>
						</el-table>
					</div>
					<div id="page_number">
						第{{pageNum}}页，共{{pageTotal}}条
					</div>
					<div id="page_content" style="float: right">
						<el-pagination background layout="prev,pager,next" :total="pageTotal" :page-size="pageSize"
							@current-change="pageData">
						</el-pagination>
					</div>
				</div>
				</template>
				<template v-else>
					      <router-view></router-view>
				</template>
			</el-main>
		</el-container>
	</div>
</template>

<script>
	import moment from "moment";
	import TeacherHeader from '@/views/common/TeacherHeader';
	export default{
		name:'ClazzStuControl',
		components:{TeacherHeader},
		data(){
			return{
				student:{
					stuloginName:'',
				},
				teacher:[],
				clazz:[],
				students:[],
				students2:[],
				head:[],
				addStudent:{
					stuloginName:'',
				},
				dialogAddStudent:false,
				pageNum: 1, //当前页
				pageTotal: 0, //总条数
				pageSize: 5 //每页显示几条
			}
		},
		methods:{
			init(){
				this.students.pageNum=this.pageNum;
				this.students.pageSize=this.pageSize;
				this.students.teacherId=this.teacher.teacherId;
				this.students.clazzId=this.clazz.clazzId;
				//把json对象转为字符串
				var param = this.$qs.stringify(this.students);
				console.log(param);
				this.$axios
					.post("/teacher/findAllTeaClaStu", param)
					.then(rst => {
						console.log(rst);
						this.pageTotal = rst.data.total;
						this.students = rst.data.list;
						console.log(rst.data);
				
					}).catch(ex => {
						console.log(ex);
					});
			},
			pageData(curr){
			    this.pageNum=curr;
			    this.init();
			},
			goBack(){
				this.$router.go(-1);
				console.log("go back");
			},
			clearForm(){
				this.student.stuloginName=null;
				
				this.init();
			},
			addStudentSubmit(){
				var postData=this.$qs.stringify({
					clazzId:this.clazz.clazzId,
					stuloginName:this.addStudent.stuloginName,
				});
				this.$axios
				.post("/teacher/addClaStu",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(er=>{
					console.log(er);
				})
				this.dialogAddStudent=false;
			},
			findByParam(){
				this.students2.pageNum=this.pageNum;
				this.students2.pageSize=this.pageSize;
				this.students2.teacherId=this.teacher.teacherId;
				this.students2.clazzId=this.clazz.clazzId;
				this.students2.stuloginName=this.student.stuloginName;
				//把json对象转为字符串
				var param = this.$qs.stringify(this.students2);
				console.log(param);
				this.$axios
					.post("/teacher/findClaStuByParam", param)
					.then(rst => {
						console.log(rst);
						this.pageTotal = rst.data.total;
						this.students = rst.data.list;
						console.log(rst.data.list);
						if(rst.data.list.length==0){
							console.log("不存在")
							this.$notify({
							    title: '提示',
							    message: '该学生不存在！',
							    duration: 3000
							});
							this.init();
						}
					}).catch(ex => {
						console.log(ex);
					});
			},
			viewStu(row){
				sessionStorage.setItem("onestudent",JSON.stringify(row));
				this.$router.push("/clazzControl/clazzStuControl/stuDetail");
			}
		},
		created() {
			this.teacher = JSON.parse(sessionStorage.getItem("teacher"));
			this.clazz=JSON.parse(sessionStorage.getItem("clazzId"));
			this.init();
		}
	}
</script>

<style scoped>
</style>
