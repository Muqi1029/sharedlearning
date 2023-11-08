package com.muqi.backendsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.backendsl.entity.Article;
import com.muqi.backendsl.entity.Link;
import com.muqi.backendsl.mapper.LinkMapper;
import com.muqi.backendsl.mapper.UserCourseMapper;
import com.muqi.backendsl.model.dto.LinkDTO;
import com.muqi.backendsl.model.request.LinkRequest;
import com.muqi.backendsl.model.vo.RecommendLinkVO;
import com.muqi.backendsl.service.LinkService;
import com.muqi.backendsl.utils.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author mq
 * @description 针对表【t_link】的数据库操作Service实现
 * @createDate 2023-03-02 18:08:04
 */
@Service
@Slf4j
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
        implements LinkService {


    @Resource
    private LinkMapper linkMapper;

    @Resource
    private UserCourseMapper userCourseMapper;


    @Override
    public LinkDTO listLinkByCourseIDOpen(int courseID) {

        return null;
    }

    @Override
    public LinkDTO listLinkByCourseIDPrivate(int courseID, int userID) {
        return null;
    }

    @Override
    public List<Link> listLinkByCourseID(Integer courseID) {
        LambdaQueryWrapper<Link> linkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        linkLambdaQueryWrapper.eq(Link::getCourseID, courseID);
        return linkMapper.selectList(linkLambdaQueryWrapper);
    }

    @Override
    public RecommendLinkVO getRecommendURL(Integer userID) {
        return null;
    }


    @Override
    public boolean saveLink(LinkRequest linkRequest) {

        String url = linkRequest.getUrl();

        String name = linkRequest.getName();
        Integer courseID = linkRequest.getCourseID();
        Integer userID = linkRequest.getUserID();
        Integer source = linkRequest.getSource();
        Integer tagID = linkRequest.getTagID();
        Integer isRecommend = linkRequest.getIsRecommend();

        try {
            if (!url.matches("^http.*")) {
                url = "https://" + url;
            }
            if (!UrlUtil.checkUrl(url)) {
                return false;
            }
            if (name == null) {
                Document document = Jsoup.connect(url).get();
                name = document.title();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Link link = new Link();
        link.setUrl(url);
        link.setName(name);
        link.setCourseID(courseID);
        link.setUserID(userID);
        link.setIsOffice(1);
        link.setSource(source);
        link.setTagID(tagID);
        link.setIsRecommend(isRecommend);

        return this.save(link);
    }

    @Override
    public List<LinkDTO> getPendingLink() {
        return linkMapper.getPendingLink();
    }

    @Override
    public boolean changeLinkStatus(int lId, int sId) {
        Link link = linkMapper.selectById(lId);
        if (link == null) {
            return false;
        }
        link.setLinkStatus(sId);
        return linkMapper.updateById(link) == 1;
    }


}




