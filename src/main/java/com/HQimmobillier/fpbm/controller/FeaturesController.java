package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.dto.user.features.AddFeaturesRequest;
import com.HQimmobillier.fpbm.entity.Features;
import com.HQimmobillier.fpbm.services.FeaturesService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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

}
