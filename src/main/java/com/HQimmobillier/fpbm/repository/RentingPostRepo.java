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


        @Query(value = "SELECT * from post inner join renting_post on post.id = renting_post.id  " +
                "where (COALESCE(:title,NULL) IS NULL OR post.title LIKE CONCAT('%',:title,'%'))" +
                "AND (COALESCE(NULLIF(:cityId, 0) ,NULL) IS NULL OR  post.cities_id=:cityId )" +
                "AND (COALESCE(NULLIF(:Categories, 0) ,NULL) IS NULL OR post.categories_id IN :Categories )" +
                "AND (COALESCE(NULLIF(:priceMin, 0) ,NULLIF(:priceMax, 0),NULL) IS NULL OR post.price BETWEEN :priceMin AND :priceMax)" +
                "AND (COALESCE(NULLIF(:minRoom, 0) ,NULLIF(:maxRoom, 0) ,NULL) IS NULL OR post.number_room BETWEEN :minRoom AND :maxRoom)" +
                "AND (COALESCE(NULLIF(:minArea, 0) ,NULLIF(:maxArea, 0),NULL ) IS NULL OR post.area_size BETWEEN :minArea AND :maxArea)" +
                "ORDER BY CASE when :sortByPrice = 1 AND :ASC=1 THEN post.price END , " +
                "CASE when :sortByPrice=1 AND :ASC=0 THEN post.price END DESC, " +
                "CASE when :byDate = 1 And :ASC=1 THEN post.date END desc "
                ,nativeQuery = true
        )
        Page<RentingPost>  findByCostumeFilter(@Param("title") String title,@Param("cityId") Long CityId,@Param("Categories") List<Long> categorieIds,
                                               @Param("priceMin") double priceMin,@Param("priceMax") double priceMax, @Param("minRoom") int MinRoom,@Param("maxRoom")
                                               int MaxRoom, @Param("minArea") float minArea, @Param("maxArea") float MaxArea,@Param("byDate") boolean OrderByDate
                ,@Param("sortByPrice") Boolean OrderByPrice,@Param("ASC") boolean asc, Pageable pageRequest);


        List<RentingPost> findByTitleLike(String title);



    }
