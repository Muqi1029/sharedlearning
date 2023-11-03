package com.muqi.backendsl.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkRequest implements Serializable {

    /**
     * url名称
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     * this link uploaded by userID
     */
    private Integer userID;

    /**
     * the course this link belong to
     */
    private Integer courseID;

    private Integer tagID;

    /**
     * source
     * 0: github
     * 1: article
     */
    private Integer source;

    private Integer isRecommend;


    private static final long serialVersionUID = 1L;
}
