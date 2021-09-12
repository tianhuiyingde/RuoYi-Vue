import request from '@/utils/request'

// 查询问题详情列表
export function listQuestion(query) {
  return request({
    url: '/aixuexi/question/list',
    method: 'get',
    params: query
  })
}

// 查询问题详情详细
export function getQuestion(questionId) {
  return request({
    url: '/aixuexi/question/' + questionId,
    method: 'get'
  })
}

// 新增问题详情
export function addQuestion(data) {
  return request({
    url: '/aixuexi/question',
    method: 'post',
    data: data
  })
}

// 修改问题详情
export function updateQuestion(data) {
  return request({
    url: '/aixuexi/question',
    method: 'put',
    data: data
  })
}

// 删除问题详情
export function delQuestion(questionId) {
  return request({
    url: '/aixuexi/question/' + questionId,
    method: 'delete'
  })
}

// 导出问题详情
export function exportQuestion(query) {
  return request({
    url: '/aixuexi/question/export',
    method: 'get',
    params: query
  })
}