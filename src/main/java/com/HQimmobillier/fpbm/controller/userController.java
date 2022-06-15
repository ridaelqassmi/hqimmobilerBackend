package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.entity.User;
import com.HQimmobillier.fpbm.repository.UserRepo;
import com.HQimmobillier.fpbm.services.AccountService;
import com.HQimmobillier.fpbm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final AccountService accountService;

    public userController(AccountService accountService) {
        this.accountService = accountService;
    }

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
    @GetMapping("authentifiedUser")
    public User getAuthentifiedUser(){
        return accountService.getAuthenticatedUser();
    }

    }

