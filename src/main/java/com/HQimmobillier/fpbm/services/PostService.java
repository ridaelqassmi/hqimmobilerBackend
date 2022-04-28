package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.entity.Post;
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
    public List<RentingPost> getAll();
    public List<RentingPost> getAllRentingPostByUser();
    public List<Post> getAllRentingPosts();

}
