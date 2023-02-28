<template>
<el-container direction="vertical">
	<!-- 头部 -->
	<Header></Header>
	<el-main>
		<div class="zhuti">
			Ant Motion
		</div>
		<div class="xiangxi">
			三带一项目小组 与您共同关心孩子的健康成长
		</div>
	</el-main>
</el-container>
</template>

<script>
	import Header from '@/views/common/Header';
	export default {
		components:{Header},
		name:'Main',
	    data() {    
	      return {
	      }
	    }
	  };
</script>

<style scoped="scoped">
.logo{
	left: 0px;
	top:0px;
	position: absolute;
	width: 259px;
	height: 60px;
}
.logo a{
	position: absolute;
	top: 0px;
	display: block;
	width: 259px;
	height: 60px;
	background: url(../../assets/1.png) no-repeat;
	font-size: 0;
}
.el-container{
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
}
.el-main{
	background: url(../../assets/背景.png);
	background-position: center top;
	background-attachment: fixed;
	background-size: cover;
} 
.el-header{
	top: 0px;
	left: 0px;
}
.zhuti{
	position: absolute;
	left: 93px;
	top: 318px;
	width: 330px;
	height: 84px;
	font-size: 60px;
	text-align: left;
	font-family: SourceHanSansSC-regular;	
	color: rgba(255, 255, 255, 100);
}
.xiangxi{
	position: absolute;
	left: 93px;
	top: 407px;
	width: 370px;
	height: 32px;
	font-size: 17px;
	text-align: left;
	font-family: SourceHanSansSC-regular;
	color: rgba(255, 255, 255, 100);
}
.el-button{
	width: 50px; height: 50px;
	border-radius:              50%;
	border: none;
	width: 197px;
	height: 197px;
	line-height: 20px;
	opacity: 0.65;
	background-color: rgba(10, 35, 54, 100);
	text-align: center;
	border: 1px solid rgba(187, 187, 187, 100);
}
</style>
