import request from "@/utils/request";

// 获取下一个ID
export function nextId() {
  return request({
    url: '/common/nextId',
    method: 'get',
  });
}

//获取druid 数据源信息
export function getAllDriver() {
  return request({
    url: '/common/getAllDriver',
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

//获取druid 数据源信息
export function getOneImg() {
  return request({
    url: '/common/getOneImg',
    method: 'get',
  });
}
