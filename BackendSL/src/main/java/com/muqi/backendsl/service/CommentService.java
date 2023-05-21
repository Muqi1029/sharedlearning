package com.muqi.backendsl.service;

import com.muqi.backendsl.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muqi.backendsl.model.dto.CommentDTO;

import java.util.List;

/**
* @author mq
* @description 针对表【t_comment】的数据库操作Service
* @createDate 2023-03-23 20:53:43
*/
public interface CommentService extends IService<Comment> {

    List<CommentDTO> listCommentByArticleID(Integer articleID);
}
