/**
 * store all articles
 */

import { defineStore } from "pinia";

import { IArticle } from "@/types/types";
import { getArticleById, getArticleByCourseId } from "@/api/article";
import markdownToHtml from "@/utils/markdown";

export const useArticleStore = defineStore("articleStore", {
  state() {
    return {
      topArticles: "" as any,
      articles: [] as any[],
    };
  },

  actions: {
    // getArticles(pagination: any) {
    //     return new Promise<void>((resolve) => {
    //         getArticles({
    //             current: pagination.current,
    //             size: pagination.size
    //         })
    //             .then((data: any) => {
    //                 if (data.flag) {
    //                     data.data.records.forEach((item: any) => {
    //                         item.articleContent = md
    //                             .render(item.articleContent)
    //                             .replace(/<\/?[^>]*>/g, '')
    //                             .replace(/[|]*\n/, '')
    //                             .replace(/&nbsp;/gi, '')
    //                     })
    //                     this.articles = data.data.records
    //                     pagination.total = data.data.count
    //                 }
    //             })
    //     })
    // },

    // fetch articles depend on courseId
    fetchArticlesByCourseId(id: number) {
      return this.articles.filter((article) => article.courseID === id);
    },

    getArticlesByCourseId(id: number) {
      return new Promise((resolve) => {
        getArticleByCourseId(id).then((data) => {
          resolve(data.data);
        });
      });
    },

    // fetch article depend on articleId
    getArticleById(id: number) {
      return new Promise((resolve) => {
        /**
         * 向后端发送请求文章id获取文章
         */
        getArticleById(id).then(({ data }) => {
          console.log("getArticleById return:", data);

          /**
           * 文章内容转换
           */
          data.articleContent = markdownToHtml(data.articleContent);
          resolve(data);
        });
      });
    },
  },
});
