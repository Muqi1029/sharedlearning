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
  <div class="relative h-full rounded-2xl list-none">
    <!--   course cover -->
    <div
      class="relative cursor-pointer row-span-1 courseCover-container"
      @click="toCourse"
    >
      <img
        v-if="props.data.courseCover"
        alt=""
        v-lazy="props.data.courseCover"
      />
      <img v-else :src="defaultImg" alt="" />
      <span
        class="z-30 absolute w-full h-12/10 left-0 pointer-events-none opacity-40"
      />
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
      <p class="h-32 truncate line-clamp-5">
        {{ props.data.intro }}
      </p>
    </div>
  </div>
</template>

<style scoped lang="scss">
.courseCover-container {
  img {
    @apply rounded-2xl;
  }
}
</style>
