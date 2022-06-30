package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.BuyingPost;
import com.HQimmobillier.fpbm.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface BuyingPostRepo extends JpaRepository<BuyingPost,Long> {



    @Modifying
    @Transactional
    @Query(value = "DELETE FROM  buying_post where buying_post.id = :id ",nativeQuery = true)
    void MydeleteById(@Param("id") Long id);


    @Query(value = "SELECT * from post p   " +
            "where (COALESCE(:title,NULL) IS NULL OR p.title LIKE CONCAT('%',:title,'%'))" +
            "AND (COALESCE(NULLIF(:cityId, 0) ,NULL) IS NULL OR  p.cities_id=:cityId )" +
            "AND post_type='BUY'"+
            "AND (COALESCE(NULLIF(:Categories, 0) ,NULL) IS NULL OR p.categories_id = :Categories )" +
            "AND (COALESCE(NULLIF(:priceMin, 0) ,NULLIF(:priceMax, 0),NULL) IS NULL OR p.price BETWEEN :priceMin AND :priceMax)" +
            "AND (COALESCE(NULLIF(:minRoom, 0) ,NULLIF(:maxRoom, 0) ,NULL) IS NULL OR p.number_room BETWEEN :minRoom AND :maxRoom)" +
            "AND (COALESCE(NULLIF(:minArea, 0) ,NULLIF(:maxArea, 0),NULL ) IS NULL OR p.area_size BETWEEN :minArea AND :maxArea)"
            ,nativeQuery = true
    )
    Page<BuyingPost> findByCostumeFilter(@Param("title") String title,@Param("cityId") Long CityId,@Param("Categories") Long categoryId,
                                   @Param("priceMin") double priceMin,@Param("priceMax") double priceMax, @Param("minRoom") int MinRoom,@Param("maxRoom")
                                   int MaxRoom, @Param("minArea") float minArea, @Param("maxArea") float MaxArea, Pageable pageRequest);
}
