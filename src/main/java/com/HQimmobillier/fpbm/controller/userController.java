package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.User;
import com.HQimmobillier.fpbm.repository.UserRepo;
import com.HQimmobillier.fpbm.services.UserService;
import com.HQimmobillier.fpbm.utility.CommenFunctions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class userController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;
    @PostMapping("users")
    public User save(@RequestPart("file") MultipartFile file,
                     @RequestParam(value="user") String u)
            throws IOException {

     return  userService.save(file,u);
    }
    @GetMapping("/users")
    public List<User> findAll(){
       return userRepo.findAll();
    }
    @GetMapping("/users/{id}")
    public User findByID(@PathVariable Long id) throws IOException {
        User user = userRepo.findById(id).get();

        return user;
    }
    @DeleteMapping("/users/delete")
    public void deleteUserById(long id){
        //here it should check if the user has the authorization
         userRepo.deleteById(id);
    }
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable long id ,@RequestBody User user){

        User user1 = userService.updateUser(id, user);
        return user1;
    }

    }

