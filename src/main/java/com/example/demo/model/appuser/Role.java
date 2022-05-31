package com.example.demo.model.appuser;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    NORMAL,ADMIN,PREMIUM;
    @Override
    public String getAuthority(){
        return name();
    }
}
