<script lang="ts" setup>
import { reactive, ref, defineEmits } from "vue";
import { useUserStore } from "@/stores/user";
import { FormInstance, FormRules } from "element-plus";
import { useLinkStore } from "@/stores/link";

// eslint-disable-next-line no-undef
const props = defineProps({
  dialogVisible: {
    type: Boolean,
    default: false,
    required: true,
  },
});

const emits = defineEmits(["changeVisible"]);

const userStore = useUserStore();
const linkStore = useLinkStore();

// the form
const uploadLinkForm = reactive({
  url: "",
  name: "",
  userID: userStore.userID,
});

// the function used to submit form
const submitUploadLinkForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate(async (valid) => {
    if (valid) {
      const data = await linkStore.importOfficialLink(uploadLinkForm);
      if ((data as any).flag) {
        alert("操作成功");
        formEl.resetFields();
        emits("changeVisible", false);
      } else {
        alert("操作失败");
      }
    } else {
      alert("错误的表单");
    }
  });
};

const rules = reactive<FormRules>({
  url: [],
});

const uploadLinkFormRef = ref<FormInstance>();
</script>

<template>
  <div v-show="dialogVisible" class="mask">
    <div class="dialog p-10 text-center w-1/3">
      <h1>输入表单</h1>
      <el-form
        :model="uploadLinkForm"
        ref="uploadLinkFormRef"
        size="large"
        :rules="rules"
      >
        <el-form-item label="url" required prop="url" label-width="50px">
          <el-input
            placeholder="请输入网站的url"
            v-model="uploadLinkForm.url"
          ></el-input>
        </el-form-item>

        <el-form-item label="name" prop="name" label-width="50px">
          <el-input
            placeholder="请输入网站名称(optional)"
            v-model="uploadLinkForm.name"
          ></el-input>
        </el-form-item>
      </el-form>

      <el-button @click="emits('changeVisible', false)">Cancel</el-button>
      <el-button @click="submitUploadLinkForm(uploadLinkFormRef)" type="primary"
        >Confirm
      </el-button>
    </div>
  </div>
</template>

<style scoped lang="scss">
.mask {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1000;
}

.dialog {
  background: lightblue;
  position: absolute;
  transform: translate(-50%, -50%);
  top: 50%;
  left: 50%;
  z-index: 1000;
}
</style>
