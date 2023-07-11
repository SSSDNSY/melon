/**
 * 表单配置
 */
const formConf = {
  version:'1.10',
  formId:'',
  tableName:'',
  formRef: 'elForm',
  formModel: 'form',
  rules: 'rules',
  size: 'medium',
  labelPosition: 'right',
  labelWidth: 80,
  formRules: 'rules',
  gutter: 15,
  disabled: false,
  dynamicTableAllowed:true
};
export default formConf;

//动态表单允许放入的组件
export const dynamicTableAllowedItems = [
  'input',
  'select',
  'radio',
  'checkbox',
  'Switch',
  'inputNumber',
  'textarea',
  'slider',
  'rate',
  'date',
  'time',
  'dialogList'
];

//row允许放入的items
export const rowAllowedItems = [
  'input',
  'input',
  'select',
  'radio',
  'checkbox',
  'Switch',
  'inputNumber',
  'textarea',
  'slider',
  'rate',
  'date',
  'time',
  'upload'
]
//table允许放入的items
export const tableAllowedItems = [
  'input',
  'input',
  'select',
  'radio',
  'checkbox',
  'Switch',
  'inputNumber',
  'textarea',
  'slider',
  'rate',
  'date',
  'time',
  'text',
  'upload'
]