package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.dto.user.CommentDto;
import com.HQimmobillier.fpbm.entity.review;
import com.HQimmobillier.fpbm.exception.ApiRequestException;
import com.HQimmobillier.fpbm.services.reviewService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/api/post/")
public class CommentsController {
    private final reviewService commentsService;


    public CommentsController(reviewService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("{id}/comment")
    public List<review> getAllCommentByPost(@PathVariable("id") long Postid){
        return commentsService.getCommentsByPostId(Postid);
    }

    @PostMapping("comment")
    public review addComment(@RequestBody  CommentDto commentsDto){
        System.out.println(commentsDto.getMessage());
        return commentsService.addCommentToPost(commentsDto);
    }

    @DeleteMapping("comment/{id}")
    public void RemoveComment(@PathVariable("id") long commentid) throws ApiRequestException {
        commentsService.removeCommentToPost(commentid);

    }


}
