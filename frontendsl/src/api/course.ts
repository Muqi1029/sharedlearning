import request from "@/utils/request";

export function getCourses() {
  return request({
    url: "/course/all",
    method: "get",
  });
}

export function getCourseIntroById(id: number) {
  return request({
    url: "/course/getIntroById/" + id,
    method: "get",
  });
}
