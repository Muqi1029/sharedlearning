import request from "@/utils/request";

export function upload_img(params: any, config: any) {
  return request.post("/editor/img", params, config);
}
