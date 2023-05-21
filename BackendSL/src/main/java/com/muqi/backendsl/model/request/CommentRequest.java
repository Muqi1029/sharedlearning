package com.muqi.backendsl.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest implements Serializable {

    private Integer articleID;

    private Integer userID;

    private String commentContent;

    private Integer parentID;

    private static final long serialVersionUID = 1L;
}
