<script lang="ts" setup>
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();
</script>
<template>
  <div>个人账号信息的组件</div>
  <div>{{ userStore }}</div>
</template>

<style lang="scss" scoped></style>
