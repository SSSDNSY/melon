import MyProcessDesigner from "./designer";
import MyProcessPenal from "./penal";
import MyProcessPalette from "./palette";

const components = [MyProcessDesigner, MyProcessPenal, MyProcessPalette];

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
