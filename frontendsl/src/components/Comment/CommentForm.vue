<template>
  <div class="flex space-x-3 xl:space-x-5">
    <!-- 头像 -->
    <img
      v-if="userStore.avatarURL"
      :class="avatarClass"
      :src="userStore.avatarURL"
    />
    <img v-else :class="avatarClass" src="@/assets/daselogo.png" alt="" />
    <!-- 评论输入框 -->
    <div class="comment flex flex-col flex-wrap-reverse w-full max-w-full-calc">
      <textarea
        v-model="commentContent"
        class="w-full shadow-md rounded-md p-4 focus:outline-none input"
        placeholder="快发表你的评论吧..."
        cols="30"
        rows="5"
      />
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
</template>
<script lang="ts">
import { defineComponent, toRefs, reactive, computed } from "vue";
import SubTitle from "@/components/Title/SubTitle.vue";
import { useUserStore } from "@/stores/user";
import { useRoute } from "vue-router";
import { useCommentStore } from "@/stores/comment";
import { useAppStore } from "@/stores/app";
import { saveComment } from "@/api/comment";

export default defineComponent({
  name: "CommentItem",
  components: {},
  setup() {
    const userStore = useUserStore();
    const commentStore = useCommentStore();
    const appStore = useAppStore();
    const route = useRoute();
    const reactiveData = reactive({
      commentContent: "" as any,
    });
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
      //todo  post上传数据？
      // saveComment(params).then(({ data }: { data: any }) => {
      //   //清空输入框
      //   reactiveData.commentContent = "";
      // });

      const data = await saveComment(params);
      if ((data as any).flag) {
        reactiveData.commentContent = "";
        alert("回复成功");

      }
      // console.log(data);
    };

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
