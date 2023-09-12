<script setup lang="ts">
import RecommendLinkCard from "@/components/LinkCard/RecommendLinkCard.vue";
import Title from "@/components/Title/Title.vue";

const githubList = [];
const articleList = [];
</script>

<template>
  <div class="border-4 border-blue-600">
    <div class="flex justify-between">
      <Title title="Recommendation" icon="hot" />
      <el-button class="h-6 p-5" size="large"> 上传链接</el-button>
    </div>
    <div class="flex">
      <recommend-link-card title="Github" :data="githubList" />
      <recommend-link-card title="文章 教程" :data="articleList" />
    </div>
  </div>
</template>

<style scoped lang="scss"></style>
