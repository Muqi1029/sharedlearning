/**
 * store all articles
 */

import { defineStore } from "pinia";

export const useArticleStore = defineStore("articleStore", {
  state() {
    return {
      topArticles: "" as any,
      articles: [] as any[],
    };
  },
  actions: {},
});
