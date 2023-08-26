<template>
  <div class="">
    <!-- header -->
    <!--    <template v-if="isShowHeader">-->
    <!--      <Header />-->
    <!--    </template>-->

    <div class="app-banner app-banner-image"></div>

    <div
      class="app-banner app-banner-screen"
      :style="headerBaseBackground"
    ></div>

    <!-- main -->
    <div class="relative z-10">
      <!-- 作用域插槽，子组件传递Component给父组件router-view -->
      <router-view v-slot="{ Component }">
        <!-- the transition animation of the router-view -->
        <transition name="fade-slide-y" mode="out-in">
          <!-- 一个用于渲染动态组件或元素的“元组件”。 -->
          <component :is="Component"></component>
        </transition>
      </router-view>
    </div>
  </div>
</template>

<script lang="ts">
import Header from "./components/Header/Header.vue";
import { useRoute } from "vue-router";
import { ref, watch, computed } from "vue";
import { useAppStore } from "./stores/app";
import { marked } from "marked";
import hljs from "highlight.js";
import "highlight.js/styles/atom-one-dark.css";
import Login from "@/views/Login.vue";

export default {
  name: "App",
  components: { Login, Header },

  setup() {
    const markdown = ref("");

    const renderedMarkdown = ref("");

    const route = useRoute();

    let isShowHeader = ref(false);

    const appStore = useAppStore();

    watch(
      route,
      (newRoute: any) => {
        if (newRoute.path !== "/login" && newRoute.path !== "/404") {
          isShowHeader.value = true;
        } else {
          isShowHeader.value = false;
        }
      },
      {
        immediate: true,
      }
    );

    watch(markdown, (newValue) => {
      renderedMarkdown.value = marked(newValue, {
        highlight: function (code: any, language: any) {
          if (language && hljs.getLanguage(language)) {
            return hljs.highlight(code, { language }).value;
          }
          return hljs.highlightAuto(code).value;
        },
      });
    });

    return {
      isShowHeader,
      headerBaseBackground: computed(() => {
        return {
          background: appStore.themeConfig.header_gradient_css,
          opacity: 0.91,
        };
      }),
      renderedMarkdown,
      markdown,
    };
  },
};
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  position: relative;
}

//.app-banner {
//  display: block;
//  height: 600px;
//  z-index: 1;
//  width: 100%;
//  position: absolute;
//  top: 0;
//  clip-path: polygon(
//    100% 0,
//    0 0,
//    0 77.5%,
//    1% 77.4%,
//    2% 77.1%,
//    3% 76.6%,
//    4% 75.9%,
//    5% 75.05%,
//    6% 74.05%,
//    7% 72.95%,
//    8% 71.75%,
//    9% 70.55%,
//    10% 69.3%,
//    11% 68.05%,
//    12% 66.9%,
//    13% 65.8%,
//    14% 64.8%,
//    15% 64%,
//    16% 63.35%,
//    17% 62.85%,
//    18% 62.6%,
//    19% 62.5%,
//    20% 62.65%,
//    21% 63%,
//    22% 63.5%,
//    23% 64.2%,
//    24% 65.1%,
//    25% 66.1%,
//    26% 67.2%,
//    27% 68.4%,
//    28% 69.65%,
//    29% 70.9%,
//    30% 72.15%,
//    31% 73.3%,
//    32% 74.35%,
//    33% 75.3%,
//    34% 76.1%,
//    35% 76.75%,
//    36% 77.2%,
//    37% 77.45%,
//    38% 77.5%,
//    39% 77.3%,
//    40% 76.95%,
//    41% 76.4%,
//    42% 75.65%,
//    43% 74.75%,
//    44% 73.75%,
//    45% 72.6%,
//    46% 71.4%,
//    47% 70.15%,
//    48% 68.9%,
//    49% 67.7%,
//    50% 66.55%,
//    51% 65.5%,
//    52% 64.55%,
//    53% 63.75%,
//    54% 63.15%,
//    55% 62.75%,
//    56% 62.55%,
//    57% 62.5%,
//    58% 62.7%,
//    59% 63.1%,
//    60% 63.7%,
//    61% 64.45%,
//    62% 65.4%,
//    63% 66.45%,
//    64% 67.6%,
//    65% 68.8%,
//    66% 70.05%,
//    67% 71.3%,
//    68% 72.5%,
//    69% 73.6%,
//    70% 74.65%,
//    71% 75.55%,
//    72% 76.35%,
//    73% 76.9%,
//    74% 77.3%,
//    75% 77.5%,
//    76% 77.45%,
//    77% 77.25%,
//    78% 76.8%,
//    79% 76.2%,
//    80% 75.4%,
//    81% 74.45%,
//    82% 73.4%,
//    83% 72.25%,
//    84% 71.05%,
//    85% 69.8%,
//    86% 68.55%,
//    87% 67.35%,
//    88% 66.2%,
//    89% 65.2%,
//    90% 64.3%,
//    91% 63.55%,
//    92% 63%,
//    93% 62.65%,
//    94% 62.5%,
//    95% 62.55%,
//    96% 62.8%,
//    97% 63.3%,
//    98% 63.9%,
//    99% 64.75%,
//    100% 65.7%
//  );
//}

.app-banner-image {
  z-index: 1;
  background-size: cover;
  opacity: 0;
  transition: ease-in-out opacity 300ms;
}

.app-banner-screen {
  transition: ease-in-out opacity 300ms;
  z-index: 2;
  opacity: 0.91;
}
</style>