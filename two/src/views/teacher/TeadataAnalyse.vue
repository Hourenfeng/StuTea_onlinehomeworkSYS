<template>
  <div class="block">
    <span class="demonstration">作业日期：</span>
    <el-date-picker
        v-model="value1"
        type="date"
        placeholder="选择日期">
    </el-date-picker>
  </div>

  <div>
    <el-row :gutter="10">
      <el-col :span="12">
        <div id="myChart1" style="width:500px;height:500px" ref="chart" ></div>
      </el-col>
      <el-col :span="12">
        <div id="myChart2" style="width:500px;height:500px" ></div>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :span="12">
        <div id="myChart3" style="width:500px;height:500px" ></div>
      </el-col>
      <el-col :span="12">
        <div id="myChart4" style="width:500px;height:500px" ></div>
      </el-col>
    </el-row>
  </div>

</template>


<script>
import 'echarts-liquidfill' //水球图
const echarts = require('echarts');
const labelRight = {
  position: 'right'
};

export default{
  data () {
    return {
      myChart1:null,
      value1:'',
      teacher:'',
      teacherById:[],
      data:null,
      four_data:null,
      pie_data:[
        { value: 13, name: '80-100' },
        { value: 35, name: '60-80' },
        { value: 20, name: '40-60' },
        { value: 5, name: '20-40' },
        { value: 1, name: '0-20' }
      ]
    };

  },
  watch:{
    data:function(){
      this.$nextTick(function(){
        this.initCharts();
      })
    },
    four_data:function(){
      this.$nextTick(function(){
        this.initCharts4();
      })
    },
    pie_data:function(){
      this.$nextTick(function(){
        this.initCharts2();
      })
    },


  },
  methods: {
    initCharts () {
      if(this.myChart1!=null){
        this.myChart.dispose();
      }else{
        this.myChart1 = echarts.init(this.$refs.chart);
        this.myChart1.setOption({
          title: {
            text: '分数',
            top: '5%',
            left: '40%',
            right: '1%',
            bottom: '3%',
          },
          tooltip: {},
          xAxis: {
            data: ["最低分","平均分","最高分"]
          },
          yAxis: {},
          series: [{
            name: '分数',
            type: 'bar',
            data: this.data
          }]
        });
      }

    },

    initCharts2 () {
      let myChart2 = echarts.init(document.getElementById("myChart2"));
      myChart2.setOption({
        // title: {
        //   text: '分数占比统计',
        //   left: 'center'
        // },
        // tooltip: {
        //   trigger: 'item',
        //   formatter: '{a} <br/>{b} : {c} ({d}%)'
        // },
        // legend: {
        //   top: '5%',
        //   left: 'center'
        // },
        // series: [
        //   {
        //     name: 'Access From',
        //     type: 'pie',
        //     radius: ['40%', '70%'],
        //     avoidLabelOverlap: false,
        //     itemStyle: {
        //       borderRadius: 10,
        //       borderColor: '#fff',
        //       borderWidth: 2
        //     },
        //     label: {
        //       show: false,
        //       position: 'center'
        //     },

        //     emphasis: {
        //       label: {
        //         show: true,
        //         fontSize: '40',
        //         fontWeight: 'bold'
        //       }
        //     },
        //     labelLine: {
        //       show: false
        //     },
        //     data: this.pie_data
        //   }
        // ]
		      title: {
		        text: '分数占比统计',
		        left: 'center',
		        bottom: 50,
		        top: 170,
		        containLabel: true,
		      },
		
		      legend: {
		        bottom: 10,
		        left: 'center',
		        textStyle:{
		          fontSize:10
		        },
		        x:'right',
		        y:'bottom',
				
		      },
		      series: [{
		        type: 'pie',
		        radius: ['40%', '60%'],
		        clockwise: false,
		        center: ['50%', '40%'],
		        labelLine: {
		          smooth: true
		        },
		        label: {
		        // formatter: '{b}: {d}%',
		        normal: {
		          formatter: '{b}\n{c}名\n{per|{d}%}',
		          rich: {
		            b: {
		              fontSize: 12,
		              height: 60,
		              lineHeight: 20,
		            },
		          }
		        }
		        },
		        itemStyle: {
		          color: function (params) {
		            let colorList = [
		              ['#FFBBA8', '#FF3E3E'],
		              ['#766EF9', '#FF9CF3'],
		              ['#7DB6EE', '#90ECC7'],
		              ['#FFE18F', '#FF9C62'],
		              ['#766EF9', '#FF9CF3'],
		            ]
		            let index = params.dataIndex
		            if (params.dataIndex >= colorList.length) {
		              index = params.dataIndex - colorList.length
		            }
		            return new echarts.graphic.LinearGradient(0, 0, 0, 1,
		              [{
		                offset: 0,
		                color: colorList[index][0]
		              },
		              {
		                offset: 1,
		                color: colorList[index][1]
		              }
		              ])
		          }
		        },
		        data: this.pie_data,
		        },
		      ]
      });
    },
    initCharts3(){
      let myChart3 = echarts.init(document.getElementById("myChart3"));
      myChart3.setOption({
        title: {
          text: '及格率',
          left: 'center'
        },
        grid:{
          top:'10%',
          left:'3%',
          right:'4%',
          bottom:'3%',
          containLabel:true
        },
        series: [{
          type: 'liquidFill',
          radius: '100px',
          data: [0.6],
          label: {
            normal: {
              color: '#fff',
              insideColor: 'transparent',
              textStyle: {
                fontSize: 30,
                fontWeight: 'bold',
              }
            }
          },
          color: [{
            type: 'linear',
            x: 0,
            y: 1,
            x2: 0,
            y2: 0,
            colorStops: [{
              offset: 1,
              color: ['#6a7feb'], // 0% 处的颜色
            }, {
              offset: 0,
              color: ['#27e5f1'], // 100% 处的颜色
            }],
            global: false // 缺省为 false
          }],
          outline: {
            show: true,
            borderDistance: 5,
            itemStyle: {
              borderColor: 'rgba(67,209,100,1)',
              borderWidth: 0
            }
          }
        }]
      })
    },
    initCharts4 () {
      let myChart4 = echarts.init(document.getElementById("myChart4"));
      myChart4.setOption({
        title: {
          text: '作业完成情况',
          top: '5%',
          left: '40%',
          right: '1%',
          bottom: '3%',
        },
        tooltip: {},
        xAxis: {
          data: ["已完成人数","未完成人数"]
        },
        yAxis: {},
        series: [{
          name: '人数',
          type: 'bar',
          data: this.four_data
        }]
      });
    },
  },

  mounted () {
    this.teacher=JSON.parse(sessionStorage.getItem("teacher"));
    // console.log(this.teacher.teacherId);
    this.teacherById.teacherId=this.teacher.teacherId;
    var param=this.$qs.stringify(this.teacherById);
    // this.initCharts();
    //第一个图表
    this.$axios
        .post("/chart/barChart",param)
        .then(res => {

          // console.log(res);
          var data=[];
          data.push(res.data[0].scoremina);
          data.push(res.data[0].scoreavga);
          data.push(res.data[0].scoremaxa);
          this.data=data;
          // console.log(this.data)
          // console.log(res.data[0].scoreavg)
          // var data=[];
          // for(var i=0;i<res.data.length;i++){
          // 	 data.push(res.data[i].value);
          // console.log(res.data[i]);
          // }

          // this.data=data;
          // console.log(this.data);

        }).catch((err) => {
      console.log(err)
      alert('请求失败！请检查后端接口！！！！')
    })

    //this.initCharts2();
    this.initCharts3();
    //this.initCharts4();
    //第四个图表
    this.$axios
        .post("/chart/fourChart",param)
        .then(res => {

          console.log(res);
          var four_data=[];
          four_data.push(res.data[0].doita);
          four_data.push(res.data[0].nodoita);
          this.four_data=four_data;
          console.log(this.four_data)
        }).catch((err) => {
      console.log(err)
      alert('请求失败！请检查后端接口！！！！')
    })

    //第二个图表
    this.$axios
        .post("/chart/twoChart",param)
        .then(res => {

          console.log(res);
          var pie_data=[];
		  pie_data.push({
		  		value:res.data[0].sa,
		  			  name:'0-20分'
		  });
		  pie_data.push({
		  		value:res.data[0].sb,
		  			  name:'20-40分'
		  });
		  pie_data.push({
		  		value:res.data[0].sc,
		  			  name:'40-60分'
		  });
		  pie_data.push({
		  		value:res.data[0].sd,
		  			  name:'60-80分'
		  });
		  pie_data.push({
		  		value:res.data[0].se,
		  			  name:'80-100分'
		  });
          // pie_data.push(res.data[0].sa);
          // pie_data.push(res.data[0].sb);
          // pie_data.push(res.data[0].sc);
          // pie_data.push(res.data[0].sd);
          // pie_data.push(res.data[0].se);
          this.pie_data=pie_data;
          console.log(this.pie_data)
        }).catch((err) => {
      console.log(err)
      alert('请求失败！请检查后端接口！！！！')
    })

  },

  //自适应
  init(){
    const self = this;
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
  ::-webkit-scrollbar {
     width: 0 !important;
   }
   ::-webkit-scrollbar {
     width: 0 !important;height: 0;
   }
</style>
