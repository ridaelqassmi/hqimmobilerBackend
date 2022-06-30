package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.dto.user.CommentDto;
import com.HQimmobillier.fpbm.entity.Review;
import com.HQimmobillier.fpbm.entity.Post;
import com.HQimmobillier.fpbm.exception.ApiRequestException;
import com.HQimmobillier.fpbm.repository.ReviewRepo;
import com.HQimmobillier.fpbm.repository.PostRepository;
import com.HQimmobillier.fpbm.services.AccountService;
import com.HQimmobillier.fpbm.services.reviewService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class reviewServiceImpl implements reviewService {
    private final ReviewRepo commentsRepo;
    private final PostRepository postRepository;
    private final AccountService accountService;

    public reviewServiceImpl(ReviewRepo commentsRepo, PostRepository postRepository, AccountService accountService) {
        this.commentsRepo = commentsRepo;
        this.postRepository = postRepository;
        this.accountService = accountService;
    }

    @Override
    public List<Review> getCommentsByPostId(long id) {
        Post post= postRepository.findById(id).get();
        return commentsRepo.findAllByPost(post);
    }

    @Override
    public Review addCommentToPost(CommentDto commentDto) {

        Review comments = new Review();
        Post post= postRepository.findById(commentDto.getPostid()).get();
        comments.setRating(commentDto.getRating());
        comments.setUser(accountService.getAuthenticatedUser());
        comments.setPost(post);
        comments.setContent(commentDto.getMessage());

        comments.setCreatedAt(Timestamp.from(Instant.now()));
        return commentsRepo.save(comments);
    }

    @Override
    public void removeCommentToPost(long id) throws ApiRequestException {
        /*we have to remove it only if the user is the authentified and it is his own
        * comments
        * */
        Review comments = commentsRepo.findById(id).get();
        if(comments.getUser() == accountService.getAuthenticatedUser()){
            commentsRepo.delete(comments);
        }
        else {
            throw new ApiRequestException("you are not authorized to delete this comments", HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @Override
    public List<Review> getReplyForAspecificComment(long CommmetId) {
        return null;
    }
}
