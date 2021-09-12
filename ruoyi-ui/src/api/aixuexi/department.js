import request from '@/utils/request'

// 查询研发部门列表
export function listDepartment(query) {
  return request({
    url: '/aixuexi/department/list',
    method: 'get',
    params: query
  })
}

// 查询研发部门详细
export function getDepartment(departmentId) {
  return request({
    url: '/aixuexi/department/' + departmentId,
    method: 'get'
  })
}

// 新增研发部门
export function addDepartment(data) {
  return request({
    url: '/aixuexi/department',
    method: 'post',
    data: data
  })
}

// 修改研发部门
export function updateDepartment(data) {
  return request({
    url: '/aixuexi/department',
    method: 'put',
    data: data
  })
}

// 删除研发部门
export function delDepartment(departmentId) {
  return request({
    url: '/aixuexi/department/' + departmentId,
    method: 'delete'
  })
}

// 导出研发部门
export function exportDepartment(query) {
  return request({
    url: '/aixuexi/department/export',
    method: 'get',
    params: query
  })
}