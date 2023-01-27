import request from '@/utils/request'


// 查询参数列表
export function listConfig(query) {
  return request({
    url: '/config/project/list',
    method: 'get',
    params: query
  })
}

// 查询参数详细
export function getConfig(configId) {
  return request({
    url: '/config/project/' + configId,
    method: 'get'
  })
}


// 新增参数配置
export function addConfig(data) {
  return request({
    url: '/config/project',
    method: 'post',
    data: data
  })
}

// 修改参数配置
export function updateConfig(data) {
  return request({
    url: '/config/project',
    method: 'put',
    data: data
  })
}

// 删除参数配置
export function delConfig(configId) {
  return request({
    url: '/config/project/' + configId,
    method: 'delete'
  })
}

