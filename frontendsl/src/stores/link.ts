import { defineStore } from "pinia";
import {
  getLinkByCourseID,
  importOfficialLink,
  importPrivateLink,
} from "@/api/link";

export const useLinkStore = defineStore("linkStore", {
  state: () => {
    return {};
  },

  actions: {
    // delete!
    getLinkByCourseID(courseID: number) {
      return new Promise((resolve, reject) => {
        getLinkByCourseID(courseID).then(
          ({ data }) => {
            console.log(data);
            resolve(data);
          },
          (reason) => {
            console.log("fail to get link: " + reason);
            reject(reason);
          }
        );
      });
    },

    /**
     *
     * @param officialForm 用户上传官方链接
     * @returns Promise
     */
    importOfficialLink(officialForm: any) {
      return new Promise((resolve, reject) => {
        importOfficialLink(officialForm).then(
          (data) => {
            resolve(data);
          },
          (reason) => {
            reject(reason);
          }
        );
      });
    },

    async importPrivateLink(privateForm: any) {
      return await importPrivateLink(privateForm);
    },
  },
});
