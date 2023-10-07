<script setup lang="ts">
import {onUnmounted, ref, nextTick, provide} from "vue";
import { useRoute } from "vue-router";
import { v3ImgPreviewFn } from "v3-img-preview";
import { deleteHTMLTag } from "@/utils/html";
import tocbot from "tocbot";
import Prism from "prismjs";
import { toPageTop } from "@/utils/html";
import { getArticleById } from "@/api/article";
import markdownToHtml from "@/utils/markdown";
import ArticleHeader from "@/components/Header/ArticleHeader.vue";
import Sticky from "@/components/Sticky.vue";
import Comment from "@/components/Comment/Comment.vue";
import Sidebar from "@/components/Sidebar/Sidebar.vue";

const route = useRoute();
const loading = ref(true);
const articleRef = ref();
const comments = ref<Array<any>>([]);
const images = ref([]);

const handlePreview = (index: number) => {
  v3ImgPreviewFn({
    images: images.value,
    index: images.value.indexOf(index),
  });
};

/**
 * 文章目录渲染
 */
const initTocbot = () => {
  let nodes = articleRef.value.children;
  if (nodes.length) {
    for (let i = 0; i < nodes.length; i++) {
      let node = nodes[i];
      let reg = /^H[1-4]{1}$/;
      if (reg.exec(node.tagName)) {
        node.id = i;
      }
    }
  }
  tocbot.init({
    tocSelector: "#toc1",
    contentSelector: ".post-html",
    headingSelector: "h1, h2, h3",
    onClick: function (e) {
      e.preventDefault();
    },
  });

  // get all img to get all picture preview
  const imgs = articleRef.value.getElementsByTagName("img");
  let i;
  for (i = 0; i < imgs.length; i++) {
    images.value.push(imgs[i].src);
    imgs[i].addEventListener("click", function (e: any) {
      handlePreview(e.target.currentSrc);
    });
  }
};

const article = ref();
const articleId = ref();
const wordNum = ref();
const readTime = ref();

articleId.value = route.params.articleId;
provide("articleId", articleId.value);
getArticleById(articleId.value).then(
  ({ data }) => {
    console.log(data);
    data.articleContent = markdownToHtml(data.articleContent);
    article.value = data;
    wordNum.value =
      Math.round(deleteHTMLTag(data.articleContent).length / 100) / 10 + "k";
    readTime.value =
      Math.round(deleteHTMLTag(data.articleContent).length / 400) + "mins";
    loading.value = false;
    nextTick(() => {
      Prism.highlightAll();
      initTocbot(); /** 生成目录 */
    });
  },
  (reason) => {
    console.log("fail to get article, reason is ", reason);
  }
);
toPageTop();

onUnmounted(() => {
  article.value = "";
});
</script>

<template>
  <div class="article-content-container flex flex-col">
    <!-- articleHeader -->
    <ArticleHeader
      :article="article"
      :loading="loading"
      :readTime="readTime"
      :wordNum="wordNum"
    />

    <hr />
    <!-- show the content of article -->
    <div class="main-grid mt-4">
      <div>
        <!-- show the article content -->
        <template v-if="article.articleContent">
          <div
            class="post-html"
            ref="articleRef"
            v-html="article.articleContent"
          />
        </template>
      </div>

      <sidebar>
        <Sticky
          :stickyTop="32"
          endingElId="footer"
          dynamicElClass="#sticky-sidebar"
        >
          <div id="sticky-sidebar">
            <transition name="fade-slide-y" mode="out-in">
              <div class="sidebar-box mb-4">
                <!--                <SubTitle :title="'titles.toc'" icon="toc" />-->
                <div id="toc1"></div>
              </div>
            </transition>
          </div>
        </Sticky>
      </sidebar>

      <Comment />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.post-html {
  word-wrap: break-word;
  word-break: break-all;
}
</style>
