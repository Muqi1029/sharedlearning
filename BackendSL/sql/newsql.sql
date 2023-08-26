-- auto-generated definition
create table t_article
(
    id             bigint auto_increment
        primary key,
    userID         bigint                               not null comment '文章作者',
    articleCover   varchar(1024)                        null comment '文章缩略图',
    articleTitle   varchar(50)                          not null comment '标题',
    articleContent longtext                             not null comment '文章内容',
    isFeatured     tinyint(1) default 0                 not null comment '0 - 不置顶
1 - 推荐',
    isDelete       tinyint    default 0                 not null comment '0 - 未删除
1 - 已删除',
    articleStatus  tinyint    default 0                 null comment '0 - 公开
1 - 私密
2 - 草稿',
    articleType    tinyint    default 0                 not null comment '0 - 原创
1 - 转载
2 - 翻译',
    originalURL    varchar(255)                         null comment '原文链接',
    createTime     datetime   default CURRENT_TIMESTAMP not null comment '发表时间',
    updateTime     datetime   default CURRENT_TIMESTAMP not null comment '更新时间',
    courseID       bigint                               not null comment '0 独立文章 >= 1 课程文章',
    rewardCount    int        default 0                 not null comment '打赏数',
    thumbUpCount   int        default 0                 not null comment '点赞数',
    commentCount   int        default 0                 not null comment '评论数',
    viewCount      int        default 0                 not null comment '观看数',
    isTop          int        default 0                 not null
);



DROP TABLE if exists t_comment;
-- auto-generated definition
create table t_comment
(
    id         int auto_increment
        primary key,
    userID     int                                 not null comment '用户iD（表示该条评论由哪条用户发出）',
    articleID  int                                 null comment '文章ID',
    parentID   int                                 null comment '父评论ID',
    isDelete   tinyint   default 0                 not null comment '是否删除',
    content    varchar(500)                        null comment '评论内容',
    createTime timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
    isReview   tinyint   default 1                 not null comment '是否审核'
);
-- auto-generated definition
create table t_course
(
    id          int auto_increment
        primary key,
    term        varchar(20)                           null comment '课程所在学期',
    category    varchar(30)                           null,
    professor   varchar(20)                           null,
    intro       varchar(1000)                         null,
    title       varchar(20) default '课程标题'        null,
    updateTime  datetime    default CURRENT_TIMESTAMP null comment '修改时间',
    createTime  datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    isDelete    tinyint     default 0                 not null comment '课程是否删除',
    status      tinyint     default 0                 null comment '0 公开 1 私密 ',
    courseCover varchar(300)                          null
)
    comment '课程类';
-- auto-generated definition
create table t_link
(
    id          int auto_increment comment '主键id'
        primary key,
    url         varchar(300)      not null comment '链接url',
    courseID    int               null,
    isOffice    tinyint(1)        null comment '是否公有',
    userID      int               null comment '该链接属于哪个用户',
    name        varchar(200)      null comment '链接别名',
    isRecommend tinyint default 0 not null comment '是否能被推荐',
    source      tinyint default 1 not null comment '0:github 1:article ',
    tagID       int               null comment '表名该条链接属于哪个tag'
);


-- auto-generated definition
create table t_tag
(
    id          int auto_increment
        primary key,
    tag_name    varchar(100)                       not null comment '标签名',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    courseID    int                                not null comment '课程ID'
)
    comment '标签表';
-- auto-generated definition
create table t_user
(
    id            int auto_increment comment '用户ID'
        primary key,
    userName      varchar(256)                       null comment '用户昵称',
    loginAccount  varchar(256)                       null comment '登录账号',
    loginPassword varchar(32)                        not null comment '登录密码',
    avatarURL     varchar(1024)                      null comment '用户头像',
    gender        tinyint                            null comment '0: male 1: female',
    userAuthority tinyint  default 0                 null comment '0：普通用户 1：管理员 2：root',
    userStatus    tinyint  default 1                 not null comment '用户是否有效 0：无效 1：有效',
    isDelete      tinyint  default 0                 null comment '0：未删除 1：删除',
    userEmail     varchar(512)                       null comment '用户邮箱',
    createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    isSubscribe   tinyint  default 0                 null,
    intro         varchar(255)                       null,
    userPhone     varchar(30)                        null
);
-- auto-generated definition
create table t_user_course
(
    id       int auto_increment
        primary key,
    userID   int    not null comment '用户ID',
    courseID int    not null comment '课程ID',
    clicks   bigint not null comment '点击数'
)
    comment '记录用户点击课程次数';









