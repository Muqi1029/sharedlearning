<script setup lang="ts">
import Navigation from "@/components/Header/Navigation.vue";
import Recommend from "@/components/Recommend/Recommend.vue";
import Title from "@/components/Title/Title.vue";
import CourseCard from "@/components/CourseCard/CourseCard.vue";
import Bottom from "@/components/Bottom.vue";
import { reactive, ref } from "vue";
import { terms, languages } from "@/constant/app";
import Sidebar from "@/components/Sidebar/Sidebar.vue";
import Profile from "@/components/Sidebar/Profile.vue";
import RecentReserve from "@/components/Sidebar/RecentReserve.vue";
import { getCourses } from "@/api/course";

const term = ref<string>("");
const language = ref<string>("");

const tabClass = reactive({
  tab: true,
  "expanded-tab": false,
});

const expanderClass = reactive({
  "tab=expander": true,
  expanded: false,
});

const changeReserve = () => {
  alert("改变收藏");
};

const courses = ref([]);
const favList = ref<number[]>([1]);
const haveCourses = ref<boolean>(false);

getCourses().then(({ records }: any) => {
  courses.value = records;
  haveCourses.value = true;
});
</script>

<template>
  <navigation />

  <recommend />

  <Title :title="'Class Cards'" icon="article" />
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
              :favList="favList"
            />
          </li>
        </template>
      </ul>
    </div>

    <!--   sidebar used to exhibit user info -->
    <sidebar>
      <profile />
      <recent-reserve :have-courses="haveCourses" :fav-list="favList" />
    </sidebar>
  </div>

  <bottom />
</template>

<style scoped lang="scss"></style>
