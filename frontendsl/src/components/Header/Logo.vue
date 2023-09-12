<script lang="ts" setup>
import { computed } from "@vue/reactivity";
import { useRouter } from "vue-router";
import { useCommonStore } from "@/stores/common";
import { useAppStore } from "@/stores/app";
import { useNavigatorStore } from "@/stores/navigator";

const appStore = useAppStore();
const commonStore = useCommonStore();
const navigatorStore = useNavigatorStore();

const router = useRouter();

const handleClick = () => {
  router.push({
    name: "home",
  });
  if (commonStore.isMobile && navigatorStore.openMenu === true) {
    navigatorStore.toggleMobileMenu();
  }
};

const websiteConfig = computed(() => {
  return appStore.websiteConfig;
});
</script>

<template>
  <!-- 
    align-self:对齐当前 grid 或 flex 行中的元素，并覆盖已有的 align-items 的值
   -->

  <div class="flex items-start self-stretch relative" @click="handleClick">
    <div
      class="flex flex-col relative py-4 z-10 text-white text-opacity-85 font-medium cursor-pointer"
    >
      <!-- the loading of name -->
      <span class="text-3xl" v-if="websiteConfig.name">
        {{ websiteConfig.name }}
      </span>
      <span v-else class="text-3xl animation-text">LOADING</span>

      <!-- EnglishName -->
      <span class="font-bold text-xs uppercase">
        {{ websiteConfig.englishName || "Website" }}
      </span>
    </div>

    <!-- website logo -->
    <img
      class="logo-image h-full absolute left-0 top-0 opacity-25"
      :src="websiteConfig.logo"
      alt="site-logo"
    />
  </div>
</template>

<style lang="scss" scoped>
.logo-image {
  width: 125px;
  max-width: 125px;
}
</style>
