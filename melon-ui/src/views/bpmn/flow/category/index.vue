<template>
  <container class="app-container">
    <!-- 搜索 -->
    <div class="btn-container">
      <el-button size="mini" type="primary" icon="el-icon-plus" @click="openDialog('add')">新增类别</el-button>
      <!-- <el-button size="mini" style="width: 117px">批量操作</el-button> -->
    </div>
    <!-- table -->
    <el-table
      row-key="id"
      v-loading="listLoading"
      :data="list"
      :indent="36"
      default-expand-all
      :tree-props="{ children: 'children' }"
      style="width: 100%"
    >
      <el-table-column v-for="item in tableColumns" :key="item.prop" align="center" v-bind="item">
        <template slot-scope="scope">
          <div v-if="item.prop === 'index'">{{ scope.$index + 1 }}</div>
          <div v-else-if="item.prop === 'operation'" class="operation-btn">
            <el-button size="mini" type="text" icon="el-icon-edit" @click="openDialog('edit', scope.row)">编辑</el-button>
            <el-popconfirm title="您确定要删除吗？" @confirm="deleteById(scope.row)">
              <el-button size="mini" icon="el-icon-delete" slot="reference" type="text" class="btn-danger">删除
              </el-button>
            </el-popconfirm>
          </div>
          <span v-else>{{ scope.row[item.prop] }}</span>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      :title="titleMap[dialogStatus]"
      :visible.sync="dialogVisible"
      width="630px"
      destroy-on-close
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <el-form
        ref="dataForm"
        :rules="rules"
        :model="formData"
        label-suffix="："
        label-width="110px"
        class="dialog-form"
      >
        <el-form-item label="类别名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入类别名称"/>
        </el-form-item>
        <el-form-item label="所属上级" prop="parentId">
          <select-tree
            class="tree"
            v-model="formData.parentId"
            :data="treeData"
            placeholder="请选择所属上级"
          ></select-tree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" @click="dialogVisible = false"> 取消</el-button>
        <el-button size="mini" type="primary" @click="onOk(dialogStatus)"> 确定</el-button>
      </div>
    </el-dialog>
  </container>
</template>

<script>
import {deleteFlowSort, flowSortTree, saveFlowSort} from '@/api/bpmn/flowSort';
import Pagination from '@/components/Pagination';
import {titleMap} from '@/const';
import SelectTree from '@/components/SelectTree/index.vue';
import {clearObj} from '@/utils/comm';


export default {
  name: 'user-manage',
  components: {Pagination, SelectTree},
  data() {
    return {
      titleMap,
      tableColumns: [
        {
          label: '序号',
          prop: 'index',
          type: 'index'
        },
        {
          label: '类型名称',
          prop: 'name',
          align: 'left'
        },
        {
          label: '类型编号',
          prop: 'id'
        },
        {
          label: '操作',
          prop: 'operation',
          width: '200px'
        }
      ],
      list: [],
      listLoading: false,
      searchForm: {
        page: 1,
        pagesize: 10,
        name: undefined
      },
      dialogVisible: false,
      dialogStatus: 'add',
      formData: {
        name: '',
        parentId: null
      },
      rules: {
        name: [{required: true, message: '请输入名称', trigger: 'blur'}]
      },
      treeData: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 获取
    getList() {
      this.listLoading = true;
      flowSortTree(this.searchForm).then((res) => {
        if (res.code === 200) {
          this.list = res.data;
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
    // 获取下拉框数据
    getSelectData() {
      flowSortTree().then((res) => {
        if (res.code === 200) {
          const data = res.data;
          data.forEach((x) => {
            x.children.forEach((y) => (y.disabled = true));
          });
          this.treeData = data;
        } else {
          this.$message.error('获取失败');
        }
      });
    },
    openDialog(action, row) {
      this.getSelectData();
      clearObj(this.formData);
      this.dialogStatus = action;
      this.dialogVisible = true;
      this.$nextTick(() => {
        this.$refs.dataForm.clearValidate();
        if (action !== 'add') {
          this.formData.id = row.id;
          this.formData.name = row.name;
          this.formData.parentId = row.parentId;
        }
      });
    },
    onOk(action) {
      console.log('this.formData', this.formData);
      this.$refs.dataForm.validate(async (valid) => {
        if (valid) {
          const res =
            action === 'add'
              ? await saveFlowSort(this.formData)
              : await saveFlowSort(this.formData);
          if (res.code === 200) {
            this.$message.success(`${titleMap[action]}成功`);
            this.dialogVisible = false;
            this.search();
          } else {
            this.$message.error(`${titleMap[action]}失败`);
          }
        }
      });
    },
    async deleteById(row) {
      const res = await deleteFlowSort(row.id);
      if (res.code === 200) {
        this.$message.success(`删除成功`);
        this.search();
      } else {
        this.$message.error(`删除失败`);
      }
    }
  }
};
</script>
