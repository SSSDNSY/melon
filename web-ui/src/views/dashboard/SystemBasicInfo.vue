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
  import SockJS from  'sockjs-client';
  import  Stomp from 'stompjs';
  import resize from './mixins/resize'
  import Cookies from "js-cookie";

  require('echarts/theme/macarons') // echarts theme

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
        cpuVal: null,
        data: '',
        stompClient:'',
        timer:''
      }
    },
    mounted() {
      this.$nextTick(() => {
        this.initCpuInfo()
        this.initWebSocket()
      })
    },
    beforeDestroy() {
      // 页面离开时断开连接,清除定时器
      this.disconnect()
      clearInterval(this.timer)

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

      },
      //初始化链接
      initWebSocket() {
        this.connection();
        let that= this;
        // 断开重连机制,尝试发送消息,捕获异常发生时重连
        this.timer = setInterval(() => {
          try {
            that.stompClient.send("/app/welcome",{},JSON.stringify({sender: '',chatType: 'JOIN'}),)   //用户加入接口
            // console.log("******发送消息******")
          } catch (err) {
            console.log("断线了: " + err)
            that.connection()
          }
        }, 5000);
      },
      // 连接服务器
      connection() {
        console.log("******连接服务器******")
        // 建立连接对象
        let socket = new SockJS('http://127.0.0.1:8080/melon/endpoint');
        // 获取STOMP子协议的客户端对象
        this.stompClient = Stomp.over(socket);
        // 定义客户端的认证信息,按需求配置
        let headers = {
          "melon-token": Cookies.get('melon-token')
        }
        let that = this
        // 向服务器发起websocket连接
        this.stompClient.connect(headers,(t) => {
          console.log("******连接成功******",t)
          this.stompClient.subscribe('/topic/sysInfo',  function(data) { //订阅消息
            console.log("******收到消息******")
            //console.log(data)
            that.data = JSON.parse(data.body);
            that.setData( that.cpuChart, (that.data.cpu.sys+that.data.cpu.used).toFixed(2))
            that.setData( that.memChart, that.data.mem.usage)
            that.setData( that.netChart, that.data.jvm.usage)
            that.setData( that.diskChart, that.data.sysFiles[1].usage)

          })
        }, (err) => {
          // 连接发生错误时的处理函数
          console.log('连接错误',err)
        });
      },
      // 断开连接
      disconnect() {
        if (this.stompClient) {
          this.stompClient.disconnect()
        }
      },
      setData(chart,value) {
          chart.setOption({
            series: [
              {
                data: [
                  {
                    value:value
                  }
                ]
              }
            ]
          });
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
