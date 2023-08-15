<script setup lang="ts">
import { IArticle } from "@/types/types";
import { getPendingArticle } from "@/api/article";

const handlePass = (id: number) => {
  // invoke api
  alert(`id为${id}的文章审核通过`);
};

const handleDelete = (id: number) => {
  alert(`id为${id}的文章被删除`);
};

let tableData: IArticle[] = [
  { id: 1, articleTitle: "hello", articleContent: "world" },
];

getPendingArticle().then(
  (res) => {
    tableData = res.data;
  },
  (err) => {
    console.log(err);
  }
);
</script>

<template>
  <div class="admin-container">
    <aside>
      <div>文章</div>
      <div>用户</div>
    </aside>

    <div>
      <el-table :data="tableData">
        <el-table-column label="文章标题" prop="articleTitle"></el-table-column>
        <el-table-column
          label="文章内容"
          prop="articleContent"
        ></el-table-column>
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
  </div>
</template>

<style scoped lang="scss">
.admin-container {
  display: flex;
  flex-direction: row;

  aside {
    background: #0fb6d6;
    flex: 1;

    div {
      display: flex;
      flex-direction: column;
    }
  }

  div {
    flex: 5;
    width: 100%;
  }
}
</style>
