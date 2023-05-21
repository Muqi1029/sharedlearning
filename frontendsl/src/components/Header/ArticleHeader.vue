<template>
  <!-- the header of article -->
  <div class="post-header mx-7 h-97 rounded-2xl banner-img p-5">
    <!-- show semester, category (?) -->
    <span class="post-labels">
      <ob-skeleton
        v-if="loading"
        tag="b"
        height="20px"
        width="35px"
      ></ob-skeleton>
      <b v-else-if="!loading && article.category">
        <span>{{ article.category }}</span>
      </b>

      <!-- labels: #计算机 -->
      <ul>
        <ob-skeleton
          v-if="loading"
          :count="2"
          tag="li"
          height="16px"
          width="35px"
          class="mr-2"
        />
        <template v-else>
          <li>
            <em class="opacity-50">#</em>
            {{ article.term }}
          </li>
        </template>
      </ul>
    </span>

    <!-- show title of article -->
    <h1 v-if="article.articleTitle" class="post-title text-white">
      {{ article.articleTitle }}
    </h1>

    <!-- if there is not title, show skeleton -->
    <ob-skeleton
      v-else
      class="post-title text-white uppercase"
      width="100%"
      height="clamp(1.2rem, calc(1rem + 3.5vw), 4rem)"
    />

    <!-- // TODO show information of author, shared time -->
    <div class="flex flex-row items-center justify-start mt-8 mb-4">
      <!--    the info of author  -->
      <div class="post-footer" v-if="article.author">
        <!-- avater -->
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
.banner-img {
  background: rgb(86, 239, 241);
  background: linear-gradient(
    90deg,
    rgba(86, 239, 241, 1) 0%,
    rgba(33, 190, 221, 1) 15%,
    rgba(49, 15, 230, 1) 99%
  );
}
</style>
