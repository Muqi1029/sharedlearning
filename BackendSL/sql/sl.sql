CREATE DATABASE IF NOT EXISTS sharedlearning;
USE sharedlearning;


DROP TABLE IF EXISTS `t_user`;
-- --------------------------
-- table structure for t_user
-- ---------------------------

CREATE TABLE t_user
(
    id            int auto_increment comment '用户ID'
        primary key,
    userName      varchar(256)                       null comment '用户昵称',
    loginAccount  varchar(256)                       null comment '登录账号',
    loginPassword varchar(32)                        not null comment '登录密码',
    avatarURL     varchar(1024)                      null comment '用户头像',
    gender        tinyint                            null comment '0: male 1: female',
    userAuthority tinyint  default 0                 null comment '0：普通用户 1：管理员 2：root',
    isDelete      tinyint  default 0                 null comment '0：未删除 1：删除',
    userEmail     varchar(512)                       null comment '用户邮箱',
    createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    intro         varchar(255)                       null,
    userPhone     varchar(30)                        null,
    token         varchar(100)                       null
) COMMENT '用户类' engine = InnoDB;

-- --------------------
-- Records of t_user
-- --------------------

INSERT INTO t_user (userName, loginAccount, loginPassword, avatarURL, userAuthority)
VALUES ('test', '123456789', '0bc415306d4250a869dfa71a5ae86179', 'http://localhost:8000/api/image/userAvatar/test.jpg',
        0)
     , ('admin', '12345678', '2b772da1e8dc19e0d05948dc3e4dfa5c', 'http://localhost:8000/api/image/daselogo.png', 1);

-- -------------------------------
-- table structure for t_course
-- -------------------------------
DROP TABLE IF EXISTS t_course;
CREATE TABLE t_course
(
    id           int auto_increment
        primary key,
    term         varchar(20)                           null comment '课程所在学期',
    category     varchar(30)                           null,
    professor    varchar(20)                           null,
    intro        varchar(500)                          null,
    title        varchar(20) default '课程标题'        null,
    updateTime   datetime    default CURRENT_TIMESTAMP null comment '修改时间',
    createTime   datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    isDelete     tinyint     default 0                 not null comment '课程是否删除',
    courseStatus tinyint     default 0                 null comment '0 公开 1 私密 ',
    courseCover  varchar(300)                          null
)
    COMMENT '课程类' engine = InnoDB;

-- --------------------------
-- Records of t_course
-- ------------------------
insert into t_course (term, category, professor, intro, title)
VALUES ('大一下', '概率论', '倪葎', '概率论与数理统计', '概率论与数理统计')
;



-- ------------------------------
-- table structure for t_article
-- ------------------------------
DROP TABLE IF EXISTS t_article;

create table t_article
(
    id             bigint auto_increment
        primary key,
    userID         bigint                                not null comment '文章作者',
    articleCover   varchar(1024)                         null comment '文章缩略图',
    articleTitle   varchar(50) default '文章标题'        not null comment '标题',
    articleContent longtext                              not null comment '文章内容',
    isTop          tinyint(1)  default 0                 not null comment '0 - 不置顶
1 - 置顶',
    isDelete       tinyint     default 0                 not null comment '0 - 未删除
1 - 已删除',
    articleStatus  tinyint     default 0                 null comment '0 - 公开
1 - 私密
2 - 草稿',
    articleType    tinyint     default 0                 not null comment '0 - 原创
1 - 转载
2 - 翻译',
    originalURL    varchar(255)                          null comment '原文链接',
    createTime     datetime    default CURRENT_TIMESTAMP not null comment '发表时间',
    updateTime     datetime    default CURRENT_TIMESTAMP not null comment '更新时间',
    courseID       bigint                                not null comment '0 独立文章 >= 1 课程文章',
    rewardCount    int         default 0                 not null comment '打赏数',
    thumbUpCount   int         default 0                 not null comment '点赞数',
    commentCount   int         default 0                 not null comment '评论数',
    viewCount      int         default 0                 not null comment '观看数'
);
INSERT INTO t_article (userID, articleCover, articleTitle, articleContent, isFeatured, courseID)
VALUES (0, 'http://localhost:8000/api/image/courseCover/lackedu.png', '计算机教育中缺失的一课',
        'https://missing-semester-cn.github.io/', 1, 0),
       (0, 'http://localhost:8000/api/image/courseCover/crashcs.png', 'Crash Course Computer Science',
        'https://www.bilibili.com/video/BV1EW411u7th/', 1, 0),
       (0, 'http://localhost:8000/api/image/courseCover/csdiy.png', 'CS 学习规划',
        'https://csdiy.wiki/CS%E5%AD%A6%E4%B9%A0%E8%A7%84%E5%88%92/', 1, 0);



-- ------------------------
-- table structure for t_tag
-- ---------------------------

drop table if exists t_tag;
create table t_tag
(
    id          int auto_increment
        primary key,
    tag_name    varchar(20)                        not null comment '标签名',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null comment '更新时间'
)
    comment '标签表';


-- ------------------
-- Records of t_tag
-- ---------------------
# insert into t_tag();


-- -----------------
-- table structure of t_link
-- ------------------
DROP TABLE IF EXISTS t_link;
CREATE TABLE t_link
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
    tagID       int               null comment '表名该条链接属于哪个tag',
    cover       varchar(50)       null,
    linkStatus  tinyint default 0
);


INSERT INTO t_link(userID, cover, name, url, isRecommend, courseID, linkStatus)
VALUES (0, 'http://localhost:8000/api/image/courseCover/lackedu.png', '计算机教育中缺失的一课',
        'https://missing-semester-cn.github.io/', 1, 0, 1),
       (0, 'http://localhost:8000/api/image/courseCover/crashcs.png', 'Crash Course Computer Science',
        'https://www.bilibili.com/video/BV1EW411u7th/', 1, 0, 1),
       (0, 'http://localhost:8000/api/image/courseCover/csdiy.png', 'CS 学习规划',
        'https://csdiy.wiki/CS%E5%AD%A6%E4%B9%A0%E8%A7%84%E5%88%92/', 1, 0, 1);
insert into `t_link` (url, courseID, isOffice, userID, linkStatus)
values ('http://math.itdiffer.com/positive_definite.html', 1, 1, 12, 1),
       ('https://www.bilibili.com/video/BV1cD4y1D7uR/', 2, 1, 12, 1),
       ('https://www.bilibili.com/video/BV1iW411d7hd/', 2, 1, 12, 1),
       ('http://csapp.cs.cmu.edu/3e/labs.html', 2, 1, 12, 1);



-- ------------------------------
-- table structure of user_course
-- -------------------------------
create table t_user_course
(
    id       int auto_increment
        primary key,
    userID   int     not null comment '用户ID',
    courseID int     not null comment '课程ID',
    isValid  tinyint not null comment '是否有效'
)
    comment '用户收藏的课程';