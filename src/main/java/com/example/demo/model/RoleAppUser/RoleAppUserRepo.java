package com.example.demo.model.RoleAppUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleAppUserRepo extends JpaRepository<RoleAppUser,Long> {
    Optional<RoleAppUser> findById(Long id);
}
