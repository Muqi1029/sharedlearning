package com.muqi.backendsl.strategy;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

public interface ArticleImportStrategy {


    void importArticles(MultipartFile file, Integer userID);

}