<template>
  <div class="flex-1 bg-white mx-2 max-h-96 h-96 overflow-scroll">
    <div class="flex items-center ml-4">
      <img
        v-if="title === 'Github'"
        src="@/assets/github.png"
        class="w-8 h-8"
      />
      <h1 class="ml-2">{{ title }}</h1>
    </div>

    <ul class="link-container list-none flex flex-col">
      <li v-for="item in data" :key="item.id">
        <!--        {{ item }}-->
        <LinkCard :data="item"></LinkCard>
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { ILink } from "@/types/types";
import LinkCard from "@/components/LinkCard/LinkCard.vue";

export default defineComponent({
  name: "RecommendLinkCard",
  components: { LinkCard },
  props: {
    data: {
      // type: Object as () => ILink[],
      required: true,
    },
    title: {
      type: String,
    },
  },
  setup() {
    return {};
  },
});
</script>

<style scoped>
.link-container {
  //border: 5px solid black; background: #ffffff;
}
</style>
