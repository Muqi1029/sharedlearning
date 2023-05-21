<template>
  <div :style="gradientBackground">
    <div>
      <div>
        <div class="flex flex-col justify-center items-center">
          <img
            v-if="userStore.avatarURL"
            :class="avatarClass"
            class="rounded-full"
            :src="userStore.avatarURL"
          />
          <img v-else :class="avatarClass" src="@/assets/daselogo.png" alt="" />
          <!-- user name -->
          <h2 class="text-center font-bold text-2xl italic">
            <template v-if="userStore.name">
              {{ userStore.name }}
            </template>
            <ob-skeleton v-else height="2.25rem" width="7rem"></ob-skeleton>
          </h2>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useAppStore } from "@/stores/app";
import { useUserStore } from "@/stores/user";
import { defineComponent, computed } from "vue";

export default defineComponent({
  name: "Profile",
  components: {},
  setup() {
    const appStore = useAppStore();
    const userStore = useUserStore();

    return {
      gradientBackground: computed(() => {
        return {
          background: appStore.themeConfig.header_gradient_css,
        };
      }),
      userStore,
      avatarClass: computed(() => {
        return {
          "ob-avatar": true,
          [appStore.themeConfig.profile_shape]: true,
        };
      }),
      logo: computed(() => {
        return appStore.websiteConfig.logo;
      }),
    };
  },
});
</script>
<style lang="scss" scoped></style>
