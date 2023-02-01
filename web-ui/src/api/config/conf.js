import request from '@/utils/request'


// 查询列表
export function listConf(query) {
  return request({
    url: '/config/conf/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getConf(confId) {
  return request({
    url: '/config/conf/' + confId,
    method: 'get'
  })
}


// 保存配置
export function saveConf(data) {
  return request({
    url: '/config/conf',
    method: 'post',
    data: data
  })
}

// 删除配置
export function delConf(configId) {
  return request({
    url: '/config/conf/' + configId,
    method: 'delete'
  })
}

