/**
 * set and get user's token from the browser in order to get identity of user
 *
 * different from session(more secure)
 */

import Cookies from "js-cookie";

const TokenKey = "User-Token";

// Token令牌

export function getToken() {
  return Cookies.get(TokenKey);
}

export function setToken(token: any) {
  return Cookies.set(TokenKey, token, { expires: 7 });
}

export function removeToken() {
  return Cookies.remove(TokenKey);
}
