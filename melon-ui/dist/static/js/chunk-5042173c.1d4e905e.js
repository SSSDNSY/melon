(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5042173c"],{"194c":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{directives:[{name:"dialogDrag",rawName:"v-dialogDrag"}],attrs:{title:"用户",visible:e.visible,width:"60%","append-to-body":"","close-on-click-modal":!1},on:{"update:visible":function(t){e.visible=t},close:e.close}},[a("div",{staticClass:"app-container"},[a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:20,xs:24}},[a("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,inline:!0,"label-width":"68px"}},[a("el-form-item",{attrs:{label:"归属部门",prop:"deptId"}},[a("treeselect",{staticStyle:{width:"240px"},attrs:{options:e.deptOptions,"show-count":!0,placeholder:"请选择归属部门"},model:{value:e.queryParams.deptId,callback:function(t){e.$set(e.queryParams,"deptId",t)},expression:"queryParams.deptId"}})],1),a("el-form-item",{attrs:{label:"用户名称",prop:"userName"}},[a("el-input",{staticStyle:{width:"240px"},attrs:{placeholder:"请输入用户名称",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.userName,callback:function(t){e.$set(e.queryParams,"userName",t)},expression:"queryParams.userName"}})],1),a("el-form-item",{attrs:{label:"手机号码",prop:"phonenumber"}},[a("el-input",{staticStyle:{width:"240px"},attrs:{placeholder:"请输入手机号码",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.phonenumber,callback:function(t){e.$set(e.queryParams,"phonenumber",t)},expression:"queryParams.phonenumber"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.handleQuery}},[e._v("搜索")]),a("el-button",{attrs:{icon:"el-icon-refresh"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),a("el-row",{staticClass:"mb8",attrs:{gutter:10}},[a("right-toolbar",{attrs:{showSearch:e.showSearch,columns:e.columns},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"multipleTable",attrs:{data:e.userList,"row-key":e.getRowKey},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection","reserve-selection":!0,width:"50",align:"center"}}),e.columns[0].visible?a("el-table-column",{key:"userId",attrs:{label:"用户编号",align:"center",prop:"userId"}}):e._e(),e.columns[1].visible?a("el-table-column",{key:"userName",attrs:{label:"用户名称",align:"center",prop:"userName","show-overflow-tooltip":!0}}):e._e(),e.columns[2].visible?a("el-table-column",{key:"nickName",attrs:{label:"用户昵称",align:"center",prop:"nickName","show-overflow-tooltip":!0}}):e._e(),e.columns[3].visible?a("el-table-column",{key:"deptName",attrs:{label:"部门",align:"center",prop:"dept.deptName","show-overflow-tooltip":!0}}):e._e(),e.columns[4].visible?a("el-table-column",{key:"phonenumber",attrs:{label:"手机号码",align:"center",prop:"phonenumber",width:"120"}}):e._e(),e.columns[5].visible?a("el-table-column",{attrs:{label:"创建时间",align:"center",prop:"createTime",width:"160"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.parseTime(t.row.createTime)))])]}}],null,!1,3078210614)}):e._e()],1),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}})],1)],1)],1),a("div",e._l(e.chooseUserList,(function(t){return a("el-tag",{key:t.userName,staticStyle:{margin:"2px"},attrs:{closable:""},on:{close:function(a){return e.handleCloseTag(t)}}},[e._v(e._s(t.userName)+" ")])})),1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{loading:e.buttonLoading,type:"primary"},on:{click:e.confirmUser}},[e._v("确认")]),a("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)])},s=[],l=(a("d3b7"),a("159b"),a("4de4"),a("c740"),a("a434"),a("4cd5")),i=a("c0c7"),o=a("ca17"),n=a.n(o),c=(a("542c"),{props:{multiple:{type:Boolean,default:!0},dataObj:{type:Object,default:{}},nodeId:{type:String,default:""}},name:"User",dicts:["sys_user_sex"],components:{Treeselect:n.a},data:function(){return{loading:!0,buttonLoading:!1,visible:!1,showSearch:!0,total:0,userList:[],deptOptions:void 0,queryParams:{pageNum:1,pageSize:10,userName:void 0,phonenumber:void 0,deptId:void 0,type:void 0,params:void 0,ids:[]},columns:[{key:0,label:"用户编号",visible:!0},{key:1,label:"用户名称",visible:!0},{key:2,label:"用户昵称",visible:!0},{key:3,label:"部门",visible:!0},{key:4,label:"手机号码",visible:!0},{key:5,label:"创建时间",visible:!0}],chooseUserList:[],getRowKey:function(e){return e.userId}}},watch:{dataObj:function(e){var t=this;this.queryParams.params=e.assigneeId,this.queryParams.type=e.chooseWay,this.queryParams.ids=e.ids,this.$nextTick((function(){t.$refs.multipleTable.clearSelection()})),this.getList()}},created:function(){this.getTreeselect()},methods:{getList:function(){var e=this;this.loading=!0,this.chooseUserList=[],Object(l["b"])(this.queryParams).then((function(t){var a=t.data.page;e.userList=a.rows,e.total=a.total,t.data.list&&(e.chooseUserList=t.data.list,t.data.list.forEach((function(t){e.$refs.multipleTable.toggleRowSelection(t,!0)}))),e.loading=!1}))},getTreeselect:function(){var e=this;Object(i["d"])().then((function(t){e.deptOptions=t.data}))},cancel:function(){this.visible=!1,this.chooseUserList=[]},close:function(){this.visible=!1,this.chooseUserList=[]},handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){},handleSelectionChange:function(e){if(this.multiple)this.chooseUserList=e.filter((function(e,t,a){return a.findIndex((function(t){return t.userId===e.userId}))===t}));else{if(this.chooseUserList=e,e.length>1){var t=e.shift();this.$refs.multipleTable.toggleRowSelection(t,!1)}0===e.length&&(this.chooseUserList=null)}},handleCloseTag:function(e){var t=this;this.chooseUserList.splice(this.chooseUserList.indexOf(e),1),this.$refs.multipleTable.toggleRowSelection(e,!1),this.userList.forEach((function(a,r){e.userId===a.userId&&t.$refs.multipleTable.toggleRowSelection(t.userList[r],!1)}))},confirmUser:function(){this.chooseUserList.length>0?(this.$emit("confirmUser",this.chooseUserList,this.nodeId),this.visible=!1):this.$modal.msgWarning("请选择人员！")}}}),u=c,m=a("2877"),d=Object(m["a"])(u,r,s,!1,null,"6e26d3af",null);t["default"]=d.exports},"7ec9":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"120px"}},[a("el-form-item",{attrs:{label:"申请人用户名",prop:"username"}},[a("el-input",{attrs:{placeholder:"请输入申请人用户名"},model:{value:e.form.username,callback:function(t){e.$set(e.form,"username",t)},expression:"form.username"}})],1),a("el-form-item",{attrs:{label:"请假时长",prop:"duration"}},[a("el-input",{attrs:{placeholder:"请输入请假时长，单位：天"},model:{value:e.form.duration,callback:function(t){e.$set(e.form,"duration",t)},expression:"form.duration"}})],1),a("el-form-item",{attrs:{label:"工作委托人",prop:"principal"}},[a("el-input",{attrs:{placeholder:"请输入工作委托人"},model:{value:e.form.principal,callback:function(t){e.$set(e.form,"principal",t)},expression:"form.principal"}})],1),a("el-form-item",{attrs:{label:"联系电话",prop:"contactPhone"}},[a("el-input",{attrs:{placeholder:"请输入联系电话"},model:{value:e.form.contactPhone,callback:function(t){e.$set(e.form,"contactPhone",t)},expression:"form.contactPhone"}})],1),a("el-form-item",{attrs:{label:"请假类型",prop:"leaveType"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择请假类型"},model:{value:e.form.leaveType,callback:function(t){e.$set(e.form,"leaveType",t)},expression:"form.leaveType"}},e._l(e.dict.type.bs_leave_type,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:parseInt(e.value)}})})),1)],1),a("el-form-item",{attrs:{label:"标题",prop:"title"}},[a("el-input",{attrs:{placeholder:"请输入标题"},model:{value:e.form.title,callback:function(t){e.$set(e.form,"title",t)},expression:"form.title"}})],1),a("el-form-item",{attrs:{label:"请假原因",prop:"leaveReason"}},[a("el-input",{attrs:{type:"textarea",placeholder:"请输入内容"},model:{value:e.form.leaveReason,callback:function(t){e.$set(e.form,"leaveReason",t)},expression:"form.leaveReason"}})],1),a("el-form-item",{attrs:{label:"请假开始时间",prop:"startDate"}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{clearable:"",size:"small",type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择请假开始时间"},model:{value:e.form.startDate,callback:function(t){e.$set(e.form,"startDate",t)},expression:"form.startDate"}})],1),a("el-form-item",{attrs:{label:"请假结束时间",prop:"endDate"}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{clearable:"",size:"small",type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择请假结束时间"},model:{value:e.form.endDate,callback:function(t){e.$set(e.form,"endDate",t)},expression:"form.endDate"}})],1)],1),a("div",{directives:[{name:"show",rawName:"v-show",value:null===e.parentTaskId,expression:"parentTaskId===null"}],staticClass:"dialog-footer",staticStyle:{"text-align":"center"}},[a("el-button",{attrs:{loading:e.buttonLoading,size:"small",type:"info"},on:{click:e.submitCallback}},[e._v("关闭")]),a("el-button",{attrs:{loading:e.buttonLoading,size:"small",type:"primary"},on:{click:e.submitForm}},[e._v("提交")])],1),a("verify",{ref:"verifyRef",attrs:{taskId:e.taskId,taskVariables:e.taskVariables,sendMessage:e.sendMessage},on:{submitCallback:e.submitCallback}})],1)},s=[],l=a("c7eb"),i=a("1da1"),o=a("b775");function n(e){return Object(o["a"])({url:"/demo/leave/"+e,method:"get"})}var c=a("d5cd"),u={name:"Leave",dicts:["bs_leave_type"],props:{businessKey:String,parentTaskId:String,taskId:String},components:{verify:c["a"]},data:function(){return{buttonLoading:!1,loading:!0,leaveList:[],form:{},rules:{id:[{required:!0,message:"主键ID不能为空",trigger:"blur"}],username:[{required:!0,message:"申请人用户名不能为空",trigger:"blur"}],duration:[{required:!0,message:"请假时长，单位：天不能为空",trigger:"blur"}],principal:[{required:!0,message:"工作委托人不能为空",trigger:"blur"}],contactPhone:[{required:!0,message:"联系电话不能为空",trigger:"blur"}],leaveType:[{required:!0,message:"请假类型不能为空",trigger:"change"}],title:[{required:!0,message:"标题不能为空",trigger:"blur"}],leaveReason:[{required:!0,message:"请假原因不能为空",trigger:"blur"}],startDate:[{required:!0,message:"请假开始时间不能为空",trigger:"blur"}],endDate:[{required:!0,message:"请假结束时间不能为空",trigger:"blur"}]},taskVariables:void 0,sendMessage:{}}},watch:{businessKey:{immediate:!0,handler:function(e){e&&this.getById()}}},methods:{submitCallback:function(){this.$emit("closeForm")},getById:function(){var e=this;return Object(i["a"])(Object(l["a"])().mark((function t(){var a,r;return Object(l["a"])().wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,n(e.businessKey);case 2:a=t.sent,r=a.data,e.form=r;case 5:case"end":return t.stop()}}),t)})))()},submitForm:function(){var e=this;n(this.businessKey).then((function(t){e.taskVariables={entity:t.data,userId:1},e.sendMessage={title:"请假申请",messageContent:"单据【"+e.form.id+"】申请"}})),this.$refs.verifyRef.visible=!0,this.$refs.verifyRef.reset()}}},m=u,d=a("2877"),p=Object(d["a"])(m,r,s,!1,null,null,null);t["default"]=p.exports}}]);