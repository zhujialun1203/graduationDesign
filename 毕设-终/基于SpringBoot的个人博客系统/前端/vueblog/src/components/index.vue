<template >
  <div>
    <el-row :gutter="10">
      <el-col :span="18">
        <el-row :gutter="10">
          <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
            <el-card shadow="hover" style="margin-top:5%;">
              <span id="busuanzi_container_site_pv">
                本站总访问量
                <span id="busuanzi_value_site_pv"></span>次
              </span>
            </el-card>
          </el-col>
          <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
            <el-card shadow="hover" style="margin-top:5%;">
              <span id="busuanzi_container_site_uv">
                本站访客数
                <span id="busuanzi_value_site_uv"></span>人次
              </span>
            </el-card>
          </el-col>
          <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
            <el-card shadow="hover" style="margin-top:5%;">当前在线人数</el-card>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="24">
             <div
              style="height: 100%;width: 100%;">
              <chart ref="dschart" :options="polar" style="margin-top: 20px;background-color:#d3dce6"></chart>
            </div>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" style="margin-top:5%;" v-loading="cardloading">
          <div style="font-weight:bold;">系统公告</div>
          <el-divider></el-divider>
          <el-input
            type="textarea"
            style="color:black"
            :autosize="{ minRows: 10, maxRows: 15}"
            placeholder="请输入内容"
            v-model="notice.notice"
            :disabled="!isAdmin"
          ></el-input>
         
            <el-button
              :disabled="!isAdmin"
              style="width:80px;margin-top:20px;"
              @click="updateNotice()"
            >
            </el-button>

        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card shadow="hover" style="margin-top:5%;">
          文章比例
          <div id="pieReport" style="width: 100%;height: 200%;"></div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" style="margin-top:5%;">
          网站用户人数
          <div id="pieReport2" style="width: 100%;height: 200%;"></div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" style="margin-top:5%;">
          鼠标悬浮时显示
          <div id="pieReport3" style="width: 100%;height: 200%;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getRequest,putRequest} from "../utils/api";
import 'echarts/lib/chart/line'
import 'echarts/lib/component/tooltip'

import 'echarts/lib/component/polar'

import 'echarts/lib/component/legend'
import 'echarts/lib/component/title'
import 'echarts/theme/dark'
import 'echarts/lib/chart/bar'

var echarts = require("echarts");
 import ECharts from 'vue-echarts/components/ECharts.vue'

export default {
  components: {
    'chart': ECharts
  },
  data() {
    return {
          polar: {
          title: {
            text: ''
          },
          toolbox: {
            show: true,
            feature: {
              dataZoom: {
                yAxisIndex: 'none'
              },
              dataView: {
                readOnly: false
              },
              magicType: {
                type: ['line', 'bar']
              },
              restore: {},
              saveAsImage: {}
            }
          },
          tooltip: {},
          legend: {
            data: ['pv']
          },
          xAxis: {
            data: []
          },
          yAxis: {},
          series: [{
            name: 'pv',
            type: 'line',
            data: []
          }],
          animationDuration: 3000
        },
      isAdmin: true,
      notice: {},
      isCollapse: false,
      cardloading:false
    };
  },
  mounted() {
    var _this = this;
     this.cardloading =false
      getRequest("/api-article/article/dataStatistics").then(resp=> {
        if (resp.status == 200) {
          _this.$refs.dschart.options.xAxis.data = resp.data.categories;
          _this.$refs.dschart.options.series[0].data = resp.data.ds;
        } else {
          _this.$message({type: 'error', message: '数据加载失败!'});
        }
      }, resp=> {
        _this.$message({type: 'error', message: '数据加载失败!'});
      });
    getRequest("/api-user/notice/").then(resp => {
      if (resp.status == 200) {
        _this.notice = resp.data;
         _this.$alert("公告："+_this.notice.notice, {
      confirmButtonText: '确定',
      callback: action => {
      }
    });
      }
     
    });
     getRequest("/isAdmin").then(resp=> {
        if (resp.status == 200) {
          _this.isAdmin = resp.data;
        }
      })
    this.$nextTick(function() {
      // this.drawLine();
      this.drawPie("pieReport");
      this.drawPie("pieReport2");
      this.drawPie("pieReport3");
    });
  },
  methods: {
    updateNotice() {
      var _this = this;
       _this.cardloading=true;
        var _this = this;
        this.$confirm('确认修改?', '提示', {
          type: 'warning',
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(()=>{
             putRequest("/api-user/admin/notice", {notice:_this.notice.notice}).then(resp=> {
          if (resp.status == 200 && resp.data.status == 'success') {
            _this.$message({type: resp.data.status, message: resp.data.msg});
              _this.cardloading=false;
          } else {
            _this.cardloading=false;
            _this.$message({type: 'error', message: '更新失败!'});
          }
        }, resp=> {
          _this.cardloading= false;
          if (resp.response.status == 403) {
            var data = resp.response.data;
            _this.$message({type: 'error', message: data});
          }
        });
        })
    
    },
    drawPie(id) {
      this.charts = echarts.init(document.getElementById(id));
      this.charts.setOption({
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          left: 10,
          data: ["Vue", "JavaEE", "Git", "MongoDB", "Linux"]
        },
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: ["50%", "70%"],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: "center"
            },
            emphasis: {
              label: {
                show: true,
                fontSize: "30",
                fontWeight: "bold"
              }
            },
            labelLine: {
              show: false
            },
            data: [
              { value: 335, name: " Vue" },
              { value: 310, name: "JavaEE" },
              { value: 234, name: "Git" },
              { value: 135, name: "MongoDB" },
              { value: 1548, name: "Linux" }
            ]
          }
        ]
      });
    },

  }
};
</script>

<style>
.el-row {
  margin-bottom: 0px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>