package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.RentingPost;
import com.HQimmobillier.fpbm.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class RentingPostController {
    @Autowired
    PostService rentingPostService;
    @PostMapping("/post/rent/save")
    public RentingPost createRentingPost(@RequestParam(value = "idCity") long idCity,
                                         @RequestParam(value = "idCategorie") long idCategorie,
                                         @RequestPart("file") MultipartFile[] files,
                                         @RequestParam(value = "idUser") long idUser,
                                         @RequestParam(value = "rentingPost") String rentingPost) throws IOException {
        return rentingPostService.createRentingPost(idCity,idCategorie,files,idUser,rentingPost);

    }


}
