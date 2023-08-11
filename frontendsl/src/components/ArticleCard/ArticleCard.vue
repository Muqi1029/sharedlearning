<template>
  <div class="article-container cursor-pointer">
    <div class="article">
      <!-- image of article card -->
      <div class="article-thumbnail" @click="toArticle">
        <img v-if="data.articleCover" v-lazy="data.articleCover" alt="文章图片"/>
        <img v-else src="@/assets/default-cover.jpg" />
        <span class="thumbnail-screen" :style="gradientBackground"></span>
      </div>

      <div class="article-content">
        <p class="pt-7" @click="toArticle"><div class="text-2xl"> {{ data.articleTitle }} </div>{{ data.articleContent }}</p>

        <div class="article-footer flex flex-row justify-between mt-3">
          <div class="flex flex-row items-center">
            <img
              class="hover:opacity-50 cursor-pointer"
              :src="data.userInfo.avatarURL"
              alt="author avatar"
            />
            <span class="text-ob-dim">
              <strong
                class="text-ob-normal pr-1.5 hover:text-ob hover:opacity-50 cursor-pointer"
              >
                {{ data.userInfo.userName }}
              </strong>
            </span>
          </div>

          <div class="justify-end flex mb-1 text-lg">
            <span class="pr-2.5"
              ><button @click="" class="hover:opacity-50">点赞：</button
              >{{ data.thumbUpCount }}</span
            >
            <span class="pr-2.5"
              ><button @click="" class="hover:opacity-50">评论：</button
              >{{ data.commentCount }}</span
            >
            <span class="pr-2.5"
              ><button @click="" class="hover:opacity-50">打赏：</button
              >{{ data.rewardCount }}</span
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed } from "vue";

import { useRouter } from "vue-router";
import { useAppStore } from "@/stores/app";

export default defineComponent({
  name: "",
  components: {},

  props: {
    data: {
      required: true,
      type: Object as any,
      // type: Object as () => IArticle,
    },
  },

  setup(props) {
    const router = useRouter();
    const appStore = useAppStore();


    const toArticle = () => {
      router.push({
        path: "/article/" + props.data.id,
      });
    };

    const gradientBackground = computed(
      () => appStore.themeConfig.header_gradient_css
    )

    return {
      toArticle,
      gradientBackground,
    };
  },
});
</script>

<style lang="scss" scoped>
.article-content {
  p {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 5;
    -webkit-box-orient: vertical;
  }
}
</style>
