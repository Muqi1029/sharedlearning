/**
 * app.ts: store the theme of app
 */

import { defineStore } from "pinia";
// import { i18n } from '@/locales/index'
import cookies from "js-cookie"; // cookie set

import nProgress from "nprogress";
import "nprogress/nprogress.css";

// nProgress.configure({
//   showSpinner: false,
//   trickleSpeed: 100,
//   parent: '#loading-bar-wrapper'
// })

/**
 *
 * @param theme
 * this function is to switch the theme of app
 */
const setTheme = (theme: string) => {
  if (theme === "theme-dark") {
    document.body.classList.remove("theme-light");
    document.body.classList.add("theme-dark");
  } else {
    document.body.classList.remove("theme-dark");
    document.body.classList.add("theme-light");
  }
};

/**
 * @param 1: the name of mudule, pinia will have all the mudule mounted to app
 * @param 2: the object similar to vuex
 */
export const useAppStore = defineStore("appStore", {
  state: () => {
    return {
      themeConfig: {
        // default theme-dark
        theme: cookies.get("theme")
          ? String(cookies.get("theme"))
          : "theme-dark",
        profile_shape: "circle-avatar",
        feature: true,
        gradient: {
          color_1: "#24c6dc",
          color_2: "#5433ff",
          color_3: "#ff0099",
        },
        header_gradient_css: "linear-gradient(45deg, #9000ff, #5e00ff)",
        background_gradient_style: {
          background:
            "linear-gradient(130deg, #24c6dc, #5433ff 41.07%, #ff0099 76.05%)",
          "-webkit-background-clip": "text",
          "-webkit-text-fill-color": "transparent",
          "-webkit-box-decoration-break": "clone",
          "box-decoration-break": "clone",
        },
      },

      appLoading: false,

      /**
       * config logo, name in website
       */
      websiteConfig: {
        logo: "http://localhost:8000/api/image/daselogo.png",
        name: "分享学习",
        englishName: "sharedlearning",
        isCommentReview: false,
      },

      viewCount: 0,
      articleCount: 0,
      talkCount: 0,
      categoryCount: 0,
      tagCount: 0,
      NPTimeout: -1,
      loadingTimeout: -1,
      aurora_bot_enable: true,
    };
  },

  actions: {
    // changeLocale(locale: string) {
    //   cookies.set('locale', locale, { expires: 7 })
    //   i18n.global.locale = locale
    // },

    initializeTheme(mode: string) {
      setTheme(mode);
    },

    toggleTheme(isDark?: boolean) {
      this.themeConfig.theme =
        isDark === true || this.themeConfig.theme === "theme-light"
          ? "theme-dark"
          : "theme-light";
      cookies.set("theme", this.themeConfig.theme, { expires: 7 });
      setTheme(this.themeConfig.theme);
    },

    startLoading() {
      if (this.appLoading === true) return;
      if (this.NPTimeout !== -1) clearTimeout(this.NPTimeout);
      if (this.loadingTimeout !== -1) clearTimeout(this.loadingTimeout);
      nProgress.start();
      this.appLoading = true;
    },

    endLoading() {
      this.NPTimeout = <any>setTimeout(() => {
        nProgress.done();
      }, 100);

      this.loadingTimeout = <any>setTimeout(() => {
        this.appLoading = false;
      }, 300);
    },
  },
});
