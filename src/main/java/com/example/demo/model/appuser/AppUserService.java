package com.example.demo.model.appuser;



import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static com.example.demo.model.appuser.Role.ADMIN;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
private final static String USER_NOT_FOUND="user with email %s not found";
private final AppUserRepo appUserRepo;
private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser appUser= appUserRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
        Collection<SimpleGrantedAuthority> authorires = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(),authorires);
    }
    public AppUser getUser(long id){
        AppUser appUser = appUserRepo.findById(id).get();

        return appUser;
    }
    public boolean safeUser(long id, AppUser appUser){

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
    public String getUserName(long id){
        AppUser appUser = appUserRepo.findById(id).get();

        return appUser.getName()+appUser.getEmail()+appUser.getUsername();
    }
    public boolean emailNichtBelegt(String email){
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
