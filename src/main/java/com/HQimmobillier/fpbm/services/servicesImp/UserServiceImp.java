package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.entity.User;
import com.HQimmobillier.fpbm.repository.UserRepo;
import com.HQimmobillier.fpbm.services.UserService;
import com.HQimmobillier.fpbm.utility.CommenFunctions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;

    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User save(MultipartFile file,
                       String u) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(u,User.class);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());


        user.setPhotos(fileName);


        User savedUser = userRepo.save(user);

        String uploadDir = "frontend/src/assets/" + savedUser.getId();

        CommenFunctions.saveFile(uploadDir, fileName, file);

        return savedUser;
    }


    @Override
    public User updateUser(long id, User u) {
        User user = userRepo.findById(id).get();
        if(user != null){
            user.setFirstName(u.getFirstName());
            user.setLastName(u.getLastName());
            user.setPhone(u.getPhone());
            return  userRepo.save(user);
        }else{
            return null;
        }

    }

}
