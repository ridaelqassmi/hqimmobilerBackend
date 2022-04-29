package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.entity.*;
import com.HQimmobillier.fpbm.repository.BuyingPostRepo;
import com.HQimmobillier.fpbm.repository.PostImagesRepo;
import com.HQimmobillier.fpbm.repository.RentingPostRepo;
import com.HQimmobillier.fpbm.repository.UserRepo;
import com.HQimmobillier.fpbm.services.CategorieService;
import com.HQimmobillier.fpbm.services.CityService;
import com.HQimmobillier.fpbm.services.PostImageService;
import com.HQimmobillier.fpbm.services.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service

public class PostServiceImp implements PostService {
    @Autowired
    RentingPostRepo rentingPostRepo;
    @Autowired
    BuyingPostRepo buyingPostRepo;
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

/*********************************************RENTING POST SERVICES ****************************************/
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
    public RentingPost findRentingPostById(long id){
        if(rentingPostRepo.findById(id).isPresent()){
            return rentingPostRepo.findById(id).get();
        }
        return null;
    }

    @Override
    public List<RentingPost> getAllRentingPosts() {
        return rentingPostRepo.findAll();
    }



    /*SORT POSTS BY ANY FIELD */

    public List<RentingPost> sortRentingPostBy(String field,String typeOfSort){
        if(typeOfSort.toLowerCase().equals("asc")){
            return rentingPostRepo.findAll(Sort.by(Sort.Direction.ASC,field));
        }else{
            return rentingPostRepo.findAll(Sort.by(Sort.Direction.DESC,field));
        }

    }

    /*find all Posts with pagination */
    public Page<RentingPost> getRentingPostWithPagination( int page, int size){
        return rentingPostRepo.findAll(PageRequest.of(page,size));
    }
    public List<RentingPost> getAllRentigPostByUserId(long id){
        User user = userRepo.findById(id).get();
        if(user != null){
           return  rentingPostRepo.findAllRentingPostsByUser(user);
        }
        return null;
    }

/************************************************BUYING POST SERVICES ********************************************/


}
