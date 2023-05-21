/**
 * store the all courses
 */

import { defineStore } from "pinia";
import { getCourses, getCourseIntroById } from "@/api/course";

import { ICourse } from "@/types/types";

export const useCourseStore = defineStore("courseStore", {
  state() {
    return {
      courses: [] as any[],
    };
  },

  actions: {
    /**
     * get all courses
     * @param params any
     */
    getCourses(params: any) {
      return new Promise<void>((resolve, reject) => {
        getCourses().then(
          ({ data }) => {
            resolve(data);
            this.courses = data.records;
            resolve();
          },
          (reason) => {
            console.log("fail to get courses, reason:", reason);
          }
        );
      });
    },

    /**
     * get course by id
     * @param id courseID
     */
    getCourseById(id: number) {
      return this.courses.filter((item) => item.id === id);
    },

    /**
     * get course intro by id
     * @param id courseID
     */
    getCourseIntroById(id: number) {
      return new Promise((resolve, reject) => {
        getCourseIntroById(id).then(
          ({ data }) => {
            resolve(data);
          },
          (reason) => {
            console.log("fail to get courseInfo, reason:", reason);
          }
        );
      });
    },

    /**
     * filter course by term
     * @param term course term
     */
    getCourseByTerm(term: string) {
      return this.courses.filter((ele) => ele.term === term);
    },

    getFavCourse(favList: number[]) {
      // console.log("111", this.courses);
      return this.courses.filter((ele) => favList.includes(ele.id));
    },
  },
});
