package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.PostImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PostImagesRepo extends JpaRepository<PostImages,Long> {
    @Transactional
    @Modifying
    @Query(value = "Delete from image where  post_id=:id",nativeQuery = true)
    public void deleteAllImagesForPost(@Param("id") Long id);

}
