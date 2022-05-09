package com.HQimmobillier.fpbm.mapper;

import com.HQimmobillier.fpbm.dto.user.RegisterDto.RequestRegisterDto;
import com.HQimmobillier.fpbm.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapRequestRegisterDtoToUser(RequestRegisterDto requestRegisterDto){
        User user = new User();
        user.setFirstName(requestRegisterDto.getFirstName());
        user.setLastName(requestRegisterDto.getLastName());
        user.setPassword(requestRegisterDto.getPassword());
        user.setEmail(requestRegisterDto.getEmail());
        user.setPhone(requestRegisterDto.getPhoneNumber());
        return user;
    }
}
