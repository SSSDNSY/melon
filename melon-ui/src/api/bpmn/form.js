import request from '@/utils/request';

// 获取表单列表
export function formList(queryParams) {
  return request({
    url: '/formMeta/list',
    method: 'get',
    params: queryParams
  });
}


// 删除表单
export function deleteForm(queryParams) {
  return request({
    url: '/api/form/deleteForm',
    method: 'post',
    data: queryParams
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
export function getFormEntity(queryParams) {
  return request({
    url: '/formMeta/' + queryParams,
    method: 'get',
  });
}

// 删除动态下拉框信息
export function getDataById(approval) {
  return request({
    url: '/api/formData/getDataById',
    method: 'post',
    data: approval
  });
}

// 删除表单分类
export function deleteFormSort(approval) {
  return request({
    url: '/formMeta/remove',
    method: 'post',
    data: approval
  });
}
// 获取表单数据信息
export function getGenerWork(data) {
  return request({
    url: '/api/form/getGenerWork',
    method: 'get',
    params: data
  });
}
// 删除表单数据
export function deleteGenerWork(data) {
  return request({
    url: '/api/form/deleteGenerWork',
    method: 'get',
    params: data
  });
}
