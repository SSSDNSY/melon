import {defaultSettings} from "../../../packages/preset-configuration/editor.config";
import {unObserver} from "@utils/tool";

const state = {
  editor: {...defaultSettings},
  bpmn: {},
  getEditor: (state) => state.editor,
  getProcessDef: (state) => ({
    processName: state.editor.processName,
    processId: state.editor.processId
  }),
  getProcessEngine: (state) => state.editor.processEngine,
  getEditorConfig: (state) => {
    return Object.keys(state.editor).reduce((config, key) => {
      if (!["processName", "processId", "processEngine"].includes(key)) {
        config[key] = state.editor[key];
      }
      return config;
    }, {});
  },

  // modeler
  getModeler: (state) => state.bpmn._modeler,
  getModeling: (state) => (state.bpmn._modeler ? state.bpmn._modeler.get("modeling") : undefined),
  getActive: (state) => state.bpmn.activeElement
}

const mutations = {
  // editor
  setConfiguration(state, conf) {
    state.editor = {...state.editor, ...conf};
  },

  clearBpmnState(state) {
    state.bpmn = {};
  },
  /**
   * @param state
   * @param modeler { object }
   */
  setModeler(state, modeler) {
    state.bpmn._modeler = unObserver(modeler);
  },
  /**
   * @param state
   * @param key { string }
   * @param module { object }
   */
  // setModules(state, { key, module }) {
  //   // state.bpmn[`_${key}`] = Object.freeze(module);
  // },
  /**
   * @param state
   * @param id { string }
   * @param element { object }
   */
  setElement(state, {element, id}) {
    state.bpmn.activeElement = {element: unObserver(element), id};
  }
}

const actions = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
