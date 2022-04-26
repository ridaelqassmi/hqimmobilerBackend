package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.RentingPost;
import com.HQimmobillier.fpbm.services.RentingPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class RentingPostController {
    @Autowired
    RentingPostService rentingPostService;

}
