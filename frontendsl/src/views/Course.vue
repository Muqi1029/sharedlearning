<script setup lang="ts">
import ArticleCard from "@/components/ArticleCard/ArticleCard.vue";
import LinkCard from "@/components/LinkCard/LinkCard.vue";
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { getArticleByCourseId } from "@/api/article";
import { getCourseIntroById } from "@/api/course";
import { getLinkByCourseID } from "@/api/link";
import Header from "@/components/Header/Header.vue";

const route = useRoute();
const router = useRouter();
const courseID = parseInt(route.params.courseID as string);

const articles = ref([]);

let md = require("markdown-it")();

getArticleByCourseId(courseID).then(
  ({ data }) => {
    data.records.forEach((element: any) => {
      element.articleContent = md
        .render(element.articleContent)
        .replace(/<\/?[^>]*>/g, "")
        .replace(/[|]*\n/, "")
        .replace(/&npsp;/gi, "");
    });
    articles.value = data.records;
  },
  () => {
    alert("获取课程失败");
  }
);

const intro = ref<string>();
getCourseIntroById(courseID).then(({ data }) => {
  intro.value = data;
});

const links = ref([]);
getLinkByCourseID(courseID).then(({ data }) => {
  links.value = data;
});

const enterEditor = () => {
  router.push({
    path: "/editor",
    query: {
      course_name: courseID,
    },
  });
};
</script>

<template>
  <Header />
  <div class="course-container">
    <div
      class="course-intro m-4 p-4 text-xl bg-purple-500 text-white text-opacity-90"
    >
      <h1 class="mx-auto mb-4">课程介绍</h1>
      {{ intro }}
    </div>
    <hr class="h-16" />

    <!--  编辑文章-->
    <div class="flex justify-end">
      <button class="w-1/6 h-16 border-8 border-blue-600" @click="enterEditor">
        编辑文章
      </button>
    </div>
    <!--    <div>-->
    <!--      <el-upload-->
    <!--        action="http://localhost:8000/api/article/import/1"-->
    <!--        method="post"-->
    <!--        drag-->
    <!--      >-->
    <!--        <el-button type="primary">上传文章</el-button>-->
    <!--      </el-upload>-->
    <!--    </div>-->

    <!--  main_content  -->
    <div class="articlelist-grid mt-5 ml-4">
      <!--   article card   -->
      <ul>
        <template v-if="articles.length">
          <li v-for="article in articles" :key="article.id" class="my-4">
            <article-card :data="article" />
          </li>
        </template>
      </ul>

      <!--   aside link    -->
      <aside class="ml-10 border-l-4">
        <div class="flex flex-col">
          <h1 class="text-center">课程相关资料</h1>
          <ul>
            <li v-for="link in links" :key="link">
              <link-card :data="link" />
            </li>
          </ul>
          上传链接
        </div>

        <div class="flex flex-col">
          <h1 class="text-center">私有的链接</h1>
          <button>上传</button>
        </div>
      </aside>
    </div>
  </div>
</template>

<style scoped lang="scss">
.course-intro {
  border: 2px solid lightblue;
  box-shadow: 2px 2px 5px #888;
  border-radius: 10px;
  transition: all 0.3s ease;
  background: rgb(158, 66, 155);
  background: linear-gradient(
    90deg,
    rgba(158, 66, 155, 1) 0%,
    rgba(116, 86, 182, 1) 24%,
    rgba(0, 141, 255, 1) 90%
  );
}

.articlelist-grid {
  display: flex;
  flex-direction: column;
}

@media (min-width: 1024px) {
  .articlelist-grid {
    display: grid;
    gap: (--gap);
    grid-template-columns: 1fr 1fr;
  }
}
</style>
