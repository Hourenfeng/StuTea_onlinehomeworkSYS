<template>
<!--  <div>
    <el-row :gutter="10">
      <el-col :span="12">
        <div id="myChart6" style="width:500px;height:500px" ref="chart" ></div>
      </el-col>
      <el-col :span="12">
        <div id="myChart7" style="width:500px;height:500px" ></div>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :span="12">
        <div id="myChart8" style="width:500px;height:500px" ></div>
      </el-col>
      <el-col :span="12">
        <div id="myChart9" style="width:500px;height:500px" ></div>
      </el-col>
    </el-row>
  </div> -->

  <div>
	  <el-row :gutter="24">
		<el-col  :span="8">
			<el-card class="box-card"  shadow="hover">
				<div slot="header" class="clearfix">
				</div>
				<div id="myChart6" style="width:500px;height:500px" ref="chart" ></div>
			</el-card>
		</el-col>
		
		<el-col :span="8">  
			<el-card class="box-card" shadow="hover">
				<div slot="header" class="clearfix">
				</div>
				<div id="myChart61" style="width:500px;height:500px" ></div>
			</el-card>
		 </el-col>
		 <el-col :span="8">
		 	<el-card class="box-card" shadow="hover">
		 		<div slot="header" class="clearfix">
		 		</div>
		 		<div id="myChart62" style="width:500px;height:500px" ></div>
		 	</el-card>
		 </el-col>
	  </el-row>
	  
	  
<!-- 	  <el-row :gutter="50">
		<el-rol :span="4">
				<el-card class="box-card" shadow="hover">
					<div slot="header" class="clearfix">
					</div>
					<div id="myChart9" style="width:500px;height:500px" ></div>
				</el-card>
			
				
			
		</el-rol>
		<el-rol :span="16">			
			<el-card class="box-card" shadow="hover">
			  	<div slot="header" class="clearfix">
			  	</div>
			  	<div id="myChart8" style="width:500px;height:500px" ></div>
			</el-card>
		</el-rol>
		
		<el-rol :span="4">
			<el-card class="box-card" shadow="hover">
			  	<div slot="header" class="clearfix">
			  	</div>
			  	<div id="myChart7" style="width:500px;height:500px" ></div>
			</el-card>
		</el-rol> -->
	  <!-- </el-row>
	  -->
	 
	 <el-row :gutter="24">
	   <el-col :span="8">
	     <div id="myChart8" style="width:500px;height:500px" ></div>
	   </el-col>
	   <el-col :span="8">
	     <div id="myChart7" style="width:500px;height:500px" ></div>
	   </el-col>
	   <el-col :span="8">
	     <div id="myChart9" style="width:500px;height:500px" ></div>
	   </el-col>
	 </el-row>
	  
  </div>
</template>


<script>
const echarts = require('echarts');
require("echarts/lib/component/legend");
require('echarts/lib/chart/pie');
export default{
  data () {
    return {
      myChart6:null,
      myChart7:null,
      student:'',
      studentById:[],
      data:null,
      six_data:null,
      seven_data:[
        { value: 13, name: '0-20' },
        { value: 35, name: '20-40' },
        { value: 20, name: '40-60' },
        { value: 5, name: '60-80' },
        { value: 12, name: '80-100' }
      ],
      eight_data:null,
      nine_data:null,
	  myChart61:null,
	  myChart62:null,
	  sixa_data:null,
	  sixb_data:null,
	  
    };

  },
  watch:{
    six_data:function(){
      this.$nextTick(function(){
        this.initCharts6();
      })
    },
	
	sixa_data:function(){
		 this.$nextTick(function(){
			 this.initCharts61();
		 })
	},
	sixb_data:function(){
		 this.$nextTick(function(){
			 this.initCharts62();
		 })
	},
	
	
    seven_data:function(){
      this.$nextTick(function(){
        this.initCharts7();
      })
    },
    eight_data:function(){
      this.$nextTick(function(){
        this.initCharts8();
      })
    },
    nine_data:function(){
      this.$nextTick(function(){
        this.initCharts9();
      })
    },

  },
  methods: {
    initCharts6 () {
      if(this.myChart6!=null){
        this.myChart.dispose();
      }else{
        this.myChart6 = echarts.init(this.$refs.chart);
        this.myChart6.setOption({
          title: {
            text: '数学分数',
            top: '5%',
            left: '40%',
            right: '1%',
            bottom: '3%',
          },
		  barWidth:70,
          tooltip: {},
          xAxis: {
            data: ["平均分","最低分","最高分"]
          },
          yAxis: {},
          series: [{
            name: '分数',
            type: 'bar',
            data: this.six_data
          }]
        });
      }

    },
	//增
	initCharts61(){
	let myChart61 = echarts.init(document.getElementById("myChart61"));
	myChart61.setOption({
			color: ['#61a0a8'],
			      barWidth:70,
			title: {
						  text: '语文分数',
						  top: '5%',
						  left: '40%',
						  right: '1%',
						  bottom: '3%',
						  },
			tooltip: {},
			xAxis: {
			  data: ["平均分","最低分","最高分"]
			},
			yAxis: {},
			series: [{
			  name: '语文分数',
			  type: 'bar',
			  data: this.sixa_data
			}]  
	})
	},
	
	initCharts62(){
	let myChart62 = echarts.init(document.getElementById("myChart62"));
	myChart62.setOption({
			  barWidth:70,
			title: {
						  text: '英语分数',
						  top: '5%',
						  left: '40%',
						  right: '1%',
						  bottom: '3%',
						  },
			tooltip: {},
			xAxis: {
			  data: ["平均分","最低分","最高分"]
			},
			yAxis: {},
			series: [{
			  name: '英语分数',
			  type: 'bar',
			  data: this.sixb_data
			}]  
	})
	},
	
    initCharts7 () {
      let myChart7 = echarts.init(document.getElementById("myChart7"));
      myChart7.setOption({
        title: {
          text: '分数占比',
          left: 'center',
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          top: '5%',
          left: 'center',
        },
        series: [
          {
            name:"次数",
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },

            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false,
            },
            data: this.seven_data
          }
        ]
      });
    },
    initCharts8 () {
      let myChart8 = echarts.init(document.getElementById("myChart8"));
      myChart8.setOption({
        // tooltip: {
        //   trigger: 'axis',
        //   axisPointer: {
        //     type: 'shadow'
        //   }
        // },
        // grid: {
        //   left: '3%',
        //   right: '4%',
        //   bottom: '3%',
        //   containLabel: true
        // },
        // xAxis: [
        //   {
        //     type: 'category',
        //     data: ['提交作业次数', '未提交作业次数'],
        //     axisTick: {
        //       alignWithLabel: true
        //     }
        //   }
        // ],
        // yAxis: [
        //   {
        //     type: 'value'
        //   }
        // ],
        // series: [
        //   {
        //     name: 'Direct',
        //     type: 'bar',
        //     barWidth: '60%',
        //     data: this.eight_data
        //   }
        // ]
        title: {
          text: '作业提交情况',
          left: 'center'
        },
		barWidth:70,
        xAxis: {
          type: 'category',
          data: ['提交作业次数', '未提交作业次数']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.eight_data,
            type: 'line',
            smooth: true
          }
        ]

      });
    },
    //第九个图表
    initCharts9 () {
      let myChart9 = echarts.init(document.getElementById("myChart9"));
      myChart9.setOption({
        title: {
          text: '提交成功作业提交次数',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: ['提交一次', '提交多次'],
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
			max: 10,
			min: 0,
			interval:1,
          }
        ],
        series: [
          {
            name: 'Direct',
            type: 'bar',
            barWidth: '60%',
            data: this.nine_data
          }
        ]

      });
    },

  },

  mounted () {
    this.student=JSON.parse(sessionStorage.getItem("student"));
    // console.log(this.teacher.teacherId);
    this.studentById.studentId=this.student.studentId;
    var param=this.$qs.stringify(this.studentById);

    //第六个图表
    this.$axios
        .post("/chart/sixChart",param)
        .then(res => {
          var six_data=[];
          six_data.push(res.data[0].scoreavg);
          six_data.push(res.data[0].scoremin);
          six_data.push(res.data[0].scoremax);
          this.six_data=six_data;
        }).catch((err) => {
      console.log(err)
      alert('请求失败！请检查后端接口！！！！')
    })
	
	//增   语文
	this.$axios
	.post("/chart/sixaChart",param)
	.then(res => {
			  
			  var sixa_data=[]; 
			  sixa_data.push(res.data[0].scoreavg2);
			  sixa_data.push(res.data[0].scoremin2);
			  sixa_data.push(res.data[0].scoremax2);
			  this.sixa_data=sixa_data;
	}).catch((err) => {
	    console.log(err)
	    alert('请求失败！请检查后端接口！！！！')
	})
	// 增 英语
	this.$axios
	.post("/chart/sixbChart",param)
	.then(res => {
			  
			  var sixb_data=[]; 
			  sixb_data.push(res.data[0].scoreavg3);
			  sixb_data.push(res.data[0].scoremin3);
			  sixb_data.push(res.data[0].scoremax3);
			  this.sixb_data=sixb_data;
	}).catch((err) => {
	    console.log(err)
	    alert('请求失败！请检查后端接口！！！！')
	})
	
    //第七个图表
    this.$axios
        .post("/chart/sevenChart",param)
        .then(res => {
		
          var seven_data=[];
          seven_data.push({
			  value:res.data[0].s1,
			  name:'0-20分'
		  });
          seven_data.push({
			  value:res.data[0].s2,
			  name:'20-40分'
		  });
          seven_data.push({
			  value:res.data[0].s3,
			  name:'40-60分'
		  });
          seven_data.push({
			  value:res.data[0].s4,
			  name:'60-80分'
		  });
          seven_data.push({
			  value:res.data[0].s5,
			  name:'80-100分'
		  });
          this.seven_data=seven_data;
		  console.log(this.seven_data);
        }).catch((err) => {
      console.log(err)
      alert('请求失败！请检查后端接口！！！！')
    })
    //第八个图表
    this.$axios
        .post("/chart/eightChart",param)
        .then(res => {
          var eight_data=[];
          eight_data.push(res.data[0].doit);
          eight_data.push(res.data[0].nodoit);
          this.eight_data=eight_data;
        }).catch((err) => {
      console.log(err)
      alert('请求失败！请检查后端接口！！！！')
    })
    //第九个图表
    this.$axios
        .post("/chart/nineChart",param)
        .then(res => {
          var nine_data=[];
          nine_data.push(res.data[0].sub);
          nine_data.push(res.data[0].subup);
          this.nine_data=nine_data;
        }).catch((err) => {
      console.log(err)
      alert('请求失败！请检查后端接口！！！！')
    })

  },

  //自适应
  init(){
    const self = this;//因为箭头函数会改变this指向，指向windows。所以先把this保存
    setTimeout(() => {
      window.addEventListener('resize', function() {
        self.chart = self.$echarts.init(self.$refs.Echart);
        self.chart.resize();
      })
    },10);
  },


}

</script>

<style >
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 450px;
	padding:10px;
  }
</style>
