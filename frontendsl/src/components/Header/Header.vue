<script lang="ts" setup>
import Logo from "./Logo.vue";
import Navigation from "./Navigation.vue";
import Controls from "./Controls.vue";
</script>

<template>
  <header
    class="relative flex z-50 mt-1 text-xs justify-between border-8 border-blue-500 mb-4"
  >
    <logo />
    <navigation />
    <controls />
  </header>
</template>

<style lang="scss" scoped>
header {
  background: linear-gradient(45deg, #9000ff, #5e00ff);
}
</style>
