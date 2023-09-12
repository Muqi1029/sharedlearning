// import router from "@/router";
// import { getToken } from "@/utils/auth";
// // import { useAppStore } from "@/stores/app";
// import { useUserStore } from "@/stores/user";
//
// router.beforeEach(async (to, from, next) => {
//   const userStore = useUserStore();
//   // determine whether the user has logged in or not
//   const hasToken = getToken();
//
//   if (hasToken) {
//     if (!userStore.isHave) {
//       userStore.getUserByID(hasToken);
//     }
//
//     // if user has logged in, redirect to home
//     if (to.path === "/login") {
//       next({
//         path: "/home",
//       });
//       return;
//     }
//     next();
//     return;
//   } else {
//     // don't have token
//     if (to.path === "/login") {
//       next();
//     } else {
//       next({
//         path: "/login",
//       });
//     }
//   }
// });
//
// // router.afterEach(() => {
// //   const appStore = useAppStore();
// //   appStore.endLoading();
// // });
