package com.muqi.backendsl.controller;

import com.muqi.backendsl.entity.Comment;
import com.muqi.backendsl.model.dto.CommentDTO;
import com.muqi.backendsl.model.request.CommentRequest;
import com.muqi.backendsl.model.vo.ResultVO;
import com.muqi.backendsl.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

//    @RequestMapping("/{courseID}")
//    public ResultVO<CommentDTO>
    @Autowired
    private CommentService commentService;


    @GetMapping("/search/{articleID}")
    public ResultVO<List<CommentDTO>> listCommentByArticleID(@PathVariable("articleID")Integer articleID) {
        List<CommentDTO> commentDTOS = commentService.listCommentByArticleID(articleID);
        return ResultVO.ok(commentDTOS);
    }

    @PostMapping("/save")
    public ResultVO<?> saveComment(@RequestBody CommentRequest commentRequest) {
        Integer articleID = commentRequest.getArticleID();
        Integer userID = commentRequest.getUserID();
        String commentContent = commentRequest.getCommentContent();
        Integer parentID = commentRequest.getParentID();

        Comment comment = new Comment();
        comment.setUserID(userID);
        comment.setArticleID(articleID);
        comment.setContent(commentContent);
        comment.setParentID(parentID);

        boolean save = commentService.save(comment);
        if (save) {
            return ResultVO.ok();
        }
        return ResultVO.fail();

    }

    @GetMapping("/{id}/replies")
    public ResultVO<List<CommentDTO>> listCommentRepliesByID(@PathVariable("id") Integer id) {
        return null;
    }



}
