package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.User;
import com.HQimmobillier.fpbm.repository.UserRepo;
import com.HQimmobillier.fpbm.utility.CommenFunctions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin

public class userController {

    @Autowired
    UserRepo userRepo;
    @PostMapping("user/save")
    public User save(@RequestPart("file") MultipartFile file,
                     @RequestParam(value="user") String u)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(u,User.class);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        user.setPhotos(fileName);


        User savedUser = userRepo.save(user);

        String uploadDir = "images/user-photos/" + savedUser.getId();

        CommenFunctions.saveFile(uploadDir, fileName, file);

        return savedUser;
    }
    @GetMapping("/users")
    public List<User> findAll(){
       return userRepo.findAll();
    }
    @GetMapping("/users/{id}")
    public User findByID(@PathVariable Long id) throws IOException {
        User user = userRepo.findById(id).get();

        byte[] photobyte= CommenFunctions.retriveFileById("user-photos/"+user.getId()+"/"+user.getPhotos());
        user.setPhotosInBytes(photobyte);
        return user;




    }

    }

