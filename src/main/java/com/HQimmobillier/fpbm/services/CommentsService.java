package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.dto.user.CommentDto;
import com.HQimmobillier.fpbm.entity.Comments;
import com.HQimmobillier.fpbm.entity.Post;
import com.HQimmobillier.fpbm.exception.ApiRequestException;

import java.util.List;

public interface CommentsService {

    List<Comments> getCommentsByPostId(long id);



    Comments addCommentToPost(CommentDto commentDto);

    /*remove post*/
    public void removeCommentToPost(long id) throws ApiRequestException;
    /*get all reply to a specifique comments*/
    public List<Comments> getReplyForAspecificComment(long CommmetId);
}
