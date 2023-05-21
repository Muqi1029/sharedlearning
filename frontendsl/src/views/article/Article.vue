<template>
  <div class="article-content-container flex flex-col">
    <!-- articleHeader -->
    <ArticleHeader
      :article="article"
      :loading="loading"
      :readTime="readTime"
      :wordNum="wordNum"
    ></ArticleHeader>

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

        <!-- if there is not article content, show skeleton -->
        <div
          v-else
          class="bg-ob-deep-800 px-14 py-16 rounded-2xl shadow-xl block min-h-screen"
        >
          <ob-skeleton
            tag="div"
            :count="1"
            height="36px"
            width="150px"
            class="mb-6"
          />
          <br />
          <ob-skeleton
            tag="div"
            :count="35"
            height="16px"
            width="100px"
            class="mr-2"
          />
          <br />
          <br />
          <ob-skeleton
            tag="div"
            :count="25"
            height="16px"
            width="100px"
            class="mr-2"
          />
        </div>

        <!-- //TODO:pre and next article card -->
        <!-- <div
          class="flex flex-col lg:flex-row justify-start items-end my-8 my-gap"
        >
          <div
            class="w-full h-full self-stretch mr-0 lg:mr-4"
            v-if="preArticleCard"
          >
            <SubTitle title="settings.paginator.pre" icon="arrow-left-circle" />
            <ArticleCard class="pre-and-next-article" :data="preArticleCard" />
          </div>
          <div class="w-full h-full self-stretch mt-0" v-if="nextArticleCard">
            <SubTitle
              title="settings.paginator.next"
              :side="!isMobile ? 'right' : 'left'"
              icon="arrow-right-circle"
            />
            <ArticleCard class="pre-and-next-article" :data="nextArticleCard" />
          </div>
        </div>
        <Comment /> -->

        <!-- show sidebar, profile,  -->
      </div>
      <div>
        <sidebar>
          <Sticky
            :stickyTop="32"
            endingElId="footer"
            dynamicElClass="#sticky-sidebar"
          >
            <div id="sticky-sidebar">
              <transition name="fade-slide-y" mode="out-in">
                <div class="sidebar-box mb-4">
                  <SubTitle :title="'titles.toc'" icon="toc" />
                  <div id="toc1"></div>
                </div>
              </transition>
              <!--              <Navigator />-->
            </div>
          </Sticky>
        </sidebar>
      </div>

      <div>
        <Comment />
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import {
  defineComponent,
  reactive,
  onMounted,
  onUnmounted,
  toRefs,
  ref,
  nextTick,
  provide,
} from "vue";
import { useRoute } from "vue-router";
import { v3ImgPreviewFn } from "v3-img-preview";
import ArticleHeader from "@/components/Header/ArticleHeader.vue";
import { useArticleStore } from "@/stores/article";
import { useCommentStore } from "@/stores/comment";
import { deleteHTMLTag } from "@/utils/html";
import Sticky from "@/components/Sticky.vue";
import tocbot from "tocbot";
import subTitle from "@/components/Title/SubTitle.vue";
import Prism from "prismjs";
import Comment from "@/components/Comment/Comment.vue";
import Sidebar from "@/components/Sidebar/Sidebar.vue";
import { toPageTop } from "@/utils/html";

export default defineComponent({
  name: "Article",
  components: {
    Sidebar,
    Comment,
    ArticleHeader,
    Sticky,
    subTitle,
  },
  setup() {
    const route = useRoute();

    const loading = ref(true);
    const articleRef = ref();

    const articleStore = useArticleStore();
    const commentStore = useCommentStore();

    const reactiveData = reactive({
      articleId: "" as any,
      article: "" as any,
      wordNum: "" as any,
      readTime: "" as any,
      // comments: [] as any,
      images: [] as any,
      preArticleCard: "" as any,
      nextArticleCard: "" as any,
      haveMore: false as any,
      isReload: false as any,
    });

    const comments = ref<any>([]);

    /**
     * 页面信息
     */
    const pageInfo = reactive({
      current: 1,
      size: 7,
    });

    /**
     * 图片预览
     * @param index
     */
    const handlePreview = (index: any) => {
      v3ImgPreviewFn({
        images: reactiveData.images,
        index: reactiveData.images.indexOf(index),
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
      // console.log(nodes);

      tocbot.init({
        tocSelector: "#toc1",
        contentSelector: ".post-html",
        headingSelector: "h1, h2, h3",
        onClick: function (e) {
          e.preventDefault();
        },
      });

      /**
       * get all img to get all picture preview
       */
      const imgs = articleRef.value.getElementsByTagName("img");
      let i;
      for (i = 0; i < imgs.length; i++) {
        reactiveData.images.push(imgs[i].src);
        imgs[i].addEventListener("click", function (e: any) {
          handlePreview(e.target.currentSrc);
        });
      }
    };

    /**
     * 获取评论
     */
    const fetchComment = async () => {
      comments.value = await commentStore.getComments(
        parseInt(route.params.articleId as string)
      );
    };

    /**
     * 注入评论
     */
    provide("comments", comments);

    onMounted(() => {
      reactiveData.articleId = route.params.articleId;

      /**
       * 根据路由的文章id信息获取文章
       */
      articleStore.getArticleById(reactiveData.articleId).then(
        (article: any) => {
          reactiveData.article = article;
          reactiveData.wordNum =
            Math.round(deleteHTMLTag(article.articleContent).length / 100) /
              10 +
            "k";
          reactiveData.readTime =
            Math.round(deleteHTMLTag(article.articleContent).length / 400) +
            "mins";
          loading.value = false;
          nextTick(() => {
            Prism.highlightAll();
            initTocbot(); /** 生成目录 */
          });
          console.log("article:", reactiveData.article);
        },
        (reason) => {
          console.log("fail to get article, reason is ", reason);
        }
      );

      toPageTop();

      fetchComment();
    });

    onUnmounted(() => {
      reactiveData.article = "";
    });

    return {
      loading,
      ...toRefs(reactiveData),
      articleRef,
      comments,
    };
  },
});
</script>
<style lang="scss" scoped>
.post-html {
  word-wrap: break-word;
  word-break: break-all;
}

.article-content-container {
  //background: url("@/assets/login.jpg") top center no-repeat;
}

.banner-img {
  background: rgb(66, 96, 158);
  background: linear-gradient(
    90deg,
    rgba(66, 96, 158, 1) 0%,
    rgba(33, 114, 221, 1) 12%,
    rgba(15, 122, 230, 1) 99%
  );
}
</style>
