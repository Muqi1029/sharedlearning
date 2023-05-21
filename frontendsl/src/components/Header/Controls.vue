<template>
  <div class="header-controls absolute right-4 flex flex-row top-7" tabindex="0">
  
      <!-- search-icon -->
      <span class="hover:opacity-50" data-dia="search" @click="handleOpenModel">
        <svg-icon icon-class="search"></svg-icon>
      </span>

      <template></template>

      <span>
        <div class="text-xl text-center hover:opacity-50" @click="handleLogout">LOGOUT</div>
      </span>
  </div>
</template>
<script lang="ts">
import { useRouter } from "vue-router";
import { defineComponent } from "vue";
import { removeToken } from "@/utils/auth";
import { useSearchStore } from "@/stores/search";

export default defineComponent({
  components: {},

  setup(props, context) {

    const router = useRouter()
    const searchStore = useSearchStore()

    const handleOpenModel:any = (status: boolean) => {
      searchStore.setOpenModel(status)
    }

    const handleLogout = () => {
      removeToken()

      router.push({
        path: '/login'
      })
    }
    return {
      handleLogout,
      handleOpenModel
    };
  },
});
</script>
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
    margin-right: 0.5rem;
    pointer-events: none;
  }
}
</style>
