<script setup lang="ts">
import TinymceEditor from "@/components/tinymceEditor/tinymceEditor.vue";
import { useRoute } from "vue-router";
import { reactive, ref } from "vue";
import { useUserStore } from "@/stores/user";
import { saveArticle } from "@/api/article";
import { ArticleVO } from "@/types/types";

const route = useRoute();
const userStore = useUserStore();
const userId = parseInt(userStore.userID);
const courseId = parseInt(route.query["course_name"] as string);

const content = ref<string>("");

const articleVO = reactive<ArticleVO>({
  articleContent: "",
  userId,
  courseId,
});

const setValue = (value: string) => {
  articleVO.articleContent = value;
};

const saveContent = () => {
  alert(1);
  saveArticle(articleVO);
};
</script>

<template>
  <div>
    <div class="flex justify-around">
      <header>
        {{ route.query["course_name"] }}
      </header>
      <button
        @click="saveContent"
        class="border-8 border-blue-600 p-4 rounded-lg hover:scale-75"
      >
        发布
      </button>
    </div>
    <tinymce-editor @getContent="(value) => setValue(value)" />
    <div>课程</div>
    {{ content }}
  </div>
</template>

<style scoped lang="scss"></style>
