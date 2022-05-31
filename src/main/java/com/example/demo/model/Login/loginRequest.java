package com.example.demo.model.registration;

import com.example.demo.model.appuser.AppUserZiele;
import com.example.demo.model.appuser.Gender;
import com.example.demo.model.appuser.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class loginRequest {
    private  final String email;
    private final Role role;
    private final String password;

}