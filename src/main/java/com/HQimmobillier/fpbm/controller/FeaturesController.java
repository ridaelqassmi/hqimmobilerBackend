package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.dto.AddFeaturesRequest;
import com.HQimmobillier.fpbm.entity.Features;
import com.HQimmobillier.fpbm.repository.FeaturesRepo;
import com.HQimmobillier.fpbm.services.FeaturesService;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class FeaturesController {
    private final FeaturesService featuresService;
    private final FeaturesRepo featuresRepo;

    public FeaturesController(FeaturesService featuresService, FeaturesRepo featuresRepo) {
        this.featuresService = featuresService;
        this.featuresRepo = featuresRepo;
    }

    @PostMapping("api/admin/features")
    public Features addFeature(AddFeaturesRequest addFeaturesRequest) throws IOException {
        return  featuresService.addFeatures(addFeaturesRequest);
    }

    @PutMapping("api/admin/features")
    public Features updateFeature(AddFeaturesRequest addFeaturesRequest) throws IOException {
        return featuresService.updateFeature(addFeaturesRequest);
    }
    @DeleteMapping("api/admin/features/{id}")
    public void deleteFeature(@PathVariable("id") long id){
        featuresService.deleteFeature(id);
    }

    @GetMapping("api/admin/features/{id}")
    public Features getFeatureById(@PathVariable("id") Long id){
        return featuresRepo.findById(id).get();
    }

    @GetMapping("api/features")
    public List<Features> getAllFeatures(){
        return featuresService.getAllFeatures();
    }

}
