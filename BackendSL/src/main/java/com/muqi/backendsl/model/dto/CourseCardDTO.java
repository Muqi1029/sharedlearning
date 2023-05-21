package com.muqi.backendsl.model.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.muqi.backendsl.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCardDTO {

    private Integer id;

    /**
     * 课程所在学期
     */
    private String term;

    /**
     * 课程类型
     */
    private String category;

    /**
     * 课程教授
     */
    private String professor;

    /**
     * 课程简介
     */
    private String intro;

    /**
     * 课程标题
     */
    private String title;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 课程是否删除
     */
//    private Integer isDelete;

    /**
     * 0 公开 1 私密
     */
    private Integer status;

    /**
     * 课程封面
     */
    private String courseCover;

    /**
     *
     */
    private Tag[] tags;

}
