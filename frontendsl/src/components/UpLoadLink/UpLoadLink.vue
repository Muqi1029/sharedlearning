<template>
  <div>
    <el-dialog v-model="dialogVisible" title="Upload Link">
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

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">Cancel</el-button>
          <el-button
            @click="submitUploadLinkForm(uploadLinkFormRef)"
            type="primary"
            >Confirm</el-button
          >
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref } from "vue";
import { useUserStore } from "@/stores/user";
import { FormInstance, FormRules } from "element-plus";
import { useLinkStore } from "@/stores/link";

export default defineComponent({
  components: {},

  setup: function () {
    const dialogVisible = ref(false);
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
            dialogVisible.value = false;
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

    return {
      uploadLinkForm: uploadLinkForm,
      dialogVisible,
      rules,
      submitUploadLinkForm,
      uploadLinkFormRef,
    };
  },
});
</script>

<style scoped lang="scss"></style>
