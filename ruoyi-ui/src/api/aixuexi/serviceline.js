import request from '@/utils/request'

// 查询业务线列表
export function listServiceline(query) {
  return request({
    url: '/aixuexi/serviceline/list',
    method: 'get',
    params: query
  })
}

// 查询业务线详细
export function getServiceline(servicelineId) {
  return request({
    url: '/aixuexi/serviceline/' + servicelineId,
    method: 'get'
  })
}

// 新增业务线
export function addServiceline(data) {
  return request({
    url: '/aixuexi/serviceline',
    method: 'post',
    data: data
  })
}

// 修改业务线
export function updateServiceline(data) {
  return request({
    url: '/aixuexi/serviceline',
    method: 'put',
    data: data
  })
}

// 删除业务线
export function delServiceline(servicelineId) {
  return request({
    url: '/aixuexi/serviceline/' + servicelineId,
    method: 'delete'
  })
}

// 导出业务线
export function exportServiceline(query) {
  return request({
    url: '/aixuexi/serviceline/export',
    method: 'get',
    params: query
  })
}