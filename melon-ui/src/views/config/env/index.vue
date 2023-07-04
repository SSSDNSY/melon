<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
      <el-form-item label="项目列表" prop="env">
        <el-input
          v-model="queryParams.env"
          placeholder="请输入环境英文名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="list"
    >
      <el-table-column label="序号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="env" label="Env" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="title" label="环境名词" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="order" label="顺序" :show-overflow-tooltip="true"></el-table-column>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
          >新增
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 添加或修改项目对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="680px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="环境" prop="env">
              <el-input v-model="form.env" placeholder="请输入环境英文名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="环境名称" prop="title">
              <el-input v-model="form.title" placeholder="请输入环境中文名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="顺序" prop="order">
              <el-input v-model="form.order" placeholder="请输入顺序数字"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {delEnv, getEnv, listEnv, saveEnv} from "@/api/config/env";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";

  export default {
    name: "Env",
    dicts: ['sys_show_hide', 'sys_normal_disable'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 显示搜索条件
        showSearch: true,
        // 项目表格树数据
        list: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 重新渲染表格状态
        refreshTable: true,
        //分页数据
        total: 0,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          env: undefined,
          title: undefined,
          order: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          env: [
            {required: true, message: "英文名(KEY)不能为空", trigger: "blur"}
          ],
          title: [
            {required: true, message: "中文名(VALUE)不能为空", trigger: "blur"}
          ],
          order: [
            {required: true, message: "中文名(VALUE)不能为空", trigger: "blur"}
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      // 选择图标
      selected(name) {
        this.form.icon = name;
      },
      /** 查询项目列表 */
      getList() {
        this.loading = true;
        listEnv(this.queryParams).then(response => {
          console.log(response)
          this.list = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          parentId: 0,
          env: undefined,
          title: undefined,
          orderNum: undefined,
          isFrame: "1",
          isCache: "0",
          visible: "0",
          status: "0"
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 新增按钮操作 */
      handleAdd(row) {
        this.reset();
        this.open = true;
        this.title = "添加项目";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        getEnv(row.env).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改项目";
        });
      },
      /** 提交按钮 */
      submitForm: function () {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.env != undefined) {
              saveEnv(this.form).then(response => {
                this.$modal.msgSuccess("保存成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$modal.confirm('是否确认删除名称为"' + row.env + '"的数据项？').then(function () {
          return delEnv(row.env);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      }
    }
  };
</script>
