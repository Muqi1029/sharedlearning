import request from "@/utils/request";
import { ArticleVO } from "@/types/types";

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

export function saveArticle(articleVO: ArticleVO) {
  return request({
    url: "article/import-article",
    method: "post",
    data: {
      ...articleVO,
    },
  });
}

export function changeArticleStatus(articleId: number, articleStatus: number) {
  return request({
    url: "article/changeStatus",
    method: "post",
    data: {
      articleId,
      articleStatus,
    },
  });
}

export function getFeatureArticles() {
  return request({
    url: "article/features",
    method: "get",
  });
}
