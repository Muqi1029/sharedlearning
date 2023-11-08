import request from "@/utils/request";

export function getLinkByCourseID(courseID: number) {
  return request({
    url: "/link/" + courseID,
    method: "get",
  });
}

export function importOfficialLink(data: any) {
  return request({
    data,
    method: "post",
    url: "/link/import/official",
  });
}

export function importPrivateLink(data: any) {
  return request({
    data,
    method: "post",
    url: "/link/import/private",
  });
}

export function getRecommendLinkByUserID(userID: number) {
  return request({
    url: "/link/recommend/" + userID,
    method: "get",
  });
}

export function changeLinkStatus(linkID: number, status: number) {
  return request({
    url: "/link/changeStatus",
    method: "post",
    data: {
      linkID,
      status,
    },
  });
}

export function getPendingLink() {
  return request({
    url: "/link/pendingLink",
  });
}
