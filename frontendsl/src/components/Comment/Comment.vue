<template>
  <div
    class="bg-ob-deep-800 p-4 mt-8 lg:px-14 lg:py-10 rounded-2xl shadow-xl mb-8 lg:mb-0"
    id="comments"
  >
    <SubTitle :title="'评论'" />
    <CommentForm />
    <CommentList :comments="comments" />
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import SubTitle from "@/components/Title/SubTitle.vue";
import CommentForm from "./CommentForm.vue";
import CommentList from "./CommentList.vue";

export default defineComponent({
  name: "Comment",
  components: { SubTitle, CommentForm, CommentList },
  props: ["comments"],
  setup() {
    return {};
  },
});
</script>
