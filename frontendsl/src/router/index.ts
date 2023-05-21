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
    name: "market",
    path: "/market",
    component: () => import("../views/market/MarketInfo.vue"),
  },
  {
    name: "account",
    path: "/account",
    component: () => import("../views/account/AccountInfo.vue"),
  },
  {
    // get articles by couseID
    path: "/course/:courseId",
    name: "ArticleList",
    component: () => import("../views/articlelist/ArticleList.vue"),
  },
  {
    path: "/article/:articleId",
    name: "articles",
    component: () => import("../views/article/Article.vue"),
  },
  // {
  //     path: '/article-list/:tagId',
  //     name: 'ArticleList',
  //     component: () => import('../views/ArticleList.vue')
  // },
  // {
  //     path: '/tags',
  //     name: 'Tags',
  //     component: () => import('../views/Tags.vue')
  // },
  // {
  //     path: '/about',
  //     name: 'About',
  //     component: () => import('../views/About.vue')
  // },
  {
    path: "/message",
    name: "Message",
    component: () => import("../views/message/index.vue"),
  },
  // {
  //     path: '/friends',
  //     name: 'Friends',
  //     component: () => import('../views/FriendLink.vue')
  // },
  // {
  //     path: '/photos/:albumId',
  //     name: 'Photos',
  //     component: () => import('../views/Photos.vue')
  // },
  {
    path: "/404",
    name: "404",
    component: () => import("../views/error-page/404.vue"),
  },
  // {
  //     path: '/oauth/login/qq',
  //     name: 'qqLogin',
  //     component: () => import('../components/OauthLogin.vue')
  // },
  {
    path: "/:catchAll(.*)",
    redirect: "/404",
    hidden: true,
  },
];

// @ts-ignore <- ignore it
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  // the correct type for routes is RouteRecordRaw
  routes: routes as RouteRecordRaw[],
});

export default router
