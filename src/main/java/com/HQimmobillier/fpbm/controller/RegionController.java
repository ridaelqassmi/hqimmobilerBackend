package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.Region;
import com.HQimmobillier.fpbm.repository.RegionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {
    private final RegionRepository regionRepository;
    public RegionController(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
    @GetMapping("/api/regions")
    public List<Region> getAllRegions(){
        return regionRepository.findAll();
    }

    @GetMapping("/api/regionss")
    public List<Region> getALLg(){
        return regionRepository.findAll();
    }
}
