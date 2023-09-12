<script lang="ts" setup>
import { useRouter } from "vue-router";
import { removeToken } from "@/utils/auth";

const router = useRouter();

const handleLogout = () => {
  removeToken();
  router.push({
    path: "/login",
  });
};

const showClick = () => {
  alert("亲，我暂时还不支持搜索功能噢");
};
</script>

<template>
  <span
    class="header-controls absolute right-4 flex flex-row top-7"
    tabindex="0"
  >
    <!-- search-icon -->
    <span class="hover:opacity-50 mr-1" @click="showClick">
      <svg-icon icon-class="search"></svg-icon>
    </span>
    <span>
      <span class="text-xl text-center hover:opacity-50" @click="handleLogout">
        LOGOUT
      </span>
    </span>
  </span>
</template>

<style lang="scss" scoped>
.header-controls {
  span {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    color: #fff;
    cursor: pointer;
    transition: opacity 250ms ease;
    padding-right: 0.5rem;
  }

  .svg-icon {
    stroke: #fff;
    height: 2rem;
    width: 2rem;
  }
}
</style>
