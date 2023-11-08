export function deleteHTMLTag(content: string) {
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
