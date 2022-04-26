package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {


}
