package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.entity.*;
import com.HQimmobillier.fpbm.repository.PostImagesRepo;
import com.HQimmobillier.fpbm.repository.RentingPostRepo;
import com.HQimmobillier.fpbm.repository.UserRepo;
import com.HQimmobillier.fpbm.services.CategorieService;
import com.HQimmobillier.fpbm.services.CityService;
import com.HQimmobillier.fpbm.services.PostImageService;
import com.HQimmobillier.fpbm.services.PostService;
import com.HQimmobillier.fpbm.utility.CommenFunctions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service

public class RentingPostImp implements PostService {
    @Autowired
    RentingPostRepo rentingPostRepo;
    @Autowired
    CategorieService categorieService;
    @Autowired
    CityService cityService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostImagesRepo postImagesRepo;
    @Autowired
    PostImageService postImageService;


    @Override
    public RentingPost createRentingPost(long id_city,
                                         long id_categorie,
                                         MultipartFile[] file,
                                         long id_user,
                                         String RentingPost1) throws IOException {

        RentingPost rentingPost = new ObjectMapper().readValue(RentingPost1, RentingPost.class);
        rentingPost.setCities(cityService.findById(id_city));
        rentingPost.setUser(userRepo.findById(id_user).get());
        RentingPost rentingPost1 = rentingPostRepo.save(rentingPost);
        postImageService.saveImages(file,rentingPost1);


        return rentingPost1;
    }

    public List<RentingPost> getAll(){
        return rentingPostRepo.findAll();
    }

    @Override
    public List<RentingPost> getAllRentingPostByUser() {
        return null;
    }

    @Override
    public List<Post> getAllRentingPosts() {
        return null;
    }


}
