<script setup lang="ts">
import RecommendLinkCard from "@/components/LinkCard/RecommendLinkCard.vue";

const githubList = [];
const articleList = [];
</script>

<template>
  <div class="flex justify-between">
    <h1>智能推荐部分</h1>
    <el-button class="h-6 p-5" size="large"> 上传链接</el-button>
  </div>
  <div class="flex">
    <recommend-link-card title="Github" :data="githubList" />
    <recommend-link-card title="文章 教程" :data="articleList" />
  </div>
</template>

<style scoped lang="scss"></style>
