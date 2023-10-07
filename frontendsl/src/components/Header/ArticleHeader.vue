<template>
  <!-- the header of article -->
  <div class="post-header mx-7 rounded-2xl bg-gray-300 p-5">
    <!-- show semester, category (?) -->
    <span class="post-labels">
      <span>{{ article.category }}</span>
      <!-- labels: #计算机 -->
      <span>
        <em class="opacity-50">#</em>
        {{ article.term }}
      </span>
    </span>

    <!-- show title of article -->
    <h1 v-if="article.articleTitle" class="post-title text-white">
      {{ article.articleTitle }}
    </h1>

    <div class="flex flex-row items-center justify-start mt-8 mb-4">
      <!--    the info of author  -->
      <div class="post-footer" v-if="article.author">
        <!-- avatar -->
        <img
          class="hover:opacity-50 cursor-pointer"
          v-lazy="article.author.avatar"
          alt="author avatar"
          @click="handleAuthorClick(article.author.website)"
        />

        <span class="text-white opacity-80">
          <!-- name -->
          <strong
            class="text-white pr-1.5 hover:opacity-50 cursor-pointer"
            @click="handleAuthorClick(article.author.website)"
          >
            {{ article.author.nickname }}
          </strong>
        </span>
      </div>
      <!-- shared time -->
      <span class="opacity-70">
        shared on
        {{ new Date(article.createTime).getFullYear() }}
        {{ new Date(article.createTime).getMonth() + 1 }}
        {{ new Date(article.createTime).getDate() }}
      </span>
      <!--   wordNum and readTime   -->
      <div class="ml-4">
        <span>
          <svg-icon icon-class="text-outline" style="stroke: white" />
          <span class="pl-2 opacity-70">
            {{ wordNum }}
          </span>
        </span>
        <!-- the read time -->
        <span class="ml-4">
          <svg-icon icon-class="clock-outline" style="stroke: white" />
          <span class="pl-2 opacity-70">
            {{ readTime }}
          </span>
        </span>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { IArticle } from "@/types/types";
import { defineComponent, toRefs } from "vue";

export default defineComponent({
  name: "ArticleHeader",
  components: {},
  props: {
    loading: {
      type: Boolean,
      required: true,
    },

    article: {
      type: Object as () => IArticle,
      required: true,
    },

    readTime: {},
    wordNum: {},
  },
  setup(props) {
    const handleAuthorClick = (link: string) => {
      if (link === "") link = window.location.href;
      window.location.href = link;
    };

    // console.log("props", props);
    return {
      ...toRefs(props),
      handleAuthorClick,
    };
  },
});
</script>
<style lang="scss" scoped>
.post-header {
  //background: rgba(0, 0, 0, 0.3);
}
</style>
