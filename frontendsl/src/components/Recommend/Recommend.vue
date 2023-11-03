<script setup lang="ts">
import Title from "@/components/Title/Title.vue";
import { ref } from "vue";
import { getFeatureArticles } from "@/api/article";
import UpLoadLink from "@/components/UpLoadLink/UpLoadLink.vue";

const featuresItems = ref([]);

getFeatureArticles().then(({ data }) => {
  featuresItems.value = data;
});
</script>

<template>
  <div class="">
    <div class="flex justify-between">
      <Title title="Recommendation" icon="hot" />
      <UpLoadLink name="Your contribution here!!!" />
    </div>

    <el-carousel :interval="5000" type="card" height="300px">
      <el-carousel-item v-for="item in featuresItems" :key="item">
        <div
          :style="{
            background: 'url(' + item.articleCover + ')',
            backgroundSize: 'cover',
          }"
        >
          <a :href="item.articleContent" target="_blank">
            <h3>{{ item.articleTitle }}</h3>
          </a>
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<style scoped lang="scss">
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 350px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.el-carousel__item {
  border: 2px solid #f5c2c2;
}
</style>
