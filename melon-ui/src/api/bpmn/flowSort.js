import request from '@/utils/request';

// 流程类别分页
export function list(queryParams) {
  return request({
    url: '/flowSort/list',
    method: 'get',
    params: queryParams
  });
}

// 获取流程类别
export function flowSortTree() {
  return request({
    url: '/flowSort/getTree',
    method: 'get',
  });
}

// 保存流程类型
export function saveFlowSort(queryParams) {
  return request({
    url: '/flowSort/save',
    method: 'post',
    data: queryParams
  });
}

// 删除流程列表
export function deleteFlowSort(queryParams) {
  return request({
    url: '/flowSort/' + queryParams,
    method: 'delete',
  });
}
