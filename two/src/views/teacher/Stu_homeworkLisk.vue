<template>
	<div id="container">
		<template v-if="this.$router.currentRoute.value.path=='/teahklist/stuhkList'">
			<el-container>
				<el-main>
					<el-page-header @back="goBack" content="详情页面" title="返回作业列表">
					</el-page-header>
					<div>
						<el-form>
							<el-form-item  style="float:right">
							<el-dropdown @command="handleCommand">
								<span class="el-dropdown-link">
								      全部学生
								      <el-icon class="el-icon--right">
								        <arrow-down />
								      </el-icon>
								    </span>

								<template #dropdown>
									<el-dropdown-menu>
										<el-dropdown-item command="2">全部的</el-dropdown-item>
										<el-dropdown-item command="1">已提交</el-dropdown-item>
										<el-dropdown-item command="0">未提交</el-dropdown-item>
									</el-dropdown-menu>
								</template>
							</el-dropdown>
							</el-form-item>
						</el-form>
					</div>
					<el-table :data="shomeworks">
						<el-table-column label="学生姓名" prop="loginName"></el-table-column>
						<el-table-column label="提交日期" prop="date" v-if="commands!=0"></el-table-column>
						<el-table-column label="课程名称" prop="courseName"></el-table-column>
						<el-table-column label="操作" prop="submit">
							<template #default="scope">
								<template v-if="scope.row.submit==1">
									<el-button @click="lookHK(scope.row)">查看作业提交情况</el-button>
								</template>
								<template v-else>
									<el-badge is-dot class="item">
									<el-button @click="remind(scope.row)">提醒交作业</el-button>
									</el-badge>
								</template>
							</template>
						</el-table-column>
						
						<el-table-column 
						label="得分" prop="score"
						v-if="commands==1">
							
						</el-table-column>
					</el-table>
					<div id="page_content">
						<el-pagination background layout="total,sizes,prev, pager, next,jumper" :total="pageTotal"
							:page-sizes="[4,6,8,10]" :page-size="pageSize" @size-change="handleSizeChange"
							@current-change="handleCurrentChange" :current-change="pageNum">
						</el-pagination>
					</div>
				</el-main>
			</el-container>
		</template>
		<template v-else>
			<router-view />
		</template>
	</div>
</template>

<script>
	import TeacherHeader from '@/views/common/TeacherHeader';
	import { ArrowDown } from '@element-plus/icons-vue';
	import { Share } from '@element-plus/icons-vue'
	export default {
		components: {
			TeacherHeader,
			ArrowDown,
			Share
		},
		name: 'Stu_homeworkList',
		data() {
			return {
				num: '',
				shomeworks: [],
				pageNum: 1,
				pageTotal: 0,
				pageSize: 10,
				commands:2,
				homework: {

				},
				teacher: {}
			}
		},
		mounted() {
			this.homework = JSON.parse(sessionStorage.getItem("homework"));
			this.teacher = JSON.parse(sessionStorage.getItem("teacher"));
			console.log(this.homework);
			this.init();
		},
		methods: {
			handleCurrentChange(val) {
				console.log(`当前页:${val}`);
				this.pageNum = val;
				this.handleCommand(this.commands);
			},
			handleSizeChange(val) {
				console.log(`每页${val}条`);
				this.pageSize = val;
				this.handleCommand(this.commands);
			},
			goBack() {
				this.$router.go(-1);
				console.log("go back");
			},
			handleCommand(command){
				this.commands=command;
				if(command==2){
					console.log("2!!");
					this.init();
				}
				if(command==1){
					console.log("1!!");
					this.findSubmitState(1);
				}
				if(command==0){
					console.log("0!!");
					this.findSubmitState(0);
				}
			},
			init() {
				this.shomeworks.pageNum = this.pageNum;
				this.shomeworks.pageSize = this.pageSize;
				this.shomeworks.homeworkId = this.homework.homeworkId;
				this.shomeworks.teacherId = this.teacher.teacherId;
				var param = this.$qs.stringify(this.shomeworks);
				this.$axios
					.post("stuhomework/findByHomeworkId", param)
					.then(rst => {
						this.shomeworks = rst.data.list;
						this.pageTotal = rst.data.total;
						console.log(rst.data.list);
						console.log(this.shomeworks.submit);
					})
					.catch(ex => {
						console.log(ex);
					})
			},
			lookHK(row) {
				sessionStorage.setItem("one_homework", JSON.stringify(row));
				this.$router.push("/teahklist/stuhkList/oneStuhkList");
			},
			findSubmitState(command){
				this.shomeworks.pageNum = this.pageNum;
				this.shomeworks.pageSize = this.pageSize;
				this.shomeworks.homeworkId = this.homework.homeworkId;
				this.shomeworks.teacherId = this.teacher.teacherId;
				this.shomeworks.submit=command;
				var param = this.$qs.stringify(this.shomeworks);
				this.$axios
					.post("stuhomework/findByHomeworkIdByState", param)
					.then(rst => {
						this.shomeworks = rst.data.list;
						this.pageTotal = rst.data.total;
						console.log(rst.data);
					})
					.catch(ex => {
						console.log(ex);
					})
			}
		}
	}
</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
.item {
  margin-top: 10px;
  margin-right: 40px;
}
</style>
