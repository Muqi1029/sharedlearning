package com.muqi.backendsl.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.muqi.backendsl.entity.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendLinkVO implements Serializable {

    /**
     * githubList
     */
    private List<Link> githubList;

    /**
     *
     */
    private List<Link> articleList;

    /**
     *
     */
    private List<Link> otherList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
