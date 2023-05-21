package com.muqi.backendsl.service;

import com.muqi.backendsl.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muqi.backendsl.model.dto.LinkDTO;
import com.muqi.backendsl.model.request.LinkRequest;
import com.muqi.backendsl.model.vo.RecommendLinkVO;

import java.util.List;

/**
* @author mq
* @description 针对表【t_link】的数据库操作Service
* @createDate 2023-03-02 18:08:04
*/
public interface LinkService extends IService<Link> {

    LinkDTO listLinkByCourseIDOpen(int courseID);

    LinkDTO listLinkByCourseIDPrivate(int courseID, int userID);

    /**
     * 根据courseID找到相对应的link
     * @param courseID 课程ID
     * @return 该课程相关的List<Link>
     */
    List<Link> listLinkByCourseID(Integer courseID);

    /**
     * 根据用户ID获取推荐的URL
     * @param userID 用户ID
     * @return 包装List<Link>的RecommendLinkVO
     */
    RecommendLinkVO getRecommendURL(Integer userID);

    /**
     *
     * 用户保存link
     * @param linkRequest 包装link信息的类
     * @return The result of saving link
     */
    boolean saveLink(LinkRequest linkRequest);
}
