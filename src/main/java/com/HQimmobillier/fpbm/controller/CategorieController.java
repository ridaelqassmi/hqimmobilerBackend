package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.Categories;
import com.HQimmobillier.fpbm.repository.CategoriesRepo;
import com.HQimmobillier.fpbm.services.CategorieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin
public class CategorieController {
    private final CategoriesRepo categoriesRepo;
    private final CategorieService categorieService;

    public CategorieController(CategoriesRepo categoriesRepo, CategorieService categorieService) {
        this.categoriesRepo = categoriesRepo;
        this.categorieService = categorieService;
    }

    @GetMapping("/categories")
    public List<Categories> getAll(){

        return categorieService.findAll();
    }

    @GetMapping("/categories/{id}")
    public Categories getCategorieById(@PathVariable("id") Long id){
        return categoriesRepo.findById(id).get();
    }
    @PostMapping("/categories")
    public Categories create(@RequestBody Categories categories){
        return categorieService.createCategorie(categories);
    }

    @PutMapping("/categories/{id}")
    public Categories update(@RequestBody Categories categories, @PathVariable Long id){
        return categorieService.update(categories,id);
    }
    @DeleteMapping("/categories/{id}")
    public void deleteCategorie(@PathVariable long id){
         categorieService.deleteCategorie(id);
    }

}
