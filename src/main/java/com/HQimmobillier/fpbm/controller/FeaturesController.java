package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.dto.AddFeaturesRequest;
import com.HQimmobillier.fpbm.entity.Features;
import com.HQimmobillier.fpbm.services.FeaturesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FeaturesController {
    private final FeaturesService featuresService;

    public FeaturesController(FeaturesService featuresService) {
        this.featuresService = featuresService;
    }

    @PostMapping("api/admin/features")
    public Features addFeature(AddFeaturesRequest addFeaturesRequest) throws IOException {
        return  featuresService.addFeatures(addFeaturesRequest);
    }

    @GetMapping("api/features")
    public List<Features> getAllFeatures(){
        return featuresService.getAllFeatures();
    }

}
