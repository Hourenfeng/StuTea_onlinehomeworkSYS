<template>
  <div ref="canvasContRef" class="canvas-container">
    <div v-show="imgIsLoad" ref="canvasWrapRef" class="canvas-wrap" :style="canvasStyle">
      <!-- 用于拖拽内容的截图，不显示 -->
      <drag-step ref="stepWrapRef" :drag-list="dragList" :drag-style="stepWrapStyle" :is-hide="true" />
      <!-- 用于显示拖拽内容 -->
      <drag-step :drag-list="dragList" />
      <!-- 画布 -->
      <canvas ref="canvasRef" @drop="drop" @dragover="dragover" />
    </div>
    <canvas-toolbar @changeTool="changeTool" />
  </div>
</template>
 
<script>
import CanvasToolbar from './canvasToolbar'
import DragStep from './dargStep.vue'
import domtoimage from 'dom-to-image'
import { mixImg } from 'mix-img'
export default {
  name: 'CanvasContainer',
  components: { CanvasToolbar, DragStep },
  props: {
    imgUrl: {
      type: String,
      require: true,
      default: ''
    }
  },
  data() {
    return {
      canvas: null,
      ctx: null,
      imgIsLoad: false,
      // 所使用的工具名称 drag draw
      toolName: '',
      // 画布的属性值
      canvasValue: {
        width: 0,
        height: 0,
        left: 0,
        top: 0,
        scale: 1,
        rotate: 0,
        cursor: 'default'
      },
      // 拖拽的元素列表
      dragList: [],
      // 记录当前画布的操作（暂时没用）
      imgData: null,
      // 记录每一步操作
      preDrawAry: []
    }
  },
  computed: {
    canvasStyle() {
      const { width, height, left, top, scale, rotate, cursor } = this.canvasValue
      return {
        width: `${width}px`,
        height: `${height}px`,
        left: `${left}px`,
        top: `${top}px`,
        transform: `rotate(${rotate}deg) scale(${scale})`,
        cursor: cursor
        // backgroundImage: `url(${this.imgUrl})`
      }
    },
    // 上层拖拽样式（用于dom截图）
    stepWrapStyle() {
      const { width, height } = this.canvasValue
      return {
        width: `${width}px`,
        height: `${height}px`,

      }
    }
  },
  mounted() {
    const canvas = this.$refs.canvasRef
    const ctx = canvas.getContext('2d')
    this.loadImg(canvas, ctx)
    this.changeTool('drag')
    // 监听窗口发生变化
    window.addEventListener('resize', this.reload)
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.reload)
  },
  methods: {
    // 监听窗口发生变化
    reload() {
      this.$nextTick(() => {
        const canvas = this.$refs.canvasRef
        this.canvasCenter(canvas)
      })
    },
 
    // 加载图片
    loadImg(canvas, ctx) {
      const img = new Image()
      // 图片加载成功
      img.onload = () => {
        console.log('图片加载成功')
        this.imgIsLoad = true
        canvas.width = img.width
        canvas.height = img.height
		this.canvasValue.width=img.width
		this.canvasValue.height=img.height
        canvas.style.backgroundImage = `url(${this.imgUrl})`
        this.canvasCenter(canvas)
 
        // this.loadHistory(ctx)
      }
      // 图片加载失败
      img.onerror = () => {
        console.log('图片加载失败！')
      }
      img.src = this.imgUrl
    },
 
    // 加载历史画笔记录 img是保存的base64格式的画笔轨迹图
    loadHistory(ctx, img) {
      const imgCatch = new Image()
      imgCatch.src = img
      imgCatch.onload = () => {
        ctx.drawImage(imgCatch, 0, 0, imgCatch.width, imgCatch.height)
      }
    },
 
    // 切换工具
    changeTool(name) {
      console.log(name)
      // 清除拖拽的按下事件
      const wrapRef = this.$refs.canvasWrapRef
      wrapRef.onmousedown = null
      const canvas = this.$refs.canvasRef
      const ctx = canvas.getContext('2d')
      switch (name) {
        case 'drag':
          this.dragCanvas(canvas)
          break
        case 'draw':
          this.drawPaint(canvas, ctx)
          break
        case 'eraser':
          this.eraser(canvas, ctx)
          break
        case 'revoke':
          this.revoke(canvas, ctx)
          break
        case 'clear':
          this.clearCanvas(canvas, ctx)
          break
        case 'save':
          this.saveCanvas(canvas)
          break
        case 'rotate':
          
		  this.canvasValue.rotate=this.canvasValue.rotate + 90
          break
        case 'enlarge':
		  this.canvasValue.scale=this.canvasValue.scale + 0.2
          break
        case 'narrow':
		  this.canvasValue.scale=this.canvasValue.scale - 0.2
          break
        default:
          break
      }
    },
 
    // 拖拽画布
    dragCanvas(canvas) {
      console.log('dragCanvas')
      // 清除上次监听的事件
      const wrapRef = this.$refs.canvasWrapRef
      const container = this.getPosition(this.$refs.canvasContRef)
      let isDown = false
 
      wrapRef.onmousedown = (e) => {
        isDown = true
		this.canvasValue.cursor="move"
        // 算出鼠标相对元素的位置
        const disX = e.clientX - wrapRef.offsetLeft
        const disY = e.clientY - wrapRef.offsetTop
 
        document.onmousemove = (e) => {
          if (!isDown) return
          // 用鼠标的位置减去鼠标相对元素的位置，得到元素的位置
          let left = e.clientX - disX
          let top = e.clientY - disY
 
          // 判断canvas是否在显示范围内，减4是border=2px的原因
          const width = container.width - canvas.width / 2 - 4
          const height = container.height - canvas.height / 2 - 4
          left = Math.min(Math.max(-canvas.width / 2, left), width)
          top = Math.min(Math.max(-canvas.height / 2, top), height)

		  this.canvasValue.left=left
		  this.canvasValue.top=top
        }
 
        document.onmouseup = (e) => {
          isDown = false
          document.onmousemove = null
		  this.canvasValue.cursor="default"
        }
      }
    },
 
    // 画笔
    drawPaint(canvas, ctx) {
       const wrapRef = this.$refs.canvasWrapRef
      canvas.onmousedown = (e) => {
		this.canvasValue.cursor="crosshair"
        this.imgData = ctx.getImageData(0, 0, canvas.width, canvas.height)
        this.preDrawAry.push(this.imgData)
        ctx.beginPath()
        ctx.lineWidth = 2
        ctx.strokeStyle = 'red'
        ctx.moveTo(e.offsetX, e.offsetY)
 
        canvas.onmousemove = (e) => {
          ctx.lineTo(e.offsetX, e.offsetY)
          ctx.stroke()
        }
      }
 
      // 鼠标抬起取消鼠标移动的事件
      document.onmouseup = (e) => {
        canvas.onmousemove = null
        ctx.closePath()
		this.canvasValue.cursor="default"
		
      }
 
      // 鼠标移出画布时 移动事件取消
      // document.onmouseout = (e) => {
      //   document.onmousemove = null
      //   ctx.closePath()
      // }
    },
 
    // 橡皮擦
    eraser(canvas, ctx, r = 10) {
      // const wrapRef = this.$refs.canvasWrapRef
      canvas.onmousedown = (e) => {
        this.imgData = ctx.getImageData(0, 0, canvas.width, canvas.height)
        this.preDrawAry.push(this.imgData)
        let x1 = e.offsetX
        let y1 = e.offsetY
 
        // 鼠标第一次点下的时候擦除一个圆形区域，同时记录第一个坐标点
        ctx.save()
        ctx.beginPath()
        ctx.arc(x1, y1, r, 0, 2 * Math.PI)
        ctx.clip()
        ctx.clearRect(0, 0, canvas.width, canvas.height)
        ctx.restore()
 
        canvas.onmousemove = (e) => {
          const x2 = e.offsetX
          const y2 = e.offsetY
          // 获取两个点之间的剪辑区域四个端点
          const asin = r * Math.sin(Math.atan((y2 - y1) / (x2 - x1)))
          const acos = r * Math.cos(Math.atan((y2 - y1) / (x2 - x1)))
 
          // 保证线条的连贯，所以在矩形一端画圆
          ctx.save()
          ctx.beginPath()
          ctx.arc(x2, y2, r, 0, 2 * Math.PI)
          ctx.clip()
          ctx.clearRect(0, 0, canvas.width, canvas.height)
          ctx.restore()
 
          // 清除矩形剪辑区域里的像素
          ctx.save()
          ctx.beginPath()
          ctx.moveTo(x1 + asin, y1 - acos)
          ctx.lineTo(x2 + asin, y2 - acos)
          ctx.lineTo(x2 - asin, y2 + acos)
          ctx.lineTo(x1 - asin, y1 + acos)
          ctx.closePath()
          ctx.clip()
          ctx.clearRect(0, 0, canvas.width, canvas.height)
          ctx.restore()
 
          // 记录最后坐标
          x1 = x2
          y1 = y2
        }
      }
 
      // 鼠标抬起取消鼠标移动的事件
      document.onmouseup = (e) => {
        canvas.onmousemove = null
      }
 
      // 鼠标移出画布时 移动事件取消
      // canvas.onmouseout = (e) => {
      //   canvas.onmousemove = null
      // }
    },
 
    // 撤销
    revoke(canvas, ctx) {
      if (this.preDrawAry.length > 0) {
        const popData = this.preDrawAry.pop()
        ctx.putImageData(popData, 0, 0)
      } else {
        this.clearCanvas(canvas, ctx)
      }
    },
 
    // 清空画布
    clearCanvas(canvas, ctx) {
      ctx.clearRect(0, 0, canvas.width, canvas.height)
    },
 
    // 保存
    saveCanvas(canvas) {
      const wrapRef = this.$refs.stepWrapRef.$el
      const { width, height } = this.canvasValue
      const image = canvas.toDataURL('image/png')
      console.log(this.preDrawAry)
      domtoimage.toPng(wrapRef)
        .then((dataUrl) => {
          console.log(dataUrl)
          const mixConfig = {
            'base': {
              'backgroundImg': image,
              'width': width,
              'height': height,
              'quality': 0.8,
              'fileType': 'png'
            },
            'dynamic': [{
              'type': 1,
              'size': {
                'dWidth': width,
                'dHeight': height
              },
              'position': {
                'x': 0,
                'y': 0
              },
              'imgUrl': dataUrl
            }]
          }
          mixImg(mixConfig).then(res => {
            console.log(res.data.base64)
          })
        })
        .catch((error) => {
          console.error('oops, something went wrong!', error)
        })
    },
 
    // 获取dom元素在页面中的位置与大小
    getPosition(target) {
      const width = target.offsetWidth
      const height = target.offsetHeight
      let left = 0
      let top = 0
      do {
        left += target.offsetLeft || 0
        top += target.offsetTop || 0
        target = target.offsetParent
      } while (target)
      return { width, height, left, top }
    },
 
    // canvas居中显示
    canvasCenter(canvas) {
      const wrap = this.getPosition(this.$refs.canvasContRef)
      const left = (wrap.width - canvas.width) / 2
      const top = (wrap.height - canvas.height) / 2
	  this.canvasValue.left=left
	  this.canvasValue.top=top
    },
 
    drop(e) {
      // e.preventDefault()
      const { type, value } = JSON.parse(e.dataTransfer.getData('data'))
      console.log(e.offsetX, e.offsetY)
      this.dragList.push({
        x: e.offsetX,
        y: e.offsetY,
        type,
        value
      })
    },
 
    dragover(e) {
      // 取消默认动作是为了drop事件可以触发
      e.preventDefault()
      // console.log(e)
    }
  }
}
</script>
 
<style scoped>
.canvas-container{
  position: relative;
  width: 100%;
  height: 400px;
  border: 2px solid #f0f;
  background-color: lightblue;
  box-sizing: border-box;
  overflow: hidden;
}
.canvas-container .canvas-wrap{
  position: absolute;
  transition: transform .3s;
  /* background-color: #ff0; */
}
.canvas-toolbar{
  width: 850px;
  height: 40px;
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);

  user-select: none;
}
</style>