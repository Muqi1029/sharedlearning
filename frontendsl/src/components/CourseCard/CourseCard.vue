<script setup lang="ts">
import { defineProps } from "vue";
import { defaultImg } from "@/constant/app";
import router from "@/router";

const props = defineProps({
  data: Object as any,
});
const toCourse = () => {
  alert(props.data.id);
  router.push({
    path: "/course/" + props.data.id,
  });
};
</script>

<template>
  <div class="relative h-full rounded-2xl list-none border-blue-500">
    <!--   course cover -->
    <div
      class="relative cursor-pointer courseCover-container h-52"
      @click="toCourse"
    >
      <img
        v-if="props.data.courseCover"
        alt=""
        v-lazy="props.data.courseCover"
      />
      <img v-else :src="defaultImg" alt="" />
      <span class="z-30 absolute w-full left-0 opacity-40 mix-blend-screen" />
    </div>

    <!-- course info -->
    <div class="pt-10">
      <div>
        <b v-if="props.data.term" class="text-ob text-xs uppercase">
          #{{ props.data.term }}
        </b>
      </div>

      <div>{{ props.data.professor }}</div>
      <h1>{{ props.data.title }}</h1>
      <p class="course-intro">
        {{ props.data.intro }}
      </p>
    </div>
  </div>
</template>

<style scoped lang="scss">
.courseCover-container {
  &:after {
    pointer-events: none;
    content: "";
    position: absolute;
    z-index: 35;
    top: 13%;
    left: 0;
    height: 120%;
    width: 100%;
    background: var(--article-cover);
  }

  img {
    @apply rounded-2xl block absolute z-20 bg-cover bg-no-repeat object-cover w-full h-12/10;
  }
}

.course-intro {
  height: 120px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -web-kit-line-clamp: 5;
  -webkit-box-orient: vertical;
}
</style>
