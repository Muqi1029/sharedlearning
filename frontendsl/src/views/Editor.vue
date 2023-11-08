<script setup lang="ts">
import Tinymce from "@/components/Tinymce.vue";
import { useRoute, useRouter } from "vue-router";
import { ref } from "@vue/reactivity";
import { useUserStore } from "@/stores/user";
import { saveArticle } from "@/api/article";
import { ArticleVO } from "@/types/types";
import { reactive } from "vue";

const val = ref("Welcome! Write your thoughts here!");
const isAnonymous = ref(false);
const route = useRoute();
const router = useRouter();
const userStore = useUserStore();
const userId = parseInt(userStore.userID);
const courseId = parseInt(route.query["courseID"] as string);

const articleVO = reactive<ArticleVO>({
  articleContent: "",
  userId,
  courseId,
});
const saveContent = async () => {
  try {
    articleVO.articleContent = val.value;
    articleVO.userId = isAnonymous ? 0 : userId;
    const result = await saveArticle(articleVO);
    if (result.data) {
      alert("发布成功");
      await router.push({
        path: "course/" + courseId,
      });
    } else {
      alert("发布失败， 请重试");
    }
  } catch (error) {
    console.error("An error occurred while saving the article:", error);
    alert("发布失败，请重试");
  }
};
</script>

<template>
  <div class="home">
    <h2 class="text-2xl m-5">
      课程名称: <span>{{ route.query.course }}</span>
    </h2>
    <Tinymce v-model:value="val" />
    <div class="flex flex-row-reverse relative pr-4">
      <el-checkbox v-model="isAnonymous" class="text-xl absolute top-24"
        >匿名</el-checkbox
      >
      <button
        @click="saveContent"
        class="border-2 text-2xl mt-6 px-20 p-10 rounded-lg hover:opacity-80 mx-8"
      >
        发布
      </button>
    </div>
  </div>
</template>

<style scoped lang="scss"></style>
