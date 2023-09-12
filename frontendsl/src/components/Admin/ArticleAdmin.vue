<script setup lang="ts">
import { ref } from "vue";
import { changeArticleStatus, getPendingArticle } from "@/api/article";
import { useUserStore } from "@/stores/user";

const handlePass = (id: number) => {
  alert(`id为${id}的文章审核通过`);
  changeArticleStatus(id, 2).then((res) => {
    if (res.data) {
      alert("修改成功");
    } else {
      alert("修改失败");
    }
  });
};
const handleDelete = (id: number) => {
  alert(`id为${id}的文章被删除`);
  changeArticleStatus(id, 0);
};

let tableData = ref([]);

const userStore = useUserStore();
getPendingArticle(userStore.userAuthority).then(
  (res) => {
    tableData.value = res.data;
  },
  (err) => {
    console.log(err);
  }
);
</script>

<template>
  <div class="">
    <el-table :data="tableData">
      <el-table-column label="文章标题" prop="articleTitle"></el-table-column>
      <el-table-column label="文章内容" prop="articleContent"></el-table-column>
      <el-table-column align="right">
        <template #default="scope">
          <el-button size="small" @click="handlePass(scope.row.id)"
            >审核通过
          </el-button>
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row.id)"
            >审核不通过
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped lang="scss"></style>
