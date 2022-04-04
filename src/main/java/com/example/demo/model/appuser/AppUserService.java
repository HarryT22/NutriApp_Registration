package com.example.demo.model.appuser;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
private final static String USER_NOT_FOUND="user with email %s not found";
private final AppUserRepo appUserRepo;
private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }

    public String singUpUser(AppUser appUser){
        boolean userExists = appUserRepo.findByEmail(appUser.getEmail()).isPresent();
        if(userExists){
            throw  new IllegalStateException("Email schon belegt");
        }
        String encodePassword= bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodePassword);
        appUserRepo.save(appUser);
        return "it works";
    }
}
