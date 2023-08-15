<template>
  <div class="login-container">
    <div class="loginFormData">
      <!-- title -->
      <div class="title-container">
        <h3 class="title">Sharedlearning</h3>
      </div>

      <el-form
        label-width="80px"
        label-position="right"
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginRules"
        class="relative bg-white p-8"
        size="large"
      >
        <!-- 用户名 -->
        <el-form-item label="用户名:" prop="username" class="mt-8" size="large">
          <el-input
            ref="usernameRef"
            v-model="loginForm.username"
            placeholder="请输入你的账号"
            name="username"
            tabindex="1"
            @keyup.enter="handleLogin"
            size="large"
          />
        </el-form-item>

        <el-tooltip
          :visible="!capsTooltip"
          content="Caps lock is On"
          placement="right"
          effect="dark"
        >
          <!-- password -->
          <el-form-item prop="password" label="密码：">
            <el-input
              type="text"
              ref="passwordRef"
              v-model="loginForm.password"
              placeholder="请输入你的密码"
              tabindex="2"
              @keyup="checkCapslock"
              @keyup.enter="handleLogin"
              show-password
            />
          </el-form-item>
        </el-tooltip>

        <div class="flex justify-between">
          <el-checkbox v-model="checked" @change="rememberPwd"
            >记住密码
          </el-checkbox>
          <span
            @click="forgetPwd"
            class="cursor-pointer hover:text-blue-300 text-sm"
            >忘记密码</span
          >
        </div>

        <el-button
          :loading="loading"
          type="primary"
          class="w-full mb-3"
          @click.prevent="handleLogin"
        >
          登录
        </el-button>

        <div class="relative flex justify-end mr-20">
          <div>
            <el-button class="absolute" type="info" @click="showDialog = true">
              QQ登录
              <!-- TODO: 处理QQ登录 -->
            </el-button>
          </div>
        </div>
      </el-form>
    </div>

    <!-- title: 定义标题 -->
    <el-dialog title="其他登录方式" :visible="showDialog">
      Can not be simulated on local, so please combine you own business
      simulation! ! !
      <br />
      <br />
      <br />
      <!-- social登录组件 -->
    </el-dialog>
  </div>
</template>

<script lang="ts">
import { reactive, ref, watch, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useUserStore } from "@/stores/user";
import { ElTooltip } from "element-plus";

export default {
  name: "UserLogin",
  components: { ElTooltip },
  setup() {
    /* --------------------------------------------------------------- */
    let capsTooltip = ref(true);
    let showDialog = ref(false); // 是否显示对话框(用来表示其他登录方式)
    let redirect = ref(); // redirect去的组件
    let otherQuery = reactive({}); // tmp

    const checked = ref(false);

    /* --------------------handle route------------------------------  */
    // route variable
    const route = useRoute(); // not a responsive data(as the data changes, the page changes with the rendering)
    const router = useRouter();
    // watch route
    watch(
      route,
      (newroute: any) => {
        const query = newroute.query; // 获取query
        if (query) {
          redirect = query.redirect; // 获取重定向的路由
          otherQuery = getOtherQuery(query); // 获取其他的query
        }
      },
      { immediate: true }
    );

    /* ------------------------get dom-------------------------------------- */
    const usernameRef = ref<HTMLElement | null>(null);
    const passwordRef = ref<HTMLElement | null>(null); // get the dom

    // lifecycle: when this dom is mounted
    onMounted(() => {
      // 获取焦点
      if (loginForm.username === "") {
        usernameRef.value?.focus();
      } else if (loginForm.password === "") {
        passwordRef.value?.focus();
      }
    });

    /* --------------------------use userStore----------------------------------*/
    // userStore
    const userStore = useUserStore() as any;

    let loginForm = reactive({
      username: "",
      password: "",
    });

    // loginForm Rules
    let loginRules = {
      username: [
        // trigger: 失去焦点
        { required: true, message: "用户名不能为空", trigger: "blur" },
      ],
      password: [{ required: true, message: "密码不能为空", trigger: "blur" }],
    };

    /* --------------------- handle login--------------------------------- */
    let loginFormRef = ref();
    let loading = ref(false); // 记录登录按钮是否在加载中

    function handleLogin() {
      // 把loginForm表单传入store中
      // console.log(loginFormRef.value)
      loginFormRef.value.validate((valid: any) => {
        if (valid) {
          loading.value = true;
          userStore
            .userLogin(loginForm)
            .then((res: number) => {
              if (res === 1) {
                router.push({
                  path: "/admin",
                });
              } else {
                // if success, page will get into home
                router.push({
                  path: "/home",
                  query: otherQuery,
                });
                loading.value = false;
              }
            })
            .catch(() => {
              // if there is any error
              loading.value = false;
              loginForm.username = "";
              loginForm.password = "";
            });
        } else {
          // value in form isn't valid
          console.log("error submit!!");
          return false;
        }
      });
    }

    /* ------------------------check caps lock------------------------------ */
    function checkCapslock(e: any) {
      if (e === undefined) return;
      const { key } = e; // 获取键盘按键
      capsTooltip.value = !(
        key &&
        key.length === 1 &&
        key >= "A" &&
        key <= "Z"
      );
    }

    function getOtherQuery(query: any) {
      return Object.keys(query).reduce((acc: any, cur) => {
        if (cur !== "redirect") {
          acc[cur] = query[cur];
        }
        return acc;
      }, {});
    }

    const rememberPwd = (data: boolean) => {
      checked.value = data;
      if (checked.value) {
        localStorage.setItem("news", JSON.stringify(loginForm));
      } else {
        localStorage.removeItem("news");
      }
    };

    const forgetPwd = () => {
      alert("please to ask 10215501421 for help");
    };

    // return a object, the elements in which can be used in template
    return {
      loginForm,
      loginRules,

      loading,
      capsTooltip,
      showDialog,
      redirect,
      usernameRef,
      passwordRef,
      loginFormRef,
      checkCapslock,
      handleLogin,
      getOtherQuery,

      checked,
      rememberPwd,
      forgetPwd,
    };
  },
};
</script>

<style lang="scss" scoped>
.login-container {
  width: 100%;
  height: 100%;
  overflow: hidden;
}

.loginFormData {
  width: 600px;
  padding: 30px;
  border-radius: 10px;
  margin: 400px auto;
}

.title-container {
  position: relative;

  .title {
    font-size: 26px;
    margin: 0 auto;
    text-align: center;
    font-weight: bold;
    text-shadow: 2px 2px 4px #dcd5d5;
  }
}

.login-container {
  //background: url("@/assets/login.jpg") top center no-repeat;
  //background-size: cover
}

.el-input:-internal-autofill-previewed,
.el-input:-internal-autofill-selected {
  -webkit-text-fill-color: #ffffff !important;
  transition: background-color 5000s ease-in-out 0s !important;
}
</style>