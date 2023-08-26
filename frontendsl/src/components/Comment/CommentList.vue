<template>
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
</template>

<script lang="ts">
import { defineComponent, inject, ref, reactive, toRefs } from "vue";
import CommentItem from "./CommentItem.vue";
import { saveComment, getComments } from "@/api/comment";

export default defineComponent({
  components: {
    CommentItem,
  },
  setup() {
    const reactiveData = reactive({
      comments: [] as any,
    });
    const articleId = inject("articleId");
    console.log("articleId is", articleId);
    getComments(articleId).then((res) => {
      console.log(res);
      reactiveData.comments = res.data;
      console.log("List", reactiveData.comments);
    });

    return {
      ...toRefs(reactiveData),
    };
  },
});
</script>
<style lang="scss" scoped>
.load-more-button {
  outline: none;
  background: var(--main-gradient);
}
</style>
