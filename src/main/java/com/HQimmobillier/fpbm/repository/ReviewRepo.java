package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.Review;
import com.HQimmobillier.fpbm.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ReviewRepo extends JpaRepository<Review,Long> {

    List<Review> findAllByPost(Post post);



    @Query(value = "select *  from review where post_id =:id",nativeQuery = true)
    List<Review> findAllReviewsByPost(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "Delete from review where post_id =:id",nativeQuery = true)
    public void deleteAllReviewForPost(@Param("id") Long id);
}
