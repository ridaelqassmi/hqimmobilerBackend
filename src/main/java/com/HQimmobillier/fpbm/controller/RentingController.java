package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.RentingPost;
import com.HQimmobillier.fpbm.repository.RentingPostRepo;
import com.HQimmobillier.fpbm.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class RentingController {
    @Autowired
    PostService postService;
    @Autowired
    RentingPostRepo rentingPostRepo;

    @GetMapping("rent")
    public List<RentingPost> findAll(){
        return postService.getAllRentingPosts();
    }
    @PostMapping("rent")
    public RentingPost create(long idCity,
                              long idCategorie,
                              MultipartFile[] file,
                              long idUser,
                              String RentingPost) throws IOException {
        return postService.createRentingPost(idCity,idCategorie,file,idUser,RentingPost);
    }
    @PutMapping("rent/{id}")
    public RentingPost update(@PathVariable long id, RentingPost rentingPost){
        RentingPost post = postService.findRentingPostById(id);
        post.setTitle(rentingPost.getTitle());
        post.setDuree(rentingPost.getDuree());
        post.setPrice(rentingPost.getPrice());
        post.setAreaSize(rentingPost.getAreaSize());
        post.setCategories(rentingPost.getCategories());
        post.setNumberRoom(rentingPost.getNumberRoom());
        return rentingPostRepo.save(post);


    }
}
