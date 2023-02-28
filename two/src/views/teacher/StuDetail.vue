<template>
  <div class="studentDetail">
    <el-container>
      <el-main>
        <router-view />
        <el-page-header @back="goBack" content="学生情况" title="返回学生列表">
        </el-page-header>
        <div style="width: 100%; overflow: hidden;">
          <div id="studentTable" style="float: left;width: 50%; float: left;">
            <div id="show_table">
              <el-table :data="onestudent">
                <el-table-column label="作业" prop="title"></el-table-column>
                <el-table-column label="分数" prop="score"></el-table-column>
              </el-table>
            </div>
            <div id="page_number">
              第{{pageNum}}页，共{{pageTotal}}条
            </div>
            <div id="page_content" style="float: right">
              <el-pagination background layout="prev,pager,next" :total="pageTotal" :page-size="pageSize"
                             @current-change="pageData">
              </el-pagination>
            </div>
          </div>
          <div id="mychart5"  style="float: right;width: 50%;float: right;height: 500px;" ref="chart" >

          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import TeacherHeader from '@/views/common/TeacherHeader';

//echarts引入
const echarts = require('echarts');
const labelRight = {
  position: 'right'
};

export default {
  name: 'StuDetail',
  components: {
    TeacherHeader
  },
  data() {
    return {
      onestudent: [],
      student: [],
      head: [],
      pageNum: 1, //当前页
      pageTotal: 0, //总条数
      pageSize: 5 ,//每页显示几条

      //echarts
      student1:'',
      studentByName:[],
      five_data:null,
      myChart5:null,
    };
  },

  //监听
  watch:{
    five_data:function(){
      this.$nextTick(function(){
        this.initCharts5();
      })
    },


  },


  methods: {
	  goBack(){
	  	this.$router.go(-1);
	  	console.log("go back");
	  },
    pageData(curr) {
      this.pageNum = curr;
      this.init();
    },
    init() {
      this.onestudent.pageNum = this.pageNum;
      this.onestudent.pageSize = this.pageSize;
      this.onestudent.loginName = this.student.loginName;
      //把json对象转为字符串
      var param = this.$qs.stringify(this.onestudent);
      console.log(param);
      this.$axios
          .post("/student/findAllStuHomework", param)
          .then(rst => {
            console.log(rst);
            this.pageTotal = rst.data.total;
            this.onestudent = rst.data.list;
            console.log(rst.data.list);

          }).catch(ex => {
        console.log(ex);
      });
    },


    //echarts
    initCharts5(){
      //let myChart5 = echarts.init(this.$refs.chart);
      if(this.myChart5!=null){
        this.myChart.dispose();
      }else{
        this.myChart5 = echarts.init(this.$refs.chart);
        this.myChart5.setOption({
          title: {
            text: '作业完成情况',
            top: '5%',
            left: '40%',
            right: '1%',
            bottom: '3%',
          },
          tooltip: {},
          xAxis: {
            data: ["作业总次数","作业平均分","最低分","最高分"]
          },
          yAxis: {},
          series: [{
            name: '分数',
            type: 'bar',
            data: this.five_data
          }]
        });
      }
    },
  },


  created() {
    this.student = JSON.parse(sessionStorage.getItem("onestudent"));
    console.log(this.student);
    this.init();
  },

  //echarts
  mounted () {
    this.student1=JSON.parse(sessionStorage.getItem("onestudent"));
    this.studentByName.loginName=this.student1.loginName;
    var param=this.$qs.stringify(this.studentByName);
    console.log(this.student1);
    //第五个图表
    this.$axios
        .post("/chart/fiveChart",param)
        .then(res => {

          //console.log(res);
          var five_data=[];
          five_data.push(res.data[0].b1);
          five_data.push(res.data[0].scoreavgde);
          five_data.push(res.data[0].scoreminde);
          five_data.push(res.data[0].scoremaxde);
          this.five_data=five_data;
          console.log(this.five_data)
        }).catch((err) => {
      console.log(err)
      alert('请求失败！请检查后端接口！！！！')
    })
    //this.initCharts5();
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

<style scoped>
</style>
