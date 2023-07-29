(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-008dfd0e"],{a235:function(e,t,r){"use strict";r.r(t);var o=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"app-container"},[r("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,size:"small",inline:!0,"label-width":"68px"}},[r("el-form-item",{attrs:{label:"表单key",prop:"formKey"}},[r("el-input",{attrs:{placeholder:"请输入表单key",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.formKey,callback:function(t){e.$set(e.queryParams,"formKey",t)},expression:"queryParams.formKey"}})],1),r("el-form-item",{attrs:{label:"表单名称",prop:"formName"}},[r("el-input",{attrs:{placeholder:"请输入表单名称",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.formName,callback:function(t){e.$set(e.queryParams,"formName",t)},expression:"queryParams.formName"}})],1),r("el-form-item",[r("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),r("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),r("el-row",{staticClass:"mb8",attrs:{gutter:10}},[r("el-col",{attrs:{span:1.5}},[r("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["workflow:dynamicForm:add"],expression:"['workflow:dynamicForm:add']"}],attrs:{type:"primary",plain:"",icon:"el-icon-plus",size:"mini"},on:{click:e.handleAdd}},[e._v("新增")])],1),r("el-col",{attrs:{span:1.5}},[r("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["workflow:dynamicForm:edit"],expression:"['workflow:dynamicForm:edit']"}],attrs:{type:"success",plain:"",icon:"el-icon-edit",size:"mini",disabled:e.single},on:{click:e.handleUpdate}},[e._v("修改")])],1),r("el-col",{attrs:{span:1.5}},[r("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["workflow:dynamicForm:remove"],expression:"['workflow:dynamicForm:remove']"}],attrs:{type:"danger",plain:"",icon:"el-icon-delete",size:"mini",disabled:e.multiple},on:{click:e.handleDelete}},[e._v("删除")])],1),r("el-col",{attrs:{span:1.5}},[r("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["workflow:dynamicForm:export"],expression:"['workflow:dynamicForm:export']"}],attrs:{type:"warning",plain:"",icon:"el-icon-download",size:"mini"},on:{click:e.handleExport}},[e._v("导出")])],1),r("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),r("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.dynamicFormList},on:{"selection-change":e.handleSelectionChange}},[r("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),e._e(),r("el-table-column",{attrs:{label:"表单key",align:"center",prop:"formKey"}}),r("el-table-column",{attrs:{label:"表单名称",align:"center",prop:"formName"}}),r("el-table-column",{attrs:{label:"表单备注",align:"center",prop:"formRemark"}}),r("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["workflow:dynamicForm:edit"],expression:"['workflow:dynamicForm:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(r){return e.handleUpdate(t.row)}}},[e._v("修改")]),r("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["workflow:dynamicForm:edit"],expression:"['workflow:dynamicForm:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-thumb"},on:{click:function(r){return e.handleFormDesigner(t.row)}}},[e._v("表单设计")]),r("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["workflow:dynamicForm:remove"],expression:"['workflow:dynamicForm:remove']"}],attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(r){return e.handleDelete(t.row)}}},[e._v("删除")])]}}])})],1),r("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}}),r("el-dialog",{attrs:{title:e.title,visible:e.open,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[r("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"80px"}},[r("el-form-item",{attrs:{label:"表单key",prop:"formKey"}},[r("el-input",{attrs:{placeholder:"请输入表单key"},model:{value:e.form.formKey,callback:function(t){e.$set(e.form,"formKey",t)},expression:"form.formKey"}})],1),r("el-form-item",{attrs:{label:"表单名称",prop:"formName"}},[r("el-input",{attrs:{placeholder:"请输入表单名称"},model:{value:e.form.formName,callback:function(t){e.$set(e.form,"formName",t)},expression:"form.formName"}})],1),r("el-form-item",{attrs:{label:"排序",prop:"orderNo"}},[r("el-input-number",{attrs:{placeholder:"排序"},model:{value:e.form.orderNo,callback:function(t){e.$set(e.form,"orderNo",t)},expression:"form.orderNo"}})],1),r("el-form-item",{attrs:{label:"表单备注",prop:"formRemark"}},[r("el-input",{attrs:{type:"textarea",placeholder:"请输入内容"},model:{value:e.form.formRemark,callback:function(t){e.$set(e.form,"formRemark",t)},expression:"form.formRemark"}})],1)],1),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{attrs:{loading:e.buttonLoading,type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),r("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],1)],1)},a=[],n=r("5530"),i=(r("14d9"),r("d81d"),r("d3b7"),r("d60a")),l={name:"DynamicForm",data:function(){return{buttonLoading:!1,loading:!0,ids:[],single:!0,multiple:!0,showSearch:!0,total:0,dynamicFormList:[],title:"",open:!1,queryParams:{pageNum:1,pageSize:10,formKey:void 0,formName:void 0},form:{},rules:{formKey:[{required:!0,message:"表单key不能为空",trigger:"blur"}],formName:[{required:!0,message:"表单名称不能为空",trigger:"blur"}]}}},created:function(){this.getList()},methods:{handleFormDesigner:function(e){this.$router.push("/workflow/dynamicFormDesigner/"+e.id)},getList:function(){var e=this;this.loading=!0,Object(i["d"])(this.queryParams).then((function(t){e.dynamicFormList=t.rows,e.total=t.total,e.loading=!1}))},cancel:function(){this.open=!1,this.reset()},reset:function(){this.form={id:void 0,formKey:void 0,formName:void 0,formDesignerText:void 0,createTime:void 0,updateTime:void 0,createBy:void 0,updateBy:void 0,formRemark:void 0,status:!0,orderNo:0},this.resetForm("form")},handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.resetForm("queryForm"),this.handleQuery()},handleSelectionChange:function(e){this.ids=e.map((function(e){return e.id})),this.single=1!==e.length,this.multiple=!e.length},handleAdd:function(){this.reset(),this.open=!0,this.title="添加流程单"},handleUpdate:function(e){var t=this;this.loading=!0,this.reset();var r=e.id||this.ids;Object(i["c"])(r).then((function(e){t.loading=!1,t.form=e.data,t.open=!0,t.title="修改流程单"}))},submitForm:function(){var e=this;this.$refs["form"].validate((function(t){t&&(e.buttonLoading=!0,null!=e.form.id?Object(i["f"])(e.form).then((function(t){e.$modal.msgSuccess("修改成功"),e.open=!1,e.getList()})).finally((function(){e.buttonLoading=!1})):Object(i["a"])(e.form).then((function(t){e.$modal.msgSuccess("新增成功"),e.open=!1,e.getList()})).finally((function(){e.buttonLoading=!1})))}))},handleDelete:function(e){var t=this,r=e.id||this.ids;this.$modal.confirm('是否确认删除流程单编号为"'+r+'"的数据项？').then((function(){return t.loading=!0,Object(i["b"])(r)})).then((function(){t.loading=!1,t.getList(),t.$modal.msgSuccess("删除成功")})).finally((function(){t.loading=!1}))},handleExport:function(){this.download("workflow/dynamicForm/export",Object(n["a"])({},this.queryParams),"dynamicForm_".concat((new Date).getTime(),".xlsx"))}}},s=l,m=r("2877"),c=Object(m["a"])(s,o,a,!1,null,null,null);t["default"]=c.exports},d60a:function(e,t,r){"use strict";r.d(t,"d",(function(){return a})),r.d(t,"e",(function(){return n})),r.d(t,"c",(function(){return i})),r.d(t,"a",(function(){return l})),r.d(t,"f",(function(){return s})),r.d(t,"b",(function(){return m}));var o=r("b775");function a(e){return Object(o["a"])({url:"/workflow/dynamicForm/list",method:"get",params:e})}function n(e){return Object(o["a"])({url:"/workflow/dynamicForm/enableList",method:"get",params:e})}function i(e){return Object(o["a"])({url:"/workflow/dynamicForm/"+e,method:"get"})}function l(e){return Object(o["a"])({url:"/workflow/dynamicForm",method:"post",data:e})}function s(e){return Object(o["a"])({url:"/workflow/dynamicForm",method:"put",data:e})}function m(e){return Object(o["a"])({url:"/workflow/dynamicForm/"+e,method:"delete"})}}}]);