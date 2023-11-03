<script setup lang="ts">
import Header from "@/components/Header/Header.vue";
import Recommend from "@/components/Recommend/Recommend.vue";
import Title from "@/components/Title/Title.vue";
import CourseCard from "@/components/CourseCard/CourseCard.vue";
import Bottom from "@/components/Bottom.vue";
import { reactive, ref, watch, watchEffect } from "vue";
import { terms, languages } from "@/constant/app";
import Sidebar from "@/components/Sidebar/Sidebar.vue";
import Profile from "@/components/Sidebar/Profile.vue";
import RecentReserve from "@/components/Sidebar/RecentReserve.vue";
import { readData, saveData } from "@/utils/localStorageUtil";
import { useCourseStore } from "@/stores/course";
import UpLoadLink from "@/components/UpLoadLink/UpLoadLink.vue";

const term = ref<string>("");
const language = ref<string>("");
const courseStore = useCourseStore();

const tabClass = reactive({
  tab: true,
  "expanded-tab": false,
});

const courses = ref([]);
const haveCourses = ref<boolean>(false);
const reservedCourses = ref([]);
const favList = ref<number[]>([]);
favList.value = readData();

const changeReserve = (courseID: number, isReserve: boolean) => {
  const index = favList.value.indexOf(courseID);
  if (isReserve && index == -1) {
    favList.value.push(courseID);
  } else {
    favList.value.splice(index, 1);
  }
  saveData(favList.value);
};

watch(
  favList,
  (newValue) => {
    reservedCourses.value = courses.value.filter((ele: any) =>
      newValue.includes(ele.id)
    );
  },
  { immediate: true, deep: true }
);

courseStore.getCoursesAction().then(() => {
  courses.value = courseStore.courses;
  haveCourses.value = true;
  reservedCourses.value = courses.value.filter((ele: any) => {
    return favList.value.includes(ele.id);
  });
});

/* filter by term and programming language */
watchEffect(() => {
  // if courseStore have courses and term is not blank
  if (haveCourses.value && term.value) {
    courses.value = courseStore.getCourseByTerm(term.value);
  } else if (haveCourses.value && !term.value) {
    courses.value = courseStore.courses;
  }
});

/* upload dialog */
const isShowDialog = ref<boolean>(false);
</script>

<template>
  <Header />

  <recommend />

  <div class="flex">
    <Title :title="'Class Cards'" icon="article" />
  </div>

  <div class="main-grid">
    <div class="flex flex-col relative">
      <!--  tab column  -->
      <ul :class="tabClass">
        <el-select
          placeholder="学期"
          v-model="term"
          clearable
          filterable
          size="large"
        >
          <el-option
            v-for="term in terms"
            :key="term.value"
            :label="term.label"
            :value="term.value"
          />
        </el-select>

        <el-select
          placeholder="编程语言"
          v-model="language"
          clearable
          filterable
          size="large"
          class="ml-3"
        >
          <el-option
            v-for="language in languages"
            :key="language.value"
            :label="language.label"
            :value="language.value"
          />
        </el-select>
      </ul>

      <!--   course-card   -->
      <ul class="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-8 ml-4">
        <template v-if="haveCourses">
          <li v-for="course in courses" :key="course.id">
            <CourseCard
              :data="course"
              @courseID="changeReserve"
              :fav="favList.includes(course.id)"
            />
          </li>
        </template>
      </ul>
    </div>

    <!--   sidebar used to exhibit user info -->
    <sidebar>
      <profile />
      <recent-reserve :courses="reservedCourses" />
    </sidebar>
  </div>

  <teleport to="body">
    <up-load-link
      :dialog-visible="isShowDialog"
      @change-visible="(args) => (isShowDialog = args)"
    />
  </teleport>

  <bottom />
</template>

<style scoped lang="scss">
.main-grid {
  display: flex;
  flex-direction: column;
}

@media (min-width: 1024px) {
  .main-grid {
    display: grid;
    gap: var(--gap);
    grid-template-columns: minmax(0, 1fr) 320px;
  }
}
</style>
