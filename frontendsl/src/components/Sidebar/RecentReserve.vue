<template>
  <div class="sidebar-box">
    <sub-title :title="'最近收藏的课程'" icon="quote"></sub-title>

    <div class="flex flex-col">
      <li v-for="item in ReservedClass" :key="item">
        <MiniCourseCard :data="item"></MiniCourseCard>
      </li>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, ref, toRefs, watch } from "vue";
import SubTitle from "../Title/SubTitle.vue";
import MiniCourseCard from "@/components/CourseCard/MiniCourseCard.vue";
import { useCourseStore } from "@/stores/course";

export default defineComponent({
  name: "RecentReserve",
  components: { SubTitle, MiniCourseCard },
  props: {
    favList: {
      required: true,
      type: Object as () => any[],
    },
    hasCourses: {
      required: true,
      type: Boolean,
    },
  },
  setup(props) {
    const courseStore = useCourseStore();
    let ReservedClass = ref();

    // watch
    watch(
      () => props.hasCourses,
      (newValue) => {
        if (newValue) {
          ReservedClass.value = courseStore.getFavCourse(props.favList);
        }
      },
      { deep: true }
    );

    watch(
      () => props.favList,
      (newValue) => {
        ReservedClass.value = courseStore.getFavCourse(newValue);
      },
      { deep: true }
    );

    return {
      ReservedClass,
    };
  },
});
</script>
<style lang="scss" scoped>
li {
  list-style-type: none;
}
</style>
