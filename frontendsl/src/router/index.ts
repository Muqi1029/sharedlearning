import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    name: "login",
    path: "/login",
    component: () => import("../views/Login.vue"),
  },
  {
    name: "home",
    path: "/home",
    component: () => import("../views/Home.vue"),
  },
  {
    name: "account",
    path: "/account",
    component: () => import("../views/Account.vue"),
  },
  {
    path: "/course/:courseID",
    name: "courses",
    component: () => import("../views/Course.vue"),
  },
  {
    path: "/editor",
    name: "course_editor",
    component: () => import("../views/Editor.vue"),
  },
  {
    path: "/article/:articleId",
    name: "articles",
    component: () => import("../views/Article.vue"),
  },
  {
    path: "/message",
    name: "Message",
    component: () => import("../views/Message.vue"),
  },
  {
    path: "/404",
    name: "404",
    component: () => import("../views/404.vue"),
  },
  {
    path: "/admin",
    name: "admin",
    component: () => import("../views/Admin.vue"),
    children: [
      {
        path: "user",
        component: () => import("@/components/Admin/UserAdmin.vue"),
      },
      {
        path: "article",
        component: () => import("@/components/Admin/ArticleAdmin.vue"),
      },
    ],
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404",
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
