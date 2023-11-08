<script setup lang="ts">
import { ref } from "vue";
import { getAllUsers, changeUser } from "@/api/user";

const usersData = ref([]);

const fun = () => {
  getAllUsers().then(
    (res) => {
      usersData.value = res.data;
    },
    (err) => {
      console.log(err);
    }
  );
};

const ban = (id: number) => {
  changeUser(id, 1).then(
    ({ flag }) => {
      if (flag) {
        alert("操作成功");
      } else {
        alert("操作失败");
      }
      fun();
    },
    () => {
      alert("操作失败");
    }
  );
};

fun();
</script>

<template>
  <div>
    <!--    {{ usersData }}-->
    <el-table :data="usersData">
      <el-table-column label="用户名" prop="userName"></el-table-column>
      <el-table-column align="right">
        <template #default="scope">
          <el-button size="small" @click="ban(scope.row.id)">封禁</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped lang="scss"></style>
