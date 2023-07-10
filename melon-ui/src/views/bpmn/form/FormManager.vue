<template>
  <container type="ghost">
    <div class="app-container">
      <!-- 搜索 -->
      <div class="filter-container">
        <el-form ref="form" :model="searchForm" label-width="80px" align="right" @submit.prevent.native>
          <el-row>
            <el-col :span="6">
              <el-form-item label="关键字" prop="name">
                <el-input clearable v-model="searchForm.searchKeyword" @keyup.enter.native="search"
                          placeholder="请输入表单ID、名称或标识KEY"></el-input>
              </el-form-item>
            </el-col>
            <!-- <el-col :span="6">
              <el-form.js-item label="流程类型">
                <el-input v-model="searchForm.form_type" placeholder="请输入流程类型"></el-input>
              </el-form.js-item>
            </el-col> -->
            <el-col :span="18" class="btn-container">
              <el-button icon="el-icon-search" type="primary"  @click="search">查询</el-button>
              <el-button icon="el-icon-refresh" @click="search('reset')">重置</el-button>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div class="btn-container">
        <el-button type="primary" icon="el-icon-plus" @click="openNewDialog">新建表单</el-button>
        <!--         <el-button style="width: 117px">批量操作</el-button> -->
        <!--         <el-button style="width: 117px">导入</el-button> -->
        <!--         <el-button style="width: 117px">导入</el-button> -->
      </div>
      <!-- table -->
      <el-table row-key="id" stripe v-loading="listLoading" :data="list" style="width: 100%">
        <el-table-column v-for="item in tableColumns" :key="item.prop" v-bind="item">
          <template slot-scope="scope">
            <div v-if="item.prop === 'index'">{{ scope.$index + 1 }}</div>
            <div v-else-if="item.prop === 'operation'" class="operation-btn">
              <el-button icon="el-icon-view" type="text" @click="openView(scope.row)">预览</el-button>
              <el-button icon="el-icon-edit" type="text" @click="edit(scope.row)">编辑</el-button>
              <el-button icon="el-icon-edit" type="text" @click="edit(scope.row)">版本</el-button>
              <el-popconfirm title="您确定要删除吗？" @confirm="deleteById(scope.row)">
                <el-button icon="el-icon-delete" slot="reference" type="text" class="btn-danger">删除</el-button>
              </el-popconfirm>
            </div>
            <span v-else>{{ scope.row[item.prop] }}</span>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="searchForm.page"
        :limit.sync="searchForm.pagesize"
        @pagination="getList"
      />
    </div>
    <el-dialog
      title="新建表单"
      v-if="dialogNewSortVisible == true"
      :close-on-click-modal="false"
      :modal-append-to-body="true"
      :visible.sync="dialogNewSortVisible"
      @close="dialogNewSortVisible = false"
      custom-class="center-dialog"
    >
      <slot name="-" style="border: none; padding: 0px">
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          style="width: 95%"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="表单ID" prop="id">
            <el-input v-model="ruleForm.id"></el-input>
          </el-form-item>
          <el-form-item label="表单名称" prop="name">
            <el-input v-model="ruleForm.name" @change="setTableName"></el-input>
          </el-form-item>
          <el-form-item label="存储表名" prop="tableName">
            <el-input v-model="ruleForm.tableName"></el-input>
          </el-form-item>
          <el-form-item label="表单分类" prop="formSort">
            <select-tree class="tree"
                         v-model="ruleForm.formSort"
                         :data="tabList"
                         placeholder="请选择所属分类">
            </select-tree>
          </el-form-item>
        </el-form>
      </slot>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogNewSortVisible = false"> 取消</el-button>
        <el-button type="primary" @click="openFormDialog"> 确定</el-button>
      </div>
    </el-dialog>

    <el-drawer
      class="form-design"
      title="表单设计"
      v-if="dialogVisible == true"
      :visible.sync="dialogVisible"
      size="100%"
      :append-to-body="false"
      direction="btt"
      destroy-on-close
      @close="closeDialog"
    >
      <form-designer :formConf="formConf" :mapList="mapList" :formId="formId"></form-designer>
    </el-drawer>

    <el-drawer
      :visible.sync="viewVisible"
      title="表单预览"
      size="95%"
      class="radius"
      :append-to-body="false"
      direction="btt"
      destroy-on-close
    >
      <preview :itemList="itemList" :formConf="formConf"/>
    </el-drawer>
  </container>
</template>

<script>
import {mapActions} from 'vuex';
import SelectTree from '@/components/SelectTree';
import {deleteForm, formList} from '@/api/bpmn/form';
import Pagination from '@/components/Pagination';
import FormDesigner from '@/components/FormComponents/formDesigner.vue';
import formConf from '@/components/FormComponents/custom/formConf';
import preview from '@/components/FormComponents/preview';
import {colorList, titleMap} from '@/const';

const pinyin = require('js-pinyin');

export default {
  name: 'formManager',
  components: {Pagination, FormDesigner, preview, SelectTree},
  data() {
    return {
      colorList,
      titleMap,
      tabList: [],
      activeName: '',
      tableColumns: [
        {
          label: '序号',
          prop: 'index',
          type: 'index'
        },
        {
          label: '表单名称',
          prop: 'name'
        },
        {
          label: '创建人',
          prop: 'createBy'
        },
        {
          label: '创建时间',
          prop: 'createTime'
        },
        {
          label: '修改人',
          prop: 'updateBy'
        },
        {
          label: '修改时间',
          prop: 'updateTime'
        },
        {
          label: '版本',
          prop: 'version'
        },
        {
          label: '操作',
          prop: 'operation',
          width: '300px'
        }
      ],
      list: [],
      total: 0,
      listLoading: false,
      searchForm: {
        page: 1,
        pagesize: 10,
        searchKeyword: '',
      },
      dialogVisible: false,
      viewVisible: false,
      dialogNewSortVisible: false,
      ruleForm: {
        id: '',
        name: '',
        tableName: '',
        formSort: null
      },
      itemList: [],
      formConf,
      formId: '',
      mapList: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    ...mapActions('store/page', ['close']),
    // 获取
    getList() {
      formList(this.searchForm).then((res) => {
        if (res.code === 200) {
          this.list = res.rows;
          this.total = res.total;
        } else {
          this.$message.error(`获取列表失败`);
        }
        this.listLoading = false;
      });
    },
    search(type) {
      if (type === 'reset') {
        this.$refs.form.resetFields();
      }
      this.searchForm.page = 1;
      this.getList();
    },
    async deleteById(row) {
      const res = await deleteForm({id: row.id});
      if (res.code === 200) {
        this.$message.success(`删除成功`);
        this.search('reset');
      } else {
        this.$message.error(`删除失败`);
      }
    },
    // 新建表单
    openNewDialog() {
      this.dialogNewSortVisible = true;
      this.ruleForm.id = this.uuidv4();
    },
    setTableName() {
      this.ruleForm.tableName = pinyin.getCamelChars(this.ruleForm.name);
    },
    //打开表单设计器
    openFormDialog() {
      if (this.ruleForm.name == '' || this.ruleForm.name == null) {
        this.$message.error('请填写必填项');
        return;
      }
      this.dialogNewSortVisible = false;
      this.dialogVisible = true;
      this.formConf = formConf;
      this.formConf.tableName = this.ruleForm.tableName;
      this.formConf.formSort = this.ruleForm.formSort;
      this.formConf.formRef = this.ruleForm.name;
      this.mapList = [];
      this.formId = this.ruleForm.id;
    },
    // 预览
    openView(row) {
      if (row.form_json == undefined) {
        this.formConf = formConf;
        this.itemList = [];
      } else {
        const mapJson = JSON.parse(row.form_json);
        this.formConf = mapJson.config;
        this.itemList = mapJson.list;
      }
      this.viewVisible = true;
    },
    // 关闭后，刷新列表
    closeDialog() {
      this.search('reset');
    },
    // 编辑
    edit(row) {
      console.log('row', row);
      if (row.form_json === undefined) {
        this.formConf = formConf;
        this.mapList = [];
        this.formId = row.id;
      } else {
        const mapJson = JSON.parse(row.form_json);
        this.formConf = mapJson.config;
        this.formId = row.id;
        this.mapList = mapJson.list;
      }
      this.dialogVisible = true;
    },
    uuidv4() {
      return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (Math.random() * 16) | 0,
          v = c == 'x' ? r : (r & 0x3) | 0x8;
        return v.toString(16);
      });
    },
    addForm() {
      // 新打开一个标签显示
      this.$router.push('/test');
      // 关闭该标签
      // this.close({ tagName: '/test' });
    }
  }
};
</script>
<style lang="scss">
.el-drawer__header {
  margin-bottom: 0px !important;
}

.el-drawer .el-drawer__header {
  color: #1f2228;
  border-color: rgba(0, 0, 0, .09);
  font-size: 16px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  line-height: 24px;
  border-bottom: 1px solid rgba(0, 0, 0, .09);
  padding: 20px 20px 15px;
  margin-bottom: 0;
}

.form-design .el-drawer__header {
  padding: 10px 15px;
}

.v-modal {
  z-index: 2000 !important;
}
</style>
