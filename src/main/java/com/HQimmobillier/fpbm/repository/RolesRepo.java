package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RolesRepo extends JpaRepository<Roles, Long> {
    @Query(value = "Select * from roles where role_name=?",nativeQuery = true)
    Roles findbyRoleName(String roleName);
}
