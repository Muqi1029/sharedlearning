<template>
  <div v-show="!isMobile">
    <slot></slot>
  </div>
</template>
<script lang="ts">
import { defineComponent, computed } from "vue";

import { useCommonStore } from "@/stores/common";

export default defineComponent({
  name: "Sidebar",
  components: {},
  setup() {
    const commonStore = useCommonStore();
    return {
      isMobile: computed(() => commonStore.isMobile),
    };
  },
});
</script>
<style lang="scss" scoped></style>