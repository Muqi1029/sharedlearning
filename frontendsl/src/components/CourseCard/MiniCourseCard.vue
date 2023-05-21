<template>
  <div>
    <div class="article-container-mini m-2">
      <div class="article" @click="toCourse">
        <!-- Course Img -->
        <div class="article-img">
          <img v-if="course.courseCover" v-lazy="course.courseCover" alt="" />
          <img v-else src="@/assets/default-cover.jpg" />
        </div>
        <div class="article-content">
          <h4>{{ course.title }}</h4>
          授课老师：{{ course.professor }}
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, toRefs, computed, toRef } from "vue";
import { useRouter } from "vue-router";
import { useAppStore } from "@/stores/app";

export default defineComponent({
  name: "MiniCourseCard",
  components: {},

  props: ["data"],
  setup(props) {
    const router = useRouter();
    const toCourse = () => {
      router.push({
        path: "/course/" + props.data.id,
      });
    };

    return {
      course: toRefs(props).data,
      toCourse,
    };
  },
});
</script>
<style lang="scss" scoped>
.article-container-mini {
  height: 70px;
}

.article {
  color: #fff;

  &:hover {
    cursor: pointer;
    transform: scale(1.1);
  }
}
</style>
