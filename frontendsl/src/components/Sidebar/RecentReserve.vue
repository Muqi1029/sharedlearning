<script lang="ts" setup>
import { ref, watch } from "vue";
import SubTitle from "../Title/SubTitle.vue";
import MiniCourseCard from "@/components/CourseCard/MiniCourseCard.vue";

// eslint-disable-next-line no-undef
const props = defineProps({
  courses: {
    required: true,
    type: Array,
  },
});

// watch
// watch(
//   () => props.haveCourses,
//   (newValue) => {
//     if (newValue) {
//       ReservedClass.value = courseStore.getFavCourse(props.favList);
//     }
//   },
//   { deep: true }
// );
//
// watch(
//   () => props.favList,
//   (newValue) => {
//     ReservedClass.value = courseStore.getFavCourse(newValue);
//   },
//   { deep: true }
// );
</script>

<template>
  <div class="sidebar-box">
    <sub-title :title="'最近收藏的课程'" icon="quote"></sub-title>

    <div class="flex flex-col">
      <li v-for="item in courses" :key="item">
        <MiniCourseCard :data="item"></MiniCourseCard>
      </li>
    </div>
  </div>
</template>

<style lang="scss" scoped>
li {
  list-style-type: none;
}
</style>
