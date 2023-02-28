<template>
	<el-container id="container">
		<TeacherHeader></TeacherHeader>
		<el-container>
			<el-aside width="200px">
				<LeftMenu></LeftMenu>
			</el-aside>
			<el-main id="main" style="margin-left: 30px;margin-right: 30px;margin-top: 20px;">
				<div v-show="this.$route.fullPath=='/teaHomepage'">
					<div class="homeWelcome" style="display: flex;">
						<el-card style="width: 40%;flex:2;margin-right: 10px;">
							<div class="el-icon-zzlaoshi" style="float: left;font-size: 90px;"></div>
							<div style="font-size: 20px;margin-top: 10px;font-family: bold;">
								<div>早上好，郭磊老师~</div>
								<div style="margin-top: 10px;">您本月共布置 20 次作业，上传 10 个文件</div>
							</div>
						</el-card>
						<el-card style="width: 60%;flex:3;margin-left: 10px;">
							<div style="display: flex;justify-content: space-around;font-size: 20px;margin-top: 8px;">
								<div>
									<div>总学生数</div>
									<div class="numbertea">89</div>
								</div>
								<div>
									<div>总作业数</div>
									<div class="numbertea">50</div>
								</div>
								<div>
									<div>总资源数</div>
									<div class="numbertea">36</div>
								</div>
								<div>
									<div>学生作业完成率</div>
									<div class="numbertea" style="color: #DF5000;">91%</div>
								</div>
								<div>
									<div>学生作业平均分</div>
									<div class="numbertea" style="color: darkgreen;">87</div>
								</div>
							</div>
						</el-card>
					</div>
					<div style="display: flex;margin-top: 35px;">
						<el-card style="width: 40%;flex:2;margin-right: 10px;">
							<div style="margin-bottom: 20px;margin-left: 13px;">
								<div
									style="font-size: 20px;float: left;color: #84837c;border-radius: 15px 0px 15px 0px;width: 150px;height: 50px;text-align: center;line-height: 50px;border:2px solid #4e8484">
									近期课表</div>
							</div>
							<div class="con">
								<div class="now-data-myself">
									<div class="now-data-myself-time">{{ date }}</div>
									<div class="now-data-myself-week">{{ week }}</div>
								</div>
								<Calendar v-on:choseDay="clickDay" v-on:changeMonth="changeDate"
									v-on:isToday="clickToday"></Calendar>
							</div>
						</el-card>
						<el-card style="width: 60%;flex:3;margin-left: 10px;">
							<div style="width: 100%;height: 45px;margin-top: 10px;">
								<div class="el-icon-zzyingyong" style="float: left;font-size: 40px;"></div>
								<div style="font-size: 25px;font-weight: 400;float: left;margin-left: 20px;">常用功能</div>
							</div>
							<div style="display: flex;justify-content: space-around;">
								<div>
									<div
										style="display: flex;justify-content: space-around;margin-top: 40px;width: 450px;">
										<div>
											<div class="el-icon-zzbanji" style="font-size: 30px;"></div>
											<div>班级管理</div>
										</div>
										<div>
											<div class="el-icon-zzshoudongbiaozhu" style="font-size: 30px;"></div>
											<div>手动批改</div>
										</div>
										<div>
											<div class="el-icon-zzzidongfanmaiji" style="font-size: 30px;"></div>
											<div>自动批改</div>
										</div>
									</div>
									<div
										style="display: flex;justify-content: space-around;margin-top: 40px;width: 450px;">
										<div>
											<div class="el-icon-zzzuoye" style="font-size: 30px;"></div>
											<div>提交作业</div>
										</div>
										<div>
											<div class="el-icon-zzzhongxinshengcheng" style="font-size: 30px;"></div>
											<div>申请重做</div>
										</div>
										<div>
											<div class="el-icon-zzziyuan-xianxing" style="font-size: 30px;"></div>
											<div>学习资源</div>
										</div>
									</div>
									<div
										style="display: flex;justify-content: space-around;margin-top: 40px;width: 450px;">
										<div>
											<div class="el-icon-zzshujufenxi" style="font-size: 30px;"></div>
											<div>学情分析</div>
										</div>
										<div>
											<div class="el-icon-zzicon-test" style="font-size: 30px;"></div>
											<div>师生互动</div>
										</div>
									</div>
								</div>
								<div>
									<div>
										<div style="float: left;font-size: 20px;">消息通知</div>
										<div style="color: #0086B3;float: right;">详情>></div>
									</div>

									<div style="margin-top: 40px;"><span style="margin: 0 10px"><span
												class="login-cycle"></span>十一国庆节，为响应国家法定节假日号召，我...</span></div>
									<div style="margin-top: 10px;"><span style="margin: 0 10px"><span
												class="login-cycle"></span>2021年~2022年第一学期，致家长的一封信，...</span></div>
									<div style="margin-top: 10px;"><span style="margin: 0 10px"><span
												class="login-cycle"></span>为组织开展好 我校五好小公民 “文明美德伴我...</span></div>
									<div style="margin-top: 10px;"><span style="margin: 0 10px"><span
												class="login-cycle"></span>2021年春节放假安排，1月7日开始放假，老师...</span></div>
									<div style="margin-top: 10px;"><span style="margin: 0 10px"><span
												class="login-cycle"></span>关于在我校深入开展“我的中国梦"主题教育活...</span></div>
									<div style="margin-top: 10px;"><span style="margin: 0 10px"><span
												class="login-cycle"></span>按市教育局、区教体局通知要求，创建全国文...</span></div>
									<div style="margin-top: 10px;"><span style="margin: 0 10px"><span
												class="login-cycle"></span>为配合学院迎新工作，丰富新生校园生活，进...</span></div>
									<div style="margin-top: 10px;"><span style="margin: 0 10px"><span
												class="login-cycle"></span>昭公三校学校关于收取住宿费的通知：各位同...</span></div>

								</div>
							</div>
						</el-card>
					</div>
					</div>
				<div v-show="this.$route.fullPath!='/teaHomepage'">
					<router-view></router-view>
				</div>

			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	import TeacherHeader from '@/views/common/TeacherHeader';
	import LeftMenu from '@/views/common/LeftMenu';
	import Calendar from '../../components/calendar/calendar.vue';
	export default {
		components: {
			TeacherHeader,
			LeftMenu,
			Calendar
		},
		name: 'Tea_Homepage',
		data() {
			return {
				teacher: {

				},
				date: "",
				week: ""
			}
		},
		created() {
			this.teacher = JSON.parse(sessionStorage.getItem("teacher"));
			console.log(this.teacher);
			var now = new Date();
			this.date = now.getDate(); //得到日期
			var day = now.getDay(); //得到周几
			var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
			this.week = arr_week[day];
		},
		methods: {
			clickDay(data) {
				console.log(data); //选中某天
			},
			changeDate(data) {
				console.log(data); //左右点击切换月份
			},
			clickToday(data) {
				console.log(data); // 跳到了本月
			}
		},
		mounted() {
			console.log(this.$route);
		},
		computed: {
			breadcrumbList() {
				return this.$route.matched;
			}
		}
	}
</script>

<style>
	#container {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
	}

	#main {
		width: 100%;
		flex: 1;
	}

	#aside {
		background-color: #071b2e;
	}

	.numbertea {
		margin-top: 10px;
		color: #0077AA;
		font-size: 20px;
	}

	.login-cycle {
		width: 10px;
		height: 10px;
		display: inline-block;
		background-color: black;
		border-radius: 50%;
	}

	.now-data-myself {
		width: 40%;
		position: absolute;
		border-right: 1px solid rgba(227, 227, 227, 0.6);
	}

	.con {
		position: relative;
		max-width: 280px;
		margin: auto;
	}

	.con .wh_content_all {
		background: transparent !important;
	}

	.wh_top_changge {
		float: right;
		width: 60%;
	}

	.wh_top_changge li {
		color: #F56C6C !important;
		font-size: 15px !important;

	}

	.wh_content_item,
	.wh_content_item_tag {
		color: #303133 !important;
	}

	.wh_content_item .wh_isToday {
		background: #00d985 !important;
		color: #fff !important;
	}

	.wh_content_item .wh_chose_day {
		background: #409EFF !important;
		color: #ffff !important;
	}

	.wh_item_date:hover {
		background: rgb(217, 236, 255) !important;
		border-radius: 100px !important;
		color: rgb(102, 177, 255) !important;
	}

	.wh_jiantou1[data-v-2ebcbc83] {
		border-top: 2px solid #0000ff;
		border-left: 2px solid #0000ff;
		width: 7px;
		height: 7px;
	}

	.wh_jiantou2[data-v-2ebcbc83] {
		border-top: 2px solid #0000ff;
		border-right: 2px solid #0000ff;
		width: 7px;
		height: 7px;
	}

	.wh_top_tag[data-v-2ebcbc83] {
		color: #409EFF;
		border-top: 1px solid rgba(227, 227, 227, 0.6);
		border-bottom: 1px solid rgba(227, 227, 227, 0.6);
	}

	.wh_container[data-v-2ebcbc83] {
		max-width: 280px;
	}

	.wh_top_changge[data-v-2ebcbc83] {
		display: flex;
		width: 50%;
		margin-left: 43%;
	}

	.now-data-myself-time {
		color: #F56C6C;
		font-size: 28px;
		height: 30px;
		font-family: "Helvetica Neue";
	}

	.now-data-myself-week {
		font-size: 10px;
		color: #909399;
	}

	.wh_top_changge .wh_content_li[data-v-2ebcbc83] {
		font-family: Helvetica;
	}
</style>
