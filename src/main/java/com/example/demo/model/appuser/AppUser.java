package com.example.demo.model.appuser;

import com.example.demo.model.appuser.AppUserRole;
import com.example.demo.model.appuser.AppUserZiele;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

    @SequenceGenerator(name = "app_user_generator",
    sequenceName = "app_user_generator",
    allocationSize = 1)
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_generator"
    )
    private int id ;
    private String name;
    private String userName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserZiele appUserZiele ;
    private short groesse;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private short gewicht;

    public AppUser(String name,String userName,String email,String password,AppUserZiele appUserZiele,short groesse, AppUserRole appUserRole,short gewicht){
    this.name=name;
        this.userName=userName;
        this.email=email;
        this.password=password;
        this.appUserZiele=appUserZiele;
        this.groesse=groesse;
        this.appUserRole=appUserRole;
        this.gewicht=gewicht;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
