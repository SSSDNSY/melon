import request from '@/utils/request';

// 获取流程部署列表
export function list(queryParams) {
  return request({
    url: '/flowDefinition/list',
    method: 'post',
    data: queryParams
  });
}

// 保存流程信息
export function save(queryParams) {
  return request({
    url: '/flowDefinition/save',
    method: 'post',
    data: queryParams
  });
}

// 获取流程设置
export function get(queryParams) {
  return request({
    url: '/flowDefinition/get',
    method: 'get',
    params: queryParams
  });
}

// 删除流程
export function remove(queryParams) {
  return request({
    url: '/flowDefinition/remove',
    method: 'delete',
    params: queryParams
  });

}



// 获取流程定义xml
export function deploymentXML(deploymentId) {
  return request({
    url: '/flowDefinition/getDeploymentXmlById/'+deploymentId,
    method: 'get'
  });
}

// 流程发布
export function deployWithBPMNJS(queryParams) {
  return request({
    url: '/flowDefinition/deployWithBPMNJS',
    method: 'post',
    data: queryParams
  });
}



// 获取流程节点配置
export function getFlowElementAttrs(queryParams) {
  return request({
    url: '/api/as/getFlowElementAttrs',
    method: 'get',
    params: queryParams
  });
}

// 设置节点处理人信息
export function setAssigneeUserWithTaskId(queryParams) {
  return request({
    url: '/api/as/setAssigneeUser',
    method: 'post',
    data: queryParams
  });
}

// 获取节点处理人信息
export function getAssigneeUserWithTaskId(queryParams) {
  return request({
    url: '/api/as/getAssigneeUserWithTaskId',
    method: 'get',
    params: queryParams
  });
}

// 删除处理人信息
export function deleteAssigneeUser(approval) {
  return request({
    url: '/api/as/deleteAssigneeUser',
    method: 'post',
    data: approval
  });
}
// 设置流程信息
export function setFlowAttrs(data) {
  return request({
    url: '/api/as/setFlowElementAttrs',
    method: 'post',
    data: data
  });
}
