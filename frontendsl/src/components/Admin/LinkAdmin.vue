<script setup lang="ts">
import { ref } from "vue";
import { changeLinkStatus, getPendingLink } from "@/api/link";

const handlePass = (id: number) => {
  alert(`id为${id}的链接审核通过`);
  changeLinkStatus(id, 1).then(({ flag }) => {
    if (flag) {
      alert("修改成功");
    } else {
      alert("修改失败");
    }
    fun();
  });
};
const handleDelete = (id: number) => {
  alert(`id为${id}的链接被删除`);
  changeLinkStatus(id, 0);
  fun();
};

let tableData = ref([]);

const fun = () => {
  getPendingLink().then(
    (res) => {
      tableData.value = res.data;
    },
    (err) => {
      console.log(err);
    }
  );
};
fun();
</script>

<template>
  <div class="">
    <el-table :data="tableData">
      <el-table-column label="链接对应课程" prop="courseName"></el-table-column>
      <el-table-column label="链接地址" prop="url"></el-table-column>
      <el-table-column label="链接名称" prop="name"></el-table-column>
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
