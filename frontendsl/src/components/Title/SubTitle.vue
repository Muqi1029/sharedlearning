<template>
  <p
    class="relative flex items-center pb-2 mb-4 text-xl text-ob-bright uppercase"
  >
    <svg-icon
      v-if="icon && side === 'left'"
      :icon-class="icon"
      class="inline-block mr-2"
    ></svg-icon>
    <span :class="titleClass">{{ titleStr }}</span>
    <svg-icon
      v-if="icon && side === 'right'"
      :icon-class="icon"
      class="inline-block ml-2"
    >
    </svg-icon>
    <span :class="lineClass" :style="gradientBackground"></span>
  </p>
</template>
<script lang="ts">
import { defineComponent, computed, toRefs } from "vue";

import { useAppStore } from "@/stores/app";

export default defineComponent({
  name: "SubTitle",
  props: {
    title: {
      type: String,
      default: "",
      required: true,
    },
    side: {
      type: String,
      default: "left",
    },
    icon: String,
  },
  components: {},
  setup(props) {
    const appStore = useAppStore();

    const side = toRefs(props).side;
    const titleStr = toRefs(props).title;
    return {
      gradientBackground: computed(() => {
        return { background: appStore.themeConfig.header_gradient_css };
      }),
      titleClass: computed(() => {
        return {
          "w-full": true,
          block: true,
          "text-right": side.value === "right" ? true : false,
        };
      }),
      lineClass: computed(() => {
        return {
          absolute: true,
          "bottom-0": true,
          "h-1": true,
          "w-14": true,
          "rounded-full": true,
          "right-0": side.value === "right" ? true : false,
        };
      }),
      titleStr,
    };
  },
});
</script>
<style lang="scss" scoped></style>
