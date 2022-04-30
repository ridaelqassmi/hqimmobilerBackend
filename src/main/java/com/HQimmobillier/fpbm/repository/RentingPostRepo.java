package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.Categories;
import com.HQimmobillier.fpbm.entity.RentingPost;
import com.HQimmobillier.fpbm.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentingPostRepo extends JpaRepository<RentingPost,Long> {

    List<RentingPost> findAllRentingPostsByUser(User user);
    Page<RentingPost> findAll(Pageable p);

    Page<RentingPost> findAllByCategories(Categories categories, Pageable pageRequest);
}
