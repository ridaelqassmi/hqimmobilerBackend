package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.Comments;
import com.HQimmobillier.fpbm.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepo extends JpaRepository<Comments,Long> {

    List<Comments> findAllByPost(Post post);
}
