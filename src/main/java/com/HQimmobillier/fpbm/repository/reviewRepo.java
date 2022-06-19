package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.review;
import com.HQimmobillier.fpbm.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface reviewRepo extends JpaRepository<review,Long> {

    List<review> findAllByPost(Post post);
}
