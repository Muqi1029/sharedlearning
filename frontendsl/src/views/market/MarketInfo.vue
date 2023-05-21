<template>
  <div>
    <h1 class="text-center title-container">学院二手市场</h1>

    <!-- flex-column when width < 1024px, grid: minmax(0,1fr) 500 otherwise -->
    <div class="market-grid">
      <div class="m-4 text-2xl p-4">
        <el-tabs v-model="activeKey" type="card" @tab-click="handleClick">
          <!-- all content -->
          <el-tab-pane label="all" :name="0">
            <div class="grid grid-cols-3 gap-4">
              <el-card
                class="h-96 relative"
                shadow="hover"
                v-for="item in markets"
                :key="item.id"
              >
                <template #header>
                  <div class="card-header">
                    <span>{{ item.name }}</span>
                    <el-button class="button" text>联系</el-button>
                  </div>
                </template>

                <img class="h-48" :src="item.profile" />

                <div class="absolute left-2 bottom-2">
                  售价：{{ item.price }} yuan
                </div>
              </el-card>
            </div>
          </el-tab-pane>

          <el-tab-pane label="生活用品" :name="1">
            <div class="grid grid-cols-3 gap-4">
              <el-card
                class="h-96 relative"
                shadow="hover"
                v-for="item in markets"
                :key="item.id"
              >
                <template #header>
                  <div class="card-header">
                    <span>{{ item.name }}</span>
                    <el-button class="button" text>联系</el-button>
                  </div>
                </template>

                <img class="h-48" :src="item.profile" />

                <div class="absolute left-2 bottom-2">
                  售价：{{ item.price }} yuan
                </div>
              </el-card>
            </div>
          </el-tab-pane>

          <el-tab-pane label="书籍课本" :name="2">
            <div class="grid grid-cols-3 gap-4">
              <el-card
                class="h-96 relative"
                shadow="hover"
                v-for="item in markets"
                :key="item.id"
              >
                <template #header>
                  <div class="card-header">
                    <span>{{ item.name }}</span>
                    <el-button class="button" text>联系</el-button>
                  </div>
                </template>

                <img class="h-48" :src="item.profile" />

                <div class="absolute left-2 bottom-2">
                  售价：{{ item.price }} yuan
                </div>
              </el-card>
            </div>
          </el-tab-pane>

          <el-tab-pane label="电子产品" :name="3">
            <div class="grid grid-cols-3 gap-4">
              <el-card
                class="h-96 relative"
                shadow="hover"
                v-for="item in markets"
                :key="item.id"
              >
                <template #header>
                  <div class="card-header">
                    <span>{{ item.name }}</span>
                    <el-button class="button" text>联系</el-button>
                  </div>
                </template>

                <img class="h-48" :src="item.profile" />

                <div class="absolute left-2 bottom-2">
                  售价：{{ item.price }} yuan
                </div>
              </el-card>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import {
  defineComponent,
  reactive,
  ref,
  toRefs,
  watch,
  watchEffect,
} from "vue";

import { getMarketByTag } from "@/api/market";

export default defineComponent({
  components: {},

  setup: function () {
    const reactiveData = reactive({
      hasMarket: true,
    });

    const markets = ref([]);

    const activeKey = ref(0);
    const handleClick = (key: string) => {
      console.log("key", key);
    };

    watch(
      activeKey,
      async (newValue) => {
        // console.log(newValue);
        const data = await getMarketByTag(newValue);
        markets.value = data.data;
        // console.log(newValue);
        // console.log(markets.value);
      },
      { immediate: true }
    );

    return {
      ...toRefs(reactiveData),
      activeKey,
      handleClick,
      markets,
    };
  },
});
</script>
<style lang="scss" scoped>
.market-grid {
  display: flex;
  flex-direction: column;
}

@media (min-width: 1024px) {
  .market-grid {
    display: grid;
    grid-template-columns: minmax(0, 1fr);
  }
}

.sidebar {
  border-left: 5px solid plum;
  height: 500px;
  background-color: aquamarine;
}

.el-tabs__item {
  font-size: 64px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.title-container {
  font-size: 48px; /* 设置字体大小 */
  color: #e5d6d6; /* 设置字体颜色 */
  text-align: center; /* 居中对齐 */
  text-transform: uppercase; /* 字母转换成大写 */
  letter-spacing: 3px; /* 设置字符间距 */
  font-family: Arial, sans-serif; /* 设置字体 */
  //text-shadow: 2px 2px 5px #9c5bef; /* 添加文字阴影 */
}
</style>
