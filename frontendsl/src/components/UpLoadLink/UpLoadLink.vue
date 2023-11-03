<script lang="ts" setup>
import { reactive, ref } from "vue";
import { ElMessageBox, FormInstance, FormRules } from "element-plus";
import { useUserStore } from "@/stores/user";
import { useLinkStore } from "@/stores/link";

const props = defineProps({
  name: String,
});

const dialogVisible = ref<boolean>(false);
const linkStore = useLinkStore();
const userStore = useUserStore();
// the form
const uploadLinkForm = reactive({
  url: "",
  name: "",
  userID: userStore.userID,
  add_info: "",
});

// the function used to submit form
const handleConfirm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate(async (valid) => {
    if (valid) {
      const data = await linkStore.importOfficialLink(uploadLinkForm);
      if (!data) {
        alert("操作失败");
      } else if ((data as any).flag) {
        alert("操作成功");
        formEl.resetFields();
      }
    } else {
      alert("错误的表单");
    }
    dialogVisible.value = false;
  });
};

const rules = reactive<FormRules>({
  url: [],
});

const uploadLinkFormRef = ref<FormInstance>();
const handleClose = (done: () => void) => {
  ElMessageBox.confirm("Are you sure to close this dialog?")
    .then(() => {
      done();
    })
    .catch(() => {
      // catch error
    });
};
</script>

<template>
  <el-button @click="dialogVisible = true" style="padding: 20px; margin: 10px"
    >{{ name }}
  </el-button>
  <el-dialog
    v-model="dialogVisible"
    title="输入表单"
    width="30%"
    :before-close="handleClose"
  >
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

      <el-form-item label="additional information" prop="add_info">
        <el-input v-model="uploadLinkForm.add_info" type="textarea" />
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="handleConfirm(uploadLinkFormRef)">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>
