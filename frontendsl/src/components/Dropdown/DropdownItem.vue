<template>
  <div
    @click.stop.prevent="handleClick"
    class="drop-item block cursor-pointer my-1 px-4 py-1 font-medium"
  >
    <slot />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useDropdownStore } from "@/stores/dropdown";

export default defineComponent({
  name: "DropdownItem",
  props: {
    name: String,
  },
  setup(props) {
    const dropdownStore = useDropdownStore();
    const handleClick = () => {
      dropdownStore.setCommand(String(props.name));
    };
    return { handleClick };
  },
});
</script>

<style lang="scss">
.drop-item {
  &:hover {
    color: #0fb6d6;
  }
}
</style>
