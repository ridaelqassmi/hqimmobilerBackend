package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.dto.AddFeaturesRequest;
import com.HQimmobillier.fpbm.entity.Features;
import com.HQimmobillier.fpbm.repository.FeaturesRepo;
import com.HQimmobillier.fpbm.services.FeaturesService;
import com.HQimmobillier.fpbm.utility.CommenFunctions;
import com.HQimmobillier.fpbm.utility.Constants;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public class FeaturesServiceImpl implements FeaturesService {
    private final FeaturesRepo featuresRepo;

    public FeaturesServiceImpl(FeaturesRepo featuresRepo) {
        this.featuresRepo = featuresRepo;
    }

    @Override
    public Features addFeatures(AddFeaturesRequest addFeaturesRequest) throws IOException {

        String filePath = CommenFunctions.saveFile(Constants.featuresImagesPath,addFeaturesRequest.getFile());
        Features features = new Features();
        features.setName(addFeaturesRequest.getName());
        features.setImageSrc(filePath);
        return featuresRepo.save(features);
    }




    @Override
    public void DeleteFeatures(Long id) {
        try {
            featuresRepo.deleteById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Features> getAllFeatures() {
        return featuresRepo.findAll();
    }

    @Override
    public Features updateFeature(Long id, Features feature) {
        Features c = getFeatureById(id);
        c.setName(feature.getName());
        c.setImageSrc(feature.getImageSrc());
        return c;
    }

    @Override
    public Features getFeatureById(Long id) {
        return  featuresRepo.findById(id).get();
    }

    @Override
    public Features updateFeature(AddFeaturesRequest addFeaturesRequest) throws IOException {
        Features features1 =  featuresRepo.findById(addFeaturesRequest.getId()).get();
        //supprimer l'image physic
        File f = new File(Constants.defaultPath+features1.getImageSrc());
        if(f.delete()){
            System.out.println("deleted secuudfie");
        }
        //save the new image
        String filePath = CommenFunctions.saveFile(Constants.featuresImagesPath,addFeaturesRequest.getFile());


        features1.setName(addFeaturesRequest.getName());
        features1.setImageSrc(filePath);
        return featuresRepo.save(features1);

    }

    @Override
    public void deleteFeature(long id) {
        Features features1 =  featuresRepo.findById(id).get();
        File f = new File(Constants.defaultPath+features1.getImageSrc());
        if(f.delete()){
            System.out.println("deleted secuudfie");
        }
        featuresRepo.delete(features1);
    }


}
