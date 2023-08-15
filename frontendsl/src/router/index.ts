import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const routes = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    name: "login",
    path: "/login",
    component: () => import("../views/login/UserLogin.vue"),
  },
  {
    name: "home",
    path: "/home",
    component: () => import("../views/home/IndexMain.vue"),
  },
  {
    name: "account",
    path: "/account",
    component: () => import("../views/account/AccountInfo.vue"),
  },
  {
    // get articles by courseID
    path: "/course/:courseId",
    name: "ArticleList",
    component: () => import("../views/articlelist/ArticleList.vue"),
  },
  {
    path: "/article/:articleId",
    name: "articles",
    component: () => import("../views/article/Article.vue"),
  },
  {
    path: "/message",
    name: "Message",
    component: () => import("../views/message/index.vue"),
  },
  {
    path: "/404",
    name: "404",
    component: () => import("../views/error-page/404.vue"),
  },
  {
    path: "/admin",
    name: "admin",
    component: import("../views/Admin.vue"),
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404",
    hidden: true,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  // the correct type for routes is RouteRecordRaw
  routes: routes as RouteRecordRaw[],
});

export default router;
