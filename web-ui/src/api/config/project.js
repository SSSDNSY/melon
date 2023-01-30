import request from '@/utils/request'


// 查询参数列表
export function listProject(query) {
  return request({
    url: '/config/project/list',
    method: 'get',
    params: query
  })
}

// 查询参数详细
export function getProject(configId) {
  return request({
    url: '/config/project/' + configId,
    method: 'get'
  })
}


// 保存参数配置
export function saveProject(data) {
  return request({
    url: '/config/project',
    method: 'post',
    data: data
  })
}

// 删除参数配置
export function delProject(configId) {
  return request({
    url: '/config/project/' + configId,
    method: 'delete'
  })
}

