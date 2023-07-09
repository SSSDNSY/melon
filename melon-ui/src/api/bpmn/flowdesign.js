import request from '@/utils/request';

// 获取流程部署列表
export function processList(queryParams) {
  return request({
    url: '/api/deployment/getDeployment',
    method: 'post',
    data: queryParams
  });
}

// 保存流程信息
export function updataProcess(queryParams) {
  return request({
    url: '/api/deployment/updateDeployment',
    method: 'post',
    data: queryParams
  });
}

// 获取流程设置
export function getFormElement(queryParams) {
  return request({
    url: '/api/as/getFormElement',
    method: 'get',
    params: queryParams
  });
}
// 删除流程
export function delDefinition(queryParams) {
  return request({
    url: '/api/deployment/delDefinition',
    method: 'get',
    params: queryParams
  });
}
