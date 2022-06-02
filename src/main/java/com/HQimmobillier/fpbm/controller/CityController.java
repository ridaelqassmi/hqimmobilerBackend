package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.Cities;
import com.HQimmobillier.fpbm.services.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("api/cities")
    public List<Cities> getAllCities(){
        return cityService.findAll();
    }

}
