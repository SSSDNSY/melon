(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-18c21288"],{"89fe":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,inline:!0,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"流程名称",prop:"name"}},[a("el-input",{attrs:{placeholder:"请输入流程名称",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.name,callback:function(t){e.$set(e.queryParams,"name",t)},expression:"queryParams.name"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),a("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),a("el-row",{staticClass:"mb8",attrs:{gutter:10}},[a("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.dataList},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),a("el-table-column",{attrs:{fixed:"",align:"center",type:"index",label:"序号",width:"50"}}),a("el-table-column",{attrs:{fixed:"",align:"center",prop:"name",label:"任务名称"}}),a("el-table-column",{attrs:{align:"center",prop:"processDefinitionName",label:"流程定义名称"}}),a("el-table-column",{attrs:{align:"center",prop:"processDefinitionKey",label:"流程定义Key"}}),a("el-table-column",{attrs:{align:"center",prop:"version",label:"版本号",width:"90"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.row;return[e._v(" v"+e._s(a.version)+".0")]}}])}),a("el-table-column",{attrs:{align:"center",prop:"assignee",label:"流程办理人","min-width":"130"}}),a("el-table-column",{attrs:{align:"center",prop:"startTime",label:"启动时间",width:"160"}}),a("el-table-column",{attrs:{align:"center",prop:"endTime",label:"结束时间",width:"160"}})],1),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}})],1)},r=[],o=(a("d81d"),a("9a9d")),s={data:function(){return{buttonLoading:!1,loading:!0,exportLoading:!1,ids:[],single:!0,multiple:!0,showSearch:!0,total:0,open:!1,dataList:[],queryParams:{pageNum:1,pageSize:10,name:void 0}}},created:function(){this.getList()},methods:{handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.daterangeCreateTime=[],this.resetForm("queryForm"),this.handleQuery()},handleSelectionChange:function(e){this.ids=e.map((function(e){return e.id})),this.single=1!==e.length,this.multiple=!e.length},getList:function(){var e=this;this.loading=!0,o["a"].getAllTaskFinishByPage(this.queryParams).then((function(t){e.dataList=t.rows,e.total=t.total,e.loading=!1}))}}},l=s,i=a("2877"),u=Object(i["a"])(l,n,r,!1,null,null,null);t["default"]=u.exports},"9a9d":function(e,t,a){"use strict";a("99af");var n=a("b775");t["a"]={getTaskWaitByPage:function(e){return Object(n["a"])({url:"/workflow/task/getTaskWaitByPage",method:"get",params:e})},getTaskFinishByPage:function(e){return Object(n["a"])({url:"/workflow/task/getTaskFinishByPage",method:"get",params:e})},getAllTaskWaitByPage:function(e){return Object(n["a"])({url:"/workflow/task/getAllTaskWaitByPage",method:"get",params:e})},getAllTaskFinishByPage:function(e){return Object(n["a"])({url:"/workflow/task/getAllTaskFinishByPage",method:"get",params:e})},completeTask:function(e){return Object(n["a"])({url:"/workflow/task/completeTask",method:"post",data:e})},getNextNodeInfo:function(e){return Object(n["a"])({url:"/workflow/task/getNextNodeInfo",method:"post",data:e})},getBackNodes:function(e){return Object(n["a"])({url:"/workflow/task/getBackNodes/"+e,method:"get"})},backProcess:function(e){return Object(n["a"])({url:"/workflow/task/backProcess",method:"post",data:e})},deleteByNodeIds:function(e){return Object(n["a"])({url:"/workflow/task/deleteByNodeIds",method:"post",data:e})},claim:function(e){return Object(n["a"])({url:"/workflow/task/claim/"+e,method:"post"})},returnTask:function(e){return Object(n["a"])({url:"/workflow/task/returnTask/"+e,method:"post"})},delegateTask:function(e){return Object(n["a"])({url:"/workflow/task/delegateTask",method:"post",data:e})},transmitTask:function(e){return Object(n["a"])({url:"/workflow/task/transmitTask",method:"post",data:e})},addMultiInstanceExecution:function(e){return Object(n["a"])({url:"/workflow/task/addMultiInstanceExecution",method:"post",data:e})},deleteMultiInstanceExecution:function(e){return Object(n["a"])({url:"/workflow/task/deleteMultiInstanceExecution",method:"post",data:e})},updateAssignee:function(e){return Object(n["a"])({url:"/workflow/task/updateAssignee",method:"post",data:e})},getProcessInstVariable:function(e){return Object(n["a"])({url:"/workflow/task/getProcessInstVariable/"+e,method:"get"})},editComment:function(e,t){return Object(n["a"])({url:"/workflow/task/editComment/".concat(e,"/").concat(t),method:"put"})}}}}]);