<template>
  <div >
    <el-card class="box-card">
      <el-tabs v-model="tabName">
        <el-tab-pane
          :key="item.name"
          v-for="item in tableTabs"
          :label="item.title"
          :name="item.name"
          class="tab-pane"
        >
          <div v-if="item.name ==='form'">
            <form-builder ref="formBuilder" v-model="formVal" :buildData="formCode" v-if="itemList.length>0"/>
            <div style="margin-bottom:15px;text-align:center">
                <el-button type="primary" class="button" @click="handlerSubForm">提交</el-button>
            </div>
          </div>
          <codemirror v-model="code" :options="codeMirror" v-show="item.name ==='config'||item.name ==='value'"/>
          <div v-if="item.name==='view'">
            <form-viewer ref="formViewer" v-model="formVal" :buildData="formCode"></form-viewer>
          </div>
        </el-tab-pane>
      </el-tabs>

    </el-card>
  </div>
</template>

<script>
import formBuilder from '@/components/FormDesigner/formBuilder'
import formViewer from '@/components/FormDesigner/formViewer'
import {codemirror} from 'vue-codemirror';
import axios from 'axios'
// 核心样式
import 'codemirror/lib/codemirror.css';
// 引入主题后还需要在 options 中指定主题才会生效
import 'codemirror/theme/dracula.css';
import 'codemirror/mode/javascript/javascript'
const options = {
    tabSize: 2, // 缩进格式
    theme: 'dracula', // 主题，对应主题库 JS 需要提前引入
    lineNumbers: true, // 显示行号
    line: true,
    styleActiveLine: true, // 高亮选中行
    hintOptions: {
      completeSingle: true // 当匹配只有一项的时候是否自动补全
    }
  }
export default {
  components:{
    formBuilder,
    codemirror,
    formViewer
  },
  data(){
    return{
      formCode:'',
      formVal:'',
      tabName:'form',
      tableTabs: [{
          title: '编辑表单',
          name: 'form',
        }, {
          title: '配置',
          name: 'config',
        }, {
          title: '数据',
          name: 'value',
        }],
      codeMirror:options
    }
  },
  mounted(){
    this.$nextTick(()=> {
      const formValue = localStorage.getItem("formValue");
      this.formCode = formValue;
      axios.get('https://www.fastmock.site/mock/51715c0157535b99010bde55f2df33c8/formDesigner/api/cascaderOptions')
        .then(res => {
          //console.log(res.data);
        })
    })

  },
  methods:{
    handlerchangeopen(){
    },
    handlerSubForm(){
      this.$refs['formBuilder'][0].validate();
      if(this.formVal!==''){
        this.addNewTab();
      }
    },
    addNewTab(){
      if(this.tableTabs.length<4){
        this.tableTabs.push({
          title: '查看表单',
          name: 'view'
        });
      }

    }
  },
  computed:{
    itemList(){
      if(this.formCode!==''){
        const form = JSON.parse(this.formCode);
        return form.list;
      }else{
        return [];
      }
    },
    code:{
      get() {
        if(this.tabName ==='form'){
          return ''
        }else if(this.tabName === 'config'){
          return this.formCode
        }else{
          return this.formVal
        }
      },
      set(){}
    }
  }
}
</script>

<style scoped>
/* .box-card >>> .el-tabs__header{
  margin: 0px 0px 0px 0px;
} */

.box-card{
  width:60%;
  position:absolute;
  top:0;
  left:0;
  right:0;
  bottom:0;
  margin:auto;
  overflow: auto;
  display: block;
}
#app{
  position: relative;
}
</style>
