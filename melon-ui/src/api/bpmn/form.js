import request from '@/utils/request';

// 获取表单列表
export function formList(queryParams) {
  return request({
    url: '/formMeta/list',
    method: 'get',
    params: queryParams
  });
}


// 增加表单
export function addForm(queryParams) {
  return request({
    url: '/formMeta',
    method: 'post',
    data: queryParams
  });
}

// 获取表单信息
export function getForm(queryParams) {
  return request({
    url: '/formMeta/' + queryParams,
    method: 'get',
  });
}


// 删除表单数据
export function deleteForm(data) {
  return request({
    url: '/formMeta/' + data,
    method: 'delete',
  });
}
