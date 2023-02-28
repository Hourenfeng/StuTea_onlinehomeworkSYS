<template>
	<div class="imgDraw" @mousemove="beginPaths($event)" style="width: 100%;height: 100%;">
		<a href="javascript:void(0);" ref="download" download="picture.jpg" v-show="false"></a>
		<!-- 	  <el-button type="primary" @click="handleShowCanvas">批改</el-button>
	  <el-dialog
	    destroy-on-close :close-on-click-modal="false" :close-on-press-escape="false" :show-close="false"
	  	v-model="show" fullscreen
	  > -->
		
			
				<div class="board" ref="board">
					<canvas id="ctx_front" ref="ctx_front" @mousedown="canvasDown($event)" @mouseup="canvasUp($event)"
						@mousemove="canvasMove($event)" @touchstart="canvasDown($event)" @touchend="canvasUp($event)"
						@touchmove="canvasMove($event)"></canvas>
					<canvas id="ctx_base" ref="ctx_base"></canvas>
					<canvas id="ctx_back" ref="ctx_back"></canvas>

				</div>
				<div id="control" class="fl" style="float: right;float: right;">
					<!--画笔颜色-->
					<div id="canvas-color">
						<h5>
							画笔颜色</h5>
						<ul>
							<li v-for="item in colors" :class="{'active':config.lineColor === item}"
								:style="{ background: item }" @click="setColor(item)"></li>
						</ul>
					</div>
					<!--画笔-->
					<div id="canvas-brush">
						<h5>画笔大小</h5>
						<span v-for="pen in brushs"
							:class="[pen.className,{'active': config.lineWidth === pen.lineWidth}]"
							@click="setBrush(pen.lineWidth)">
							<svg class="icon" aria-hidden="true" :style="pen.style">
								<use xlink:href="#icon-huabi_huaban1"></use>
							</svg>
						</span>
					</div>
					<!--操作-->
					<div id="canvas-control">
						<h5>操作</h5>
						<span v-for="control in controls" :title="control.title" :class="control.className"
							@click="controlCanvas(control.action)">
							<svg class="icon" aria-hidden="true" style="width: 20px;height: 20px;">
								<use :xlink:href="control.icon"></use>
							</svg>
						</span>
					</div>
				</div>
			
		
		<!-- </el-dialog> -->
	</div>
</template>

<script>
	import {client} from '@/router/oss.js'
	  import bg from "@/views/imgDraw/img";
	export default {
		name: "tdraw",
		data() {
			return {
				bg:'',
				fileName:'',
				url1:'',
				show: false,
				defaultColor: "#333333",
				colors: ['#fef4ac', '#0018ba', '#ffc200', '#f32f15', '#cccccc', '#5ab639'],
				brushs: [{
				  className: 'small fa fa-paint-brush',
				  style:'width: 20px;height: 20px;',
				  icon:'huabi_huaban1',
				  lineWidth: 3
				}, {
				  className: 'middle fa fa-paint-brush',
				  style:'width: 25px;height: 25px;',
				  icon:'huabi_huaban1',
				  lineWidth: 6
				}, {
				  className: 'big fa fa-paint-brush',
				  style:'width: 30px;height: 30px;',
				  icon:'huabi_huaban1',
				  lineWidth: 12
				}],
				canvas_front: null,
				canvas_back: null,
				canvas_base: null,
				ctx_base: {},
				ctx_front: {},
				ctx_back: {},
				context: {},
				canvasMoveUse: false,
				// 存储当前表面状态数组-上一步
				preDrawAry: [],
				// 存储当前表面状态数组-下一步
				nextDrawAry: [],
				// 中间数组
				middleAry: [],
				currentImg: {
					url: bg,
					width: "",
					height: "",
					scale: 1,
					index: 0,
				},
				canvasStore: [bg],
				baseMap: bg,
				prevDis: true,
				nextDis: true,
				// 配置参数
				config: {
					lineWidth: 1,
					lineColor: '#f2849e',
					shadowBlur: 2
				}
			}
		},
		computed: {
		  controls () {
		    return [{
		      title: '上一步',
			  icon:"#icon-chexiao",
		      action: 'prev',
		      className: this.preDrawAry.length ? 'active fa fa-reply' : 'fa fa-reply'
		    }, {
		      title: '下一步',
			  icon:"#icon-zhongzuo",
		      action: 'next',
		      className: this.nextDrawAry.length ? 'active fa fa-share' : 'fa fa-share'
		    }, {
		      title: '清除',
			  icon:"#icon-lajixiang_huaban1",
		      action: 'clear',
		      className: (this.preDrawAry.length || this.nextDrawAry.length) ? 'active fa fa-trash' : 'fa fa-trash'
		    },{
		      title: '保存',
			  icon:"#icon-baocun",
		      action: 'save',
		      className: 'saved'
		    }]
		  }
		},

		methods: {
			// 起始一条路径，或重置当前路径
			beginPaths(e) {
				// const canvas = document.getElementById("ctx_front")
				// this.context = canvas.getContext("2d")
				if (e.target !== this.canvas_front) {
					console.log('beginPath')
					// console.log(this.ctx_front)
					this.ctx_front.beginPath()
				}
			},
			// 设置颜色
			setColor (color) {
			  this.config.lineColor = color
			},
			// 设置笔刷大小
			setBrush (type) {
			  this.config.lineWidth = type
			},
			isPc() {
				const userAgentInfo = navigator.userAgent
				const Agents = ['Android', 'iPhone', 'SymbianOS', 'Windows Phone', 'iPad', 'iPod']
				let flag = true
				for (let v = 0; v < Agents.length; v++) {
					if (userAgentInfo.indexOf(Agents[v]) > 0) {
						flag = false
						break
					}
				}
				return flag
			},
			canvasMove(e) {
				if (this.canvasMoveUse) {
					console.log('canvasMove')
					const t = e.target
					let canvasX
					let canvasY
					if (this.isPc()) {
						canvasX = e.clientX - t.parentNode.offsetLeft
						canvasY = e.clientY - t.parentNode.offsetTop
					} else {
						canvasX = e.changedTouches[0].clientX - t.parentNode.offsetLeft
						canvasY = e.changedTouches[0].clientY - t.parentNode.offsetTop
					}
					this.ctx_front.lineTo(canvasX, canvasY)
					this.ctx_front.stroke()
				}
			},
			// mouseup
			canvasUp(e) {
				console.log('canvasUp')
				const preData = this.ctx_front.getImageData(0, 0, 600, 400)
				if (!this.nextDrawAry.length) {
					// 当前绘图表面进栈
					this.middleAry.push(preData)
				} else {
					this.middleAry = []
					this.middleAry = this.middleAry.concat(this.preDrawAry)
					this.middleAry.push(preData)
					this.nextDrawAry = []
				}
				this.canvasMoveUse = false
				this.handleSaveCanvasStore()
			},
			// mousedown
			canvasDown(e) {
				console.log('canvasDown')
				this.canvasMoveUse = true
				// client是基于整个页面的坐标
				// offset是cavas距离顶部以及左边的距离
				const canvasX = e.clientX - e.target.parentNode.offsetLeft
				const canvasY = e.clientY - e.target.parentNode.offsetTop
				this.setCanvasStyle()
				// 清除子路径
				this.ctx_front.beginPath()
				this.ctx_front.moveTo(canvasX, canvasY)
				console.log('moveTo', canvasX, canvasY)
				// 当前绘图表面状态
				const preData = this.ctx_front.getImageData(0, 0, 600, 400)
				// 当前绘图表面进栈
				this.preDrawAry.push(preData)
			},
			/** 初始化画布*/
			handleInitCanvas() {				  
				console.log("在这运行了")
				this.currentImg = {
					url: bg,
					width: "",
					height: "",
					scale: 1,
					index: 0,
				};
				this.canvasStore = [bg];
				this.prevDis = true;
				this.nextDis = true;

				// 用于绘制的画板
				this.canvas_front = document.getElementById("ctx_front");
				// 用于生成绘制后图片的画板
				this.canvas_back = document.getElementById("ctx_back");
				// 底图画板，橡皮擦除时获取像素放到绘制画板中，达到不擦出底图的效果
				this.canvas_base = document.getElementById("ctx_base");

				this.ctx_base = this.canvas_base.getContext("2d");
				this.ctx_front = this.canvas_front.getContext("2d");
				this.ctx_back = this.canvas_back.getContext("2d");
				this.ctx_front.strokeStyle = this.defaultColor;
				let img = new Image();
				img.src = this.baseMap;
				let _this = this;
				img.onload = function() {
					let width = parseInt(this.width);
					let height = parseInt(this.height);
					_this.currentImg.width = width;
					_this.currentImg.height = height;
					_this.canvas_front.width = width;
					_this.canvas_front.height = height;
					_this.canvas_back.width = width;
					_this.canvas_back.height = height;
					_this.canvas_base.width = width;
					_this.canvas_base.height = height;
					//_this.ctx_front.drawImage(this, 0, 0, width, height);
					_this.ctx_back.drawImage(this, 0, 0, width, height);
					_this.ctx_base.drawImage(this, 0, 0, width, height);
				};
			},
			/** 处理放大缩小*/
			handleDrawImage() {
				console.log("在这运行了")
			  let _this = this;
			  let img = new Image();
			  let baseImg = new Image();
			  img.src = this.currentImg.url;
			  baseImg.src = this.baseMap;
			  _this.currentImg.width = _this.currentImg.width * this.currentImg.scale;
			  _this.currentImg.height = _this.currentImg.height * this.currentImg.scale;
			  img.onload = function() {
			    _this.canvas_front.width = _this.currentImg.width;
			    _this.canvas_front.height = _this.currentImg.height;
			    _this.canvas_back.width = _this.currentImg.width;
			    _this.canvas_back.height = _this.currentImg.height;
			    _this.ctx_front.drawImage(
			      this,
			      0,
			      0,
			      _this.currentImg.width,
			      _this.currentImg.height
			    );
			    _this.ctx_back.drawImage(
			      this,
			      0,
			      0,
			      _this.currentImg.width,
			      _this.currentImg.height
			    );
			  };
			  baseImg.onload = () => {
			    _this.canvas_base.width = _this.currentImg.width;
			    _this.canvas_base.height = _this.currentImg.height;
			    _this.ctx_base.drawImage(
			      baseImg,
			      0,
			      0,
			      _this.currentImg.width,
			      _this.currentImg.height
			    );
			  };
			},
			// 操作
			controlCanvas (action) {
			  switch (action) {
			    case 'prev':
			      // if (this.preDrawAry.length) {
			      //   const popData = this.preDrawAry.pop()
			      //   const midData = this.middleAry[this.preDrawAry.length + 1]
			      //   this.nextDrawAry.push(midData)
			      //   this.ctx_front.putImageData(popData, 0, 0)
			      // }
				  if (this.currentImg.index > 0) {
				    this.nextDis = false;
				    this.currentImg.index -= 1;
				    this.currentImg.index==0?this.prevDis = true:this.prevDis = false
				    this.currentImg.url = this.canvasStore[this.currentImg.index];
				    this.currentImg.scale = 1;
				    this.handleDrawImage();
				  } else {
				    this.prevDis = true;
				  }
			      break
			    case 'next':
			      // if (this.nextDrawAry.length) {
			      //   const popData = this.nextDrawAry.pop()
			      //   const midData = this.middleAry[this.middleAry.length - this.nextDrawAry.length - 2]
			      //   this.preDrawAry.push(midData)
			      //   this.ctx_front.putImageData(popData, 0, 0)
			      // }
				  if (this.currentImg.index<this.canvasStore.length-1) {
				    this.prevDis = false;
				    this.currentImg.index += 1;
				    this.currentImg.index==this.canvasStore.length-1?this.nextDis = true:this.nextDis = false
				    this.currentImg.url = this.canvasStore[this.currentImg.index];
				    this.currentImg.scale = 1;
				    this.handleDrawImage();
				  } else {
				    this.nextDis = true;
				  }
			      break
			    case 'clear':
			      this.ctx_front.clearRect(0, 0, this.ctx_front.canvas.width, this.ctx_front.canvas.height)
			      this.preDrawAry = []
			      this.nextDrawAry = []
			      this.middleAry = [this.middleAry[0]]
				  this.handleInitCanvas()
			      break
				case 'save':
				  this.handleCanvas2Img();
				  break
			  }
			},
			dataURLtoFile(dataurl, filename) {
			  const arr = dataurl.split(',');
			  const mime = arr[0].match(/:(.*?);/)[1];
			  const bstr = atob(arr[1]);
			  let n = bstr.length;
			  const u8arr = new Uint8Array(n);
			  while (n--) {
			    u8arr[n] = bstr.charCodeAt(n);
			  }
			  return new Blob([u8arr], { type: mime });// if env support File, also can use this: return new File([u8arr], filename, { type: mime });
			},
			getTime () {
			  var Time = new Date()
			  var year = Time.getFullYear()
			  var month = Time.getMonth() + 1
			  var date = Time.getDate()
			  if (month < 10) {
			        month = "0" + month;
			      }
			  var time = year + '-' +month +'-'+ date 
			  return time
			},
			/** 下载图片*/
			handleCanvas2Img() {
				let str=this.$route.query.url;
				str = str.slice(46);
				console.log(str)
			  let canvas = document.getElementById("ctx_back");
			  console.log(canvas.toDataURL())
			  this.$refs.download.href = canvas.toDataURL();
			  const base64Content = canvas.toDataURL();
			  // this.$refs.download.click();
			  var fileName = str;
			  const imgfile = this.dataURLtoFile(base64Content, fileName);
			  //key表示上传的object key ,imgFile表示dataURLtoFile处理后返回的图片
			  client.multipartUpload(fileName, imgfile).then((res) => {
			    console.log('upload success: %j', res);
				this.$message.success('批改成功！')
			  }).catch((err) => {
			    console.error(err);
			  });
			},
			/** 保存绘制*/
			handleSaveCanvasStore() {
				console.log("在这运行了")
			  let url = this.canvas_front.toDataURL();
			  let image = new Image();
			  image.src = url;
			  image.onload = () => {
			    this.ctx_front.clearRect(
			      0,
			      0,
			      this.canvas_front.width,
			      this.canvas_front.height
			    );
			    this.ctx_front.drawImage(image, 0, 0, image.width, image.height);
			    this.ctx_back.drawImage(image, 0, 0, image.width, image.height);
			    const url2 = this.canvas_back.toDataURL();
			    this.currentImg.url = url2;
			    this.currentImg.index += 1;
			    this.canvasStore.push(url2);
			    this.prevDis = false;
			    console.log(this.canvasStore);
			  };
			},
			imageBase64(){
				let image = new Image();
				            //解决跨域问题
				image.setAttribute('crossOrigin', 'anonymous');
				let imageUrl = this.$route.query.url;
				image.src = imageUrl
				var canvas = document.createElement("canvas");
				canvas.width = image.width;
				canvas.height = image.height;
				var context = canvas.getContext('2d');
				context.drawImage(image, 0, 0, image.width, image.height);              
				var quality = 0.8;
				                //这里的dataurl就是base64类型
				var dataURL = canvas.toDataURL("image/jpeg", quality);
			},

			// 设置绘画配置
			setCanvasStyle() {
				this.ctx_front.lineWidth = this.config.lineWidth
				this.ctx_front.shadowBlur = this.config.shadowBlur
				this.ctx_front.shadowColor = this.config.lineColor
				this.ctx_front.strokeStyle = this.config.lineColor
			}
		},
		mounted() {
			this.imageBase64();
			this.handleInitCanvas();
		}
	}
</script>

<style scoped lang="less">
	* {
		margin: 0;
		padding: 0;
	}

	
		

		
			.board {
				position: absolute;
				padding-top: 20px;
				min-height: 100%;

				canvas {
					/* width: 100%; */
					position: absolute;
					margin: 0 auto;
					left: 0;
					right: 0;
					top: 0;
				}

				#ctx_front {
					z-index: 5;
				}

				#ctx_back {
					z-index: 3;
				}

				#ctx_base {
					z-index: 1;
				}
			
		}
	
	#control{
	  width: 130px;
	  height: 400px;
	  margin-left: 4px;
	}
	#control div{
	  padding: 5px;
	}
	#canvas-color ul{
	  overflow: hidden;
	  margin: 0;
	  padding: 0;
	}
	#canvas-color ul li{
	  float: left;
	  display: inherit;
	  width: 13px;
	  height: 13px;
	  border: 3px #fff solid;
	  margin: 8px;
	  cursor: pointer;
	}
	#canvas-color .active {
	  border: 1px solid #f2849e;
	}
	#canvas-brush span{
	  display: inline-block;
	  width: 20px;
	  height: 15px;
	  margin-left: 10px;
	  cursor: pointer;
	}
	#canvas-brush .small {
	  font-size: 12px;
	}
	#canvas-brush .middle {
	  font-size: 14px;
	}
	#canvas-brush .big {
	  font-size: 16px;
	}
	 
	#canvas-control span{
	  display: inline-block;
	  font-size: 14px;
	  width: 20px;
	  height: 15px;
	  margin-left: 10px;
	  cursor: pointer;
	}
	#canvas-control .active,
	#canvas-brush .active {
	  color: #f2849e;
	}
</style>
