import request from '@/utils/request'


// 查询列表
export function listConf(query) {
  return request({
    url: '/config/conf/list',
    method: 'get',
    params: query
  })
}

// 查询环境、项目的下拉框
export function listEnvApp(query) {
  return request({
    url: '/config/conf/listEnvApp',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getConf(env,key) {
  return request({
    url: '/config/conf/' + env+'/'+key,
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
export function delConf(env,key) {
  return request({
    url: '/config/conf/' + env+'/'+key,
    method: 'delete'
  })
}

