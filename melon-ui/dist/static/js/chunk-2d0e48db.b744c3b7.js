(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0e48db"],{"915b":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,size:"small",inline:!0}},[a("el-form-item",{attrs:{label:"项目名",prop:"appname"}},[a("el-select",{staticStyle:{width:"240px"},attrs:{placeholder:"请选择项目",clearable:""},model:{value:e.queryParams.appname,callback:function(t){e.$set(e.queryParams,"appname",t)},expression:"queryParams.appname"}},e._l(e.appList,(function(e){return a("el-option",{key:e.appname,attrs:{label:e.title,value:e.appname}})})),1)],1),a("el-form-item",{attrs:{label:"描述",prop:"title"}},[a("el-input",{attrs:{clearable:"",placeholder:"请输入配置项描述",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.title,callback:function(t){e.$set(e.queryParams,"title",t)},expression:"queryParams.title"}})],1),a("el-form-item",{attrs:{label:"环境",prop:"env"}},[a("el-select",{attrs:{placeholder:"请选择配置环境",clearable:""},model:{value:e.queryParams.env,callback:function(t){e.$set(e.queryParams,"env",t)},expression:"queryParams.env"}},e._l(e.envList,(function(e){return a("el-option",{key:e.env,attrs:{label:e.title,value:e.env}})})),1)],1),a("el-form-item",{attrs:{label:"键",prop:"key"}},[a("el-input",{attrs:{clearable:"",placeholder:"请输入配置项键",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.key,callback:function(t){e.$set(e.queryParams,"key",t)},expression:"queryParams.key"}})],1),a("el-form-item",{attrs:{label:"值",prop:"value"}},[a("el-input",{attrs:{clearable:"",placeholder:"请输入配置项值",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.value,callback:function(t){e.$set(e.queryParams,"value",t)},expression:"queryParams.value"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),a("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),a("el-row",{staticClass:"mb8",attrs:{gutter:10}},[a("el-col",{attrs:{span:1.5}},[a("el-button",{attrs:{type:"primary",plain:"",icon:"el-icon-plus",size:"mini"},on:{click:e.handleAdd}},[e._v("新增 ")])],1),a("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),e.refreshTable?a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.list}},[a("el-table-column",{attrs:{label:"序号",type:"index",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s((e.queryParams.pageNum-1)*e.queryParams.pageSize+t.$index+1))])]}}],null,!1,830294093)}),a("el-table-column",{attrs:{prop:"env",label:"环境","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{prop:"appname",label:"项目名","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{prop:"title",label:"描述","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{prop:"key",label:"配置KEY","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{prop:"value",label:"配置VALUE","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{prop:"createBy",label:"创建者","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{prop:"createTime",label:"创建时间","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{prop:"updateBy",label:"更新人","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{prop:"updateTime",label:"更新时间","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(a){return e.handleUpdate(t.row)}}},[e._v("修改 ")]),a("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-plus"},on:{click:function(a){return e.handleAdd(t.row)}}},[e._v("新增 ")]),a("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(a){return e.handleDelete(t.row)}}},[e._v("删除 ")])]}}],null,!1,3799897407)})],1):e._e(),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}}),a("el-dialog",{attrs:{title:e.title,visible:e.open,width:"680px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"100px"}},[a("el-row",[a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"环境",prop:"env"}},[a("el-select",{attrs:{span:24,placeholder:"请选择配置环境",clearable:""},model:{value:e.form.env,callback:function(t){e.$set(e.form,"env",t)},expression:"form.env"}},e._l(e.envList,(function(e){return a("el-option",{key:e.env,attrs:{label:e.title,value:e.env}})})),1)],1)],1),a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"项目",prop:"appname"}},[a("el-select",{attrs:{span:24,placeholder:"请选择配置项目",clearable:""},model:{value:e.form.appname,callback:function(t){e.$set(e.form,"appname",t)},expression:"form.appname"}},e._l(e.appList,(function(e){return a("el-option",{key:e.appname,attrs:{label:e.title,value:e.appname}})})),1)],1)],1),a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"描述",prop:"title"}},[a("el-input",{attrs:{clearable:"",placeholder:"请输入描述"},model:{value:e.form.title,callback:function(t){e.$set(e.form,"title",t)},expression:"form.title"}})],1)],1),a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"配置键",prop:"key"}},[a("el-input",{attrs:{clearable:"",placeholder:"请输入配置项KEY"},model:{value:e.form.key,callback:function(t){e.$set(e.form,"key",t)},expression:"form.key"}})],1)],1),a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"配置值",prop:"value"}},[a("el-input",{attrs:{clearable:"",placeholder:"请输入配置项VALUE"},model:{value:e.form.value,callback:function(t){e.$set(e.form,"value",t)},expression:"form.value"}})],1)],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),a("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],1)],1)},r=[],o=a("b775");function n(e){return Object(o["a"])({url:"/config/conf/list",method:"get",params:e})}function i(e){return Object(o["a"])({url:"/config/conf/listEnvApp",method:"get",params:e})}function s(e,t){return Object(o["a"])({url:"/config/conf/"+e+"/"+t,method:"get"})}function u(e){return Object(o["a"])({url:"/config/conf",method:"post",data:e})}function c(e,t){return Object(o["a"])({url:"/config/conf/"+e+"/"+t,method:"delete"})}a("542c");var p={name:"Conf",dicts:["sys_show_hide","sys_normal_disable"],data:function(){return{loading:!0,showSearch:!0,list:[],envList:[],appList:[],title:"",open:!1,refreshTable:!0,total:0,queryParams:{pageNum:1,pageSize:10,env:void 0,appname:void 0,title:void 0,key:void 0,value:void 0},form:{},rules:{env:[{required:!0,message:"环境名不能为空",trigger:"blur"}],appname:[{required:!0,message:"应用名不能为空",trigger:"blur"}],title:[{required:!0,message:"描述不能为空",trigger:"blur"}],key:[{required:!0,message:"Key不能为空",trigger:"blur"}],value:[{required:!0,message:"Value不能为空",trigger:"blur"}]}}},created:function(){this.getList()},methods:{selected:function(e){this.form.icon=e},getList:function(){var e=this;this.loading=!0,n(this.queryParams).then((function(t){e.loading=!1,e.list=t.rows,e.total=t.total})),i(this.queryParams).then((function(t){console.log(t),e.envList=t.data.env,e.appList=t.data.app}))},cancel:function(){this.open=!1,this.reset()},reset:function(){this.form={parentId:0,appname:void 0,env:void 0,tile:void 0,key:void 0,value:void 0,icon:void 0,orderNum:void 0,isFrame:"1",isCache:"0",visible:"0",status:"0"},this.resetForm("form")},handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.resetForm("queryForm"),this.handleQuery()},handleAdd:function(e){this.reset(),this.open=!0,this.title="添加配置项"},handleUpdate:function(e){var t=this;this.reset(),s(e.env,e.key).then((function(e){t.form=e.data,t.open=!0,t.title="修改配置项"}))},submitForm:function(){var e=this;this.$refs["form"].validate((function(t){t&&void 0!=e.form.appname&&u(e.form).then((function(t){e.$modal.msgSuccess("保存成功"),e.open=!1,e.getList()}))}))},handleDelete:function(e){var t=this;this.$modal.confirm("是否确认删除 ["+e.env+"."+e.key+"] 的数据项？").then((function(){return c(e.env,e.key)})).then((function(){t.getList(),t.$modal.msgSuccess("删除成功")})).catch((function(){}))}}},m=p,d=a("2877"),f=Object(d["a"])(m,l,r,!1,null,null,null);t["default"]=f.exports}}]);