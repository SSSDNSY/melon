import formDesigner from './formDesigner';
import fancyEditor from './extend/fancyEditor';
import fancyHtml from './extend/fancyHtml';
import fancyText from './extend/fancyText';
import fancyBarCode from 'vue-barcode';
import fancyDialogList from './extend/fancyDialogList';
import iconDialog from './iconDialog';



const components = [formDesigner, fancyEditor,fancyHtml,fancyText,fancyBarCode, fancyDialogList,iconDialog];

const install = function(Vue) {
  components.forEach(component => {
    Vue.component(component.name, component);
  });
};

if (typeof window !== "undefined" && window.Vue) {
  install(window.Vue);
}

export default {
  version: "0.0.1",
  install,
  ...components
};
