/*
 *  * encapsulate some validate function
 */

import axios from "axios";

/**
 *
 * @param path
 * @returns boolean
 * judge whether the url is external or not
 */
export function isExternal(path: string): boolean {
  return /^(http?:|mailto:|tel:)/.test(path);
}

/**
 *
 * @param {String} path
 * @returns {Boolean}
 * 判断是否是外部的Icon
 */
export function isExternalIcon(path: string): boolean {
  return (
    /^(\/)+([a-zA-Z0-9\s_\\.\-():/])+(.svg|.png|.jpg)$/g.test(path) ||
    /^(https?:|mailto:|tel:)/.test(path)
  );
}

/**
 * @returns url是否有效
 * @param url 表单的url
 */
export function isValidURL(url: string): boolean {
  let flag = false;

  // axios
  //   .get(url, {
  //     baseURL: "https://",
  //     responseType: "json",
  //   })
  //   .then((res) => {
  //     console.log(res);
  //     flag = true;
  //   })
  //   .catch((reason) => {
  //     console.log("1", reason);
  //   });
  // return flag;
  const regex =
    /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?[a-z0-9]+([-.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;
  if (regex.test(url)) {
    flag = true;
  }
  return true;
}
