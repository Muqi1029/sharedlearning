<script lang="ts" setup>
import { useAppStore } from "@/stores/app";
import { computed, defineProps } from "vue";

const { title, icon } = defineProps({
  title: {
    required: true,
    type: String,
  },
  icon: {
    required: true,
    type: String,
  },
});

const appStore = useAppStore();

const gradientBackground = computed(() => {
  return {
    background: appStore.themeConfig.header_gradient_css,
  };
});
</script>
<template>
  <p
    class="title uppercase relative opacity-9 flex items-center pb-1 mb-3 text-3xl"
  >
    <svg-icon :icon-class="icon" class="inline-block mr-2"></svg-icon>
    {{ title }}

    <span
      class="absolute bottom-0 h-1 w-full rounded-full"
      :style="gradientBackground"
    >
    </span>
  </p>
</template>

<style lang="scss" scoped></style>
