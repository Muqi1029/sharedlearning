import { createApp } from "vue";
import v3ImgPreview from "v3-img-preview";
import App from "./App.vue";
import { registerSvgIcon } from "@/icons";

/**
 * import components, plugins from element-plus
 */
import { components } from "./plugins/element-plus";

/**
 * import pinia (a store package)
 */
import { createPinia } from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

/**
 * import router we define
 */
import router from "./router";

// import style --------------------------------------------------------

/**
 * import the styles we define
 * use the css frame: tailwindcss
 */
import "./styles/index.scss";

/**
 * Normalize.css makes browsers render all elements more consistently
 * and in line with modern standards.
 * It precisely targets only the styles that need normalizing.
 */
import "normalize.css/normalize.css";

import "prismjs/themes/prism.css";
import "prismjs";
// import 'element-plus/theme-chalk/index.css'
import "element-plus/dist/index.css";
// end of style ------------------------------------------------------

/**
 * the lazy load of img in vue3
 */
import lazyPlugin from "vue3-lazy";

/**
 * infinite scroll
 */
import infiniteScroll from "vue3-infinite-scroll-better";

import VueClickAwayPlugin from "vue3-click-away";

// create root vm
export const app = createApp(App)
  .use(pinia)
  .use(router)
  .use(lazyPlugin, {
    loading: require("@/assets/default-cover.jpg"),
    error: require("@/assets/default-cover.jpg"),
  })
  .use(infiniteScroll)
  .use(VueClickAwayPlugin)
  .use(v3ImgPreview);

components.forEach((component) => {
  app.component(component.name, component);
});
registerSvgIcon(app);

app.mount("#app");
