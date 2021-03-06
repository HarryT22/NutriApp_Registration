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
public class RegistrationRequest {
    private final String name;
    private final String userName;
    private  final String email;
    private final String password;
    private final  AppUserZiele appUserZiele ;
    private final short groesse;
    private final short  gewicht;
    private final String geburtsdatum;
    private final Gender gender;
    private final Role role;


}
