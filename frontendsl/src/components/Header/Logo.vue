<template>
  <!-- 
    align-self:对齐当前 grid 或 flex 行中的元素，并覆盖已有的 align-items 的值
   -->

  <div class="ml-4">
    <div class="flex items-start self-stretch relative" @click="handleClick">
      <div
        class="flex flex-col relative py-4 z-10 text-white text-opacity-85 font-medium ob-drop-shadow cursor-pointer"
      >
        <!-- the loading of name -->
        <span class="text-3xl" v-if="websiteConfig.name">
          {{ websiteConfig.name }}
        </span>
        <span v-else class="flex text-3xl animation-text">LOADING</span>

        <!-- EnglishName -->
        <span class="font-extrabold text-xs uppercase">
          {{ websiteConfig.englishName || "Website" }}
        </span>
      </div>

      <!-- website logo -->
      <img
        class="logo-image h-full absolute"
        :src="websiteConfig.logo"
        alt="site-logo"
      />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

import { computed } from "@vue/reactivity";
import { useRouter } from "vue-router";

import { useCommonStore } from "@/stores/common";
import { useAppStore } from "@/stores/app";
import { useNavigatorStore } from "@/stores/navigator";

export default defineComponent({
  name: "Logo",

  setup() {
    /* ----------------stores------------------- */

    const appStore = useAppStore();
    const commonStore = useCommonStore();
    const navigatorStore = useNavigatorStore();

    /* -----------------router--------------------- */
    /**
     * if click logo(into home)
     * */
    const router = useRouter();

    const handleClick = () => {
      router.push({
        name: "home",
      });
      if (commonStore.isMobile && navigatorStore.openMenu === true) {
        navigatorStore.toggleMobileMenu();
      }
    };

    return {
      handleClick,
      websiteConfig: computed(() => {
        return appStore.websiteConfig;
      }),
    };
  },
});
</script>

<style lang="scss" scoped>
.logo-image {
  width: 125px;
  max-width: 125px;
  top: 0px;
  left: 0px;
  opacity: 0.25;
}
</style>
