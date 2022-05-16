package com.example.demo.inbound;

import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.appuser.AppUserRepo;
import com.example.demo.model.appuser.AppUserService;
import com.example.demo.model.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class AppUserController {
   private final AppUserService  appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

@PatchMapping("/{id}/settings")
  public boolean changeSettings(@PathVariable("id") long id,@RequestBody RegistrationRequest request) {
    AppUser appUser = appUserService.getUser(id);
    String encodePassword = bCryptPasswordEncoder.encode(request.getPassword());
    if (!(request.getName().equals(appUser.getName()))) {

        appUser.setName(request.getName());
    }
    if (!(request.getAppUserZiele().equals(appUser.getAppUserZiele()))) {

        appUser.setAppUserZiele(request.getAppUserZiele());
    }
    if (!(request.getEmail().equals(appUser.getEmail()))) {

        appUser.setEmail(request.getEmail());
    }
    if (!(request.getUserName().equals(appUser.getUsername()))) {

        appUser.setUserName(request.getUserName());
    }
    if (!(encodePassword.equals(appUser.getPassword()))) {

        appUser.setPassword(encodePassword);
    }

    if (request.getGroesse() == appUser.getGroesse()) {

        appUser.setGroesse(request.getGroesse());
    }
    if (!(request.getGewicht()==(appUser.getGewicht()))) {

        appUser.setGewicht(request.getGewicht());
    }
    if (!(request.getGeburtsdatum().equals(appUser.getGeburtsdatum()))) {

        appUser.setGeburtsdatum(request.getGeburtsdatum());
    }
    else{return false;}
    appUserService.safeUser(id,appUser);
    return true;
}
@GetMapping("Hello")
public String test(){
    return "YO GUNE";
}
        @GetMapping("/{id}")
public ResponseEntity<String> getUser(@PathVariable("id") long id) throws IllegalAccessError{
   if(appUserService.isAdmin(id)){
return ResponseEntity.ok().body(appUserService.getUserName(id));}
else{throw new IllegalAccessError("no Authorization"); }
}}
