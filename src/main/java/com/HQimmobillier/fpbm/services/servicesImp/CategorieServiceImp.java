package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.entity.Categories;
import com.HQimmobillier.fpbm.repository.CategoriesRepo;
import com.HQimmobillier.fpbm.services.CategorieService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategorieServiceImp implements CategorieService {

    private final CategoriesRepo categoriesRepo;

    public CategorieServiceImp(CategoriesRepo categoriesRepo) {
        this.categoriesRepo = categoriesRepo;
    }

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

    public Categories findById(Long id) {
        return categoriesRepo.findById(id).get();
    }
}
