<template>
  <div class="market-card bg-gray-400 list-none rounded-2xl p-2">
    <!-- {{ data.id }} - {{ data.name }} - {{ data.price }} -->

    <!-- the image of market -->
    <div class="market-thumbnail relative bg-cover bg-no-repeat">
      <img v-if="data.marketCover" v-lazy="data.marketCover" alt="" />
      <img
        v-else
        class="rounded-2xl block z-20 w-full h-12/10"
        src="@/assets/default-cover.jpg"
      />
      <!-- <span class="z-30 absolute w-full h-12/10 left-0 opacity-40 bg-black row-span-1 "></span> -->
    </div>

    <!-- intro of the market -->
    <div>{{ data.name }} - {{ data.price }}元</div>

    <!-- the information of seller -->
    <div
      class="market-footer flex flex-row justify-between items-center text-xl"
    >
      <div class="flex flex-row items-center">
        卖家姓名
        <img
          src="@/assets/default-cover.jpg"
          class="hover:opacity-50 cursor-pointer h-7 w-7 rounded-full mr-2"
        />
      </div>

      <div class="cursor-pointer hover:opacity-50" @click="">联系</div>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, toRefs } from "vue";
import { IMarketCard } from "@/types/types";

export default defineComponent({
  name: "MarketCard",
  components: {},

  props: {
    data: {
      required: true,
      type: Object as () => IMarketCard,
    },
  },

  setup(props) {
    return {
      ...toRefs(props).data,
    };
  },
});
</script>
<style lang="scss" scoped>
.market-card {
  transition: all 0.5s;

  &:hover {
    transform: scale(1.015);
  }
}
</style>
