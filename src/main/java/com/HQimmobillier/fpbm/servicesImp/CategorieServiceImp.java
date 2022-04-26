package com.HQimmobillier.fpbm.servicesImp;

import com.HQimmobillier.fpbm.entity.Categories;
import com.HQimmobillier.fpbm.repository.CategoriesRepo;
import com.HQimmobillier.fpbm.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategorieServiceImp implements CategorieService {
    @Autowired
    CategoriesRepo categoriesRepo;
    @Override
    public List<Categories> findAll() {
        return categoriesRepo.findAll();
    }

    @Override
    public Categories createCategorie(Categories categories) {
        return categoriesRepo.save(categories);
    }

    @Override
    public void deleteCategorie(Long id) {
        categoriesRepo.deleteAllById(Collections.singleton(id));
    }

    @Override
    public Categories update(Categories categories, Long id) {
        categories.setId(id);
        return categoriesRepo.save(categories);

    }
}
