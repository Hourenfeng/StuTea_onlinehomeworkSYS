<template>
	<div class="drag-step" :class="{'hide':isHide}" :style="dragStyle">
		<div v-for="(step,index) in dragList"
		:key="index"
		class="drag-item"
		:class="step.type"
		:style="{
			left: step.x - 30 + 'px',
			top: step.y - 15 + 'px'
		}"
		@click="clickStepItem(step.value)">
		<span v-if="step.type=='step'">{{step.value}}</span>
		<img v-if="step.type=='chapter'" draggable="false" 
		:src="require('./chapter.png')"
		alt=""/></div>
	</div>
</template>

<script>
	export default{
		
		props:{
			isHide:{
				type:Boolean,
				default:false
			},
			dragList:{
				type:Array,
				default:()=>[]
			},
			dragStyle:{
				type:Object,
				default:()=>({})
			}
		},
		methods:{
			clickStepItem(value){
				console.log(value)
			}
		}
		
	}
</script>

<style scoped>
	.drag-step.hide{
	  position: absolute;
	  top: 0;
	  left: 0;
	  z-index: -1;
	}
	.drag-item{
	  position: absolute;
	  user-select: none;
	}
	.drag-item.step{
	  width: 60px;
	  height: 30px;
	  text-align: center;
	  line-height: 30px;
	  color: #fff;
	  border-radius: 4px;
	  background-color: aquamarine;
	}
	.drag-item.chapter{
	 
	}
</style>
