package com.example.demo.model.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    Optional<AppUser>findByEmail(String email);

    @Override
    Optional<AppUser> findById(Long aLong);
}
