import request from "@/utils/request";

// TODO
export function getComments(articleID: number) {
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
