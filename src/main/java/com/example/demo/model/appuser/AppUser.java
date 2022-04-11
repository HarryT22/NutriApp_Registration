package com.example.demo.model.appuser;

import com.example.demo.model.appuser.AppUserRole;
import com.example.demo.model.appuser.AppUserZiele;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
@ToString
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
    private long id ;
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

    public AppUser(String nAme, String name, String email) {
        this.name=name;
        this.userName=userName;
        this.email=email;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
