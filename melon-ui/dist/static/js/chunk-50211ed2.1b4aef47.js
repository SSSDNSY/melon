(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-50211ed2"],{"581c":function(n,r,e){"use strict";e.r(r);var t=function(){var n=this,r=n.$createElement,e=n._self._c||r;return e("div",[e("form-designer",{ref:"formDesigner",model:{value:n.designerForm,callback:function(r){n.designerForm=r},expression:"designerForm"}})],1)},o=[],u=e("d60a"),i={name:"dynamicFormDesigner",data:function(){return{designerForm:""}},mounted:function(){var n=this;Object(u["c"])(this.$route.params.id).then((function(r){n.designerForm=r.data.formDesignerText}))},methods:{}},c=i,a=e("2877"),d=Object(a["a"])(c,t,o,!1,null,null,null);r["default"]=d.exports},d60a:function(n,r,e){"use strict";e.d(r,"d",(function(){return o})),e.d(r,"e",(function(){return u})),e.d(r,"c",(function(){return i})),e.d(r,"a",(function(){return c})),e.d(r,"f",(function(){return a})),e.d(r,"b",(function(){return d}));var t=e("b775");function o(n){return Object(t["a"])({url:"/workflow/dynamicForm/list",method:"get",params:n})}function u(n){return Object(t["a"])({url:"/workflow/dynamicForm/enableList",method:"get",params:n})}function i(n){return Object(t["a"])({url:"/workflow/dynamicForm/"+n,method:"get"})}function c(n){return Object(t["a"])({url:"/workflow/dynamicForm",method:"post",data:n})}function a(n){return Object(t["a"])({url:"/workflow/dynamicForm",method:"put",data:n})}function d(n){return Object(t["a"])({url:"/workflow/dynamicForm/"+n,method:"delete"})}}}]);