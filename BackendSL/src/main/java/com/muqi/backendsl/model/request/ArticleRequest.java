package com.muqi.backendsl.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {

    private String articleContent;

    private Long userId;

    private Long courseId;

    private static final long serialVersionUID = 1L;
}
