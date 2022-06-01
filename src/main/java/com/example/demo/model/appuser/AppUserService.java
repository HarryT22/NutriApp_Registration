package com.example.demo.model.appuser;



import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static com.example.demo.model.appuser.Role.ADMIN;


@Service
@AllArgsConstructor
@Transactional
public class AppUserService implements UserDetailsService {
private final static String USER_NOT_FOUND="user with email %s not found";
private  AppUserRepo appUserRepo;
private BCryptPasswordEncoder bCryptPasswordEncoder;

@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser appUser= appUserRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
        Collection<SimpleGrantedAuthority> authorires = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(),authorires);
    }
    public boolean safeUser(AppUser appUser){

        appUserRepo.save(appUser);
        return true;
    }
    public boolean  isAdmin(long id){
        AppUser appUser = appUserRepo.findById(id).get();
        if(appUser.getRole()==ADMIN){
            return true;
        }
        return false;
    }

    public boolean  isAdmin(String email){
        AppUser appUser = appUserRepo.findByEmail(email).get();
        if(appUser.getRole()==ADMIN){
            return true;
        }
        return false;
    }
    public AppUser getUser(long id){
        AppUser appUser = appUserRepo.findById(id).get();

        return appUser;
    }
    public AppUser getUser(String email){
        AppUser appUser = appUserRepo.findByEmail(email).get();

        return appUser;
    }
    public Role login(String email,String password){
        try {
            AppUser appUser = appUserRepo.findByEmail(email).get();
            return appUser.getRole();
        } catch (Exception e) {
            throw new IllegalStateException("Invalid username or password.");
        }

    }
    public String getUserName(long id){
        AppUser appUser = appUserRepo.findById(id).get();

        return appUser.getUsername();
    }
    /* Should return true if email is aviable*/
    public boolean emailNotTaken(String email){
        boolean userExists = appUserRepo.findByEmail(email).isPresent();
        if(userExists){
            throw  new IllegalStateException("Email schon belegt");
        }
        return true;
    }

    public String singUpUser(AppUser appUser){
        String encodePassword= bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodePassword);
        appUserRepo.save(appUser);
        return "it works";
    }
}
