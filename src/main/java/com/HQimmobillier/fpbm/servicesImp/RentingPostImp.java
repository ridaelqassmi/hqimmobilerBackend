package com.HQimmobillier.fpbm.servicesImp;

import com.HQimmobillier.fpbm.entity.Categories;
import com.HQimmobillier.fpbm.entity.Cities;
import com.HQimmobillier.fpbm.entity.RentingPost;
import com.HQimmobillier.fpbm.repository.CitiesRepo;
import com.HQimmobillier.fpbm.repository.RentingPostRepo;
import com.HQimmobillier.fpbm.services.CategorieService;
import com.HQimmobillier.fpbm.services.CityService;
import com.HQimmobillier.fpbm.services.RentingPostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RentingPostImp implements RentingPostService {
    @Autowired
    RentingPostRepo rentingPostRepo;
    @Autowired
    CategorieService categorieService;
    @Autowired
    CityService cityService;
    public List<RentingPost> findAll(){
        return rentingPostRepo.findAll();
    }
    public RentingPost create(long categorie_id,long cities_id,String rentingPost) throws JsonProcessingException {
        ObjectMapper objectMapper =new ObjectMapper();
        RentingPost rentingPost1 = objectMapper.readValue(rentingPost,RentingPost.class);
        Categories categories = categorieService.findById(categorie_id);
        rentingPost1.setCategories(categories);
        Cities city = cityService.findById(cities_id);
        rentingPost1.setCities(city);
        return rentingPostRepo.save(rentingPost1);



    }
}
