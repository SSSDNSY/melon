import request from '@/utils/request'


// 查询列表
export function listModel(query) {
  return request({
    url: '/model/list',
    method: 'get',
    params: query
  })
}


// 新增配置
export function addModel(data) {
  return request({
    url: '/model/add',
    method: 'post',
    data: data
  })
}

// 删除
export function removeModel(modleId) {
  return request({
    url: '/model/remove/' +modleId,
    method: 'delete'
  })
}


// 导出
export function exportModel(modelId) {
  return request({
    url: '/model/export/' +modleId,
    method: 'get'
  })
}

// 部署
export function deployModel(modelId) {
  return request({
    url: '/model/deploy/' +modleId,
    method: 'get'
  })
}



