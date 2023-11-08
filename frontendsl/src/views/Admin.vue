<script setup lang="ts">
import Header from "@/components/Header/Header.vue";
</script>

<template>
  <Header />
  <hr />
  <div class="flex flex-row m-4">
    <aside class="aside-container flex flex-col">
      <router-link to="/admin/article">文章</router-link>
      <router-link to="/admin/user">用户</router-link>
      <router-link to="/admin/link">链接</router-link>
    </aside>

    <div class="w-5/6 ml-4">
      <router-view />
    </div>
  </div>
</template>

<style scoped lang="scss">
.aside-container {
  border: #99a9bf;

  a {
    background: #28bbdf;
    @apply border-2 opacity-90 p-5 text-2xl m-6;
  }
}
</style>
