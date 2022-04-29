package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    public User save(MultipartFile file, String u) throws IOException;


    User updateUser(long id, User user);
}
