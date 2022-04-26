package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories,Long> {

}
