<template>
  <el-row>
    <el-col>
      <el-menu
        class="el-menu-vertical-demo"
           background-color="#ffffff"
           text-color="black" router>
            <el-submeu index="1" >
            <el-menu-item-group>
			<el-menu-item index="/teadataAnalyse" id="one">数据分析</el-menu-item>
              <el-menu-item index="/teahklist" id="two">作业列表</el-menu-item>
			  <el-menu-item index="/teacherReList" id="three">资源管理</el-menu-item>
			  
			  <el-menu-item index="/ClazzControl" id="four">班级管理</el-menu-item>
            </el-menu-item-group>
          </el-submeu>
        </el-menu>
    </el-col>
  </el-row>
</template>

<style>
  .el-menu-vertical-demo{
    height: 645px
  }
</style>

<script>
	import introJs from 'intro.js'
	import 'intro.js/introjs.css'
	import Header from '/src/views/common/TeacherHeader.vue'
  export default {
    name: 'LeftMenu',
	data(){
		return{
			isFirst:0
		}
	},
	methods: {
		guide() {
			const allSteps = [
			                {
			                    element: '#one', //这是添加引导的元素id
			                    intro: '这里是数据分析，您可以看到所教班级的学情分析', //这是引导提示内容
			                    position: 'right' //这是引导位置
			                },
			                {
			                    element: '#two',
			                    intro: '这里是作业列表，您可以看到自己发布的所有作业，点击某一作业，便可看到学生提交情况',
			                    position: 'right'
			                },
							{
			                    element: '#three',
			                    intro: '这里是资源管理，您可以看到自己所上传的学习资源',
			                    position: 'right'
			                },
			                {
			                    element: '#four',
			                    intro: '这里是班级管理，您可以看到自己所教的班级情况',
			                    position: 'top'
			                },
							{
							    element: '#five',
							    intro: '这里是个人中心，您可以查看并修改个人信息',
							    position: 'bottom'
							}
			            ]
		            introJs()
		            .setOptions({
		                prevLabel: `上一步`,
		                                nextLabel: `下一步`,
		                                skipLabel: `跳过`,
		                                doneLabel: `完成`,
		                                tooltipPosition: `bottom` /* 引导说明框相对高亮说明区域的位置 */,
		                                hidePrev: `true`, // 隐藏第一步中的上一个按钮
		                                tooltipClass: `` /* 引导说明文本框的样式 */,
		                                highlightClass: `` /* 说明高亮区域的样式 */,
		                                exitOnOverlayClick: false /* 是否允许点击空白处退出 */,
		                                showStepNumbers: false /* 是否显示说明的数据步骤*/,
		                                keyboardNavigation: false /* 是否允许键盘来操作 */,
		                                showButtons: true /* 是否按键来操作 */,
		                                showBullets: true /* 是否使用点点点显示进度 */,
		                                showProgress: false /* 是否显示进度条 */,
		                                scrollToElement: true /* 是否滑动到高亮的区域 */,
		                                overlayOpacity: 0.6 /* 遮罩层的透明度 */,
		                                positionPrecedence: [`bottom`, `top`, `right`, `left`] /* 当位置选择自动的时候，位置排列的优先级 */,
		                                disableInteraction: false, /* 是否禁止与元素的相互关联 */
		                                hintPosition: 'top-middle',
		                                steps: allSteps
		            }).oncomplete(()=> {
					//点击结束按钮后执行的事件
					}).onexit(()=> {
					//点击跳过按钮后执行的事件
					}).start()
		        },
	},
	mounted() {
		if(document.cookie.indexOf("user=new")==-1){
		        var t=new Date(new Date().getTime()+1000*60*60*24*30);
		        document.cookie="user=new; expires="+t.toGMTString();
				this.guide()
		    }else{
		    }
	        
	},
    };
</script>