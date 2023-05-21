package com.muqi.backendsl.utils;

import com.muqi.backendsl.entity.Link;
import com.muqi.backendsl.mapper.LinkMapper;
import com.muqi.backendsl.model.request.LinkRequest;
import com.muqi.backendsl.service.GPTService;
import com.muqi.backendsl.service.LinkService;
import com.muqi.backendsl.service.impl.GPTServiceImpl;
import com.muqi.backendsl.service.impl.LinkServiceImpl;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Data
public class GPTThread extends Thread {

    private GPTService gptService;

    private String tagName;

    private Integer tagID;

    private LinkMapper linkMapper;

    public GPTThread(GPTService gptService, LinkMapper linkMapper ) {
        this.gptService = gptService;
        this.linkMapper = linkMapper;
    }


    public void run() {
        try {
            List<String> gptRecommandation = gptService.getGPTRecommandation(gptService.getGPTPrompt(tagName));
            for (String url : gptRecommandation) {

                Link link = new Link();
                link.setUrl(url);
                link.setUserID(0);
                link.setTagID(tagID);
                link.setIsRecommend(1);
                String name;

                if (url.contains("github")) {
                    link.setSource(0);
                }

                if (!url.matches("^http.*")) {
                    url = "https://" + url;
                }
                if (!UrlUtil.checkUrl(url)) {
                    return;
                }
                if (url.contains(".pdf")) {
                    List<String> urls = List.of(url.split("/"));
                    for (String urlPart : urls) {
                        if (urlPart.contains("pdf")) {
                            link.setName(urlPart);
                        }
                    }
                }else {
                    Document document = Jsoup.connect(url).get();
                    name = document.title();
                    link.setName(name);
                }
                System.out.println(link);
                linkMapper.insert(link);




            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
