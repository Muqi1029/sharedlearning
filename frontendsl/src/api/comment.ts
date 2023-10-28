import request from "@/utils/request";

export function getComments(articleID: any) {
  return request({
    method: "get",
    url: "/comment/search/" + articleID,
  });
}

export function saveComment(data: any) {
  return request({
    method: "post",
    url: "/comment/save",
    data,
  });
}
