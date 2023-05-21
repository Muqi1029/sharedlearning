<template>
  <div>
    <ul>
      <li v-for="comment in comments" :key="comment.id">
        <CommentItem :comment="comment"></CommentItem>
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { defineComponent, inject } from "vue";
import CommentItem from "./CommentItem.vue";

export default defineComponent({
  components: {
    CommentItem,
  },
  setup() {
    const comments = inject("comments");

    console.log("List", comments);

    return {
      comments,
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
