<script setup lang="ts">
import TinymceEditor from "@/components/tinymceEditor/tinymceEditor.vue";
import { useRoute, useRouter } from "vue-router";
import { reactive } from "vue";
import { useUserStore } from "@/stores/user";
import { saveArticle } from "@/api/article";
import { ArticleVO } from "@/types/types";

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();
const userId = parseInt(userStore.userID);
const courseId = parseInt(route.query["course_name"] as string);

const articleVO = reactive<ArticleVO>({
  articleContent: "",
  userId,
  courseId,
});

const setValue = (value: string) => {
  articleVO.articleContent = value;
};

const saveContent = async () => {
  const result = await saveArticle(articleVO);
  if (result.data) {
    alert("发布成功");
    await router.push({
      path: "course/" + courseId,
    });
  } else {
    alert("发布失败， 请重试");
  }
};
</script>

<template>
  <div>
    <div class="flex justify-between">
      <header>
        课程名称： {{ route.query["course_name"] }}(需要放置课程名称：TODO)
      </header>
      <button
        @click="saveContent"
        class="border-8 border-blue-600 p-4 rounded-lg hover:opacity-80 mr-8"
      >
        发布
      </button>
    </div>
    <tinymce-editor @getContent="setValue" />
    <div>课程</div>
  </div>
</template>

<style scoped lang="scss"></style>
