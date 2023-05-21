package com.muqi.backendsl.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户点击课程的请求
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseRequest implements Serializable {

    /**
     * 用户ID
     */
    private Integer userID;

    /**
     * 课程ID
     */
    private Integer courseID;

    private static final long serialVersionUID = 1L;
}
