(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-a1d35264"],{"03ef":function(e,t,n){"use strict";n.r(t);var s=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,inline:!0,"label-width":"100px"}},[n("el-form-item",{attrs:{label:"流程名称",prop:"name"}},[n("el-input",{attrs:{placeholder:"请输入流程名称",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.name,callback:function(t){e.$set(e.queryParams,"name",t)},expression:"queryParams.name"}})],1),n("el-form-item",{attrs:{label:"任务发起人",prop:"key"}},[n("el-input",{attrs:{placeholder:"请输入任务发起人",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.startUserId,callback:function(t){e.$set(e.queryParams,"startUserId",t)},expression:"queryParams.startUserId"}})],1),n("el-form-item",[n("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),n("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),n("el-row",{staticClass:"mb8",attrs:{gutter:10}},[n("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.dataList},on:{"selection-change":e.handleSelectionChange}},[n("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),n("el-table-column",{attrs:{fixed:"",align:"center",type:"index",label:"序号",width:"50"}}),n("el-table-column",{attrs:{fixed:"",align:"center",prop:"name",label:"流程定义名称",width:"150"}}),n("el-table-column",{attrs:{align:"center",prop:"processDefinitionKey",label:"流程定义KEY",width:"120"}}),n("el-table-column",{attrs:{align:"center",prop:"processDefinitionVersion",label:"版本号",width:"90"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row;return[e._v(" v"+e._s(n.processDefinitionVersion)+".0")]}}])}),n("el-table-column",{attrs:{align:"center",prop:"startUserNickName",label:"流程发起人",width:"120"}}),n("el-table-column",{attrs:{align:"center",prop:"isSuspended",label:"流程状态",width:"75"},scopedSlots:e._u([{key:"default",fn:function(t){return["激活"==t.row.isSuspended?n("el-tag",{attrs:{type:"success"}},[e._v("激活")]):n("el-tag",{attrs:{type:"danger"}},[e._v("挂起")])]}}])}),n("el-table-column",{attrs:{align:"center",prop:"currTaskInfo","show-overflow-tooltip":!0,label:"当前办理人"}}),n("el-table-column",{attrs:{align:"center",prop:"businessKey","show-overflow-tooltip":!0,label:"流程关联业务ID",width:"150"}}),n("el-table-column",{attrs:{align:"center",prop:"startTime","show-overflow-tooltip":!0,label:"流程启动时间",width:"150"}}),n("el-table-column",{attrs:{align:"center",prop:"actBusinessStatus.suspendedReason","show-overflow-tooltip":!0,label:"挂起或激活原因",width:"180"}}),n("el-table-column",{attrs:{label:"操作",align:"center",width:"120","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-row",{staticClass:"mb8",attrs:{gutter:10}},[n("el-col",{attrs:{span:1.5}},["激活"==t.row.isSuspended?n("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-lock"},on:{click:function(n){return e.openDialog(t.row)}}},[e._v("挂起")]):n("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-unlock"},on:{click:function(n){return e.openDialog(t.row)}}},[e._v("激活")])],1),n("el-col",{attrs:{span:1.5}},[n("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(n){return e.handleDelete(t.row)}}},[e._v("删除")])],1)],1),n("el-row",{staticClass:"mb8",attrs:{gutter:10}},[n("el-col",{attrs:{span:1.5}},[n("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-circle-close"},on:{click:function(n){return e.invalidRuntimeProcessInst(t.row)}}},[e._v("作废")])],1)],1)]}}])})],1),n("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}}),e.dialogVisible?n("el-dialog",{attrs:{title:"挂起或激活流程","close-on-click-modal":!1,visible:e.dialogVisible,width:"60%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[n("el-input",{attrs:{type:"textarea",maxlength:"300",placeholder:"请输入原因",autosize:{minRows:4},"show-word-limit":""},model:{value:e.reason,callback:function(t){e.reason=t},expression:"reason"}}),n("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.buttonLoading,expression:"buttonLoading"}],attrs:{type:"primary",size:"small"},on:{click:e.clickUpdateProcDefState}},[e._v("确 定")]),n("el-button",{attrs:{size:"small"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")])],1)],1):e._e()],1)},a=[],o=(n("d81d"),n("d3b7"),n("b0c0"),n("e075")),i={data:function(){return{dialogVisible:!1,reason:"",buttonLoading:!1,loading:!0,ids:[],single:!0,multiple:!0,showSearch:!0,total:0,open:!1,dataList:[],queryParams:{pageNum:1,pageSize:10,name:void 0,key:void 0},processInstanceData:{}}},created:function(){this.getList()},methods:{handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.daterangeCreateTime=[],this.resetForm("queryForm"),this.handleQuery()},handleSelectionChange:function(e){this.ids=e.map((function(e){return e.id})),this.single=1!==e.length,this.multiple=!e.length},getList:function(){var e=this;this.loading=!0,o["a"].getProcessInstRunningByPage(this.queryParams).then((function(t){e.dataList=t.rows,e.total=t.total,e.loading=!1}))},handleDelete:function(e){var t=this;this.$modal.confirm('是否确认删除流程实例ID为"'+e.processInstanceId+'"的数据项？').then((function(){return t.loading=!0,o["a"].deleteRuntimeProcessAndHisInst(e.processInstanceId)})).then((function(){t.loading=!1,t.getList(),t.$modal.msgSuccess("删除成功")})).finally((function(){t.loading=!1}))},invalidRuntimeProcessInst:function(e){var t=this;this.$modal.confirm('是否确认作废流程实例ID为"'+e.processInstanceId+'"的数据项？').then((function(){return t.loading=!0,o["a"].deleteRuntimeProcessInst(e.processInstanceId)})).then((function(){t.loading=!1,t.getList(),t.$modal.msgSuccess("作废成功")})).finally((function(){t.loading=!1}))},openDialog:function(e){this.processInstanceData=e,null===e.actBusinessStatus.suspendedReason?this.reason="":this.reason=e.actBusinessStatus.suspendedReason,this.dialogVisible=!0},clickUpdateProcDefState:function(){var e=this,t="";t="激活"===this.processInstanceData.isSuspended?"暂停后，此任务不允许往后流转，您确定挂起【".concat(this.processInstanceData.name||this.processInstanceData.processDefinitionKey,"】吗？"):"启动后，此任务允许往后流转，您确定激活【".concat(this.processInstanceData.name||this.processInstanceData.processDefinitionKey,"】吗？");var n={processInstId:this.processInstanceData.processInstanceId,reason:this.reason};this.$modal.confirm(t).then((function(){return e.buttonLoading=!0,o["a"].state(n)})).then((function(){e.buttonLoading=!1,e.getList(),e.$modal.msgSuccess("操作成功"),e.dialogVisible=!1})).finally((function(){e.buttonLoading=!1}))}}},r=i,l=n("2877"),c=Object(l["a"])(r,s,a,!1,null,null,null);t["default"]=c.exports},e075:function(e,t,n){"use strict";var s=n("b775");t["a"]={startProcessApply:function(e){return Object(s["a"])({url:"/workflow/processInstance/startWorkFlow",method:"post",data:e})},getHistoryInfoList:function(e){return Object(s["a"])({url:"/workflow/processInstance/getHistoryInfoList/"+e,method:"get"})},getHistoryProcessImage:function(e){return"/melon"+"/workflow/processInstance/getHistoryProcessImage?processInstId=".concat(e,"&t=")+Math.random()},getInfoByBusinessKey:function(e){return Object(s["a"])({url:"/workflow/actBusiness/getInfoByBusinessKey/"+e,method:"get"})},getProcessInstRunningByPage:function(e){return Object(s["a"])({url:"/workflow/processInstance/getProcessInstRunningByPage",method:"get",params:e})},state:function(e){return Object(s["a"])({url:"/workflow/processInstance/state",method:"put",data:e})},deleteRuntimeProcessInst:function(e){return Object(s["a"])({url:"/workflow/processInstance/deleteRuntimeProcessInst/"+e,method:"delete"})},deleteRuntimeProcessAndHisInst:function(e){return Object(s["a"])({url:"/workflow/processInstance/deleteRuntimeProcessAndHisInst/"+e,method:"delete"})},deleteFinishProcessAndHisInst:function(e){return Object(s["a"])({url:"/workflow/processInstance/deleteFinishProcessAndHisInst/"+e,method:"delete"})},cancelProcessApply:function(e){return Object(s["a"])({url:"/workflow/processInstance/cancelProcessApply/"+e,method:"get"})},getProcessInstFinishByPage:function(e){return Object(s["a"])({url:"/workflow/processInstance/getProcessInstFinishByPage",method:"get",params:e})}}}}]);