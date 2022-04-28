package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.Categories;
import com.HQimmobillier.fpbm.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CategorieController {
    @Autowired
    CategorieService categorieService;
    @GetMapping("/categorie")
    public List<Categories> getAll(){
        return categorieService.findAll();
    }
    @PostMapping("/categorie")
    public Categories create(@RequestBody Categories categories){
        return categorieService.createCategorie(categories);
    }
    @PostMapping("/categorie/update/{id}")
    public Categories update(@RequestBody Categories categories, @PathVariable Long id){
        return categorieService.update(categories,id);
    }
    @DeleteMapping("/categorie/{id}")
    public void deleteCategorie(@PathVariable long id){
         categorieService.deleteCategorie(id);
    }

}
