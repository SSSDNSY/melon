(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-17e1ea44"],{2934:function(t,e,a){"use strict";a.d(e,"d",(function(){return i})),a.d(e,"b",(function(){return o})),a.d(e,"a",(function(){return s})),a.d(e,"c",(function(){return n}));var r=a("b775");function i(){return Object(r["a"])({url:"/common/nextId",method:"get"})}function o(){return Object(r["a"])({url:"/common/getAllDriver",method:"get"})}function s(){return Object(r["a"])({url:"/druid/datasource.json",method:"post"})}function n(){return Object(r["a"])({url:"/common/getOneImg",method:"get"})}},"9e12":function(t,e,a){"use strict";a.r(e);var r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-tabs",{staticClass:"app-container",attrs:{type:"card"},on:{"tab-click":t.handleTabClick},model:{value:t.activeName,callback:function(e){t.activeName=e},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"配置管理",name:"dbConfManager"}},[a("div",{staticClass:"app-container"},[a("h2",[t._v("驱动信息")]),a("el-row",{attrs:{gutter:20}},t._l(t.drivers,(function(e){return a("el-col",{key:e.driverName,attrs:{span:6}},[a("el-card",{attrs:{type:"card",shadow:"hover"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",{staticClass:"text-large"},[t._v(t._s(e.driverName))]),a("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"},on:{click:t.gotoDriverTab}},[t._v("更多")])],1),a("div",{staticClass:"text item text-medium"},[t._v(" "+t._s("主版本："+e.majorVersion)+" ")]),a("div",{staticClass:"text item text-medium"},[t._v(" "+t._s("次版本："+e.minorVersion)+" ")])])],1)})),1)],1),a("div",{staticClass:"app-container"},[a("h2",[t._v("数据源管理")]),a("el-row",{attrs:{gutter:20}},t._l(t.druids,(function(e){return a("el-col",{key:e.Name,attrs:{span:6}},[a("el-card",{attrs:{type:"card",shadow:"hover"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",{staticClass:"text-extra-large"},[t._v(t._s(e.DbType))]),a("el-button-group",{staticStyle:{float:"right"}},[a("el-button",{attrs:{type:"text"},on:{click:t.gotoDruidTab}},[t._v("详细")]),a("el-button",{attrs:{type:"text"},on:{click:t.gotoDruidTab}},[t._v("测试")]),a("el-button",{staticClass:"text-red",attrs:{type:"text"},on:{click:t.gotoDruidTab}},[t._v("删除")]),a("el-button",{attrs:{type:"text"},on:{click:t.gotoDruidTab}},[t._v("新增")])],1)],1),a("div",{staticClass:"text item text-base"},[t._v(" "+t._s("驱动实例名："+e.Name)+" ")]),a("div",{staticClass:"text item text-medium"},[t._v(" "+t._s("用户名："+e.UserName)+" ")]),a("div",{staticClass:"text item text-medium",staticStyle:{width:"100%","word-wrap":"break-word"}},[t._v(" 连接地址："),a("span",{staticClass:"text-extra-small"},[t._v(t._s(e.URL))])]),a("div",{staticClass:"text item text-medium"},[t._v(" "+t._s("驱动类："+e.DriverClassName)+" ")])])],1)})),1)],1)]),a("el-tab-pane",{attrs:{label:"同步任务",name:"syncTask"}},[a("div",{staticClass:"app-container"},[a("el-form",{ref:"form",attrs:{model:t.form,rules:t.rules,"label-width":"80px"}},[a("el-form-item",{attrs:{label:"参数名称",prop:"configName"}},[a("el-input",{attrs:{placeholder:"请输入参数名称"},model:{value:t.form.configName,callback:function(e){t.$set(t.form,"configName",e)},expression:"form.configName"}})],1),a("el-form-item",{attrs:{label:"参数键名",prop:"configKey"}},[a("el-input",{attrs:{placeholder:"请输入参数键名"},model:{value:t.form.configKey,callback:function(e){t.$set(t.form,"configKey",e)},expression:"form.configKey"}})],1),a("el-form-item",{attrs:{label:"参数键值",prop:"configValue"}},[a("el-input",{attrs:{placeholder:"请输入参数键值"},model:{value:t.form.configValue,callback:function(e){t.$set(t.form,"configValue",e)},expression:"form.configValue"}})],1),a("el-form-item",{attrs:{label:"系统内置",prop:"configType"}},[a("el-radio-group",{model:{value:t.form.configType,callback:function(e){t.$set(t.form,"configType",e)},expression:"form.configType"}},t._l(t.dict.type.sys_yes_no,(function(e){return a("el-radio",{key:e.value,attrs:{label:e.value}},[t._v(t._s(e.label)+" ")])})),1)],1),a("el-form-item",{attrs:{label:"备注",prop:"remark"}},[a("el-input",{attrs:{type:"textarea",placeholder:"请输入内容"},model:{value:t.form.remark,callback:function(e){t.$set(t.form,"remark",e)},expression:"form.remark"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:t.submitForm}},[t._v(" 同 步")])],1)],1)])],1)},i=[],o=(a("14d9"),a("c0c3")),s=a("2934"),n={name:"Config",dicts:["sys_yes_no"],data:function(){return{activeName:"dbConfManager",loading:!0,druids:[],drivers:[],queryParams:{pageNum:1,pageSize:10,configName:void 0},form:{},rules:{configName:[{required:!0,message:"参数名称不能为空",trigger:"blur"}],configKey:[{required:!0,message:"参数键名不能为空",trigger:"blur"}],configValue:[{required:!0,message:"参数键值不能为空",trigger:"blur"}]}}},created:function(){this.getList(),this.getDatasource(),this.getAllDrivers()},methods:{handleTabClick:function(){},gotoDriverTab:function(){this.$router.push("/monitor/druid")},gotoDruidTab:function(){this.$router.push("/monitor/druid")},getList:function(){var t=this;this.loading=!0,Object(o["e"])(this.addDateRange(this.queryParams,this.dateRange)).then((function(e){t.configList=e.rows,t.total=e.total,t.loading=!1}))},getAllDrivers:function(){var t=this;Object(s["b"])().then((function(e){t.drivers=e.data}))},getDatasource:function(){var t=this;Object(s["a"])().then((function(e){t.druids=e.Content}))},cancel:function(){this.open=!1,this.reset()},reset:function(){this.form={configId:void 0,configName:void 0,configKey:void 0,configValue:void 0,configType:"Y",remark:void 0},this.resetForm("form")},handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.dateRange=[],this.resetForm("queryForm"),this.handleQuery()},handleAdd:function(){this.reset(),this.open=!0,this.title="添加参数"},submitForm:function(){var t=this;this.$refs["form"].validate((function(e){e&&(void 0!=t.form.configId?Object(o["g"])(t.form).then((function(e){t.$modal.msgSuccess("修改成功"),t.open=!1,t.getList()})):Object(o["a"])(t.form).then((function(e){t.$modal.msgSuccess("新增成功"),t.open=!1,t.getList()})))}))}}},l=n,c=a("2877"),u=Object(c["a"])(l,r,i,!1,null,null,null);e["default"]=u.exports}}]);