package com.HQimmobillier.fpbm.services;


import com.HQimmobillier.fpbm.dto.user.FilterDto;
import com.HQimmobillier.fpbm.entity.RentingPost;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PostService {
    public RentingPost createRentingPost(long id_city,
                                         long id_categorie,
                                         MultipartFile[] file,
                                         String RentingPost) throws IOException;


    public List<RentingPost> getAllRentingPosts(String title, Integer page);
    public Page<RentingPost> getRentingPostWithPagination(int page, int size);
    public Page<RentingPost> getRentingPostByCategories(int page,int size,long id);





    RentingPost findRentingPostById(long id);
    public Page<RentingPost> sortRentingPostBy(String field, Optional<String> typeOfSort, Optional<Integer> page, int size);
    public Page<RentingPost> getRentingPostByTitle(String title,int page);
    public  List<RentingPost> getRentingPostFilter(FilterDto filter);
}
