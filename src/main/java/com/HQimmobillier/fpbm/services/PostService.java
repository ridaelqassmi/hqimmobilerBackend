package com.HQimmobillier.fpbm.services;


import com.HQimmobillier.fpbm.entity.RentingPost;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
public interface PostService {
    public RentingPost createRentingPost(long id_city,
                                         long id_categorie,
                                         MultipartFile[] file,
                                         long id_user,
                                         String RentingPost) throws IOException;


    public List<RentingPost> getAllRentingPosts();





    RentingPost findRentingPostById(long id);
}
