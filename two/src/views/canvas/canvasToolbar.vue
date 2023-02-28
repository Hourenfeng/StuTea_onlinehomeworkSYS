<template>
	<div class="canvas-toolbar">
		<el-form :inline="true">
			<el-form-item>
				<el-button v-for="tool in tools"
				:key="tool.code"
				@click="changeTool(tool.code)"
				>
				{{tool.name}}</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default{
		props:{},
		data() {
			return{
				tools: [{
				        code: 'drag',
				        name: '拖动'
				      }, {
				        code: 'draw',
				        name: '画笔'
				      }, {
				        code: 'eraser',
				        name: '橡皮'
				      }, {
				        code: 'revoke',
				        name: '撤销'
				      }, {
				        code: 'clear',
				        name: '重置'
				      }, {
				        code: 'save',
				        name: '保存'
				      }, {
				        code: 'rotate',
				        name: '旋转'
				      }, {
				        code: 'enlarge',
				        name: '放大'
				      }, {
				        code: 'narrow',
				        name: '缩小'
				      }]
			}
		},
		methods:{
			changeTool(name,value)
			{
				this.$emit('changeTool',name)
			},
			changeScale(){
				
			}
		}
	}
</script>

<style>
</style>
