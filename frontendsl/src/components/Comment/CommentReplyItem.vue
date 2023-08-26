<template>
  <div>
    <div class="flex space-x-3 xl:space-x-5">
      <img
        v-if="userStore.avatarURL"
        :class="avatarClass"
        :src="userStore.avatarURL"
      />
      <img v-else :class="avatarClass" src="@/assets/daselogo.png" alt="" />
      <div
        class="reply bg-white flex flex-col p-3 rounded-md relative shadow-md"
      >
        <p
          class="commentContent"
          v-html="commentContent.replaceAll('\n', '<br>')"
        />
        <div
          class="flex justify-between mt-2 text-xs text-gray-400 space-x-3 md:space-x-16"
        >
          <span> {{ comment_item.userID }} | {{ time }}</span>
          <div>
            <span @click="clickOnSonReply" class="cursor-pointer reply-button"
              >Reply</span
            >
          </div>
        </div>
      </div>
    </div>
    <a href="" target="_blank"></a>
    <CommentReplyForm
      class="mt-5"
      v-show="show"
      :initialContent="replyContent"
      :replyID="comment_item.parentId"
      @changeShow="changeShow"
    />
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, reactive, toRefs } from "vue";
import { useUserStore } from "@/stores/user";
import { useAppStore } from "@/stores/app";
import CommentReplyForm from "./CommentReplyForm.vue";

export default defineComponent({
  components: {
    CommentReplyForm,
  },
  props: ["comment_item"],
  setup(props) {
    const userStore = useUserStore();
    const appStore = useAppStore();
    const formatTime = (time: any): any => {
      let date = new Date(time);
      let year = date.getFullYear();
      let month = date.getMonth() + 1;
      let day = date.getDate();
      return year + "-" + month + "-" + day;
    };
    const reactiveData = reactive({
      replyContent: "" as any,
      show: false as any,
      time: formatTime(props.comment_item.createTime) as any,
    });
    const clickOnSonReply = () => {
      reactiveData.replyContent = "@" + props.comment_item.userID;
      reactiveData.show = true;
    };
    const changeShow = () => {
      reactiveData.show = false;
    };
    const commentContent = computed(() => {
      return (
        "回复 " + props.comment_item.parentID + ":" + props.comment_item.content
      );
    });
    return {
      ...toRefs(reactiveData),
      userStore,
      commentContent,
      clickOnSonReply,
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
<style lang="scss">
.reply-link {
  color: var(--text-accent);
}
</style>
