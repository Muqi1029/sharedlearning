package com.muqi.backendsl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muqi.backendsl.mapper.UserCourseMapper;
import com.muqi.backendsl.model.request.LinkRequest;
import com.muqi.backendsl.model.vo.RecommendLinkVO;
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
