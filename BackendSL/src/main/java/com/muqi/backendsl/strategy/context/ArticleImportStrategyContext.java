package com.muqi.backendsl.strategy.context;

import com.muqi.backendsl.strategy.ArticleImportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ArticleImportStrategyContext {


    @Autowired
    private ArticleImportStrategy articleImportStrategy;

    @Autowired
    private Map<String, ArticleImportStrategy> articleImportStrategyMap;


    public void importArticles(MultipartFile file, String type, Integer userID) {
        articleImportStrategy.importArticles(file, userID);
    }
}