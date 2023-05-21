export function deleteHTMLTag(content: any) {
  return content
    .replace(/<\/?[^>]*>/g, "")
    .replace(/[|]*\n/, "")
    .replace(/&npsp;/gi, "");
}

export const toPageTop = () => {
  window.scrollTo({
    top: 0,
  });
};
