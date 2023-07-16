import request from '@/utils/request'

// 查询参数列表
export function list() {
  return request({
    url: '/system/home/list',
    method: 'get',
  })
}
