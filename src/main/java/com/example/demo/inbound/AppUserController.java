package com.example.demo.inbound;

import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class AppUserController {
   private final AppUserService  appUserService;
@GetMapping("/{id}")
public ResponseEntity<String> getUser(@PathVariable("id") long id){
return ResponseEntity.ok().body(appUserService.getUserName(id));
}
}
