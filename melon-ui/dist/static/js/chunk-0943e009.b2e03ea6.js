(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0943e009"],{"15d4":function(e,t,r){"use strict";r.r(t);var s=function(){var e=this,t=e.$createElement,r=e._self._c||t;return e.visible?r("el-dialog",{directives:[{name:"dialogDrag",rawName:"v-dialogDrag"}],attrs:{title:"用户",visible:e.visible,width:"60%","append-to-body":"","close-on-click-modal":!1},on:{"update:visible":function(t){e.visible=t}}},[r("div",{staticClass:"app-container"},[r("el-row",{attrs:{gutter:20}},[r("el-col",{attrs:{span:4,xs:24}},[r("div",{staticClass:"head-container"},[r("el-input",{staticStyle:{"margin-bottom":"20px"},attrs:{placeholder:"请输入部门名称",clearable:"",size:"mini","prefix-icon":"el-icon-search"},model:{value:e.deptName,callback:function(t){e.deptName=t},expression:"deptName"}})],1),r("div",{staticClass:"head-container"},[r("el-tree",{ref:"tree",attrs:{data:e.deptOptions,props:e.defaultProps,"expand-on-click-node":!1,"filter-node-method":e.filterNode,"default-expand-all":""},on:{"node-click":e.handleNodeClick}})],1)]),r("el-col",{attrs:{span:20,xs:24}},[r("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,inline:!0,size:"mini","label-width":"68px"}},[r("el-form-item",{attrs:{label:"用户名称",prop:"userName"}},[r("el-input",{staticStyle:{width:"240px"},attrs:{placeholder:"请输入用户名称",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.userName,callback:function(t){e.$set(e.queryParams,"userName",t)},expression:"queryParams.userName"}})],1),r("el-form-item",{attrs:{label:"手机号码",prop:"phonenumber"}},[r("el-input",{staticStyle:{width:"240px"},attrs:{placeholder:"请输入手机号码",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.phonenumber,callback:function(t){e.$set(e.queryParams,"phonenumber",t)},expression:"queryParams.phonenumber"}})],1),r("el-form-item",[r("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),r("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),r("el-row",{staticClass:"mb8",attrs:{gutter:10}},[r("right-toolbar",{attrs:{showSearch:e.showSearch,columns:e.columns},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),r("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"multipleTable",attrs:{data:e.userList,"row-key":e.getRowKey},on:{"selection-change":e.handleSelectionChange}},[r("el-table-column",{attrs:{type:"selection","reserve-selection":!0,width:"50",align:"center"}}),e.columns[0].visible?r("el-table-column",{key:"userId",attrs:{label:"用户编号",align:"center",prop:"userId"}}):e._e(),e.columns[1].visible?r("el-table-column",{key:"userName",attrs:{label:"用户名称",align:"center",prop:"userName","show-overflow-tooltip":!0}}):e._e(),e.columns[2].visible?r("el-table-column",{key:"nickName",attrs:{label:"用户昵称",align:"center",prop:"nickName","show-overflow-tooltip":!0}}):e._e(),e.columns[3].visible?r("el-table-column",{key:"deptName",attrs:{label:"部门",align:"center",prop:"dept.deptName","show-overflow-tooltip":!0}}):e._e(),e.columns[4].visible?r("el-table-column",{key:"phonenumber",attrs:{label:"手机号码",align:"center",prop:"phonenumber",width:"120"}}):e._e(),e.columns[5].visible?r("el-table-column",{attrs:{label:"创建时间",align:"center",prop:"createTime",width:"160"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("span",[e._v(e._s(e.parseTime(t.row.createTime)))])]}}],null,!1,3078210614)}):e._e()],1),r("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}})],1)],1)],1),r("div",e._l(e.chooseUserList,(function(t){return r("el-tag",{key:t.userId,staticStyle:{margin:"2px"},attrs:{closable:""},on:{close:function(r){return e.handleCloseTag(t)}}},[e._v(e._s(t.userName)+" ")])})),1),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{attrs:{size:"small",type:"primary"},on:{click:e.confirmUser}},[e._v("确认")]),r("el-button",{attrs:{size:"small"},on:{click:function(t){e.visible=!1}}},[e._v("取 消")])],1)]):e._e()},n=[],a=(r("4de4"),r("d3b7"),r("159b"),r("c740"),r("a434"),r("4cd5")),i=r("c0c7"),o={props:{multiple:{type:Boolean,default:!0},propUserList:{type:Array,default:function(){return[]}}},name:"User",dicts:["sys_user_sex"],data:function(){return{loading:!0,visible:!1,showSearch:!0,total:0,userList:[],deptOptions:[],open:!1,deptName:void 0,defaultProps:{children:"children",label:"label"},queryParams:{pageNum:1,pageSize:10,userName:void 0,phonenumber:void 0,deptId:void 0,type:"person",ids:[]},columns:[{key:0,label:"用户编号",visible:!0},{key:1,label:"用户名称",visible:!0},{key:2,label:"用户昵称",visible:!0},{key:3,label:"部门",visible:!0},{key:4,label:"手机号码",visible:!0},{key:5,label:"创建时间",visible:!0}],getRowKey:function(e){return e.userId},chooseUserList:[],flag:!1}},watch:{deptName:function(e){this.$refs.tree.filter(e)},propUserList:function(e){e.length>0?(this.queryParams.ids=e,this.getList(),this.flag=!0):this.chooseUserList=[]}},created:function(){this.getList(),this.getTreeselect()},methods:{getList:function(){var e=this;this.loading=!0,Object(a["b"])(this.queryParams).then((function(t){var r=t.data.page;e.userList=r.rows,e.total=r.total,e.flag&&t.data.list&&(e.chooseUserList=t.data.list,t.data.list.forEach((function(t){e.$refs.multipleTable.toggleRowSelection(t,!0)}))),e.loading=!1}))},getTreeselect:function(){var e=this;Object(i["d"])().then((function(t){e.deptOptions=t.data}))},filterNode:function(e,t){return!e||-1!==t.label.indexOf(e)},handleNodeClick:function(e){this.queryParams.deptId=e.id,this.getList()},cancel:function(){this.open=!1,this.reset()},handleQuery:function(){this.flag=!1,this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.queryParams.deptId="",this.queryParams.phonenumber="",this.queryParams.userName="",this.handleQuery()},handleSelectionChange:function(e){if(this.multiple)this.chooseUserList=e.filter((function(e,t,r){return r.findIndex((function(t){return t.userId===e.userId}))===t}));else{if(this.chooseUserList=e,e.length>1){var t=e.shift();this.$refs.multipleTable.toggleRowSelection(t,!1)}0===e.length&&(this.chooseUserList=null)}},handleCloseTag:function(e){var t=this;this.chooseUserList.splice(this.chooseUserList.indexOf(e),1),this.$refs.multipleTable.toggleRowSelection(e,!1),this.userList.forEach((function(r,s){e.userId===r.userId&&t.$refs.multipleTable.toggleRowSelection(t.userList[s],!1)}))},confirmUser:function(){this.chooseUserList.length>0?this.$emit("confirmUser",this.chooseUserList):this.$modal.msgWarning("请选择人员！")}}},l=o,u=r("2877"),c=Object(u["a"])(l,s,n,!1,null,null,null);t["default"]=c.exports},"4cd5":function(e,t,r){"use strict";r.d(t,"b",(function(){return n})),r.d(t,"a",(function(){return a}));var s=r("b775");function n(e){return Object(s["a"])({url:"/workflow/user/getWorkflowUserListByPage",method:"post",data:e})}function a(e){return Object(s["a"])({url:"/workflow/user/getWorkflowAddMultiListByPage",method:"post",data:e})}},c0c7:function(e,t,r){"use strict";r.d(t,"h",(function(){return a})),r.d(t,"f",(function(){return i})),r.d(t,"a",(function(){return o})),r.d(t,"k",(function(){return l})),r.d(t,"c",(function(){return u})),r.d(t,"i",(function(){return c})),r.d(t,"b",(function(){return d})),r.d(t,"g",(function(){return m})),r.d(t,"l",(function(){return p})),r.d(t,"m",(function(){return h})),r.d(t,"n",(function(){return f})),r.d(t,"e",(function(){return b})),r.d(t,"j",(function(){return y})),r.d(t,"d",(function(){return g}));var s=r("b775"),n=r("c38a");function a(e){return Object(s["a"])({url:"/system/user/list",method:"get",params:e})}function i(e){return Object(s["a"])({url:"/system/user/"+Object(n["e"])(e),method:"get"})}function o(e){return Object(s["a"])({url:"/system/user",method:"post",data:e})}function l(e){return Object(s["a"])({url:"/system/user",method:"put",data:e})}function u(e){return Object(s["a"])({url:"/system/user/"+e,method:"delete"})}function c(e,t){var r={userId:e,password:t};return Object(s["a"])({url:"/system/user/resetPwd",method:"put",data:r})}function d(e,t){var r={userId:e,status:t};return Object(s["a"])({url:"/system/user/changeStatus",method:"put",data:r})}function m(){return Object(s["a"])({url:"/system/user/profile",method:"get"})}function p(e){return Object(s["a"])({url:"/system/user/profile",method:"put",data:e})}function h(e,t){var r={oldPassword:e,newPassword:t};return Object(s["a"])({url:"/system/user/profile/updatePwd",method:"put",params:r})}function f(e){return Object(s["a"])({url:"/system/user/profile/avatar",method:"post",data:e})}function b(e){return Object(s["a"])({url:"/system/user/authRole/"+e,method:"get"})}function y(e){return Object(s["a"])({url:"/system/user/authRole",method:"put",params:e})}function g(){return Object(s["a"])({url:"/system/user/deptTree",method:"get"})}}}]);