<template>
  <el-row :gutter="20">
    <el-col  :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
      <div>
        <el-card class="box-card" >
          <div slot="header" class="clearfix">
            <el-input placeholder="输入关键字进行过滤" v-model="filterText"></el-input>
          </div>
          <div class="text item">
            <el-tree
              class="filter-tree"
              :data="treedata"
              :props="defaultProps"
              :filter-node-method="filterNode"
              @node-click="expandUsers"
              ref="tree">
            </el-tree>
          </div>
        </el-card>
      </div>
    </el-col>
    <el-col  :xs="18" :sm="18" :md="18" :lg="18" :xl="18">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>查询</span>
        </div>
        <el-row :gutter="12">
          <el-table :data="dataTable" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="50"></el-table-column>
            <el-table-column type="index" width="50" label="序号"></el-table-column>
            <el-table-column prop="name" label="部门名称"></el-table-column>
            <el-table-column prop="id" label="部门帐号" ></el-table-column>
            <el-table-column prop="companyname" label="所属公司"></el-table-column>
        </el-table>
        </el-row>
      </el-card>
    </el-col>
    <el-col  :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
        <el-card class="box-card" >
          <div slot="header" class="clearfix">
            <span>已选人员</span>
          </div>
          <el-table :data="selectData" style="">
            <el-table-column type="index" width="50" label="序号"></el-table-column>
            <el-table-column prop="name" label="部门名称"></el-table-column>
            <el-table-column prop="id" label="部门帐号" ></el-table-column>
            <el-table-column prop="companyname" label="所属公司"></el-table-column>
          </el-table>
        </el-card>
    </el-col>
  </el-row>
</template>

<script>
import {getDeptByCompany, getOrgData} from '@/api/uc/orgm'

export default {
  name: "ruleToDept",
  data() {
    return {
      treedata: [],
      dataTable: [],
      selectData: [],
      isTreeChange: false,
      filterText: '',
      defaultProps: {
        value:'id',
        children: 'children',
        label: 'name'
      },
    };
  },
  components: {},
  mounted(){
    this.loadData();
  },
  methods: {
    saveTaskUser(){
      return this.selectData;
    },
    //tree输入查询
    filterNode(value, data) {
        if (!value) return true;
        return data.name.indexOf(value) !== -1;
    },//初始加载数据
    loadData(){
      getOrgData().then(res => {
        if (res.code == "200") {
          this.treedata = res.result.companyList;
          this.dataTable = res.result.departList;
        }
      });
    },//表格选中事件
    handleSelectionChange(row){
      if(this.isTreeChange==false){
        this.selectData=row;
      }
      else{
        if(row.length>0){
          this.selectData.push(row[0]);
        }
      }
    },//节点点击事件
    expandUsers(node){
      this.isTreeChange=true;
      debugger;
      const nodeid=node.id;
      getDeptByCompany({companyId:nodeid}).then(res => {
        if (res.code == "200") {
          this.dataTable = res.result;
        }
      });
    }
  },
};
</script>

<style scoped>
.m-b-1 {
  margin-bottom: 10px;
}
</style>
