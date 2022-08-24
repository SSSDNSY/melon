<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>配置管理中心</title>

    <#import "common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="${request.contextPath}/static/css/index.1.css">

</head>
<body class="hold-transition skin-blue sidebar-mini <#if cookieMap?exists && cookieMap["adminlte_settings"]?exists && "off" == cookieMap["adminlte_settings"].value >sidebar-collapse</#if> ">
<div class="wrapper">

    <@netCommon.commonHeader />

    <@netCommon.commonLeft "conf" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>报表示例</h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <!-- 任务信息 -->
            <div class="row">

                <#-- 任务信息 -->
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box bg-aqua">
                        <span class="info-box-icon"><i class="fa fa-flag-o"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text"></span>
                            <span class="info-box-number"></span>

                            <div class="progress">
                                <div class="progress-bar" style="width: 100%"></div>
                            </div>
                            <span class="progress-description"></span>
                        </div>
                    </div>
                </div>

                <#-- 调度信息 -->
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box bg-yellow">
                        <span class="info-box-icon"><i class="fa fa-calendar"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text"></span>
                            <span class="info-box-number"></span>

                            <div class="progress">
                                <div class="progress-bar" style="width: 100%"></div>
                            </div>
                            <span class="progress-description">

									<#--<#if jobLogCount gt 0>
                                        调度成功率：${(jobLogSuccessCount*100/jobLogCount)?string("0.00")}<small>%</small>
                                    </#if>-->
                            </span>
                        </div>
                    </div>
                </div>

                <#-- 执行器 -->
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box bg-green">
                        <span class="info-box-icon"><i class="fa ion-ios-settings-strong"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text"></span>
                            <span class="info-box-number"></span>

                            <div class="progress">
                                <div class="progress-bar" style="width: 100%"></div>
                            </div>
                            <span class="progress-description"></span>
                        </div>
                    </div>
                </div>

            </div>

            <#-- 调度报表：时间区间筛选，左侧折线图 + 右侧饼图 -->
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title"></h3>
                            <#--<input type="text" class="form-control" id="filterTime" readonly >-->

                            <!-- tools box -->
                            <div class="pull-right box-tools">
                                <button type="button" class="btn btn-primary btn-sm daterange pull-right"
                                        data-toggle="tooltip" id="filterTime">
                                    <i class="fa fa-calendar"></i>
                                </button>
                                <#--<button type="button" class="btn btn-primary btn-sm pull-right" data-widget="collapse" data-toggle="tooltip" title="" style="margin-right: 5px;" data-original-title="Collapse">
                                    <i class="fa fa-minus"></i>
                                </button>-->
                            </div>
                            <!-- /. tools -->

                        </div>
                        <div class="box-body">
                            <div class="row">
                                <#-- 左侧折线图 -->
                                <div class="col-md-8">
                                    <div id="lineChart" style="height: 350px;"></div>
                                </div>
                                <#-- 右侧饼图 -->
                                <div class="col-md-4">
                                    <div id="pieChart" style="height: 350px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <h1>Map with href on areas and plotted cities</h1>

                        <div class="mapcontainer">
                            <div class="map">
                                <span>Alternative content for the map</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </section>
        <!-- /.content -->

    </div>
    <!-- /.content-wrapper -->

    <@netCommon.commonFooter />

</div>
<!-- ./wrapper -->

<@netCommon.commonScript/>

<script>
    <#--var hasPermission = ${ifHasProjectPermission?string};-->
</script>

<!-- date range picker -->
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/moment.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/daterangepicker.js"></script>
<!-- maps -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-mousewheel/3.1.13/jquery.mousewheel.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.2.7/raphael.min.js" charset="utf-8"></script>
<script src="${request.contextPath}/static/plugins/echarts/echarts.common.min.js" charset="utf-8"></script>
<script src="${request.contextPath}/static/plugins/maps/jquery.mapael.js" charset="utf-8"></script>
<script src="${request.contextPath}/static/plugins/maps/world_countries.js" charset="utf-8"></script>
<script src="${request.contextPath}/static/plugins/maps/china.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        $(".mapcontainer").mapael({
            map: {
                name: "cn"
                // Enable zoom on the map
                , zoom: {
                    enabled: true
                }
                // Set default plots and areas style
                , defaultPlot: {
                    attrs: {
                        fill: "#004a9b"
                        , opacity: 0.6
                    }
                    , attrsHover: {
                        opacity: 1
                    }
                    , text: {
                        attrs: {
                            fill: "#505444"
                        }
                        , attrsHover: {
                            fill: "#000"
                        }
                    }
                }
                , defaultArea: {
                    attrs: {
                        fill: "#f4f4e8"
                        , stroke: "#ced8d0"
                    }
                    , attrsHover: {
                        fill: "#a4e100"
                    }
                    , text: {
                        attrs: {
                            fill: "#505444"
                        }
                        , attrsHover: {
                            fill: "#000"
                        }
                    }
                }
            },

            // Customize some areas of the map
            areas: {
                "department-56": {
                    text: {content: "Morbihan", attrs: {"font-size": 10}},
                    tooltip: {content: "Morbihan (56)"},
                    href: "http://fr.wikipedia.org/wiki/Morbihan"
                },
                "department-21": {
                    attrs: {
                        fill: "#488402"
                    }
                    , attrsHover: {
                        fill: "#a4e100"
                    },
                    href: "http://fr.wikipedia.org/wiki/C%C3%B4te-d%27Or",
                    target: "_blank"
                }
            },

            // Add some plots on the map
            plots: {
                // Image plot
                'paris': {
                    type: "image",
                    url: "http://www.neveldo.fr/mapael/assets/img/marker.png",
                    width: 12,
                    height: 40,
                    latitude: 48.86,
                    longitude: 2.3444,
                    attrs: {
                        opacity: 1
                    },
                    attrsHover: {
                        transform: "s1.5"
                    },
                    href: "http://fr.wikipedia.org/wiki/Paris",
                    target: "_blank"
                },
                // Circle plot
                'lyon': {
                    type: "circle",
                    size: 50,
                    latitude: 45.758888888889,
                    longitude: 4.8413888888889,
                    value: 700000,
                    tooltip: {content: "<span style=\"font-weight:bold;\">City :</span> Lyon"},
                    text: {content: "Lyon"},
                    href: "http://fr.wikipedia.org/wiki/Lyon"
                },
                // Square plot
                'rennes': {
                    type: "square",
                    size: 20,
                    latitude: 48.114166666667,
                    longitude: -1.6808333333333,
                    tooltip: {content: "<span style=\"font-weight:bold;\">City :</span> Rennes"},
                    text: {content: "Rennes"},
                    href: "http://fr.wikipedia.org/wiki/Rennes"
                },
                // Plot positioned by x and y instead of latitude, longitude
                'myplot': {
                    x: 300,
                    y: 200,
                    text: {
                        content: "My plot"
                        , position: "bottom"
                        , attrs: {"font-size": 10, fill: "#004a9b", opacity: 0.6}
                        , attrsHover: {fill: "#004a9b", opacity: 1}
                    },
                }
            }
        });

        // filter Time
        var rangesConf = {};
        var I18n = {};

        I18n.daterangepicker_ranges_recent_hour = "recent one hour"
        I18n.daterangepicker_ranges_today = "today"
        I18n.daterangepicker_ranges_yesterday = "yesterday"
        I18n.daterangepicker_ranges_this_month = "this month"
        I18n.daterangepicker_ranges_last_month = "last month"
        I18n.daterangepicker_ranges_recent_week = "recent one week"
        I18n.daterangepicker_ranges_recent_month = "recent one month"
        I18n.daterangepicker_custom_name = "custom"
        I18n.daterangepicker_custom_starttime = "start time"
        I18n.daterangepicker_custom_endtime = "end time"
        I18n.daterangepicker_custom_daysofweek = "Sun,Mon,Tue,Wed,Thu,Fri,Sat"
        I18n.daterangepicker_custom_monthnames = "Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec"

        rangesConf[I18n.daterangepicker_ranges_today] = [moment().startOf('day'), moment().endOf('day')];
        rangesConf[I18n.daterangepicker_ranges_yesterday] = [moment().subtract(1, 'days').startOf('day'), moment().subtract(1, 'days').endOf('day')];
        rangesConf[I18n.daterangepicker_ranges_this_month] = [moment().startOf('month'), moment().endOf('month')];
        rangesConf[I18n.daterangepicker_ranges_last_month] = [moment().subtract(1, 'months').startOf('month'), moment().subtract(1, 'months').endOf('month')];
        rangesConf[I18n.daterangepicker_ranges_recent_week] = [moment().subtract(1, 'weeks').startOf('day'), moment().endOf('day')];
        rangesConf[I18n.daterangepicker_ranges_recent_month] = [moment().subtract(1, 'months').startOf('day'), moment().endOf('day')];

        let charJson = {
            "code": 200,
            "msg": null,
            "content": {
                "triggerCountFailTotal": 11352,
                "triggerDayList": ["2022-08-03", "2022-08-04", "2022-08-05", "2022-08-06", "2022-08-07", "2022-08-08", "2022-08-09", "2022-08-10"],
                "triggerDayCountRunningList": [7, 6, 6, 6, 6, 6, 6, 4],
                "triggerCountSucTotal": 92739,
                "triggerDayCountFailList": [1483, 1484, 1481, 1480, 1474, 1486, 1493, 971],
                "triggerCountRunningTotal": 47,
                "triggerDayCountSucList": [12102, 12113, 12109, 12103, 12111, 12103, 12106, 7992]
            }
        }
        $('#filterTime').daterangepicker({
            autoApply: false,
            singleDatePicker: false,
            showDropdowns: false,        // 是否显示年月选择条件
            timePicker: true, 			// 是否显示小时和分钟选择条件
            timePickerIncrement: 10, 	// 时间的增量，单位为分钟
            timePicker24Hour: true,
            opens: 'left', //日期选择框的弹出位置
            ranges: rangesConf,
            locale: {
                format: 'YYYY-MM-DD HH:mm:ss',
                separator: ' - ',
                customRangeLabel: I18n.daterangepicker_custom_name,
                applyLabel: I18n.system_ok,
                cancelLabel: I18n.system_cancel,
                fromLabel: I18n.daterangepicker_custom_starttime,
                toLabel: I18n.daterangepicker_custom_endtime,
                daysOfWeek: I18n.daterangepicker_custom_daysofweek.split(','),        // '日', '一', '二', '三', '四', '五', '六'
                monthNames: I18n.daterangepicker_custom_monthnames.split(','),        // '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'
                firstDay: 1
            },
            startDate: rangesConf[I18n.daterangepicker_ranges_recent_week][0],
            endDate: rangesConf[I18n.daterangepicker_ranges_recent_week][1]
        }, function (start, end, label) {
            // freshChartDate(start, end);
            fillChartData();
        });
        // freshChartDate(rangesConf[I18n.daterangepicker_ranges_recent_week][0], rangesConf[I18n.daterangepicker_ranges_recent_week][1]);
        fillChartData();

        /**
         * fresh Chart Date
         *
         * @param startDate
         * @param endDate
         */
        function freshChartDate(startDate, endDate) {
            $.ajax({
                type: 'POST',
                url: base_url + '/chartInfo',
                data: {
                    'startDate': startDate.format('YYYY-MM-DD HH:mm:ss'),
                    'endDate': endDate.format('YYYY-MM-DD HH:mm:ss')
                },
                dataType: "json",
                success: function (charJson) {
                    if (charJson.code == 200) {
                        lineChartInit(charJson)
                        pieChartInit(charJson);
                    } else {
                        layer.open({
                            title: I18n.system_tips,
                            btn: [I18n.system_ok],
                            content: (charJson.msg || I18n.job_dashboard_report_loaddata_fail),
                            icon: '2'
                        });
                    }
                }
            });
        }
        /**
         * fill Chart Data use  JSON
         *
         * @param startDate
         * @param endDate
         */
        function fillChartData() {
            lineChartInit(charJson)
            pieChartInit(charJson);
        }

        /**
         * line Chart Init
         */
        function lineChartInit(data) {
            var option = {
                title: {
                    text: I18n.job_dashboard_date_report
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        label: {
                            backgroundColor: '#6a7985'
                        }
                    }
                },
                legend: {
                    data: [I18n.joblog_status_suc, I18n.joblog_status_fail, I18n.joblog_status_running]
                },
                toolbox: {
                    feature: {
                        /*saveAsImage: {}*/
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
                        boundaryGap: false,
                        data: data.content.triggerDayList
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: I18n.joblog_status_suc,
                        type: 'line',
                        stack: 'Total',
                        areaStyle: {normal: {}},
                        data: data.content.triggerDayCountSucList
                    },
                    {
                        name: I18n.joblog_status_fail,
                        type: 'line',
                        stack: 'Total',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        areaStyle: {normal: {}},
                        data: data.content.triggerDayCountFailList
                    },
                    {
                        name: I18n.joblog_status_running,
                        type: 'line',
                        stack: 'Total',
                        areaStyle: {normal: {}},
                        data: data.content.triggerDayCountRunningList
                    }
                ],
                color: ['#00A65A', '#c23632', '#F39C12']
            };

            var lineChart = echarts.init(document.getElementById('lineChart'));
            lineChart.setOption(option);
        }

        /**
         * pie Chart Init
         */
        function pieChartInit(data) {
            var option = {
                title: {
                    text: I18n.job_dashboard_rate_report,
                    /*subtext: 'subtext',*/
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: [I18n.joblog_status_suc, I18n.joblog_status_fail, I18n.joblog_status_running]
                },
                series: [
                    {
                        //name: '分布比例',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [
                            {
                                name: I18n.joblog_status_suc,
                                value: data.content.triggerCountSucTotal
                            },
                            {
                                name: I18n.joblog_status_fail,
                                value: data.content.triggerCountFailTotal
                            },
                            {
                                name: I18n.joblog_status_running,
                                value: data.content.triggerCountRunningTotal
                            }
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ],
                color: ['#00A65A', '#c23632', '#F49C12']
            };
            var pieChart = echarts.init(document.getElementById('pieChart'));
            pieChart.setOption(option);
        }

    });
</script>


</body>
</html>
