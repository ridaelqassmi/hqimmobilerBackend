package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.entity.Categories;
import com.HQimmobillier.fpbm.repository.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;



public interface CategorieService {
    public List<Categories> findAll();
    public Categories createCategorie(Categories categories);
    public void deleteCategorie(Long id);
    public Categories update(Categories categories,Long id);


}
