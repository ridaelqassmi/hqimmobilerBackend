package com.HQimmobillier.fpbm.mapper;

import com.HQimmobillier.fpbm.dto.post.MyPostsResponseDto;
import com.HQimmobillier.fpbm.entity.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PostMapper {
    public List<MyPostsResponseDto> MapPostToMyPostResponseDto(List<Post> posts){
        List<MyPostsResponseDto> myPostsResponseDtos = new ArrayList<>();
        posts.forEach(post->{
            MyPostsResponseDto temp = new MyPostsResponseDto();
            temp.setTitle(post.getTitle());
            temp.setDate(post.getDate());
            temp.setId(post.getId());
            temp.setState(post.isApproved());
            temp.setType(post.getDiscriminatorValue());
            myPostsResponseDtos.add(temp);
        });
    return myPostsResponseDtos;
    }
}
