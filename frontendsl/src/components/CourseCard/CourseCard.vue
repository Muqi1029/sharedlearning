<template>
  <div class="article-container relative rounded-2xl">
    <div class="article">
      <div class="article-thumbnail row-span-1 relative" @click="toCourse">
        <img v-if="course.courseCover" v-lazy="course.courseCover" alt="" />
        <img v-else src="@/assets/default-cover.jpg" />
        <span class="thumbnail-screen" :style="gradientBackground" />
      </div>
      <div class="article-content pt-10">
        <!-- example: #大二上 # 计算机 c -->
        <!-- <span>{{ data.term }} {{ data.category }}</span> -->
        <span>
          <b v-if="course.term" class="text-ob text-xs uppercase">
            #{{ course.term }}
          </b>
          <ob-skeleton v-else tag="b" height="20px" width="35px"></ob-skeleton>

          <ul v-if="course.tags && course.tags.length > 0">
            <li v-for="tag in course.tags" :key="tag.id">
              <em># {{ tag.tagName }}</em>
            </li>
          </ul>
        </span>

        <div>{{ course.professor }}</div>
        <h1 class="">{{ course.title }}</h1>
        <p>{{ course.intro }}</p>
        
        <div class="course-footer relative w-full">
          <Favorites @isFavHandle="setFav" :isFav="isFav"></Favorites>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, toRefs, computed, ref, watch } from "vue";

import { useRouter } from "vue-router";
import Favorites from "../Favorites/Favorites.vue";
import { useAppStore } from "@/stores/app";
import { useUserStore } from "@/stores/user";
import { UserClickCourse } from "@/api/user";

export default defineComponent({
  name: "CourseCard",
  components: { Favorites },
  emits: ["courseID"],
  props: ["data", "favList"],
  setup(props, { emit }) {
    const appStore = useAppStore();
    const userStore = useUserStore();

    const userID: number = parseInt(userStore.userID);
    const courseID: number = toRefs(props).data.value.id;

    const router = useRouter();

    /**
     * store the isFav state and transfer the state to Favorites.vue
     */

    const toCourse = () => {
      UserClickCourse({ userID, courseID });
      router.push({
        path: "/course/" + courseID,
      });
    };

    const setFav = (bool: boolean) => {
      emit("courseID", [courseID, bool]);
    };

    return {
      course: toRefs(props).data,
      toCourse,
      gradientBackground: computed(() => {
        return { background: appStore.themeConfig.header_gradient_css };
      }),
      setFav,
      isFav: computed(() => props.favList.includes(courseID)),
    };
  },
});
</script>
<style lang="scss" scoped>
.article {
  &:hover {
    cursor: pointer;
  }

  .article-content {
    p {
      height: 120px;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -web-kit-line-clamp: 5;
      -webkit-box-orient: vertical;
    }
  }
}
</style>
