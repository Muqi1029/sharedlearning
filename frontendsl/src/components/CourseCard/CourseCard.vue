<script setup lang="ts">
import { defineProps, ref } from "vue";
import { defaultImg } from "@/constant/app";
import { useRouter } from "vue-router";
import Favorites from "@/components/Favorites/Favorites.vue";

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
  fav: {
    type: Boolean,
    required: true,
    default: false,
  },
});
// eslint-disable-next-line no-undef
const emits = defineEmits(["courseID"]);
const router = useRouter();
const toCourse = () => {
  router.push({
    path: "/course/" + props.data.id,
    query: {
      course: props.data.title,
    },
  });
};

const val = ref<boolean>(props.fav);
const changeFav = (arg: boolean) => {
  val.value = arg;
  emits("courseID", props.data.id, arg);
};
</script>

<template>
  <div class="course-card-container h-full rounded-2xl list-none relative">
    <!--   course cover -->
    <div
      class="relative cursor-pointer courseCover-container h-52"
      @click="toCourse"
    >
      <img v-if="data.courseCover" alt="" v-lazy="data.courseCover" />
      <img v-else :src="defaultImg" alt="" />
      <span class="z-30 absolute w-full left-0 opacity-40 mix-blend-screen" />
    </div>

    <!-- course info -->
    <div class="pt-10">
      <div>
        <b v-if="data.term" class="text-ob text-xs uppercase">
          #{{ data.term }}
        </b>
      </div>

      <div>{{ data.professor }}</div>
      <h1>{{ data.title }}</h1>
      <p class="course-intro">
        {{ data.intro }}
      </p>
    </div>

    <Favorites
      class="absolute right-1 bottom-0 cursor-pointer"
      :is-fav="val"
      @isFavHandle="changeFav"
    />
  </div>
</template>

<style scoped lang="scss">
.courseCover-container {
  &:after {
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
    @apply rounded-2xl absolute z-20 bg-cover bg-no-repeat object-cover w-full h-12/10;
  }
}

.course-intro {
  height: 120px;
  overflow: hidden;
  text-overflow: ellipsis;
  -web-kit-line-clamp: 5;
  -webkit-box-orient: vertical;
}

.course-card-container {
  transition: transform 0.01s ease-in-out;

  &:hover {
    transform: scale(1.015);
  }
}
</style>
