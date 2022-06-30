package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.dto.user.FilterDto;
import com.HQimmobillier.fpbm.entity.BuyingPost;
import com.HQimmobillier.fpbm.repository.BuyingPostRepo;
import com.HQimmobillier.fpbm.services.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class BuyingController {

    private  final BuyingPostRepo buyingPostRepo;
    private final PostService postService;

    public BuyingController(BuyingPostRepo buyingPostRepo, PostService postService) {
        this.buyingPostRepo = buyingPostRepo;
        this.postService = postService;
    }

    @PutMapping("/buy/update")
    public BuyingPost updateBuyingPost(@RequestBody BuyingPost post){
        return  buyingPostRepo.save(post);
    }

    @PostMapping("buy/filter")
    public Page<BuyingPost> getRentingPostFilters(@RequestBody FilterDto filter) throws JsonProcessingException {
        return  postService.getBuyingPostByFilter(filter);
    }

}
