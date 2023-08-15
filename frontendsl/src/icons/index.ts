import SvgIcon from "@/components/SvgIcon/index.vue";
import { App } from "vue";

// register svgIcon component globally
export const registerSvgIcon = (app: App): void => {
  app.component("svg-icon", SvgIcon);
  const req = require.context("./svg", false, /\.svg$/);
  const requireAll = (requireContext: any) =>
    requireContext.keys().map(requireContext);
  requireAll(req);
};
