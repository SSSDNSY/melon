(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5782ba4e"],{5106:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("container",{staticClass:"app-container"},[a("div",{staticClass:"btn-container"},[a("el-button",{attrs:{size:"mini",type:"primary",icon:"el-icon-plus"},on:{click:function(t){return e.openDialog("add")}}},[e._v("新增类别")])],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticStyle:{width:"100%"},attrs:{"row-key":"id",data:e.list,indent:36,"default-expand-all":"","tree-props":{children:"children"}}},e._l(e.tableColumns,(function(t){return a("el-table-column",e._b({key:t.prop,attrs:{align:"center"},scopedSlots:e._u([{key:"default",fn:function(n){return["index"===t.prop?a("div",[e._v(e._s(n.$index+1))]):"operation"===t.prop?a("div",{staticClass:"operation-btn"},[a("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(t){return e.openDialog("edit",n.row)}}},[e._v("编辑")]),a("el-popconfirm",{attrs:{title:"您确定要删除吗？"},on:{confirm:function(t){return e.deleteById(n.row)}}},[a("el-button",{staticClass:"btn-danger",attrs:{slot:"reference",size:"mini",icon:"el-icon-delete",type:"text"},slot:"reference"},[e._v("删除 ")])],1)],1):a("span",[e._v(e._s(n.row[t.prop]))])]}}],null,!0)},"el-table-column",t,!1))})),1),a("el-dialog",{attrs:{title:e.titleMap[e.dialogStatus],visible:e.dialogVisible,width:"630px","destroy-on-close":"","close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-form",{ref:"dataForm",staticClass:"dialog-form",attrs:{rules:e.rules,model:e.formData,"label-suffix":"：","label-width":"110px"}},[a("el-form-item",{attrs:{label:"类别名称",prop:"name"}},[a("el-input",{attrs:{placeholder:"请输入类别名称"},model:{value:e.formData.name,callback:function(t){e.$set(e.formData,"name",t)},expression:"formData.name"}})],1),a("el-form-item",{attrs:{label:"所属上级",prop:"parentId"}},[a("select-tree",{staticClass:"tree",attrs:{data:e.treeData,placeholder:"请选择所属上级"},model:{value:e.formData.parentId,callback:function(t){e.$set(e.formData,"parentId",t)},expression:"formData.parentId"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){e.dialogVisible=!1}}},[e._v(" 取消")]),a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(t){return e.onOk(e.dialogStatus)}}},[e._v(" 确定")])],1)],1)],1)},r=[],i=a("c7eb"),o=a("1da1"),s=(a("d3b7"),a("159b"),a("b0c0"),a("ac1f"),a("841c"),a("7f1c")),l=a("333d"),c=a("8fc7"),d=a("fb36"),u=(a("53ca"),a("b64b"),a("99af"),a("25f0"),a("fb6a"),a("2ef0"));var f=function(e){for(var t in e)Object(u["isArray"])(e[t])?e[t]=[]:Object(u["isString"])(e[t])?e[t]="":e[t]=null},p={name:"user-manage",components:{Pagination:l["a"],SelectTree:d["a"]},data:function(){return{titleMap:c["b"],tableColumns:[{label:"序号",prop:"index",type:"index"},{label:"类型名称",prop:"name",align:"left"},{label:"类型编号",prop:"id"},{label:"操作",prop:"operation",width:"200px"}],list:[],listLoading:!1,searchForm:{page:1,pagesize:10,name:void 0},dialogVisible:!1,dialogStatus:"add",formData:{name:"",parentId:null},rules:{name:[{required:!0,message:"请输入名称",trigger:"blur"}]},treeData:[]}},created:function(){this.getList()},methods:{getList:function(){var e=this;this.listLoading=!0,Object(s["b"])(this.searchForm).then((function(t){200===t.code?e.list=t.data:e.$message.error("获取列表失败"),e.listLoading=!1}))},search:function(e){"reset"===e&&this.$refs.form.resetFields(),this.searchForm.page=1,this.getList()},getSelectData:function(){var e=this;Object(s["b"])().then((function(t){if(200===t.code){var a=t.data;a.forEach((function(e){e.children.forEach((function(e){return e.disabled=!0}))})),e.treeData=a}else e.$message.error("获取失败")}))},openDialog:function(e,t){var a=this;this.getSelectData(),f(this.formData),this.dialogStatus=e,this.dialogVisible=!0,this.$nextTick((function(){a.$refs.dataForm.clearValidate(),"add"!==e&&(a.formData.id=t.id,a.formData.name=t.name,a.formData.parentId=t.parentId)}))},onOk:function(e){var t=this;console.log("this.formData",this.formData),this.$refs.dataForm.validate(function(){var a=Object(o["a"])(Object(i["a"])().mark((function a(n){var r;return Object(i["a"])().wrap((function(a){while(1)switch(a.prev=a.next){case 0:if(!n){a.next=12;break}if("add"!==e){a.next=7;break}return a.next=4,Object(s["c"])(t.formData);case 4:a.t0=a.sent,a.next=10;break;case 7:return a.next=9,Object(s["c"])(t.formData);case 9:a.t0=a.sent;case 10:r=a.t0,200===r.code?(t.$message.success("".concat(c["b"][e],"成功")),t.dialogVisible=!1,t.search()):t.$message.error("".concat(c["b"][e],"失败"));case 12:case"end":return a.stop()}}),a)})));return function(e){return a.apply(this,arguments)}}())},deleteById:function(e){var t=this;return Object(o["a"])(Object(i["a"])().mark((function a(){var n;return Object(i["a"])().wrap((function(a){while(1)switch(a.prev=a.next){case 0:return a.next=2,Object(s["a"])(e.id);case 2:n=a.sent,200===n.code?(t.$message.success("删除成功"),t.search()):t.$message.error("删除失败");case 4:case"end":return a.stop()}}),a)})))()}}},m=p,b=a("2877"),h=Object(b["a"])(m,n,r,!1,null,null,null);t["default"]=h.exports},"7f1c":function(e,t,a){"use strict";a.d(t,"b",(function(){return r})),a.d(t,"c",(function(){return i})),a.d(t,"a",(function(){return o}));var n=a("b775");function r(){return Object(n["a"])({url:"/flowSort/getTree",method:"get"})}function i(e){return Object(n["a"])({url:"/flowSort/save",method:"post",data:e})}function o(e){return Object(n["a"])({url:"/flowSort/"+e,method:"delete"})}},"8fc7":function(e,t,a){"use strict";a.d(t,"b",(function(){return n})),a.d(t,"a",(function(){return r}));var n={add:"新增",edit:"编辑",view:"查看"},r=["#3D96F9","#F5B658","#42DDA9","#B48BE4","#C55A5A"]}}]);