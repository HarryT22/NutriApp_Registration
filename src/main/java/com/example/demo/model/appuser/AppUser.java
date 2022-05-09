package com.example.demo.model.appuser;

import com.example.demo.model.appuser.AppUserRole;
import com.example.demo.model.appuser.AppUserZiele;
import com.example.demo.model.role.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="user_data")
public class AppUser implements UserDetails {


    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id ;
    @Column(name = "Name")
    private String name;
    @Column(name = "User_name")
    private String userName;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @Column(name = "Ziele")
    @Enumerated(EnumType.STRING)
    private AppUserZiele appUserZiele ;
    @Column(name = "Groeße")
    private short groesse;
    @Column(name = "Role")
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    @Column(name = "Gewicht")
    private short gewicht;

    @Column(name="Geburts_datum")
    private String geburtsdatum;

    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles= new ArrayList<>();


    public AppUser(String name, String userName, String email, String password, AppUserZiele appUserZiele, short groesse, AppUserRole appUserRole, short gewicht
    , String geburtsdatum , Gender gender){
    this.name=name;
        this.userName=userName;
        this.email=email;
        this.password=password;
        this.appUserZiele=appUserZiele;
        this.groesse=groesse;
        this.appUserRole=appUserRole;
        this.gewicht=gewicht;
        this.geburtsdatum=geburtsdatum;
        this.gender=gender;
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
