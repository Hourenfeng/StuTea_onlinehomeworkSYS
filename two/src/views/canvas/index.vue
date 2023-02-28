<template>
	 <div class="correction-wrap">
	    <div class="header" />
	    <div class="main">
	      <div class="left-wrap" />
	      <div class="center-wrap">
	        <canvas-container ref="canvasContRef" :img-url="imgUrl" />
			<canvas-container ref="canvasContRef" :img-url="imgUrl1" />
	      </div>
	      <div class="right-wrap">
	        <div draggable="true" class="step-item step" @dragstart="dragstart($event, 'step' , 5)">5</div>
	        <div draggable="true" class="step-item chapter" @dragstart="dragstart($event, 'chapter' , 5)">
	        </div>
	      </div>
	    </div>
	  </div>
</template>

<script>
	import CanvasContainer from './canvasContainer.vue'
	export default{
		name:'Index',
		components:{
			CanvasContainer
		},
		data(){
			return {
				imgUrl:'https://zjy123456.oss-cn-qingdao.aliyuncs.com/zjy123456/2022-04-22/%E4%BD%9C%E4%B8%9A1.jpg',
				imgUrl1:'https://zjy123456.oss-cn-qingdao.aliyuncs.com/zjy123456/2022-04-22/4.jpg'
			}
		},
		methods:{
			dragstart(e,type,value){
				const data = JSON.stringify({type,value})
				e.dataTransfer.setData('data',data)
			}
		}
	}
</script>

<style >
	.correction-wrap{
	  width: 100%;
	  height: 100%;
	  display: flex;
	  flex-direction: column;
	  transform: rotate();
	}
	.header{
	  width: 100%;
	  height: 100px;
	  flex-shrink: 0;
	  background-color: #0099CC;
	}
	.main{
	  flex: 1;
	  display: flex;
	}
	.main .left-wrap{
	  width: 200px;
	  background-color: #9999FF;
	}
	.main .center-wrap{
	  flex: 1;
	  height: calc(100vh - 100px);
	  position: relative;
	  background-color: #CCFFFF;
	  overflow: scroll;
	}
	.main .right-wrap{
	  width: 300px;
	  background-color: #CCCCFF;
	}
	button{
	  width: 80px;
	  height: 40px;
	}
	.step-item.step{
	  width: 80px;
	  height: 40px;
	  text-align: center;
	  line-height: 40px;
	  border-radius: 4px;
	  background-color: #009999;
	}
</style>

