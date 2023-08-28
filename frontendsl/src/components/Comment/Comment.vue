<template>
  <div
    class="bg-ob-deep-800 p-4 mt-8 lg:px-14 lg:py-10 rounded-2xl shadow-xl mb-8 lg:mb-0"
    id="comments"
  >
    <SubTitle :title="'评论'" />
    <div class="flex space-x-3 xl:space-x-5">
      <!-- 头像 -->
      <img
        v-if="userStore.avatarURL"
        :class="avatarClass"
        :src="userStore.avatarURL"
      />
      <img v-else :class="avatarClass" src="@/assets/daselogo.png" alt="" />
      <!-- 评论输入框 -->
      <div
        class="comment flex flex-col flex-wrap-reverse w-full max-w-full-calc"
      >
        <div>
          <tinymce-editor v-model:modelValue="commentContent"></tinymce-editor>
        </div>
        <!-- 发表按钮 -->
        <div class="justify-between" style="text-align: right">
          <button
            @click="savenewComment"
            id="submit-button"
            class="mt-5 w-32 text-white p-2 rounded-lg shadow-lg transition transform hover:scale-105 flex float-right"
          >
            <span class="text-center flex-grow commit">Add Comment</span>
          </button>
        </div>
        <div class="w-full border-b-2 mt-6 wire"></div>
      </div>
    </div>
    <div>
      <ul>
        <li
          v-for="comment in comments"
          :key="comment.id"
          v-show="comment.parentID == 0"
        >
          <CommentItem :comment="comment"></CommentItem>
        </li>
      </ul>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, toRefs, reactive, computed, inject } from "vue";
import { useUserStore } from "@/stores/user";
import { useRoute } from "vue-router";
import { useAppStore } from "@/stores/app";
import { saveComment, getComments } from "@/api/comment";
import tinymceEditor from "@/components/tinymceEditor/tinymceEditor.vue";
import CommentItem from "./CommentItem.vue";
import SubTitle from "@/components/Title/SubTitle.vue";

export default defineComponent({
  name: "Comment",
  components: {
    tinymceEditor,
    CommentItem,
    SubTitle,
  },
  setup() {
    const userStore = useUserStore();
    const appStore = useAppStore();
    const route = useRoute();
    const reactiveData = reactive({
      commentContent: "" as any,
      comments: [] as any,
    });
    const articleId = inject("articleId");
    const savenewComment = async () => {
      if (userStore.userID === "") {
        alert("请登录后评论！");
        return;
      }
      if (reactiveData.commentContent.trim() == "") {
        alert("评论内容不能为空！");
        return;
      }
      //传输参数包括了评论内容以及文章id以及评论时间
      const params: any = {
        commentContent: reactiveData.commentContent,
        articleID: route.params.articleId,
        userID: userStore.userID,
      };
      const data = await saveComment(params);
      if ((data as any).flag) {
        reactiveData.commentContent = "";
        alert("回复成功");
      }
      // console.log(data);
    };
    getComments(articleId).then((res) => {
      console.log(res);
      reactiveData.comments = res.data;
      console.log("List", reactiveData.comments);
    });

    return {
      userStore,
      avatarClass: computed(() => {
        return {
          "ob-avatar": true,
          [appStore.themeConfig.profile_shape]: true,
        };
      }),
      ...toRefs(reactiveData),
      savenewComment,
    };
  },
});
</script>

<style lang="scss" scoped>
.input {
  background: var(--background-primary);
  resize: none;
}

#submit-button {
  outline: none;
  background: var(--main-gradient);
}

.wire {
  border-color: var(--text-normal);
}
</style>
