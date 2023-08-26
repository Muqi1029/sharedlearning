<script setup lang="ts">
import { reactive, ref } from "vue";
import { useUserStore } from "@/stores/user";
import type { FormInstance, FormRules } from "element-plus";
import { useRouter } from "vue-router";

const loginForm = reactive({
  userName: "",
  password: "",
});

const checkPwd = ref(false);

const loading = ref<boolean>(false);

const loginRules = reactive<FormRules>({
  userName: [
    {
      required: true,
      message: "用户名不能为空",
      trigger: "blur",
    },
  ],
  password: [
    {
      required: true,
      message: "密码不能为空",
      trigger: "blur",
    },
  ],
});

let loginFormRef = ref<FormInstance>();

const userStore = useUserStore();
const router = useRouter();

const handleLogin = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      loading.value = true;
      userStore
        .userLogin(loginForm)
        .then((res: number) => {
          let path = res === 0 ? "/home" : "/admin";
          router.push({ path });
        })
        .catch(() => {
          loading.value = false;
          loginForm.userName = "";
          loginForm.password = "";
        });
    } else {
      alert("登录失败");
    }
  });
};

const rememberPwd = (isChecked: boolean) => {
  checkPwd.value = isChecked;
  if (checkPwd.value) {
    localStorage.setItem("pwd", JSON.stringify(loginForm));
  } else {
    localStorage.removeItem("pwd");
  }
};
const forgetPwd = () => {
  alert("please to ask 10215501421 for help");
};
</script>

<template>
  <div class="m-40 mx-auto w-1/4">
    <h3 class="loginTitle text-center text-2xl font-bold shadow-md">
      Sharedlearning
    </h3>

    <el-form
      label-width="80px"
      label-position="right"
      ref="loginFormRef"
      :model="loginForm"
      :rules="loginRules"
      class="relative bg-white p-8"
      size="large"
    >
      <el-form-item label="用户名：" prop="userName" class="mt-8" size="large">
        <el-input
          ref="userNameRef"
          v-model="loginForm.userName"
          placeholder="请输入您的账号"
          tabindex="1"
          @keyup.enter="handleLogin"
          size="large"
        />
      </el-form-item>

      <el-form-item label="密码：" prop="password">
        <el-input
          ref="passwordRef"
          v-model="loginForm.password"
          placeholder="请输入您的密码"
          tabindex="2"
          @keyup.enter="handleLogin"
          show-password
        />
      </el-form-item>
    </el-form>

    <div class="flex justify-between">
      <el-checkbox v-model="checkPwd" @change="rememberPwd"
        >记住密码
      </el-checkbox>
      <span @click="forgetPwd" class="cursor-pointer hover:text-blue-300"
        >忘记密码</span
      >
    </div>

    <el-button
      :loading="loading"
      type="primary"
      class="w-full mb-3 h-6"
      @click.prevent="handleLogin(loginFormRef)"
      >登录
    </el-button>

    <div class="relative flex justify-end">
      <el-button class="absolute" type="info" @click="alert('QQ登录')"
        >QQ登录
      </el-button>
    </div>
  </div>
</template>

<style scoped lang="scss"></style>
