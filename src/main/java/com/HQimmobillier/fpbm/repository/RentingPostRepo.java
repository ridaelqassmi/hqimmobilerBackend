    package com.HQimmobillier.fpbm.repository;

    import com.HQimmobillier.fpbm.entity.Categories;
    import com.HQimmobillier.fpbm.entity.RentingPost;
    import com.HQimmobillier.fpbm.entity.User;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.web.multipart.MultipartFile;

    import java.util.List;

    public interface RentingPostRepo extends JpaRepository<RentingPost,Long> {

        List<RentingPost> findAllRentingPostsByUser(User user);
        Page<RentingPost> findAll(Pageable p);

        Page<RentingPost> findAllByCategories(Categories categories, Pageable pageRequest);
        Page<RentingPost> findByTitleContaining(String title,Pageable pageRequest);
        List<RentingPost> findAllByCategories(Categories categories);
        List<RentingPost> findByPriceBetween(float min,float max);

        List<RentingPost> findByNumberRoomBetween(int min, int max);

        List<RentingPost> findByAreaSizeBetween(float min, float max);


        @Query(value = "SELECT * from post p inner join renting_post rp on p.id = rp.id  " +
                "where (COALESCE(:title,NULL) IS NULL OR p.title LIKE CONCAT('%',:title,'%'))" +
                "AND (COALESCE(NULLIF(:cityId, 0) ,NULL) IS NULL OR  p.cities_id=:cityId )" +
                "AND (COALESCE(NULLIF(:Categories, 0) ,NULL) IS NULL OR p.categories_id = :Categories )" +
                "AND (COALESCE(NULLIF(:priceMin, 0) ,NULLIF(:priceMax, 0),NULL) IS NULL OR p.price BETWEEN :priceMin AND :priceMax)" +
                "AND (COALESCE(NULLIF(:minRoom, 0) ,NULLIF(:maxRoom, 0) ,NULL) IS NULL OR p.number_room BETWEEN :minRoom AND :maxRoom)" +
                "AND (COALESCE(NULLIF(:minArea, 0) ,NULLIF(:maxArea, 0),NULL ) IS NULL OR p.area_size BETWEEN :minArea AND :maxArea)"
                ,nativeQuery = true
        )
        Page<RentingPost>  findByCostumeFilter(@Param("title") String title,@Param("cityId") Long CityId,@Param("Categories") Long categoryId,
                                               @Param("priceMin") double priceMin,@Param("priceMax") double priceMax, @Param("minRoom") int MinRoom,@Param("maxRoom")
                                               int MaxRoom, @Param("minArea") float minArea, @Param("maxArea") float MaxArea, Pageable pageRequest);


        List<RentingPost> findByTitleLike(String title);



    }
