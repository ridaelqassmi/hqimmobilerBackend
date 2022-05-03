package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
   public User findByEmail(String email);

}
