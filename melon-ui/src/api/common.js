import request from "@/utils/request";

// 获取下一个ID
export function nextId() {
  return request({
    url: '/common/nextId',
    method: 'get',
  });
}
