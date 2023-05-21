/**
 * the purpose of this api is to handle user event
 *
 * @author muqi
 *
 * 1. user login
 *
 * 2. user register
 *
 * 3. user logout
 */

import request from "@/utils/request";

// 给后端发请求
export function login(data: any) {
  return request({
    url: "/user/login",
    method: "post",
    data,
  });
}

export function UserClickCourse(data: any) {
  return request({
    url: "/user/recordsClick",
    data,
    method: "post",
  });
}

export function getUserInfoByID(userID: number) {
  return request({
    url: "/user/info/" + userID,
    method: "get",
  });
}
