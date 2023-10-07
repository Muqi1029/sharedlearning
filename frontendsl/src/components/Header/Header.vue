<script lang="ts" setup>
import Logo from "./Logo.vue";
import Navigation from "./Navigation.vue";
import Controls from "./Controls.vue";
</script>

<template>
  <div class="header">
    <header class="relative flex z-50 mt-3 text-xs justify-between mb-4 mx-16">
      <logo />
      <navigation />
      <controls />
    </header>
  </div>
</template>

<style lang="scss" scoped>
.header {
  background: linear-gradient(130deg, #24c6dc, #5433ff 41.07%, #ff0099 76.05%);
}
</style>
