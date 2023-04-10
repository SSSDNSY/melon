<template>

  <el-row :gutter="20">
    <el-col :xs="24" :sm="24" :lg="6">
      <div class="chart-wrapper">
        <div id="cpuInfo" :class="classCpu" :style="{height:height,width:width}" />
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :lg="6">
      <div class="chart-wrapper">
<!--        <div :class="classMem" :style="{height:height,width:width}" />-->
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :lg="6">
      <div class="chart-wrapper">
<!--        <div :class="classDisk" :style="{height:height,width:width}" />-->
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :lg="6">
      <div class="chart-wrapper">
<!--        <div :class="classNet" :style="{height:height,width:width}" />-->
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
        default: '280%'
      }
  },
  data() {
    return {
      chart: null
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
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initCpuInfo() {
      let chartDom = document.getElementById('cpuInfo');
      this.chart = echarts.init(chartDom, 'macarons')

      this.chart.setOption({
        series: [
          {
            type: 'gauge',
            axisLine: {
              lineStyle: {
                width: 10,
                color: [
                  [0.3, '#67e0e3'],
                  [0.7, '#37a2da'],
                  [1, '#fd666d']
                ]
              }
            },
            pointer: {
              itemStyle: {
                color: 'inherit',
                fontSize: 25
              }
            },
            axisTick: {
              distance: -20,
              length: 8,
              lineStyle: {
                color: '#fff',
                width: 8
              }
            },
            splitLine: {
              distance: -10,
              length: 8,
              lineStyle: {
                color: '#fff',
                width: 2
              }
            },
            axisLabel: {
              color: 'inherit',
              distance: 10,
              fontSize: 10
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
      });
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
      }, 2000,this.chart);

    },

  }
}
</script>
<style lang="scss" scoped>
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
</style>
