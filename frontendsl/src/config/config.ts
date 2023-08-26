export default {
  routes: [
    {
      name: "Home",
      path: "/home",

      i18n: {
        cn: "首页",
        en: "Home",
      },
      children: [],
    },
    {
      name: "Account",
      path: "/account",

      i18n: {
        cn: "个人账号",
        en: "Account",
      },

      children: [],
    },

    //------------------------------- TODO
    // {
    //     name: 'Message',
    //     path: '/message',

    //     i18n: {
    //         cn: '消息',
    //         en: 'Message'
    //     }
    // },

    // {
    //     name: 'Progress',
    //     path: '/progress',

    //     i18n: {
    //         cn: '发展历程',
    //         en: 'Progress'
    //     }
    // }
  ],
};
