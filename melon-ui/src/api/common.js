import request from "@/utils/request";

// 获取下一个ID
export function nextId() {
  return request({
    url: '/common/nextId',
    method: 'get',
  });
}

//获取druid 数据源信息
export function druidDatasource() {
  return request({
    url: '/druid/datasource.json',
    method: 'post',
  });
}
