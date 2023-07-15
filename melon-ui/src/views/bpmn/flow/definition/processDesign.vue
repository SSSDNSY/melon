<template>
  <div id="procDes">
    <MyProcessDesigner
      :key="`designer-${reloadIndex}`"
      :options="{
        taskResizingEnabled: true,
        eventResizingEnabled: true
      }"
      v-model="xmlString"
      v-bind="controlForm"
      keyboard
      ref="processDesigner"
      @element-click="elementClick"
      @element-contextmenu="elementContextmenu"
      @init-finished="initModeler"
    />

    <MyPropertiesPanel
      :key="`penal-${reloadIndex}`"
      :bpmn-modeler="modeler"
      :prefix="controlForm.prefix"
      class="process-panel"
    />
  </div>
</template>

<script>
  import {translations} from './const';
  // 自定义渲染（隐藏了 label 标签）
  import CustomRenderer from '@/libs/modules/custom-renderer';
  // 自定义元素选中时的弹出菜单（修改 默认任务 为 用户任务）
  import CustomContentPadProvider from '@/components/FlowDesigner/designer/plugins/content-pad';
  // 自定义左侧菜单（修改 默认任务 为 用户任务）
  import CustomPaletteProvider from '@/components/FlowDesigner/designer/plugins/palette';
  import Log from '@/components/FlowDesigner/Log';
  import UserSql from '@/libs/modules/extension/user.json';
  // clickoutside
  import clickOutside from 'element-ui/lib/utils/clickoutside';

  // 任务resize
  //import resizeTask from 'bpmn-js-task-resize/lib';
  // bpmn theme plugin
  //import sketchyRendererModule from 'bpmn-js-sketchy';


  export default {
    name: 'processDesign',
    directives: {
      clickoutside: clickOutside
    },
    data() {
      return {
        xmlString: '',
        modeler: null,
        reloadIndex: 0,
        pageMode: false,
        translationsSelf: translations,
        controlForm: {
          processId: this.processId,
          deployMName: this.deployMName,
          deployMId: this.deployMId,
          simulation: true,
          labelEditing: false,
          labelVisible: false,
          prefix: 'activiti',
          headerButtonSize: 'mini',
          events: ['element.click', 'element.contextmenu'],
          // additionalModel: []
          moddleExtension: {user: UserSql},
          additionalModel: [CustomContentPadProvider, CustomPaletteProvider, CustomRenderer]
        },
        addis: {
          CustomContentPadProvider,
          CustomPaletteProvider
        }
      };
    },
    props: {
      processId: String,
      deployMId: String,
      deployMName: String
    },
    created() {
    },
    methods: {
      initModeler(modeler) {
        setTimeout(() => {
          this.modeler = modeler;
          const canvas = modeler.get('canvas');

          const rootElement = canvas.getRootElement();
          Log.prettyPrimary('Process Id:', rootElement.id);
          Log.prettyPrimary('Process Name:', rootElement.businessObject.name);
        }, 10);
      },
      reloadProcessDesigner(notDeep) {
        this.controlForm.additionalModel = [];
        for (let key in this.addis) {
          if (this.addis[key]) {
            this.controlForm.additionalModel.push(this.addis[key]);
          }
        }
        !notDeep && (this.xmlString = undefined);
        this.reloadIndex += 1;
        this.modeler = null; // 避免 panel 异常
      },
      changeLabelEditingStatus(status) {
        this.addis.labelEditing = status ? {labelEditingProvider: ['value', '']} : false;
        this.reloadProcessDesigner();
      },
      changeLabelVisibleStatus(status) {
        this.addis.customRenderer = status ? CustomRenderer : false;
        this.reloadProcessDesigner();
      },
      elementClick(element) {
        console.log(element);
        this.element = element;
      },
      elementContextmenu(element) {
        console.log('elementContextmenu:', element);
      },
      changePageMode(mode) {
        const theme = mode
          ? {
            // dark
            stroke: '#ffffff',
            fill: '#333333'
          }
          : {
            // light
            stroke: '#000000',
            fill: '#ffffff'
          };
        const elements = this.modeler.get('elementRegistry').getAll();
        this.modeler.get('modeling').setColor(elements, theme);
      },
      toggle() {
        console.log(this.modeler);
        console.log(this.modeler.get('toggleMode'));
        this.modeler.get('toggleMode').toggleMode();
      }
    }
  };
</script>

<style lang="scss">
  body {
    overflow: hidden;
    margin: 0;
    box-sizing: border-box;
  }

  #procDes {
    max-height: 100vh;
    width: 100%;
    box-sizing: border-box;
    display: inline-grid;
    grid-template-columns: auto max-content;
    height: calc(100vh - 76px);
  }

  .demo-info-bar {
    position: fixed;
    right: 8px;
    bottom: 108px;
    z-index: 1;
  }

  .demo-control-bar {
    position: fixed;
    right: 8px;
    bottom: 48px;
    z-index: 1;
  }

  .open-model-button {
    width: 48px;
    height: 48px;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 4px;
    font-size: 32px;
    background: rgba(64, 158, 255, 1);
    color: #ffffff;
    cursor: pointer;
  }

  .zoom-in-right-enter-active,
  .zoom-in-right-leave-active {
    opacity: 1;
    transform: scaleY(1) translateY(-48px);
    transition: all 300ms cubic-bezier(0.23, 1, 0.32, 1);
    transform-origin: right center;
  }

  .zoom-in-right-enter,
  .zoom-in-right-leave-active {
    opacity: 0;
    transform: scaleX(0) translateY(-48px);
  }

  .info-tip {
    position: absolute;
    width: 480px;
    top: 0;
    right: 64px;
    z-index: 10;
    box-sizing: border-box;
    padding: 0 16px;
    color: #333333;
    background: #f2f6fc;
    transform: translateY(-48px);
    border: 1px solid #ebeef5;
    border-radius: 4px;

    &::before,
    &::after {
      content: '';
      width: 0;
      height: 0;
      border-width: 8px;
      border-style: solid;
      position: absolute;
      right: -15px;
      top: 50%;
    }

    &::before {
      border-color: transparent transparent transparent #f2f6fc;
      z-index: 10;
    }

    &::after {
      right: -16px;
      border-color: transparent transparent transparent #ebeef5;
      z-index: 1;
    }
  }

  .control-form {
    .el-radio {
      width: 100%;
      line-height: 32px;
    }
  }

  .element-overlays {
    box-sizing: border-box;
    padding: 8px;
    background: rgba(0, 0, 0, 0.6);
    border-radius: 4px;
    color: #fafafa;
  }

  .el-drawer {
    box-sizing: border-box;
    padding: 0 15px;

    .el-drawer__header {
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      line-height: 24px;
      border-bottom: 1px solid rgba(0, 0, 0, 0.09);
      padding: 20px 20px 15px;
      margin-bottom: 0;
    }
    header.el-drawer__header{
      color:#000;
    }

    .el-drawer__body {
      box-sizing: border-box;
      overflow: hidden;
      overflow-y: auto;
      padding-top: 20px;
      padding-bottom: 20px;

      .drawer-content {
        max-height: calc(100% - 65px);
        overflow-y: auto;

        .el-form {
          width: 70%;
          margin: 0 auto;
        }
      }
    }

    .drawer-footer {
      position: absolute;
      width: calc(100% - 30px);
      height: 55px;
      line-height: 55px;
      text-align: right;
      bottom: 0;
      left: 15px;
      border-top: 1px solid rgba(0, 0, 0, 0.09);
    }
  }


</style>
