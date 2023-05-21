export function saveData(data: any) {
  localStorage.setItem("favList", JSON.stringify(data));
}

export function readData() {
  // console.log(JSON.parse(localStorage.getItem("favList") || "[]"));
  return JSON.parse(localStorage.getItem("favList") || "[]");
}
