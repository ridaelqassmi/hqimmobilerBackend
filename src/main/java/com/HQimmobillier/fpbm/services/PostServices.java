package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.entity.Post;
import com.HQimmobillier.fpbm.entity.RentingPost;
import org.springframework.web.multipart.MultipartFile;

public interface  PostServices {
   public RentingPost createRentingPost(long id_city,
                                        long id_categorie,
                                        MultipartFile[] file,
                                        long id_user,
                                        String RentingPost);

}
