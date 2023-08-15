<template>
  <div class="articlelist-container">
    <div
      class="course-intro m-4 p-4 text-xl bg-purple-500 text-white text-opacity-90"
    >
      <h1 class="m-auto mb-4">课程介绍</h1>
      <!--      <img src="@/assets/default-cover.jpg" />-->
      {{ intro }}
    </div>
    <hr />
    <div>
      <el-upload
        action="http://localhost:8000/api/article/import/1"
        method="post"
        drag
      >
        <el-button type="primary">上传文章</el-button>
        <!-- <template #tip>
          <div class="el-upload__tip">sdah</div>
        </template> -->
      </el-upload>
    </div>

    <div class="articlelist-grid mt-5 ml-4">
      <ul>
        <template v-if="hasArticles">
          <li v-for="article in articles" :key="article.id" class="my-4">
            <article-card :data="article"></article-card>
          </li>
        </template>

        <template v-else>
          <div>文章不存在</div>
        </template>
      </ul>

      <aside class="ml-10 border-l-4">
        <div class="flex flex-col">
          <h1 class="text-center">课程相关资料</h1>
          <ul>
            <li v-for="link in links" :key="link">
              <link-card :data="link"></link-card>
            </li>
          </ul>

          <div
            class="text-center p-3 text-xl bg-purple-600 rounded-2xl cursor-pointer hover:bg-purple-500"
            @click="dialog1Visible = true"
          >
            上传链接
          </div>
        </div>

        <el-dialog v-model="dialog1Visible" title="upload Official Link">
          <el-form
            :model="officialForm"
            ref="officialFormRef"
            size="large"
            :rules="rules"
          >
            <el-form-item
              label="url"
              required
              prop="url"
              :label-width="labelWidth"
            >
              <el-input
                placeholder="请输入网站的url"
                v-model="officialForm.url"
              ></el-input>
            </el-form-item>

            <el-form-item label="name" prop="name" :label-width="labelWidth">
              <el-input
                placeholder="请输入网站名称(optional)"
                v-model="officialForm.name"
              ></el-input>
            </el-form-item>
          </el-form>

          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialog1Visible = false">Cancel</el-button>
              <el-button
                @click="submitOfficialForm(officialFormRef)"
                type="primary"
                >Confirm</el-button
              >
            </span>
          </template>
        </el-dialog>

        <div class="flex flex-col">
          <h1 class="text-center">私有的链接</h1>

          <!-- <link-card></link-card> -->

          <button>上传</button> <!-- todo -->
        </div>
      </aside>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, ref, reactive, toRefs } from "vue";
import { toPageTop } from "@/utils/html";
import { useRoute } from "vue-router";
import { useArticleStore } from "@/stores/article";
import LinkCard from "@/components/LinkCard/LinkCard.vue";
import ArticleCard from "@/components/ArticleCard/ArticleCard.vue";
import Comment from "@/components/Comment/Comment.vue"
import { useCourseStore } from "@/stores/course";
import { useLinkStore } from "@/stores/link";
import type { FormInstance, FormRules } from "element-plus";
import { useUserStore } from "@/stores/user";
import { isValidURL } from "@/utils/validate";

export default defineComponent({
  name: "ArticleList",
  components: {
    ArticleCard,
    LinkCard,
    Comment
  },
  setup() {
    let md = require("markdown-it")();

    const reactiveData = reactive({
      articles: [] as any,
      hasArticles: true,
      id: "" as any, // courseID
      links: [],
      userLinks: [],
      intro: "",
      dialog1Visible: false,
    });

    const route = useRoute();
    const articleStore = useArticleStore();
    const courseStore = useCourseStore();
    const linkStore = useLinkStore();
    const userStore = useUserStore();

    const pagination = reactive({
      size: 12,
      total: 0,
      current: 1,
    });

    reactiveData.id = parseInt(route.params.courseId as string);

    onMounted(() => {
      // get all articles
      articleStore.getArticlesByCourseId(reactiveData.id).then(
        (res: any) => {
          if (res.records.length > 0) {
            reactiveData.hasArticles = true;
          }
          res.records.forEach((element: any) => {
            // console.log("element", element.articleContent)
            // element.articleContent = markdownToHtml(element.articleContent)
            // console.log("after",markdownToHtml(element.articleContent))
            element.articleContent = md
              .render(element.articleContent)
              .replace(/<\/?[^>]*>/g, "")
              .replace(/[|]*\n/, "")
              .replace(/&npsp;/gi, "");
          });
          articleStore.articles = res.records;

          // console.log("收到的文章列表为", reactiveData.articles);
        },
        (reason) => {
          console.log("fail to getArticlesByCourseId", reason);
        }
      );
      /* ================== the end of get articles =================*/
      // get course intro
      courseStore.getCourseIntroById(reactiveData.id).then((data: any) => {
        reactiveData.intro = data;
      });

      // get link
      linkStore.getLinkByCourseID(reactiveData.id).then((data: any) => {
        reactiveData.links = data;
      });
    });

    /** --------------official Form---------------------- */
    const officialForm = reactive({
      url: "",
      name: "",
      courseID: reactiveData.id,
      userID: userStore.userID,
    });

    const labelWidth = ref("100px");

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
            reactiveData.dialog1Visible = false;
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

    return {
      ...toRefs(reactiveData),
      toPageTop,
      ...toRefs(articleStore.$state),
      officialForm,
      labelWidth,
      officialFormRef,
      submitOfficialForm,
      rules,
    };
  },
});
</script>
<style lang="scss" scoped>
.course-intro {
  border: 2px solid lightblue;
  box-shadow: 2px 2px 5px #888;
  border-radius: 10px;
  transition: all 0.3s ease;
  background: rgb(158, 66, 155);
  background: linear-gradient(
    90deg,
    rgba(158, 66, 155, 1) 0%,
    rgba(116, 86, 182, 1) 24%,
    rgba(0, 141, 255, 1) 90%
  );
}

.articlelist-grid {
  display: flex;
  flex-direction: column;
}

@media (min-width: 1024px) {
  .articlelist-grid {
    display: grid;
    gap: (--gap);
    grid-template-columns: 1fr 1fr;
  }
}
</style>
