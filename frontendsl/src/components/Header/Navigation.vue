<script lang="ts" setup>
import { useRouter } from "vue-router";
import { isExternal } from "@/utils/validate";
import { routes } from "@/config/config";

const router = useRouter();
const pushPage = (path: string): void => {
  if (!path) return;
  if (isExternal(path)) {
    window.location.href = path;
  } else {
    router.push({
      path,
    });
  }
};
</script>

<template>
  <nav class="items-center flex-1 lg:flex">
    <ul class="flex flex-row list-none px-2 text-white">
      <li
        class="font-medium text-xs h-full relative flex flex-col items-center justify-center cursor-pointer text-center py-4 px-2"
        v-for="route in routes"
        :key="route.path"
      >
        <div
          class="nav-link hover:opacity-50 text-xl px-1.5 py-0.5 rounded-md uppercase cursor-pointer"
          @click="pushPage(route.path)"
          v-if="route.children && route.children.length === 0"
          :data-menu="route.name"
        >
          <span class="z-50">{{ route.name }}</span>
        </div>
      </li>
    </ul>
  </nav>
</template>

<style lang="scss" scoped>
.nav-link {
  @apply hover:text-ob-bright;
  &:hover {
    &:before {
      @apply opacity-60;
    }
  }

  &:before {
    @apply absolute rounded-lg opacity-0 transition bg-ob-deep-800 z-40;
    content: "";
    top: -4px;
    left: -4px;
    width: calc(100% + 8px);
    height: calc(100% + 8px);
  }
}
</style>
