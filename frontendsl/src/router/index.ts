import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const routes = [
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
    // get articles by courseID
    path: "/course/:courseId",
    component: () => import("../views/Course.vue"),
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
