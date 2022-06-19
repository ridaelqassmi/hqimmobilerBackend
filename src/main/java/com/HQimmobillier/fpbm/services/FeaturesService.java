package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.dto.AddFeaturesRequest;
import com.HQimmobillier.fpbm.entity.Features;

import java.io.IOException;
import java.util.List;

public interface FeaturesService {
    //add a feature
    public Features addFeatures(AddFeaturesRequest addFeaturesRequest) throws IOException;



    //delete feature
    public void DeleteFeatures(Long id);
    //get all feature
    public List<Features> getAllFeatures();


    Features updateFeature(Long id, Features feature);

    //get A feature by id
    public Features getFeatureById(Long id);
}
