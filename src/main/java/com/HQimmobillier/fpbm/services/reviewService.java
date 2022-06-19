package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.dto.user.CommentDto;
import com.HQimmobillier.fpbm.entity.review;
import com.HQimmobillier.fpbm.exception.ApiRequestException;

import java.util.List;

public interface reviewService {

    List<review> getCommentsByPostId(long id);



    review addCommentToPost(CommentDto commentDto);

    /*remove post*/
    public void removeCommentToPost(long id) throws ApiRequestException;
    /*get all reply to a specifique comments*/
    public List<review> getReplyForAspecificComment(long CommmetId);
}
