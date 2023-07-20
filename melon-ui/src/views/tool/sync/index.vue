<template>
  <el-tabs class="app-container" v-model="activeName" type="card" @tab-click="handleTabClick">
    <el-tab-pane label="配置管理" name="dbConfManager">

      <div class="app-container">
        <h2>驱动信息</h2>
        <el-row :gutter="20">
          <el-col :span="6" v-for="driver in drivers" :key="driver.driverName">
            <el-card type="card" shadow="hover">
              <div slot="header" class="clearfix">
                <span class="text-large">{{ driver.driverName }}</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="gotoDriverTab">更多</el-button>
              </div>
              <div class="text item text-medium">
                {{ '主版本：' + driver.majorVersion }}
              </div>
              <div class="text item text-medium">
                {{ '次版本：' + driver.minorVersion }}
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>

      <div class="app-container">
        <h2>数据源管理</h2>
        <el-row :gutter="20">
          <el-col :span="6" v-for="druid in druids" :key="druid.Name">
            <el-card type="card" shadow="hover">
              <div slot="header" class="clearfix">
                <span class="text-extra-large">{{ druid.DbType }}</span>
                <el-button-group style="float: right;">
                  <el-button type="text" @click="gotoDruidTab">详细</el-button>
                  <el-button type="text" @click="gotoDruidTab">测试</el-button>
                  <el-button type="text" class="text-red" @click="gotoDruidTab">删除</el-button>
                  <el-button type="text" @click="gotoDruidTab">新增</el-button>
                </el-button-group>
              </div>
              <div class="text item text-base">
                {{ '驱动实例名：' + druid.Name }}
              </div>
              <div class="text item text-medium">
                {{ '用户名：' + druid.UserName }}
              </div>
              <div class="text item text-medium" style="width: 100%;word-wrap: break-word;">
                连接地址：<span class="text-extra-small">{{ druid.URL }}</span>
              </div>
              <div class="text item text-medium">
                {{ '驱动类：' + druid.DriverClassName }}
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>

    </el-tab-pane>

    <el-tab-pane label="同步任务" name="syncTask">
      <div class="app-container">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="参数名称" prop="configName">
            <el-input v-model="form.configName" placeholder="请输入参数名称"/>
          </el-form-item>
          <el-form-item label="参数键名" prop="configKey">
            <el-input v-model="form.configKey" placeholder="请输入参数键名"/>
          </el-form-item>
          <el-form-item label="参数键值" prop="configValue">
            <el-input v-model="form.configValue" placeholder="请输入参数键值"/>
          </el-form-item>
          <el-form-item label="系统内置" prop="configType">
            <el-radio-group v-model="form.configType">
              <el-radio
                v-for="dict in dict.type.sys_yes_no"
                :key="dict.value"
                :label="dict.value"
              >{{ dict.label }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm"> 同 步</el-button>
        </div>
      </div>
    </el-tab-pane>

  </el-tabs>
</template>

<script>
import {addConfig, listConfig, updateConfig} from "@/api/system/config";
import {druidDatasource, getAllDriver} from "@/api/common";

export default {
  name: "Config",
  dicts: ['sys_yes_no'],
  data() {
    return {
      activeName: 'dbConfManager',
      // 遮罩层
      loading: true,
      druids: [],
      drivers: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        configName: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        configName: [
          {required: true, message: "参数名称不能为空", trigger: "blur"}
        ],
        configKey: [
          {required: true, message: "参数键名不能为空", trigger: "blur"}
        ],
        configValue: [
          {required: true, message: "参数键值不能为空", trigger: "blur"}
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDatasource();
    this.getAllDrivers();
  },
  methods: {
    handleTabClick() {

    },
    gotoDriverTab() {
      this.$router.push("/monitor/druid");
    },
    gotoDruidTab() {
      this.$router.push("/monitor/druid");
    },
    /** 查询参数列表 */
    getList() {
      this.loading = true;
      listConfig(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.configList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    getAllDrivers() {
      getAllDriver().then(response => {
        this.drivers = response.data;
      })
    },
    getDatasource() {
      druidDatasource().then(response => {
        this.druids = response.Content;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        configId: undefined,
        configName: undefined,
        configKey: undefined,
        configValue: undefined,
        configType: "Y",
        remark: undefined
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
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加参数";
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.configId != undefined) {
            updateConfig(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConfig(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    }
  }
};
</script>
