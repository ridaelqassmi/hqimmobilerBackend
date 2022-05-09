package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.entity.Roles;
import com.HQimmobillier.fpbm.entity.User;
import com.HQimmobillier.fpbm.repository.RolesRepository;
import com.HQimmobillier.fpbm.repository.UserRepo;
import com.HQimmobillier.fpbm.services.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class AccountServiceImpl implements AccountService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;

    public AccountServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, RolesRepository rolesRepository) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
    }


    @Override
    public User addNewUser(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepo.save(user);
    }

    @Override
    public Roles addNewRole(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user =  userRepo.findByEmail(email);
        Roles role = rolesRepository.findbyRoleName(roleName);
        user.getUserRoles().add(role);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> listUsers() {
        return userRepo.findAll();
    }
}
