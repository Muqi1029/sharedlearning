<template>
  <nav class="items-center flex-1 lg:flex bg-blue-400">
    <ul class="flex flex-row list-none px-2 text-white">
      <li
        class="not-italic font-medium text-xs h-full relative flex flex-col items-center justify-center cursor-pointer text-center py-4 px-2"
        v-for="route in routes"
        :key="route.path"
      >
        <div
          class="nav-link hover:opacity-50 text-xl block px-1.5 py-0.5 rounded-md relative uppercase cursor-pointer"
          @click="pushPage(route.path)"
          v-if="route.children && route.children.length === 0"
          :data-menu="route.name"
        >
          <span class="relative z-50">{{ route.name }}</span>
        </div>
      </li>

      <li
        class="not-italic font-medium text-xs h-full flex flex-col items-center justify-center cursor-pointer text-center py-4 px-2"
      >
        <dropdown
          hover
          class="nav-link text-xl rounded-md block uppercase px-1.5 py-0.5 cursor-pointer hover:opacity-50"
        >
          <span class="relative z-50">Switch</span>

          <dropdown-menu>
            <dropdown-item
              ><span class="relative z-50">学院1</span></dropdown-item
            >
            <dropdown-item>
              <span class="relative z-50">学院2</span>
            </dropdown-item>
          </dropdown-menu>
        </dropdown>
      </li>
    </ul>
  </nav>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useRouter } from "vue-router";
import { isExternal } from "@/utils/validate";

import config from "@/config/config";
import Dropdown from "../Dropdown/Dropdown.vue";
import DropdownMenu from "../Dropdown/DropdownMenu.vue";
import DropdownItem from "../Dropdown/DropdownItem.vue";

export default defineComponent({
  components: { Dropdown, DropdownMenu, DropdownItem },
  name: "Navigation",

  setup() {
    const router = useRouter();

    // push page
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

    return {
      routes: config.routes, // the route in config
      pushPage,
    };
  },
});
</script>

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
