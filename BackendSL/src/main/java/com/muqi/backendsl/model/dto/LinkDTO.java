package com.muqi.backendsl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkDTO {
    private Integer id;

    /**
     * 链接url
     */
    private String url;

    /**
     *
     */
    private Integer courseID;

    /**
     * 是否公有
     */
    private Integer isOffice;

    /**
     * 该链接属于哪个文章
     */
    private Integer userID;
}
