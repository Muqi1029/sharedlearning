<template>
  <div class="header-container w-full my-0">
    <header class="site-header relative flex z-50 w-full mt-1 text-xs justify-between">
      <logo></logo>
      <navigation></navigation>
      <controls></controls>
    </header>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";

import Logo from "./Logo.vue";
import Navigation from "./Navigation.vue";
import Controls from "./Controls.vue";

export default defineComponent({
  name: "Header",
  components: { Logo, Navigation, Controls },

  setup() {
    return {};
  },
});
</script>
<style lang="scss" scoped>
.header-container {
 /*  background-color: rgba($color: #130c0c, $alpha: 0.2); */
  .site-header {
    /**
        max-width: max-width 属性用来给元素设置最大宽度值。定义了 max-width 的元素会在达到 max-width 值之后避免进一步按照 width 属性设置变大。

        var: in root, define a property we design 
             in var, we use the value of this property
        */
    /*max-width: var(--max-width);*/
  }
}


</style>
