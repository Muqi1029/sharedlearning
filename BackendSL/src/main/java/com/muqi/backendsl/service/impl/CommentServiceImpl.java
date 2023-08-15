package com.muqi.backendsl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.backendsl.entity.Comment;
import com.muqi.backendsl.mapper.CommentMapper;
import com.muqi.backendsl.model.dto.CommentDTO;
import com.muqi.backendsl.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mq
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<CommentDTO> listCommentByArticleID(Integer articleID) {
        return commentMapper.selectCommentByArticleID(articleID);
    }
}
