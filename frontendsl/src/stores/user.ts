import { defineStore } from "pinia";
import { getToken, setToken } from "@/utils/auth";
import { login, getUserInfoByID } from "@/api/user";
import { ILoginForm } from "@/types/types";

export const useUserStore = defineStore("userStore", {
  state: () => {
    return {
      token: getToken(),
      name: "",
      userID: "",
      avatarURL: "",
      introduction: "",
      roles: [],
      userInfo: "",
      isHave: false,
    };
  },

  actions: {
    userLogin({ username, password }: ILoginForm) {
      return new Promise<number>((resolve, reject) => {
        login({ loginAccount: username, loginPassword: password })
          // 结果调then
          .then((res) => {
            const { data } = res;
            if (data.id > 0) {
              setToken(data.id); // 在浏览器中设置token
              this.avatarURL = data.avatarURL;
              this.name = data.userName;
              this.userID = data.id;
              this.isHave = true;
              resolve(data.userAuthority as number); // 表示回调成功,改变返回的promise对象的状态
            } else {
              reject();
            }
          })
          .catch((error) => {
            reject(error);
            alert("用户不存在，请重新输入");
          });
      });
    },

    getUserByID(userID: number) {
      getUserInfoByID(userID).then(({ data }) => {
        this.avatarURL = data.avatarURL;
        this.name = data.userName;
        this.userID = data.id;
        this.isHave = true;
      });
    },
  },

  persist: {
    storage: window.sessionStorage,
  },
});
