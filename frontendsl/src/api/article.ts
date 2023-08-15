import request from "@/utils/request";

export function getArticleById(id: number) {
  return request({
    url: "/article/" + id,
    method: "get",
  });
}

export function getArticleByCourseId(id: number) {
  return request({
    url: "/course/" + id,
    method: "get",
  });
}

export function getPendingArticle(userAuthority: number) {
  return request({
    url: "article/admin/articles",
    method: "post",
    data: { userAuthority },
  });
}
