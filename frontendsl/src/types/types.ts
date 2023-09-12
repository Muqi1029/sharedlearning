/**
 * constrain the field
 */

/**
 * LoginForm
 */
export interface ILoginForm {
  readonly userName: string;
  readonly password: string;
}

export interface IUserInfo {
  userAvatar: string;
}

/**
 * course
 */

export interface ICourse {
  id: number;
  courseCover: string;
  title: string;
  intro: string;
  professor: string;
}

/**
 * article
 */
export interface IArticle {
  id?: number;
  articleContent: string;
  articleTitle: string;
  courseID?: number;
  intro?: string;
  author?: any;
  authorAvatar?: string;
  articleAvatar?: string;
  thumbupCount?: number;
  commentCount?: number;
  rewardCount?: number;
  articleStatus?: number;
  category?: string;
  semester?: string;
  createTime?: Date;
}

export interface ILink {
  id: number;
  url: string;
  courseID: number;
  ifOffice: number;
  userID: number;
  name: string;
}

export interface IMarketCard {
  id: number;
  price: number;
  userID: number; // 可以获取avatar
  name: string;
  marketCover?: string;
  userInfo: IUserInfo;
}

export interface ArticleVO {
  articleContent: string;
  userId: number;
  courseId: number;
}
