package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.dto.user.CommentDto;
import com.HQimmobillier.fpbm.entity.Review;
import com.HQimmobillier.fpbm.exception.ApiRequestException;
import com.HQimmobillier.fpbm.services.reviewService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/api/post/")
public class ReviewController {
    private final reviewService commentsService;


    public ReviewController(reviewService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("{id}/comment")
    public List<Review> getAllCommentByPost(@PathVariable("id") long Postid){
        return commentsService.getCommentsByPostId(Postid);
    }

    @PostMapping("comment")
    public Review addComment(@RequestBody  CommentDto commentsDto){
        System.out.println(commentsDto.getMessage());
        return commentsService.addCommentToPost(commentsDto);
    }

    @DeleteMapping("comment/{id}")
    public void RemoveComment(@PathVariable("id") long commentid) throws ApiRequestException {
        commentsService.removeCommentToPost(commentid);

    }


}
