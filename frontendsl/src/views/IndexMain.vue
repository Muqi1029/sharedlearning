<template>
  <div class="mt-1">
    <div
      class="flex flex-col rounded-2xl p-4 mt-2 border-2 border-solid mx-4"
      style="background: #f4f5f5"
    >
      <div class="flex justify-between">
        <h1>智能推荐部分</h1>
        <el-button class="h-16 p-5" size="large" @click="dialogVisible = true">
          上传链接
        </el-button>
      </div>

      <el-dialog v-model="dialogVisible" title="upload Link">
        <el-form
          :model="officialForm"
          ref="officialFormRef"
          size="large"
          :rules="rules"
        >
          <el-form-item label="url" required prop="url" label-width="50px">
            <el-input
              placeholder="请输入网站的url"
              v-model="officialForm.url"
            ></el-input>
          </el-form-item>

          <el-form-item label="name" prop="name" label-width="50px">
            <el-input
              placeholder="请输入网站名称(optional)"
              v-model="officialForm.name"
            ></el-input>
          </el-form-item>
        </el-form>

        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false"> Cancel </el-button>
            <el-button
              @click="submitOfficialForm(officialFormRef)"
              type="primary"
            >
              Confirm
            </el-button>
          </span>
        </template>
      </el-dialog>

      <div class="flex">
        <recommend-link-card
          title="Github"
          :data="githubList"
        ></recommend-link-card>
        <recommend-link-card
          title="文章 教程"
          :data="articleList"
        ></recommend-link-card>
      </div>
    </div>

    <!-- article title -->
    <Title :title="'Class Cards'" icon="article" />

    <!-- articles grid -->
    <div class="main-grid">
      <div class="flex flex-col relative">
        <ul :class="tabClass">
          <el-select
            placeholder="学期"
            v-model="term"
            clearable
            filterable
            size="large"
          >
            <el-option
              v-for="term in terms"
              :key="term.value"
              :label="term.label"
              :value="term.value"
            >
            </el-option>
          </el-select>

          <el-select
            placeholder="编程语言"
            v-model="language"
            clearable
            filterable
            size="large"
            class="ml-3"
          >
            <el-option
              v-for="language in languages"
              :key="language.value"
              :label="language.label"
              :value="language.value"
            >
            </el-option>
          </el-select>
        </ul>

        <span :class="expanderClass" @click="expandHandler">
          <svg-icon icon-class="chevron"></svg-icon>
        </span>

        <!-- modify the grid-template-columns by media queries -->
        <ul class="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-8 ml-4">
          <!-- class cards （use grid layout） -->
          <template v-if="haveCourses">
            <li v-for="course in courses" :key="course.id">
              <CourseCard
                :data="course"
                @courseID="changeReserve"
                :favList="favList"
              ></CourseCard>
            </li>
          </template>
          <template v-else>
            <div>xixixixixixixixi</div>
          </template>
        </ul>
      </div>

      <!-- used to set sidebar, which shows when width >= 1024px -->
      <sidebar>
        <profile></profile>
        <recent-reserve
          :favList="favList"
          :haveCourses="haveCourses"
        ></recent-reserve>
      </sidebar>
    </div>

    <div>网站底部信息</div>
  </div>
</template>

<script lang="ts">
import Title from "@/components/Title/Title.vue";
import CourseCard from "@/components/CourseCard/CourseCardtest.vue";
import Sidebar from "@/components/Sidebar/Sidebar.vue";

import Profile from "@/components/Sidebar/Profile.vue";

import {
  defineComponent,
  toRefs,
  reactive,
  onMounted,
  ref,
  watchEffect,
  watch,
} from "vue";

import { useCourseStore } from "@/stores/course";
import { terms, languages } from "@/constant/app";
import { useAppStore } from "@/stores/app";
import RecentReserve from "@/components/Sidebar/RecentReserve.vue";
import { saveData, readData } from "@/utils/localStorageUtil";
import RecommendLinkCard from "@/components/LinkCard/RecommendLinkCard.vue";
import { ILink } from "@/types/types";
import { useUserStore } from "@/stores/user";
import { getRecommendLinkByUserID } from "@/api/link";
import { FormInstance, FormRules } from "element-plus";
import { isValidURL } from "@/utils/validate";
import { useLinkStore } from "@/stores/link";
import SvgIcon from "@/components/SvgIcon/index.vue";

export default defineComponent({
  components: {
    SvgIcon,
    RecommendLinkCard,
    Title,
    CourseCard,
    Sidebar,
    Profile,
    RecentReserve,
  },

  setup: function () {
    const courseStore = useCourseStore();
    const appStore = useAppStore();
    const userStore = useUserStore();
    const linkStore = useLinkStore();

    //所需要的响应式数据
    //包括：学期  编程语言  课程列表  github列表   文章列表
    const reactiveData = reactive({
      haveCourses: false,
      loading: false,
      term: "",
      language: "",
      courses: [] as any,
      githubList: [] as ILink[],
      articleList: [],
    });

    const dialogVisible = ref(false);

    const officialForm = reactive({
      url: "",
      name: "",
      userID: userStore.userID,
    });

    const officialFormRef = ref<FormInstance>();
    const submitOfficialForm = (formEl: FormInstance | undefined) => {
      if (!formEl) return;
      // console.log(officialForm)
      formEl.validate(async (valid) => {
        if (valid) {
          const data = await linkStore.importOfficialLink(officialForm);
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

    const validateURL = (rule: any, value: any, callback: any) => {
      if (!value) {
        return callback(new Error("请输入URL"));
      }
      if (!isValidURL(value)) {
        callback(new Error("请输入正确的URL"));
      } else {
        callback();
      }
    };

    const rules = reactive<FormRules>({
      url: [{ validator: validateURL, trigger: "blur", required: true }],
    });

    /*=============tab==================*/

    const activeTab = ref(0);
    const tabClass = reactive({
      tab: true,
      "expanded-tab": false,
    });
    const expanderClass = reactive({
      "tab-expander": true,
      expanded: false,
    });
    const activeTabStyle = (tabId: number) => {
      if (tabId === activeTab.value) {
        return {
          background: appStore.themeConfig.header_gradient_css,
        };
      }
      return {};
    };

    const expandHandler = () => {
      expanderClass.expanded = !expanderClass.expanded;
      tabClass["expanded-tab"] = !tabClass["expanded-tab"];
    };

    /* =================== end of tab ======================= */

    /**
     * get Recommend Link
     */

    const getRecommendLink = (userID: number) => {
      return getRecommendLinkByUserID(userID);
    };

    // show page
    const pagination = reactive({
      size: 12,
      total: 0,
      current: 1,
    });

    onMounted(async () => {
      // if you get courses successfully
      courseStore.getCourses(pagination).then(() => {
        reactiveData.haveCourses = true;
        reactiveData.courses = courseStore.courses;
      });

      // getRecommendLink(parseInt(userStore.userID));
      const data = await getRecommendLink(parseInt(userStore.userID));
      reactiveData.githubList = (data as any).data.githubList;
      reactiveData.articleList = (data as any).data.articleList;
      // alert(reactiveData.articleList);
    });

    /**
     * store the favList
     */
    const favList = ref<number[]>([]);

    favList.value = readData();

    //用于更新收藏课程的队列
    const changeReserve = (param: [number, boolean]) => {
      if (param[1]) favList.value.push(param[0]);
      else {
        // find the non-collected course and then splice it
        const index = favList.value.indexOf(param[0]);
        favList.value.splice(index, 1);
      }
    };

    watchEffect(() => {
      // if courseStore have courses and term is not blank
      if (reactiveData.haveCourses && reactiveData.term) {
        reactiveData.courses = courseStore.getCourseByTerm(reactiveData.term);
      } else if (reactiveData.haveCourses && !reactiveData.term) {
        reactiveData.courses = courseStore.courses;
      }
    });

    watch(favList, saveData, { deep: true });

    return {
      ...toRefs(reactiveData),
      activeTab,
      changeReserve,
      expandHandler,
      expanderClass,
      favList,
      languages,
      tabClass,
      terms,
      dialogVisible,
      officialForm,
      officialFormRef,
      submitOfficialForm,
      rules,
    };
  },
});
</script>
<style lang="scss" scoped></style>
