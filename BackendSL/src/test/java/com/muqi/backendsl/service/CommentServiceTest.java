package com.muqi.backendsl.service;

import com.muqi.backendsl.entity.Comment;
import com.muqi.backendsl.mapper.CommentMapper;
import com.muqi.backendsl.model.dto.CommentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void selectCommentTest() {
        int articleID = 1;
        List<CommentDTO> commentDTOS = commentService.listCommentByArticleID(articleID);
        for (CommentDTO commentDTO: commentDTOS) {
            System.out.println(commentDTO);
        }
    }

    @Test
    public void addCommentTest() {
        int articleID = 1;
        int userID = 12;

        Comment comment = new Comment();
        comment.setContent("这是一条评论, hello, world");
        comment.setArticleID(articleID);
        comment.setUserID(userID);

        commentMapper.insert(comment);

    }
}
