/**
 * store the all courses
 */

import { defineStore } from "pinia";
import { getCourses, getCourseIntroById } from "@/api/course";

import { ICourse } from "@/types/types";

export const useCourseStore = defineStore("courseStore", {
  state() {
    return {
      courses: [],
    };
  },

  actions: {
    /**
     * get all courses
     */
    getCoursesAction() {
      return new Promise<void>((resolve) => {
        getCourses().then(
          ({ data }) => {
            this.courses = data.records;
            resolve(data);
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
      return this.courses.filter((item: any) => item.id === id);
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
      return this.courses.filter((ele: any) => ele.term === term);
    },

    getFavCourse(favList: number[]) {
      return this.courses.filter((ele: any) => favList.includes(ele.id));
    },
  },
});
