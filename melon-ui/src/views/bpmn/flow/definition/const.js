export const flowDefinitionColumns = [
  {
    label: '序号',
    prop: 'index',
    type: 'index',
    with:'80px'
  },
  {
    label: '流程名称',
    prop: 'name'
  },
  {
    label: '流程编码',
    prop: 'id'
  },
  {
    label: '部署编码',
    prop: 'deploymentId'
  },
  {
    label: '流程定义键',
    prop: 'key'
  },
  {
    label: '操作',
    prop: 'operation',
    width: '300px'
  }
];

export const tabList = [
  {
    id: 1,
    typeName: '人事流程',
    typeCode: '001',
    children: [
      {
        id: 1001,
        typeName: '招聘需求',
        typeCode: '001001',
        img: '/image/common/flow1.png'
      },
      {
        id: 1002,
        typeName: 'Offer发放',
        typeCode: '001002',
        img: '/image/common/flow2.png'
      },
      {
        id: 1003,
        typeName: '入职申请',
        typeCode: '001003',
        img: '/image/common/flow3.png'
      },
      {
        id: 1004,
        typeName: '转正申请',
        typeCode: '001004',
        img: '/image/common/flow4.png'
      },
      {
        id: 1005,
        typeName: '培训申请',
        typeCode: '001005',
        img: '/image/common/flow5.png'
      },
      {
        id: 1006,
        typeName: '调岗申请',
        typeCode: '001006'
      },
      {
        id: 1007,
        typeName: '离职',
        typeCode: '001007'
      }
    ]
  },
  {
    id: 2,
    typeName: '行政流程',
    typeCode: '002',
    children: []
  }
];


export const translations = {
  "Exclusive Gateway": "Exklusives Gateway",
  "Parallel Gateway": "Paralleles Gateway",
  "Inclusive Gateway": "Inklusives Gateway",
  "Complex Gateway": "Komplexes Gateway",
  "Event based Gateway": "Ereignis-basiertes Gateway",
  "Message Start Event": "消息启动事件",
  "Timer Start Event": "定时启动事件",
  "Conditional Start Event": "条件启动事件",
  "Signal Start Event": "信号启动事件",
  "Error Start Event": "错误启动事件",
  "Escalation Start Event": "升级启动事件",
  "Compensation Start Event": "补偿启动事件",
  "Message Start Event (non-interrupting)": "消息启动事件 (非中断)",
  "Timer Start Event (non-interrupting)": "定时启动事件 (非中断)",
  "Conditional Start Event (non-interrupting)": "条件启动事件 (非中断)",
  "Signal Start Event (non-interrupting)": "信号启动事件 (非中断)",
  "Escalation Start Event (non-interrupting)": "升级启动事件 (非中断)"
};

