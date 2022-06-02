package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.dto.user.CommentDto;
import com.HQimmobillier.fpbm.entity.Comments;
import com.HQimmobillier.fpbm.entity.Post;
import com.HQimmobillier.fpbm.exception.ApiRequestException;
import com.HQimmobillier.fpbm.repository.CommentsRepo;
import com.HQimmobillier.fpbm.repository.PostRepository;
import com.HQimmobillier.fpbm.services.AccountService;
import com.HQimmobillier.fpbm.services.CommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentsRepo commentsRepo;
    private final PostRepository postRepository;
    private final AccountService accountService;

    public CommentsServiceImpl(CommentsRepo commentsRepo, PostRepository postRepository, AccountService accountService) {
        this.commentsRepo = commentsRepo;
        this.postRepository = postRepository;
        this.accountService = accountService;
    }

    @Override
    public List<Comments> getCommentsByPostId(long id) {
        Post post= postRepository.findById(id).get();
        return commentsRepo.findAllByPost(post);
    }

    @Override
    public Comments addCommentToPost(CommentDto commentDto) {
        Comments comments = new Comments();
        Post post= postRepository.findById(commentDto.getPostid()).get();
        comments.setUser(accountService.getAuthenticatedUser());
        comments.setPost(post);
        comments.setContent(commentDto.getMessage());
        return commentsRepo.save(comments);
    }

    @Override
    public void removeCommentToPost(long id) throws ApiRequestException {
        /*we have to remove it only if the user is the authentified and it is his own
        * comments
        * */
        Comments comments = commentsRepo.findById(id).get();
        if(comments.getUser() == accountService.getAuthenticatedUser()){
            commentsRepo.delete(comments);
        }
        else {
            throw new ApiRequestException("you are not authorized to delete this comments", HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @Override
    public List<Comments> getReplyForAspecificComment(long CommmetId) {
        return null;
    }
}
