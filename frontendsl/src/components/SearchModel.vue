<template>
  <div v-if="openModal"></div>
</template>
<script lang="ts">
import { useSearchStore } from "@/stores/search";
import { defineComponent, ref } from "vue";
export default defineComponent({
  name: "",
  components: {},
  setup() {
    const searchStore = useSearchStore()
    const openSearchContainer = ref(false)
    const openModal = ref(false)

    // watch(
    //   () => searchStore.openModal,
    //   (status: any) => {
    //     if (!(status instanceof Boolean)) {
    //       reloadRecentResult()
    //     }
    //     openModal.value = status
    //     setTimeout(() => {
    //       openSearchContainer.value = status
    //     }, 200)
    //   }
    // )

    return {
      openModal,
    };
  },
});
</script>
<style lang="scss" scoped></style>
