<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
      <el-form-item label="模型标识" prop="title">
        <el-input clearable
                  v-model="queryParams.key"
                  placeholder="请输入模型标识"
                  clearable
                  @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模型名称" prop="title">
        <el-input clearable
                  v-model="queryParams.name"
                  placeholder="请输入模型名称"
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
          <span>{{ (queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="编号" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="deploymentId" label="部署编号" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="key" label="标识" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="name" label="名称" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="category" label="分类" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="version" label="版本" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleDeploy(scope.row)"
          >部署
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleExport(scope.row)"
          >下载
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleDesign(scope.row)"
          >设计
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleRemove(scope.row)"
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
    <!-- 添加或修改配置项对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="680px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="标识" prop="title">
              <el-input clearable v-model="form.key" placeholder="请输入标识" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="名称" prop="title">
              <el-input clearable v-model="form.name" placeholder="请输入名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="分类" prop="title">
              <el-input clearable v-model="form.category" placeholder="请输入分类" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="描述" prop="title">
              <el-input clearable v-model="form.description" placeholder="请输入描述" />
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
  import {removeModel, listModel, deployModel, addModel, exportModel} from "@/api/activiti/model";

  export default {
    name: "Model",
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
          appname: undefined,
          title: undefined,
          key: undefined,
          value: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          env: [
            {required: true, message: "环境名不能为空", trigger: "blur"}
          ],
          appname: [
            {required: true, message: "应用名不能为空", trigger: "blur"}
          ],
          title: [
            {required: true, message: "描述不能为空", trigger: "blur"}
          ],
          key: [
            {required: true, message: "Key不能为空", trigger: "blur"}
          ],
          value: [
            {required: true, message: "Value不能为空", trigger: "blur"}
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
        listModel(this.queryParams).then(response => {
          this.loading = false;
          this.list = response.rows;
          this.total = response.total;
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
          appname: undefined,
          env: undefined,
          tile: undefined,
          key: undefined,
          value: undefined,
          icon: undefined,
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
        this.title = "添加配置项";
      },
      /** 提交按钮 */
      submitForm: function () {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.appname != undefined) {
              saveModel(this.form).then(response => {
                this.$modal.msgSuccess("保存成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleRemove(row) {
        this.$modal.confirm('是否确认删除 [' + row.env + '.' + row.key + '] 的数据项？').then(function () {
          return delModel(row.env, row.key);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 设计按钮操作 */
      handleRemove(row) {
      },
      /** 导出按钮操作 */
      handleRemove(row) {
      }
    }
  };
</script>
