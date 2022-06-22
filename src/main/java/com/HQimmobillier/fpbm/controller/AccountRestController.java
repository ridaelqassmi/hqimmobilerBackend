package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.dto.user.RegisterDto.RequestRegisterDto;
import com.HQimmobillier.fpbm.dto.user.loginDto.LoginRequestDto;
import com.HQimmobillier.fpbm.dto.user.loginDto.LoginResponseDto;
import com.HQimmobillier.fpbm.entity.Roles;
import com.HQimmobillier.fpbm.entity.User;
import com.HQimmobillier.fpbm.mapper.UserMapper;
import com.HQimmobillier.fpbm.services.AccountService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AccountRestController {
   private final AccountService accountService;
   private final UserMapper userMapper;
    public AccountRestController(AccountService accountService, UserMapper userMapper) {
        this.accountService = accountService;
        this.userMapper = userMapper;
    }
    @GetMapping(path="/users")
    public List<User> appUsers(){

        return accountService.listUsers();
    }
    @PostMapping(path="/register")
    public User addUser(@RequestBody RequestRegisterDto requestRegisterDto){
        return accountService.addNewUser(userMapper.mapRequestRegisterDtoToUser(requestRegisterDto));
    }
    @PostMapping(path="/roles")
    public Roles addRole(@RequestBody Roles role){
        return accountService.addNewRole(role);
    }
    @PostMapping(path="/addRoleToUser")
    public void addRoleToUser(RoleUserForm u){
         accountService.addRoleToUser(u.getUsername(),u.getRole());
    }
    @PostMapping(path = "/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        System.out.println(loginRequestDto.getPassword());

        return accountService.login(loginRequestDto);
    }

}

@Data
class RoleUserForm{
    private String role;
    private String username;
}
