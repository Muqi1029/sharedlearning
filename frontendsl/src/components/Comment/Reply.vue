<!--
reply
-->

<template>
  <div class="flex space-x-3 xl:space-x-5">
    <img
      v-if="userStore.avatarURL"
      :class="avatarClass"
      :src="userStore.avatarURL"
    />
    <img v-else :class="avatarClass" src="@/assets/daselogo.png" alt="" />
    <div
      class="reply flex flex-col flex-wrap-reverse w-full max-w-full-calc"
      style="width: fit-content"
    >
      <textarea
        v-model="commentContent"
        class="w-full shadow-md rounded-md p-4 focus:outline-none input"
        :placeholder="initialContent"
        cols="30"
        rows="5"
      />
      <div class="justify-between" style="text-align: right">
        <button
          @click="savenewReply"
          id="submit-button"
          class="mt-5 w-16 text-white p-2 rounded-lg shadow-lg transition transform hover:scale-105 flex float-right"
        >
          <span class="text-center flex-grow commit">Reply</span>
        </button>
        <button
          @click="CancelReply"
          id="submit-button"
          class="mt-5 mr-3 w-16 text-white p-2 rounded-lg shadow-lg transition transform hover:scale-105 flex float-right"
        >
          <span class="text-center flex-grow commit">Cancel</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {
  defineComponent,
  toRefs,
  reactive,
  getCurrentInstance,
  inject,
  computed,
} from "vue";
import { useUserStore } from "@/stores/user";
import { useAppStore } from "@/stores/app";
import { useRoute } from "vue-router";
import { saveComment } from "@/api/comment";

export default defineComponent({
  name: "Reply",
  components: {},
  props: ["replyID", "initialContent"],
  setup(props, { emit }) {
    const userStore = useUserStore();
    const appStore = useAppStore();
    const route = useRoute();
    const reactiveData = reactive({
      commentContent: "" as any,
    });
    const parentId = inject("parentId");
    const savenewReply = () => {
      if (userStore.userID === "") {
        alert("请登录后评论！");
        return;
      }
      if (reactiveData.commentContent.trim() == "") {
        alert("评论内容不能为空！");
        return;
      }
      const params: any = {
        parentID: parentId,
        userID: userStore.userID,
        commentContent: reactiveData.commentContent,
        articleID: route.params.articleId,
      };
      //todo  post上传数据？
      saveComment(params).then((data: any) => {
        if (data.flag) {
          //changeshow用于隐藏评论输入框
          emit("changeShow");
          //清空输入框内容
          reactiveData.commentContent = "";
          // alert("回复成功");
        }
      });
    };
    const CancelReply = () => {
      emit("changeShow");
    };
    return {
      ...toRefs(reactiveData),
      userStore,
      avatarClass: computed(() => {
        return {
          "ob-avatar": true,
          [appStore.themeConfig.profile_shape]: true,
        };
      }),
      savenewReply,
      CancelReply,
    };
  },
});
</script>
<style lang="scss" scoped>
.reply::before {
  content: "";
  position: absolute;
  width: 0;
  height: 0;
  border-right: 8px solid var(--background-primary);
  border-top: 6px solid transparent;
  border-bottom: 6px solid transparent;
  left: -8px;
  top: 14px;
}

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
