<script setup lang="ts">
import { defineEmits } from "@vue/runtime-core";

const emits = defineEmits(["isFavHandle"]);
// eslint-disable-next-line no-undef
const props = defineProps({
  isFav: {
    type: Boolean,
    required: true,
    default: false,
  },
});

const onChangeStar = () => {
  if (!props.isFav) emits("isFavHandle", true);
  else emits("isFavHandle", false);
};
</script>

<template>
  <div>
    <img v-if="isFav" src="@/assets/star.png" @click="onChangeStar" />
    <img v-else src="@/assets/star_gray.png" @click="onChangeStar" />
  </div>
</template>

<style lang="scss" scoped>
.right {
  img:hover {
    transform: scale(1.3);
  }
}
</style>
