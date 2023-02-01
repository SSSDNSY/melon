import request from '@/utils/request'


// 查询列表
export function listEnv(query) {
  return request({
    url: '/config/env/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getEnv(envId) {
  return request({
    url: '/config/env/' + envId,
    method: 'get'
  })
}


// 保存配置
export function saveEnv(data) {
  return request({
    url: '/config/env',
    method: 'post',
    data: data
  })
}

// 删除配置
export function delEnv(configId) {
  return request({
    url: '/config/env/' + configId,
    method: 'delete'
  })
}

