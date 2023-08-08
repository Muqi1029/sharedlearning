package com.muqi.backendsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.backendsl.entity.Link;
import com.muqi.backendsl.entity.Tag;
import com.muqi.backendsl.mapper.TagMapper;
import com.muqi.backendsl.mapper.UserCourseMapper;
import com.muqi.backendsl.model.dto.LinkDTO;
import com.muqi.backendsl.model.request.LinkRequest;
import com.muqi.backendsl.model.vo.RecommendLinkVO;
import com.muqi.backendsl.service.LinkService;
import com.muqi.backendsl.mapper.LinkMapper;
import com.muqi.backendsl.utils.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author mq
 * @description 针对表【t_link】的数据库操作Service实现
 * @createDate 2023-03-02 18:08:04
 */
@Service
@Slf4j
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
        implements LinkService {



    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Autowired
    private TagMapper tagMapper;

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


        // 1. 根据userID获取最感兴趣的courseID
        int courseID = userCourseMapper.getMaximumCourseIDByClick(userID);

        // 2. 根据courseID随机获取对应的tag
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.eq("courseID", courseID);
        List<Tag> tags = tagMapper.selectList(tagQueryWrapper);

        if (tags.size() == 0) {
            log.error("id为%d的课程的tag为空", courseID);
        }

        Random random = new Random();
        int r = random.nextInt(tags.size());

        /**
         * 最终获取到的tag
         */
        Tag tag = tags.get(r);
        int tagID = tag.getId();
        String tagName = tag.getTag_name();

        RecommendLinkVO recommendLinkVO = new RecommendLinkVO();
        List<Link> githubList = new ArrayList<>();
        List<Link> articleList = new ArrayList<>();

        QueryWrapper<Link> linkQueryWrapper = new QueryWrapper<>();

        linkQueryWrapper.eq("tagID", tagID);

        List<Link> links = linkMapper.selectList(linkQueryWrapper);

        for (Link link : links) {
            int i = link.getSource();
            if (i == 0) {
                githubList.add(link);
            } else if (i == 1) {
                articleList.add(link);
            }
        }

        recommendLinkVO.setGithubList(githubList);
        recommendLinkVO.setArticleList(articleList);


        return recommendLinkVO;

    }

    @Override
    public boolean saveLink(LinkRequest linkRequest) {

        String url = linkRequest.getUrl();

        String name;
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
            Document document = Jsoup.connect(url).get();
            name = document.title();
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


        boolean save = this.save(link);
        if (save) {
            return true;
        }
        return false;
    }

}




