package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.dto.user.CommentDto;
import com.HQimmobillier.fpbm.entity.Comments;
import com.HQimmobillier.fpbm.exception.ApiRequestException;
import com.HQimmobillier.fpbm.services.CommentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/comments")
public class CommentsController {
    private final CommentsService commentsService;


    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/post/{id}")
    public List<Comments> getAllCommentByPost(@PathVariable("id") long Postid){
        return commentsService.getCommentsByPostId(Postid);
    }

    @PostMapping("/post")
    public Comments addComment(@RequestBody  CommentDto commentsDto){
        return commentsService.addCommentToPost(commentsDto);
    }

    @DeleteMapping("/{id}")
    public void RemoveComment(@PathVariable("id") long commentid) throws ApiRequestException {
        commentsService.removeCommentToPost(commentid);

    }


}
