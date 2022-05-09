package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.RentingPost;
import com.HQimmobillier.fpbm.repository.RentingPostRepo;
import com.HQimmobillier.fpbm.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("api/")
@CrossOrigin
public class RentingPostController {
    @Autowired
    PostService postService;
    @Autowired
    RentingPostRepo rentingPostRepo;
    @Autowired
    PostService rentingPostService;
    @PostMapping("rent")
    public RentingPost createRentingPost(@RequestParam(value = "idCity") long idCity,
                                         @RequestParam(value = "idCategorie") long idCategorie,
                                         @RequestPart("file") MultipartFile[] files,
                                         @RequestParam(value = "idUser") long idUser,
                                         @RequestParam(value = "rentingPost") String rentingPost) throws IOException {
        return rentingPostService.createRentingPost(idCity,idCategorie,files,idUser,rentingPost);

    }

    @GetMapping(value = {"/rent", "/rent/page/{id}"})
    public Page<RentingPost> findAll(@PathVariable(required = false) Integer id){
        if(id == null){
            return rentingPostService.getRentingPostWithPagination(1,20);
        }
        return rentingPostService.getRentingPostWithPagination(id,20);

    }

    @GetMapping("/rent/field/{field}")
    public Page<RentingPost>  findAllSortedd(@PathVariable(required=true,name = "field") String field,
                                             @RequestParam(required=false,name = "sort") Optional<String> sort,
                                             @RequestParam(required=true,name="page" ) Optional<Integer> page)
    {
        return rentingPostService.sortRentingPostBy(field,sort,page,20);
    }
    @GetMapping("/rent/categorie/{id}/page/{idPage}")
    public Page<RentingPost> getPostsByCategorie(@PathVariable(name = "idPage") int page,@PathVariable(name = "id") long id){
        return rentingPostService.getRentingPostByCategories(page,20,id);
    }

    @PutMapping("rent/{id}")
    public RentingPost update(@PathVariable long id, @RequestBody RentingPost rentingPost){
        RentingPost post = postService.findRentingPostById(id);


        post.setDuree(rentingPost.getDuree());
        post.setTitle(rentingPost.getTitle());

        post.setPrice(rentingPost.getPrice());
        post.setAreaSize(rentingPost.getAreaSize());
        post.setCategories(rentingPost.getCategories());
        post.setNumberRoom(rentingPost.getNumberRoom());
        return rentingPostRepo.save(post);

    }

   @GetMapping("rent/{id}")
    public RentingPost getRentingPostById(@PathVariable long id){
        return rentingPostRepo.findById(id).get();
   }

}
