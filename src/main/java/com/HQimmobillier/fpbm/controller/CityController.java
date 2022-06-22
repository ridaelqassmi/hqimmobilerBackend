package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.Ville;
import com.HQimmobillier.fpbm.services.VilleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    private final VilleService cityService;

    public CityController(VilleService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("api/cities")
    public List<Ville> getAllCities(){
        return cityService.findAll();
    }
    @GetMapping("api/cities/region/{id}")
    public List<Ville> getAllCitiesByRegion(@PathVariable int id){
        return cityService.getAllCitiesByRegion(id);
    }

}
