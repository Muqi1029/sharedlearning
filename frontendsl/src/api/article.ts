import request from '@/utils/request'


// TODO
export function getArticleById(id: number) {
    return request({
        url: '/article/' + id,
        method: 'get'
    })
}

// TODO
export function getArticleByCourseId(id: number) {
    return request({
        url: '/course/' + id,
        method: 'get'
    })
}
