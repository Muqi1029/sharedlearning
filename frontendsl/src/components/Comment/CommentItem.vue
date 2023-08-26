<template>
  <div class="mt-5 max-w-full">
    <div class="flex space-x-3 xl:space-x-5">
      <img
        v-if="comment.userAvatar"
        :class="avatarClass"
        :src="comment.userAvatar"
      />
      <img v-else :class="avatarClass" src="@/assets/daselogo.png" alt="" />
      <div class="max-w-full-calc space-y-5">
        <div
          class="bg-white text-primary p-4 rounded-md relative shadow-md reply"
          style="width: fit-content"
        >
          <p class="commentContent" v-html="comment.content" />
          <div
            class="flex justify-between mt-3 text-xs text-gray-400 space-x-3 md:space-x-16"
          >
            <span>{{ comment.userID }} | {{ time }}</span>
            <div>
              <span @click="clickOnReply" class="cursor-pointer reply-button"
                >Reply</span
              >
            </div>
          </div>
        </div>

        <CommentReplyForm
          v-show="show"
          :replyID="comment.id"
          :initialContent="replyContent"
          @changeShow="changeShow"
        />

        <transition-group name="fade">
          <ul>
            <li
              v-for="comment_item in comments"
              :key="comment_item.id"
              v-show="comment_item.parentID == comment.id"
            >
              <CommentReplyItem :comment_item="comment_item" />
            </li>
          </ul>
        </transition-group>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {
  defineComponent,
  reactive,
  toRefs,
  provide,
  computed,
  inject,
} from "vue";
import CommentReplyItem from "./CommentReplyItem.vue";
import CommentReplyForm from "./CommentReplyForm.vue";
import { useAppStore } from "@/stores/app";
import { saveComment, getComments } from "@/api/comment";

export default defineComponent({
  components: {
    CommentReplyItem,
    CommentReplyForm,
  },
  props: {
    comment: {
      required: true,
      type: Object as any,
    },
  },
  setup(props) {
    const appStore = useAppStore();
    const articleId = inject("articleId");
    const formatTime = (time: any): any => {
      let date = new Date(time);
      let year = date.getFullYear();
      let month = date.getMonth() + 1;
      let day = date.getDate();
      return year + "-" + month + "-" + day;
    };
    const reactiveData = reactive({
      comments: [] as any,
      replyContent: "" as any,
      time: formatTime(props.comment.createTime) as any,
      show: false as any,
    });
    getComments(articleId).then((res) => {
      console.log(res);
      reactiveData.comments = res.data;
      console.log("List1", reactiveData.comments);
    });
    //提供给子组件CommentReplyItem  作为其parentId  作用于CommentReplyForm
    provide("parentId", props.comment.id);

    const changeShow = () => {
      reactiveData.show = false;
    };
    const clickOnReply = () => {
      reactiveData.replyContent = "add reply...";
      reactiveData.show = true;
    };
    return {
      ...toRefs(reactiveData),
      clickOnReply,
      changeShow,
      avatarClass: computed(() => {
        return {
          "ob-avatar": true,
          [appStore.themeConfig.profile_shape]: true,
        };
      }),
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

.reply {
  background: var(--background-primary);
}

.reply-button {
  color: var(--text-accent);
}

.commentContent {
  line-height: 26px;
  white-space: pre-line;
  word-wrap: break-word;
  word-break: break-all;
}
</style>
