(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b9febe94"],{b7ee:function(e,t,n){"use strict";n("ddfd")},ddfd:function(e,t,n){},f2e5:function(e,t,n){"use strict";n.r(t);var i=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,inline:!0,"label-width":"68px"}},[n("el-form-item",{attrs:{label:"模型名称",prop:"name"}},[n("el-input",{attrs:{placeholder:"请输入模型名称",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.name,callback:function(t){e.$set(e.queryParams,"name",t)},expression:"queryParams.name"}})],1),n("el-form-item",{attrs:{label:"标识Key",prop:"key"}},[n("el-input",{attrs:{placeholder:"请输入标识Key",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.key,callback:function(t){e.$set(e.queryParams,"key",t)},expression:"queryParams.key"}})],1),n("el-form-item",[n("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),n("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),n("el-row",{staticClass:"mb8",attrs:{gutter:10}},[n("el-col",{attrs:{span:1.5}},[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["demo:demo:add"],expression:"['demo:demo:add']"}],attrs:{type:"primary",plain:"",icon:"el-icon-plus",size:"mini"},on:{click:e.handleAdd}},[e._v("新增")])],1),n("el-col",{attrs:{span:1.5}},[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["demo:demo:remove"],expression:"['demo:demo:remove']"}],attrs:{type:"danger",plain:"",icon:"el-icon-delete",size:"mini",disabled:e.multiple},on:{click:e.handleDelete}},[e._v("删除")])],1),n("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.modelList},on:{"selection-change":e.handleSelectionChange}},[n("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),n("el-table-column",{attrs:{fixed:"",align:"center",type:"index",label:"序号",width:"50"}}),n("el-table-column",{attrs:{fixed:"",align:"center",prop:"name",label:"模型名称"}}),n("el-table-column",{attrs:{align:"center",prop:"key",label:"标识Key"}}),n("el-table-column",{attrs:{align:"center",prop:"version",label:"版本号",width:"90"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row;return[e._v(" v"+e._s(n.version)+".0")]}}])}),n("el-table-column",{attrs:{align:"center",prop:"metaInfo",label:"备注说明","min-width":"130"}}),n("el-table-column",{attrs:{align:"center",prop:"createTime",label:"创建时间",width:"160"}}),n("el-table-column",{attrs:{align:"center",prop:"lastUpdateTime",label:"更新时间",width:"160"}}),n("el-table-column",{attrs:{label:"操作",align:"center",width:"160","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-row",{staticClass:"mb8",attrs:{gutter:10}},[n("el-col",{attrs:{span:1.5}},[n("el-button",{attrs:{type:"text",size:"small",icon:"el-icon-thumb"},on:{click:function(n){return e.clickDesign(t.row.id)}}},[e._v("设计流程")])],1),n("el-col",{attrs:{span:1.5}},[n("el-button",{attrs:{type:"text",size:"small",icon:"el-icon-download"},on:{click:function(n){return e.clickExportZip(t.row)}}},[e._v("导出")])],1)],1),n("el-row",{staticClass:"mb8",attrs:{gutter:10}},[n("el-col",{attrs:{span:1.5}},[n("el-button",{attrs:{type:"text",size:"small",icon:"el-icon-c-scale-to-original"},on:{click:function(n){return e.clickDeploy(t.row.id,t.row.key)}}},[e._v("流程部署")])],1),n("el-col",{attrs:{span:1.5}},[n("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(n){return e.handleDelete(t.row)}}},[e._v("删除")])],1)],1)]}}])})],1),n("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}}),e.bpmnJsModelVisible?n("el-dialog",{attrs:{title:"设计模型","before-close":e.handleClose,visible:e.bpmnJsModelVisible,fullscreen:"","modal-append-to-body":!1},on:{"update:visible":function(t){e.bpmnJsModelVisible=t}}},[n("bpmnJs",{ref:"bpmnJsModel",attrs:{categorysBpmn:e.categorysBpmn,modelId:e.modelId},on:{"close-bpmn":e.closeBpmn}})],1):e._e()],1)},a=[],l=(n("d81d"),n("d3b7"),n("b0c0"),n("6282")),o=n("d2e3"),s={dicts:["act_category"],name:"Model",components:{BpmnJs:o["default"]},data:function(){return{buttonLoading:!1,loading:!0,exportLoading:!1,ids:[],single:!0,multiple:!0,showSearch:!0,total:0,bpmnJsModelVisible:!1,modelList:[],queryParams:{pageNum:1,pageSize:10,name:void 0,key:void 0},form:{},rules:{name:[{required:!0,message:"模型名称不能为空",trigger:"blur"}],key:[{required:!0,message:"标识key不能为空",trigger:"blur"}]},modelId:null,categorys:[]}},created:function(){this.categorysBpmn=this.dict.type.act_category,this.getList()},methods:{handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.daterangeCreateTime=[],this.resetForm("queryForm"),this.handleQuery()},handleSelectionChange:function(e){this.ids=e.map((function(e){return e.id})),this.single=1!==e.length,this.multiple=!e.length},getList:function(){var e=this;this.loading=!0,Object(l["f"])(this.queryParams).then((function(t){e.modelList=t.rows,e.total=t.total,e.loading=!1}))},handleDelete:function(e){var t=this,n=e.id||this.ids;this.$modal.confirm('是否确认删除模型id为"'+n+'"的数据项？').then((function(){return t.loading=!0,Object(l["c"])(n)})).then((function(){t.loading=!1,t.getList(),t.$modal.msgSuccess("删除成功")})).finally((function(){t.loading=!1}))},clickDeploy:function(e,t){var n=this;this.$modal.confirm("是否部署模型key为【"+t+"】流程？").then((function(){return n.loading=!0,Object(l["d"])(e)})).then((function(){n.loading=!1,n.getList(),n.$modal.msgSuccess("部署成功")})).finally((function(){n.loading=!1}))},handleAdd:function(){this.modelId="new",this.bpmnJsModelVisible=!0},submitForm:function(){var e=this;this.$refs["form"].validate((function(t){t&&(e.buttonLoading=!0,Object(l["a"])(e.form).then((function(t){e.$modal.msgSuccess("新增成功"),e.open=!1,e.getList()})).finally((function(){e.buttonLoading=!1})))}))},clickDesign:function(e){this.modelId=e,this.bpmnJsModelVisible=!0},closeBpmn:function(){this.getList(),this.bpmnJsModelVisible=!1},handleClose:function(){var e=this;this.$confirm("请记得点击保存按钮，确定关闭设计窗口?","确认关闭",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){e.bpmnJsModelVisible=!1,e.getList()})).catch((function(){}))},clickExportZip:function(e){this.$download.zip("/workflow/model/export/zip/"+e.id,e.name+"-"+e.key)}}},r=s,c=(n("b7ee"),n("2877")),d=Object(c["a"])(r,i,a,!1,null,null,null);t["default"]=d.exports}}]);