import { defineStore } from "pinia";
import { getComments } from "@/api/comment";

export const useCommentStore = defineStore("commentStore", {
  state() {
    return {
      recentComment: "" as any,
      type: "" as any,
    };
  },

  actions: {
    getComments(articleID: number) {
      return new Promise((resolve) => {
        getComments(articleID).then(({ data }) => {
          resolve(data);
        });
      });
    },
  },
});
