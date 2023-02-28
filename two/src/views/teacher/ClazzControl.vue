<template>
	<div class="studentControl">
		<el-container>
			<el-main>
				<template v-if="this.$router.currentRoute.value.path=='/ClazzControl'">
					<div id="postSearch">
						<el-form :inline="true" :model="clazz" class="demo-form-inline">
							<el-form-item label="搜索班级">
								<el-input v-model="clazz.clazzName" placeholder="请输入班级名"></el-input>
							</el-form-item>
							<el-form-item>
								<el-button type="primary" v-on:click="findByParam()">查询</el-button>
								<el-button  @click="clearForm()">清空</el-button>
							</el-form-item>
						</el-form>
					</div>
					<div id="studentTable">
						<div id="show_table">
							<el-table :data="clazzs">
								<el-table-column label="班级名" prop="clazzName"></el-table-column>
								<el-table-column label="班级人数" prop="clazzStuNum"></el-table-column>
								<el-table-column label="操作">
									<template #default="scope">
										<el-button v-on:click="viewClazz(scope.row)" type="info" plain>查看班级情况</el-button>
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
	export default {
		name: 'ClazzControl',
		data() {
			return {
				clazz: {
					clazzName: '',
				},
				teacher: [],
				clazzs: [],
				clazzs2:[],
				head: [],
				pageNum: 1, //当前页
				pageTotal: 0, //总条数
				pageSize: 3 //每页显示几条
			}
		},
		methods: {
			init() {
				this.clazzs.pageNum = this.pageNum;
				this.clazzs.pageSize = this.pageSize;
				this.clazzs.teacherId = this.teacher.teacherId;
				//把json对象转为字符串
				var param = this.$qs.stringify(this.clazzs);
				console.log(param);
				this.$axios
					.post("/teacher/findAllTeaClazzs", param)
					.then(rst => {
						console.log(rst);
						this.pageTotal = rst.data.total;
						this.clazzs = rst.data.list;
						console.log(rst.data.list);

					}).catch(ex => {
						console.log(ex);
					});
			},
			viewClazz(row){
				sessionStorage.setItem("clazzId",JSON.stringify(row));
				this.$router.push("/clazzControl/clazzStuControl");
			},
			clearForm(){
				this.clazz.clazzName=null;
				
				this.init();
			},
			findByParam(){
				this.clazzs2.pageNum=this.pageNum;
				this.clazzs2.pageSize=this.pageSize;
				this.clazzs2.teacherId=this.teacher.teacherId;
				this.clazzs2.clazzName=this.clazz.clazzName;
				//把json对象转为字符串
				var param = this.$qs.stringify(this.clazzs2);
				console.log(param);
				this.$axios
					.post("/teacher/findClazzByParam", param)
					.then(rst => {
						console.log(rst);
						this.pageTotal = rst.data.total;
						this.clazzs = rst.data.list;
						console.log(rst.data.list);
						if(rst.data.list[0].clazzId==null){
							console.log("不存在")
							this.$notify({
							    title: '提示',
							    message: '该班级不存在！',
							    duration: 3000
							});
							this.init();
						}
					}).catch(ex => {
						console.log(ex);
					});
			},
			pageData(curr){
			    this.pageNum=curr;
			    this.init();
			},
		},
		created() {
			this.teacher = JSON.parse(sessionStorage.getItem("teacher"));
			this.init();
			console.log(this.$router.currentRoute.value.path);
		}
	}
</script>

<style scoped>

</style>
