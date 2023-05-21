package com.muqi.backendsl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.backendsl.entity.Comment;
import com.muqi.backendsl.model.dto.CommentDTO;
import com.muqi.backendsl.service.CommentService;
import com.muqi.backendsl.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author mq
* @description 针对表【t_comment】的数据库操作Service实现
* @createDate 2023-03-23 20:53:43
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDTO> listCommentByArticleID(Integer articleID) {

        return commentMapper.selectCommentByArticleID(articleID);
    }
}




