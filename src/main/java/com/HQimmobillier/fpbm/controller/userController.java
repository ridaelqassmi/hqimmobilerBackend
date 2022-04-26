package com.HQimmobillier.fpbm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class userController {

    @GetMapping("/user")
    public String hello(){
        return "hello user ";
    }
}
