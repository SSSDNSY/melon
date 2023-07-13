<template>
  <div style="margin-top: 16px">
    <el-form-item label="处理人规则">
      <el-select v-model="userTaskForm.taskRules" @change="updateElementTask('taskRules')">
        <el-option
          v-for="(trs, index) in taskRules"
          :key="index"
          :label="trs.taskRuleName"
          :value="trs.ruleKey"
        />
      </el-select>
      <div v-if="processTaskRule == 'byUser'">
        <el-table :data="assigneeUserList" size="mini" max-height="240" border fit>
          <el-table-column label="序号" width="50px" type="index" />
          <el-table-column label="帐号" prop="username" show-overflow-tooltip />
          <el-table-column label="姓名" prop="name" show-overflow-tooltip />
          <el-table-column label="操作" fixed="right" width="90px">
            <template slot-scope="{ row, $index }">
              <el-button
                size="mini"
                type="text"
                style="color: #ff4d4f"
                @click="removeUser(row, $index)"
                >移除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <div class="element-drawer__button">
          <el-button
            size="mini"
            type="primary"
            icon="el-icon-plus"
            @click="openUserbutesForm(null, -1, 'byUser')"
            >添加人员</el-button
          >
        </div>
      </div>
      <el-dialog
        :visible.sync="byUserFormModelVisible"
        :close-on-click-modal="false"
        title="选择人员"
        width="80%"
        append-to-body
        destroy-on-close
      >
        <el-form :model="byUserForm" label-width="80px" style="width: 99%" size="mini">
          <rule-to-user ref="assigneeByUserRef"></rule-to-user>
        </el-form>
        <template slot="footer">
          <el-button size="mini" @click="byUserFormModelVisible = false">取 消</el-button>
          <el-button size="mini" type="primary" @click="saveAssigneeByUser('byUser')"
            >确 定</el-button
          >
        </template>
      </el-dialog>
      <div v-if="processTaskRule == 'byDept'">
        <el-table :data="assigneeUserList" size="mini" max-height="240" border fit>
          <el-table-column label="序号" width="50px" type="index" />
          <el-table-column label="部门名称" prop="name" show-overflow-tooltip />
          <el-table-column label="操作" fixed="right" width="90px">
            <template slot-scope="{ row, $index }">
              <el-button
                size="mini"
                type="text"
                style="color: #ff4d4f"
                @click="removeUser(row, $index)"
                >移除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <div class="element-drawer__button">
          <el-button
            size="mini"
            type="primary"
            icon="el-icon-plus"
            @click="openUserbutesForm(null, -1, 'byDept')"
            >添加部门</el-button
          >
        </div>
      </div>
      <el-dialog
        :visible.sync="byDeptFormModelVisible"
        :close-on-click-modal="false"
        title="选择部门"
        width="80%"
        append-to-body
        destroy-on-close
      >
        <el-form :model="byDeptForm" label-width="80px" style="width: 99%" size="mini">
          <rule-to-dept ref="assigneeByDeptRef"></rule-to-dept>
        </el-form>
        <template slot="footer">
          <el-button size="mini" @click="byDeptFormModelVisible = false">取 消</el-button>
          <el-button size="mini" type="primary" @click="saveAssigneeByUser('byDept')"
            >确 定</el-button
          >
        </template>
      </el-dialog>
      <div v-if="processTaskRule == 'byRole'">
        <template>
          <el-select v-model="userTaskForm.assigneeRoles" multiple placeholder="请选择角色">
            <el-option
              v-for="item in assigneeRoles"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
          <div class="element-drawer__button">
            <el-button
              size="mini"
              type="primary"
              icon="el-icon-plus"
              @click="saveAssigneeByUser('byRole')"
              >确认</el-button
            >
          </div>
        </template>
      </div>
      <div v-if="processTaskRule == 'byPosition'">
        <template>
          <el-select v-model="userTaskForm.assigneePosition" multiple placeholder="请选择岗位">
            <el-option
              v-for="item in assigneePosition"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
          <div class="element-drawer__button">
            <el-button
              size="mini"
              type="primary"
              icon="el-icon-plus"
              @click="saveAssigneeByUser('byPosition')"
              >确认</el-button
            >
          </div>
        </template>
      </div>
    </el-form-item>
<!--    <el-form-item label="到期时间">-->
<!--      <el-input v-model="userTaskForm.dueDate" clearable @change="updateElementTask('dueDate')" />-->
<!--    </el-form-item>-->
<!--    <el-form-item label="优先级">-->
<!--      <el-input v-model="userTaskForm.priority" clearable @change="updateElementTask('priority')" />-->
<!--    </el-form-item>-->
    <el-form-item label="驳回规则">
      <el-select v-model="userTaskForm.returnWay" @change="updateElementTask('returnWay')">
        <el-option
          v-for="(trs, index) in returnWay"
          :key="index"
          :label="trs.returnWayName"
          :value="trs.returnWayKey"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="直接返回" v-if="processReturnWay == 'allStepsTaken'">
      <el-form-item>
        <el-radio-group v-model="userTaskForm.runWay">
          <el-radio label="none">否</el-radio>
          <el-radio label="directReturn">是</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <span style="color: darkorange"
          >如果选择否，流程中存在并发分支的情况下，分支退回时，其他分支任务将会被删除</span
        >
      </el-form-item>
    </el-form-item>
    <el-form-item label="移交">
      <el-form-item>
        <el-radio-group v-model="userTaskForm.transfer" @change="updateElementTask('transfer')">
          <el-radio label="0">不启用</el-radio>
          <el-radio label="1">启用</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form-item>
    <el-form-item label="加签">
      <el-form-item>
        <el-radio-group
          v-model="userTaskForm.countersign"
          @change="updateElementTask('countersign')"
        >
          <el-radio label="0">不启用</el-radio>
          <el-radio label="1">启用</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form-item>
    <el-form-item label="跳转规则">
      <el-select v-model="userTaskForm.jumpWay" @change="updateElementTask('jumpWay')">
        <el-option
          v-for="(trs, index) in jumpWay"
          :key="index"
          :label="trs.jumpWayName"
          :value="trs.jumpWayKey"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="手动结束">
      <el-form-item>
        <el-radio-group v-model="userTaskForm.endTask" @change="updateElementTask('endTask')">
          <el-radio label="0">不启用</el-radio>
          <el-radio label="1">启用</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form-item>
    <el-form-item label="拒绝">
      <el-form-item>
        <el-radio-group v-model="userTaskForm.refuse" @change="updateElementTask('refuse')">
          <el-radio label="0">不启用</el-radio>
          <el-radio label="1">启用</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form-item>
    <el-form-item label="抄送规则">
      <el-select v-model="userTaskForm.ccWay" @change="updateElementTask('ccWay')">
        <el-option
          v-for="(trs, index) in ccWay"
          :key="index"
          :label="trs.ccName"
          :value="trs.ccKey"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="抄送设置" v-if="processCCWay == 'autoCC'">
      <div class="element-drawer__button">
        <el-button
          size="mini"
          type="primary"
          icon="el-icon-plus"
          @click="openAutoCCWayForm(null, -1)"
          >自动抄送设置</el-button
        >
      </div>
    </el-form-item>
    <!-- 自动抄送 编辑/创建 部分 -->
    <el-drawer
      :visible.sync="autoCCWayVisible"
      title="自动抄送设置"
      :size="`${width}px`"
      append-to-body
      destroy-on-close
    >
      <el-form size="mini" label-width="96px" @submit.native.prevent>
        <el-form-item label="抄送规则" prop="event">
          <el-select v-model="userTaskForm.autoCCWay">
            <el-option
              v-for="(trs, index) in autoCCWay"
              :key="index"
              :label="trs.autoCCName"
              :value="trs.autoCCKey"
            />
          </el-select>
        </el-form-item>
      </el-form>

      <div class="element-drawer__button">
        <el-button size="mini" @click="autoCCWayVisible = false">取 消</el-button>
        <el-button size="mini" type="primary">保 存</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex';
  import ruleToUser from '@/components/taskcomponents/ruleToUser.vue';
  import ruleToDept from '@/components/taskcomponents/ruleToDept.vue';
  import { getOrgData } from '@/api/orgm/orgm';
  import {
    getFlowElementAttrs,
    setAssigneeUserWithTaskId,
    getAssigneeUserWithTaskId,
    deleteAssigneeUser,
    setFlowAttrs
  } from '@/api/process/process';
  export default {
    name: 'UserTask',
    props: {
      id: String,
      type: String
    },
    inject: {
      prefix: 'prefix',
      width: 'width'
    },
    data() {
      return {
        defaultTaskForm: {
          taskRules: '',
          returnWay: 'none',
          runWay: 'none',
          jumpWay: 'none',
          ccWay: 'none',
          autoCCWay: 'none',
          transfer: '0',
          countersign: '0',
          endTask: '0',
          refuse: '',
          assignee: '',
          assigneeRoles: '',
          assigneePosition: '',
          candidateUsers: [],
          candidateGroups: [],
          dueDate: '',
          priority: ''
        },
        userTaskForm: {
          taskRules: '',
          returnWay: '',
          runWay: '',
          jumpWay: '',
          ccWay: '',
          autoCCWay: '',
          transfer: '',
          countersign: '',
          endTask: '',
          refuse: '',
          assignee: '',
          assigneeRoles: '',
          assigneePosition: '',
          candidateUsers: [],
          candidateGroups: [],
          dueDate: '',
          priority: ''
        },
        assigneeUserList: [],
        taskRules: [],
        returnWay: [],
        jumpWay: [],
        ccWay: [],
        autoCCWay: [],
        processTaskRule: '',
        processReturnWay: '',
        processCCWay: '',
        userList: [],
        assigneeRoles: [],
        assigneePosition: [],
        byUserForm: {},
        byDeptForm: {},
        byUserFormModelVisible: false,
        byDeptFormModelVisible: false,
        editingCCWayIndex: -1,
        autoCCWayVisible: false,
        mockData: [1, 2, 3, 4, 5, 6, 7]
      };
    },
    watch: {
      id: {
        immediate: true,
        handler() {
          this.bpmnElement = window.bpmnInstances.bpmnElement;
          this.$nextTick(() => this.resetTaskForm());
        }
      }
    },
    components: {
      ruleToUser,
      ruleToDept
    },
    mounted() {
      this.taskLoad();
    },
    computed: {
      ...mapGetters('store/taskRule', [
        'getTaskRules',
        'getStartRules',
        'getReturnWay',
        'getJumpWay',
        'getCCWay',
        'getAutoCCWay'
      ])
    },
    methods: {
      taskLoad() {
        let taskType = '';
        if (this.bpmnElement.businessObject.$type == 'bpmn:StartEvent') {
          taskType = 'bpmn:StartEvent';
        } else {
          const taskInComing = this.bpmnElement.businessObject.incoming;
          const taskSourceRef = taskInComing[0].sourceRef;
          taskType = taskSourceRef.$type;
        }
        if (taskType == 'bpmn:StartEvent') {
          console.log('getStartRules :>> ', this.getStartRules);
          this.taskRules = this.getStartRules;
        } else {
          console.log('getTaskRules :>> ', this.getTaskRules);
          this.taskRules = this.getTaskRules;
        }
        this.returnWay = this.getReturnWay;
        this.jumpWay = this.getJumpWay;
        this.ccWay = this.getCCWay;
        this.autoCCWay = this.getAutoCCWay;
        getFlowElementAttrs({ usertaskid: this.id }).then((res) => {
          if (res.error == '200') {
            this.userTaskForm.returnWay = res.result.returnWay;
            this.userTaskForm.runWay = res.result.runWay;
            this.userTaskForm.jumpWay = res.result.jumpWay;
            this.userTaskForm.ccWay = res.result.ccWay;
            this.userTaskForm.autoCCWay = res.result.autoCCWay;
            this.userTaskForm.transfer = res.result.transfer;
            this.userTaskForm.countersign = res.result.countersign;
            this.userTaskForm.endTask = res.result.endTask;
            this.userTaskForm.refuse = res.result.refuse;
          }
        });
        getOrgData().then((res) => {
          if (res.error == '200') {
            this.assigneeRoles = res.result.rolesList;
            this.roleList = this.assigneeRoles;
            this.assigneePosition = res.result.positionList;
          }
        });
      },
      resetTaskForm() {
        this.taskLoad();
        for (let key in this.defaultTaskForm) {
          let value;
          if (key === 'candidateUsers') {
            value = this.bpmnElement?.businessObject[key]
              ? this.bpmnElement.businessObject[key].split(',')
              : [];
            //如果是usertask，将他的规则类型取出来
            if (value == undefined || value == '' || value == null) {
              value = [];
              this.processTaskRule = '';
            } else {
              let ruleRep = value[0].replace(this.id + '_', '');
              ruleRep = ruleRep.replace('$', '');
              ruleRep = ruleRep.replace('{', '');
              value = ruleRep.replace('}', '');
              this.processTaskRule = value;
              this.userTaskForm.taskRules = value;
              this.loadAssigneeUserList(value);
            }
          } else if (key === 'candidateGroups') {
            value = this.bpmnElement?.businessObject[key]
              ? this.bpmnElement.businessObject[key].split(',')
              : [];
          } else {
            value = this.bpmnElement?.businessObject[key] || this.defaultTaskForm[key];
          }
          this.$set(this.userTaskForm, key, value);
        }
      }, //规则选择
      updateElementTask(key) {
        const taskAttr = Object.create(null);
        if (key == 'taskRules') {
          taskAttr['candidateUsers'] = '${' + this.id + '_' + this.userTaskForm[key] + '}';
          this.processTaskRule = this.userTaskForm[key];
          this.loadAssigneeUserList(this.processTaskRule);
        } else if (key == 'returnWay') {
          this.processReturnWay = this.userTaskForm[key];
          this.setFlowElementAttrs(this.id + '_' + key, this.userTaskForm[key]);
          return;
        } else if (key == 'jumpWay') {
          this.processReturnWay = this.userTaskForm[key];
          this.setFlowElementAttrs(this.id + '_' + key, this.userTaskForm[key]);
          return;
        } else if (key == 'ccWay') {
          this.processCCWay = this.userTaskForm[key];
          this.setFlowElementAttrs(this.id + '_' + key, this.userTaskForm[key]);
          return;
        } else if (key == 'transfer') {
          this.setFlowElementAttrs(this.id + '_' + key, this.userTaskForm[key]);
          return;
        } else if (key == 'countersign') {
          this.setFlowElementAttrs(this.id + '_' + key, this.userTaskForm[key]);
          return;
        } else if (key == 'endTask') {
          this.setFlowElementAttrs(this.id + '_' + key, this.userTaskForm[key]);
          return;
        } else if (key == 'refuse') {
          this.setFlowElementAttrs(this.id + '_' + key, this.userTaskForm[key]);
          return;
        }

        window.bpmnInstances.modeling.updateProperties(this.bpmnElement, taskAttr);
      }, //根据指定用户规则，设置任务处理人
      saveAssigneeByUser(key) {
        let selectData = null;
        if (key == 'byUser') {
          selectData = this.$refs.assigneeByUserRef.saveTaskUser();
        } else if (key == 'byDept') {
          selectData = this.$refs.assigneeByDeptRef.saveTaskUser();
        } else if (key == 'byRole') {
          selectData = [];
          let byRoleObj = {
            id: '',
            name: ''
          };
          this.userTaskForm.assigneeRoles.forEach((element) => {
            byRoleObj = {
              id: element,
              name: ''
            };
            selectData.push(byRoleObj);
          });
        } else if (key == 'byPosition') {
          selectData = [];
          let byPositionObj = {
            id: '',
            name: ''
          };
          this.userTaskForm.assigneePosition.forEach((element) => {
            byPositionObj = {
              id: element,
              name: ''
            };
            selectData.push(byPositionObj);
          });
        }
        setAssigneeUserWithTaskId({
          userlist: selectData,
          usertaskid: this.id+"_"+key,
          ruleName: key,
          id:this.id
        }).then((res) => {
          if (res.error == '200') {
            this.$message.success('设置成功');
            this.byUserFormModelVisible = false;
            this.byDeptFormModelVisible = false;
            this.loadAssigneeUserList(key);
          }
        });
      }, //根据指定用户规则，新增任务处理人
      openUserbutesForm(attr, index, type) {
        this.editingPropertyIndex = index;
        this.propertyForm = index === -1 ? {} : JSON.parse(JSON.stringify(attr));
        if (type == 'byUser') {
          this.byUserFormModelVisible = true;
        } else if (type == 'byDept') {
          this.byDeptFormModelVisible = true;
        }
      }, //根据指定用户规则，显示配置的任务处理人
      loadAssigneeUserList(key) {
        getAssigneeUserWithTaskId({ usertaskid: this.id, ruleName: key }).then((res) => {
          if (res.error == '200') {
            if (key == 'byRole') {
              var roleIntSet = [];
              for (let roleKey in res.result) {
                if(res.result[roleKey].username==""||
                  res.result[roleKey].username==undefined){
                  roleIntSet.push(res.result[roleKey].id);
                }
                else{
                  roleIntSet.push(res.result[roleKey].username);
                }
              }
              this.userTaskForm.assigneeRoles = roleIntSet;
            } else if (key == 'byPosition') {
              var postIntSet = [];
              for (let ptKey in res.result) {
                if(res.result[ptKey].username==""||
                  res.result[ptKey].username==undefined){
                  postIntSet.push(res.result[ptKey].id);
                }
                else{
                  postIntSet.push(res.result[ptKey].username);
                }
              }
              this.userTaskForm.assigneePosition = postIntSet;
            } else {
              this.assigneeUserList = res.result;
            }
          }
        });
      }, //移除节点处理人
      removeUser(row, index) {
        deleteAssigneeUser({ usertaskid: this.id, username: row.username }).then((res) => {
          if (res.error == '200') {
            this.$message.success('移除成功');
            this.assigneeUserList.splice(index, 1);
          }
        });
      },
      //定义参数
      setFlowElementAttrs(redisKey, attrKey) {
        setFlowAttrs({ redisKey: redisKey, attrKey: attrKey }).then((res) => {});
      },
      openAutoCCWayForm(listener, index) {
        if (listener) {
          this.editingCCWayIndex = index;
        } else {
          this.editingCCWayIndex = -1; // 标记为新增
        }
        // 打开侧边栏并清楚验证状态
        this.autoCCWayVisible = true;
      }
    },
    beforeDestroy() {
      this.bpmnElement = null;
    }
  };
</script>
