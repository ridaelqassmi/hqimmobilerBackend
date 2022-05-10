package com.HQimmobillier.fpbm.services;

import com.HQimmobillier.fpbm.dto.user.loginDto.LoginRequestDto;
import com.HQimmobillier.fpbm.entity.Roles;
import com.HQimmobillier.fpbm.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountService {
    public String login(LoginRequestDto loginRequestDto);
    User addNewUser(User user);
    Roles addNewRole(Roles roles);
    void addRoleToUser(String username,String roleName);
    User loadUserByEmail(String email);
    List<User> listUsers();


}
