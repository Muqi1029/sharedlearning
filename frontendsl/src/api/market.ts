import request from "@/utils/request";

/**
 * tag:
 * 0: all
 * 1: 生活用品
 * 2: book
 * 3. elec
 */
export function getMarketByTag(tag: number) {
  return request({
    url: "/market/tab/" + tag,
    method: "get",
  });
}
