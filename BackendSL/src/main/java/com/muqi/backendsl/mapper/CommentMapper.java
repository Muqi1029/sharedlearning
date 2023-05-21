package com.muqi.backendsl.mapper;

import com.muqi.backendsl.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muqi.backendsl.model.dto.CommentDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author mq
* @description 针对表【t_comment】的数据库操作Mapper
* @createDate 2023-03-23 20:53:43
* @Entity com.muqi.backendsl.entity.Comment
*/
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentDTO> selectCommentByArticleID(@Param("articleID") Integer articleID);
}




