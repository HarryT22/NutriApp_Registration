package com.example.demo.model.appuser;


import com.example.demo.model.role.Role;
import com.example.demo.model.role.RoleRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
private final static String USER_NOT_FOUND="user with email %s not found";
private final AppUserRepo appUserRepo;
private final RoleRepo roleRepo;
private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }
    public AppUser getUser(long id){
        AppUser appUser = appUserRepo.findById(id).get();

        return appUser;
    }
    public String getUserName(long id){
        AppUser appUser = appUserRepo.findById(id).get();

        return appUser.getName()+appUser.getEmail()+appUser.getUsername();
    }
     public Role saveRole(Role role){
        return roleRepo.save(role);
     }
     public void  addRoleToUser (String email, String roleName){
        AppUser appUser = appUserRepo.findByEmail(email).get();
        Role role  = roleRepo.findByName(roleName);
        appUser.getRoles().add(role);
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
