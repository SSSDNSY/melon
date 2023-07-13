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
