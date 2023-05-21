package com.muqi.backendsl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muqi.backendsl.entity.Tag;
import com.muqi.backendsl.mapper.TagMapper;
import com.muqi.backendsl.mapper.UserCourseMapper;
import com.muqi.backendsl.model.request.LinkRequest;
import com.muqi.backendsl.model.vo.RecommendLinkVO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class LinkServiceTest {

    @Autowired
    private LinkService linkService;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private GPTService gptService;

    @Test
    public void getRecommendURLTest() {
        int userID = 12;

        // 1. 根据userID获取最感兴趣的courseID
        int courseID = userCourseMapper.getMaximumCourseIDByClick(userID);

        // 2. 根据courseID随机获取对应的tag
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.eq("courseID", courseID);
        List<Tag> tags = tagMapper.selectList(tagQueryWrapper);

        Random random = new Random();
        int r = random.nextInt(tags.size());

        System.out.println(tags.get(r));
    }

    @Test
    public void GptTest() {
        List<String> recommendURL = null;
        try {
            recommendURL = gptService.getGPTRecommandation(gptService.getGPTPrompt("矩阵分解"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(recommendURL);
    }

    @Test
    public void LinkPreview() {
        String url = "https://www.bilibili.com/video/BV1iW411d7hd/";
//        url = "https://www.bilibili.com/video/BV1cD4y1D7uR/?vd_source=4d8586f3dded416b864b75d3304fb8ef";

        try {
            Document document = Jsoup.connect(url).get();
            Element icon = document.select("link[href~=.*\\.(ico|png)]").first();
//            String iconUrl = icon.attr("href");
//            System.out.println(iconUrl);
            System.out.println(document.title());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getRecommendURLTestGPT() {
        int userID = 12;
        RecommendLinkVO recommendURL = linkService.getRecommendURL(userID);
    }

    @Test
    public void insertLinkTest() {
        String url = "https://github.com/kjunelee/MetaOptNet";
        LinkRequest linkRequest = new LinkRequest();
        linkRequest.setUrl(url);
        linkRequest.setUserID(0);
        linkRequest.setTagID(3);
        linkRequest.setSource(0);
        linkRequest.setIsRecommend(1);

        assert linkService.saveLink(linkRequest);

    }
}
