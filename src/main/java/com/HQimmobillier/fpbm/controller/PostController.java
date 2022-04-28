package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.Post;
import com.HQimmobillier.fpbm.entity.RentingPost;
import com.HQimmobillier.fpbm.entity.buyingPost;
import com.HQimmobillier.fpbm.repository.BuyingPostRepo;
import com.HQimmobillier.fpbm.repository.PostRepository;
import com.HQimmobillier.fpbm.repository.RentingPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    RentingPostRepo rentingPostRepo;
    @Autowired
    BuyingPostRepo buyingPostRepo;
    @Autowired
    PostRepository postRepository;
    @GetMapping("/posts/buy")
    public List<buyingPost> findAllbuy(){
        return buyingPostRepo.findAll();
    }
    /*@GetMapping("/posts/rent")
    public List<RentingPost> findALL(){
        return rentingPostRepo.findAll();
    }*/
    @GetMapping("posts/{posttype}")
    public List<Post> getall(@PathVariable String posttype){
        return  postRepository.findAll();
    }



}
