package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.entity.*;
import com.HQimmobillier.fpbm.repository.*;
import com.HQimmobillier.fpbm.services.CategorieService;
import com.HQimmobillier.fpbm.services.CityService;
import com.HQimmobillier.fpbm.services.PostImageService;
import com.HQimmobillier.fpbm.services.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    CategoriesRepo categoriesRepo;

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

    public Page<RentingPost> sortRentingPostBy(String field, Optional<String> typeOfSort, Optional<Integer> page, int size){
        Integer p;
        if(page.isPresent()){
            p=page.get();
        }else{
            p=0;
        }
        Pageable sorted;
        if(!typeOfSort.isPresent()){

            sorted = PageRequest.of(p, size, Sort.by(field).ascending());

        }else{
            sorted = PageRequest.of(p, size, Sort.by(field).descending());
        }
        return rentingPostRepo.findAll(sorted);

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
    public Page<RentingPost> getRentingPostByCategories(int page,int size,long id){
        Pageable pageRequest  = PageRequest.of(page,size);
        Categories categories = categoriesRepo.findById(id).get();
        return rentingPostRepo.findAllByCategories(categories,pageRequest);
    }
/************************************************BUYING POST SERVICES ********************************************/


}
