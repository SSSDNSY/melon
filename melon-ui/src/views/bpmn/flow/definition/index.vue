<template>
  <container type="ghost">

    <div class="theme-tab-container">
      <el-tabs v-model="activeName">
        <el-tab-pane v-for="item in tabList" :key="item.id" :name="item.id + ''" :label="item.name">
          <div v-if="item.children && item.children.length" class="card-box">
            <div
              v-for="(x, i) in item.children"
              :key="x.id"
              :style="{ background: colorList[i % colorList.length] }"
              class="card-content"
              :class="{ 'is-active': 1 == x.id }"
              @click="onChangeType(x.id)"
            >{{ x.name }}
            </div
            >
          </div>
          <div v-else class="card-box-empty" flex="dir:top main:center cross:center">
            <i class="el-icon-search"></i>
            <span>没有流程类型</span>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>

    <div class="theme-main-container">
      <!-- 搜索 -->
      <div>
        <el-form ref="form" :model="searchForm" label-width="80px" align="right">
          <el-row>
            <el-col :span="6">
              <el-form-item label="流程名称" prop="name">
                <el-input v-model="searchForm.name" placeholder="请输入流程名称"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="18" class="btn-container">
              <el-button size="mini" type="primary" icon="el-icon-plus" @click="openNewDialog">新建流程</el-button>
              <el-button size="mini" icon="el-icon-search" type="primary" @click="search">查询</el-button>
              <el-button size="mini" icon="el-icon-refresh" @click="search('reset')">重置</el-button>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <!-- table -->
      <el-table row-key="id" stripe v-loading="listLoading" :data="list" style="width: 100%">
        <el-table-column v-for="item in tableColumns" :key="item.prop" v-bind="item">
          <template slot-scope="scope">
            <div v-if="item.prop === 'index'">{{ scope.$index + 1 }}</div>
            <div v-else-if="item.prop === 'operation'" class="operation-btn">
              <el-button size="mini" icon="el-icon-setting" type="text"
                         @click="editFlowDialog(scope.row.deploymentId, scope.row.name, scope.row.key)">设计流程
              </el-button>
              <el-button size="mini" icon="el-icon-edit"
                         type="text" @click="editSort(
                    scope.row.id,
                    scope.row.name,
                    scope.row.engineVersion,
                    scope.row.key)">业务配置
              </el-button>
              <el-popconfirm title="您确定要删除吗？" @confirm="deleteById(scope.row)">
                <el-button size="mini" icon="el-icon-delete" slot="reference" type="text" class="btn-danger">删除
                </el-button>
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
        :page.sync="searchForm.pageNumber"
        :limit.sync="searchForm.pageSize"
        @pagination="getList"/>

      <el-drawer
        class="flow-design"
        title="流程设计图"
        v-if="dialogVisible == true"
        :visible.sync="dialogVisible"
        size="100%"
        :append-to-body="false"
        direction="btt"
        destroy-on-close
        @close="cloaseDialog">
        <vue-Bpmn
          :processId="processId"
          :deployMName="deployMName"
          :deployMId="deployMId"
          product="activiti"/>
      </el-drawer>

      <el-drawer
        :title="title"
        v-if="dialogNewSortVisible == true"
        :close-on-click-modal="false"
        :visible.sync="dialogNewSortVisible"
        @close="cloaseSortDialog"
        :close-on-press-escape="false"
        destroy-on-close
        size="60%"
      >
        <div class="drawer-content">
          <el-form
            label-suffix="："
            label-width="90px"
            :model="ruleForm"
            ref="ruleForm"
            style="width: 95%"
          >
            <el-form-item label="流程名称" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="所属类别" prop="flowSort">
              <select-tree
                class="tree"
                v-model="value"
                :data="sortData"
                placeholder="请选择所属类别"
              ></select-tree>
            </el-form-item>
            <el-form-item label="表单类型" prop="form_type">
              <el-select v-model="ruleForm.form_type" @change="updateElementTask('form_type')">
                <el-option label="内置表单" value="0"/>
                <el-option label="外部表单" value="1"/>
              </el-select>
            </el-form-item>
            <el-form-item v-if="taskFormtype == '0'" prop="formMap" label="流程表单">
              <el-select v-model="ruleForm.formMap" filterable @change="updateAttrs">
                <el-option v-for="i in formMapList" :key="i.id" :value="i.id" :label="i.name"/>
              </el-select>
            </el-form-item>
            <el-form-item v-if="taskFormtype == '1'" prop="form_url" label="表单地址">
              <el-input
                v-model="ruleForm.form_url"
                @change="updateElementTask('form_url')"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <div v-if="taskFormtype == '0'">
                <div class="element-drawer__button">
                  <el-button size="mini" type="primary" icon="el-icon-view" @click="openView()"
                  >查看表单
                  </el-button
                  >
                </div>
              </div>
            </el-form-item>
          </el-form>
        </div>
        <div class="drawer-footer">
          <el-button size="mini" type="primary" @click="saveEdit">保存</el-button>
          <el-button size="mini" @click="cloaseSortDialog">取消</el-button>
        </div>
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
        <preview :itemList="itemList" :dataJson="[]" :formConf="formConf"/>
      </el-drawer>
    </div>
  </container>
</template>

<script>
  import VueBpmn from './processDesign';
  import preview from '@/components/FormComponents/preview';
  import {flowSortTree} from '@/api/bpmn/flowSort';
  import {get, list, remove, save} from '@/api/bpmn/flowDefinition';
  import {formList, getForm} from '@/api/bpmn/form';
  import Pagination from '@/components/Pagination';
  import {colorList} from '@/const';
  import {flowDefinitionColumns} from './const';
  import SelectTree from '@/components/SelectTree/index.vue';

  export default {
    name: 'flowDefinition',
    components: {Pagination, VueBpmn, preview, SelectTree},
    data() {
      return {
        dialogVisible: false,
        dialogNewSortVisible: false,
        formMapList: [],
        viewVisible: false,
        deployMId: '',
        deployMName: '',
        processId: '',
        taskFormtype: '',
        mapJson: '',
        itemList: [],
        formConf: Object,
        value: null,
        sortData: [],
        title: '',
        colorList,
        tabList: [],
        activeName: '',
        tableColumns: flowDefinitionColumns,
        list: [],
        total: 0,
        listLoading: false,
        searchForm: {
          pageNumber: 1,
          pageSize: 10,
          name: ''
        },
        ruleForm: {
          id: '',
          name: '',
          formType: '',
          formMap: '',
          form_url: '',
          sortId: this.value,
          processKey: ''
        }
      };
    },
    created() {
      this.getList();
      this.getSelectData();
      this.flowSortTree();
      this.loadFormList();
    },
    methods: {
      // 获取
      getList() {
        this.listLoading = true;
        list(this.searchForm).then((res) => {
          if (res.code === 200) {
            this.list = res.rows;
            this.total = res.total;
          } else {
            this.$message.error(`获取列表失败`);
          }
          this.listLoading = false;
        });
      },
      loadFormList() {
        const params = {
          name: '',
          form_type: '',
          page: 1,
          pagesize: 0
        };
        formList(params).then((res) => {
          if (res.code == '200') {
            this.formMapList = res.rows;
          } else {
            this.$message.error('获取失败');
          }
          this.loading = false;
        });
      },
      flowSortTree() {
        flowSortTree().then((res) => {
          if (res.code == '200') {
            this.sortData = res.result;
          }
        });
      },
      // 新增流程
      openNewDialog() {
        this.dialogVisible = true;
        this.deployMId = '';
        this.deployMName = '';
        this.processId = '';
      },
      editFlowDialog(deploymentId, deploymentName, processId) {
        this.dialogVisible = true;
        this.deployMId = deploymentId;
        this.deployMName = deploymentName;
        this.processId = processId;
      },
      updateElementTask(key) {
        if (key == "form_type") {
          this.taskFormtype = this.ruleForm[key];
          return;
        }
        if (key == "form_url") {
          this.taskFormtype = this.ruleForm[key];
          return;
        }
      },
      cloaseDialog() {
        this.deploy();
      },
      deploy() {
        this.getList();
        this.getSelectData();
      },
      // dialog关闭事件
      cloaseSortDialog() {
        this.dialogNewSortVisible = false;
        this.deploy();
      },
      saveEdit() {
        this.ruleForm.sortId = this.value;
        save(this.ruleForm).then((res) => {
          if (res.code == '200') {
            this.$message.success('修改成功');
            this.dialogNewSortVisible = false;
            setTimeout(() => {
              this.deploy();
            }, 1000);
          } else {
            this.$message.error('获取失败');
          }
        });
      },
      editSort(deploymentId, name, sortId, processkey) {
        this.title = '业务配置';
        this.ruleForm.name = name;
        this.ruleForm.id = deploymentId;
        this.ruleForm.processKey = processkey;
        if (sortId == 0 || sortId == null) {
          this.ruleForm.sortId = null;
          this.value = null;
        } else {
          let newSortId = sortId - 0;
          this.ruleForm.sortId = newSortId;
          this.value = newSortId;
        }
        get({processKey: processkey}).then((res) => {
          if (res.code != '200') {
            this.mapJson = '';
            this.ruleForm.form_type = '';
            this.ruleForm.formMap = '';
            this.ruleForm.form_url = '';
            this.taskFormtype = '';
            this.dialogNewSortVisible = true;
            return;
          } else {
            if (res.result == '' || res.result == undefined) {
              this.mapJson = '';
              this.ruleForm.form_type = '';
              this.ruleForm.formMap = '';
              this.ruleForm.form_url = '';
              this.taskFormtype = '';
              this.dialogNewSortVisible = true;
              return;
            }
            if (res.result.mapJson == '' || res.result.mapJson == undefined) {
              this.mapJson = '';
            } else {
              this.mapJson = JSON.parse(res.result.mapJson);
            }
            this.ruleForm.form_type = res.result.form_type;
            this.ruleForm.formMap = res.result.formMap;
            this.ruleForm.form_url = res.result.form_url;
            this.taskFormtype = res.result.form_type;
            this.dialogNewSortVisible = true;
          }
        });
      },
      updateAttrs() {
        let selectOption = this.formMapList.filter((item) => {
          if (item.id == this.ruleForm.formMap) {
            return item;
          }
        });
        if (selectOption[0].form_json == undefined) {
          this.mapJson = undefined;
          return;
        }
        this.mapJson = JSON.parse(selectOption[0].form_json);
      },
      openView() {
        getForm({id: this.ruleForm.formMap}).then((res) => {
          if (res.code == '200') {
            this.mapJson = JSON.parse(res.result.form_json);
            if (this.mapJson == undefined) {
              this.$message.error('表单内容为空');
              return;
            } else if (this.mapJson == []) {
              this.$message.error('请选择表单');
              return;
            } else {
              this.formConf = this.mapJson.config;
              this.itemList = this.mapJson.list;
            }
            this.viewVisible = true;
          } else {
            this.$message.error('获取失败');
          }
        });
      },
      search(type) {
        if (type === 'reset') {
          this.$refs.form.resetFields();
        }
        this.searchForm.page = 1;
        this.getList();
      },
      onChangeType(type) {
        this.search();
      },
      async getSelectData() {
        const data = (await flowSortTree()).data;
        if (data.length) {
          this.tabList = data;
          this.activeName = this.tabList[0].id + '';
        }
      },
      async deleteById(row) {
        const res = await remove({depID: row.deploymentID});
        if (res.code === 200) {
          this.$message.success(`删除成功`);
          this.search('reset');
        } else {
          this.$message.error(`删除失败`);
        }
      }
    }
  };
</script>

<style lang="scss" scoped>
  .theme-tab-container {
    padding: 10px 20px 5px;
    margin-bottom: 0px;

    .card-title {
      display: flex;
      justify-content: space-between;
      margin-bottom: 10px;
      align-items: center;

      .name {
        font-size: 16px;
        // font-family: SourceHanSansCN-Medium, SourceHanSansCN;
        font-weight: 500;
        // color: #4d4d4d;
      }

      .el-input {
        width: 186px;
      }
    }

    .card-box {
      overflow-x: auto;
      white-space: nowrap;

      .card-content {
        display: inline-block;
        width: 226px;
        height: 100px;
        margin-right: 10px;
        margin-bottom: 8px;
        border-radius: 4px;
        color: #fff;
        font-size: 16px;
        line-height: 100px;
        text-align: center;
        cursor: pointer;
        box-sizing: border-box;

        &:last-of-type {
          margin-right: 0;
        }

        &:hover,
        &.is-active {
          box-shadow: 0px 2px 7px 0px rgba(0, 0, 0, 0.32),
          0px 1px 4px 0px rgba(183, 183, 183, 0.06);
        }
      }

      img {
        cursor: pointer;
        height: 100%;
      }
    }
  }

  .theme-main-container {
    padding: 20px;
    padding-top: 0;
  }

  .card-box-empty {
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    justify-content: center;
    display: flex;
    flex-direction: column;
    height: 100px;
    background: rgba(0, 0, 0, .03);
    border-radius: 4px;
    margin-bottom: 8px;

    i {
      font-size: 150%;
      margin-bottom: 10px;
    }

    span {
      font-size: 14px;
    }
  }

  .el-select-dropdown {
    .el-select-dropdown__item {
      padding: 0 20px;
    }
  }

  // 流程设计器
  .flow-design {
    .el-dialog {
      margin: 0;
    }

    .el-dialog__body {
      padding: 0;
    }

    .my-process-designer__container {
      background-color: #a9a9a9;
      color: #606266;
      border-radius: 4px;
      overflow: hidden;
    }

    .el-drawer__body {
      overflow: hidden;
    }
  }


</style>
