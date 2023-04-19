<template>

  <el-row :gutter="20">
    <el-col :xs="24" :sm="24" :lg="6">
      <div class="chart-wrapper">
        <div id="cpuInfo" :style="{height:height,width:width}"/>
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :lg="6">
      <div class="chart-wrapper">
        <div id="memInfo" :style="{height:height,width:width}"/>
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :lg="6">
      <div class="chart-wrapper">
        <div id="netInfo" :style="{height:height,width:width}"/>
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :lg="6">
      <div class="chart-wrapper">
        <div id="diskInfo" :style="{height:height,width:width}"/>
      </div>
    </el-col>
  </el-row>
</template>

<script>
  import * as echarts from 'echarts';
  import resize from './mixins/resize'

  require('echarts/theme/macarons') // echarts theme

  const animationDuration = 6000

  export default {
    mixins: [resize],
    props: {
      classCpu: {
        type: String,
        default: 'chart'
      },
      width: {
        type: String,
        default: '100%'
      },
      height: {
        type: String,
        default: '300%'
      }
    },
    data() {
      return {
        cpuChart: null,
        memChart: null,
        netChart: null,
        diskChart: null,
        cpuVal: null
      }
    },
    mounted() {
      this.$nextTick(() => {
        this.initCpuInfo()
      })
    },
    beforeDestroy() {
      if (!this.chart) {
        return
      }
      this.cpuChart.dispose()
      this.memChart.dispose()
      this.netChart.dispose()
      this.diskChart.dispose()
      this.cpuChart = null
      this.cpuChart = null
      this.netChart = null
      this.diskChart = null
    },
    methods: {
      initCpuInfo() {
        let cpuDom = document.getElementById('cpuInfo');
        let memDom = document.getElementById('memInfo');
        let netDom = document.getElementById('netInfo');
        let diskDom = document.getElementById('diskInfo');

        this.cpuChart = echarts.init(cpuDom, 'macarons')
        this.memChart = echarts.init(memDom, 'macarons')
        this.netChart = echarts.init(netDom, 'macarons')
        this.diskChart = echarts.init(diskDom, 'macarons')

        let cpuOpt={
          series: [
            {
              type: 'gauge',
              axisLine: {
                lineStyle: {
                  width: 15,
                  color: [
                    [0.3, '#67e0e3'],
                    [0.7, '#37a2da'],
                    [1, '#fd666d']
                  ]
                }
              },
              pointer: {
                itemStyle: {
                  color: 'inherit'
                }
              },
              axisTick: {
                distance: -15,
                length: 5,
                lineStyle: {
                  color: '#fff',
                  width: 1.3
                }
              },
              splitLine: {
                distance: -20,
                length: 20,
                lineStyle: {
                  color: '#fff',
                  width: 2.8
                }
              },
              axisLabel: {
                color: 'inherit',
                distance: 18,
                fontSize: 15
              },
              detail: {
                valueAnimation: true,
                formatter: '{value} %',
                color: 'inherit'
              },
              detail: {
                valueAnimation: true,
                formatter: '{value} %',
                color: 'inherit'
              },
              data: [
                {
                  value: +(Math.random() * 100).toFixed(1)
                }
              ]
            }
          ]
        }
        let memOpt=Object.assign({},cpuOpt);
        let netOpt=Object.assign({},cpuOpt);;
        let diskOpt=Object.assign({},cpuOpt);;

        cpuOpt.title={
          text:"CPU"
        }

        memOpt.title={
          text:"内存"
        }

        netOpt.title={
          text:"网络"
        }

        diskOpt.title={
          text:"磁盘"
        }


        this.cpuChart.setOption(cpuOpt);
        this.memChart.setOption(memOpt);
        this.netChart.setOption(netOpt);
        this.diskChart.setOption(diskOpt);

        this.randomData(this.cpuChart)
        this.randomData(this.memChart)
        this.randomData(this.netChart)
        this.randomData(this.diskChart)
      },
      randomData(chartParam) {
        setInterval(function (chart) {
          chart.setOption({
            series: [
              {
                data: [
                  {
                    value: +(Math.random() * 100).toFixed(2)
                  }
                ]
              }
            ]
          });
        }, 2000, chartParam);
      }

    }
  }
</script>
<style lang="scss" scoped>
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin: 0 auto;
    margin-bottom: 32px;
  }
</style>
