<template>
  <div class="right absolute -right-1.5 top-0">
    <img v-if="isFav" src="@/assets/star.png" @click="onChangeStar" />
    <img v-else src="@/assets/star_gray.png" @click="onChangeStar" />
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";

export default defineComponent({
  name: "Favorites",
  emits: ["isFavHandle"],
  props: {
    isFav: {
      type: Boolean,
      required: true,
    },
  },
  components: {},
  setup(props, { emit }) {
    const onChangeStar = () => {
      if (!props.isFav) emit("isFavHandle", true);
      else emit("isFavHandle", false);
    };
    return {
      onChangeStar,
    };
  },
});
</script>

<style lang="scss" scoped>
.right {
  img:hover {
    transform: scale(1.3);
  }
}
</style>
