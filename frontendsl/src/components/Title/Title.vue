<template>
  <p class="title uppercase relative opacity-9 flex items-center pt-12 pb-2 mb-8 text-3xl text-ob-bright">
    <svg-icon :icon-class="icon" class="inline-block mr-2"></svg-icon>
    {{ title }}

    <span class="absolute bottom-0 h-1 w-64 rounded-full" :style="gradientBackground">
    </span>
  </p>
</template>
<script lang="ts">
import { defineComponent, computed } from "vue";

import { useAppStore } from "@/stores/app";

export default defineComponent({
  name: "Title",
  components: {},

  props: {
    title: {
        required: true,
        type: String,
    },
    icon: {
        type: String,
    }
  },
  setup() {
    const appStore = useAppStore();

    return {
        gradientBackground: computed(() => {
            return {
                background: appStore.themeConfig.header_gradient_css
            }
        })
    };
  },
});
</script>
<style lang="scss" scoped>
.title {
 /* background-color: rgba(0, 0, 0, 0.2); */
}
</style>
