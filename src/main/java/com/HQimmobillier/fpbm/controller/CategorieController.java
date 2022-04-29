package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.Categories;
import com.HQimmobillier.fpbm.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CategorieController {
    @Autowired
    CategorieService categorieService;
    @GetMapping("/categories")
    public List<Categories> getAll(){
        return categorieService.findAll();
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
