package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.appuser.AppUserRepo;
import com.example.demo.model.appuser.AppUserService;
import com.example.demo.model.appuser.Role;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static com.example.demo.model.appuser.Role.ADMIN;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {


    @Mock
    private AppUserRepo appUserRepo;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AppUser appUser;

    private AppUserService appUserService;


    @BeforeEach
    public void setUp(){
    this.appUserService=new AppUserService(this.appUserRepo,bCryptPasswordEncoder);
        this.appUser = new AppUser();
        appUser.setEmail("ab@domain.de");
        appUser.setPassword("password");
        appUser.setRole(Role.NORMAL);
        appUser.setId(1);

    }
    @Test
    public void safeUser() {
        appUserService.safeUser(this.appUser);
        given(this.appUserRepo.findByEmail(this.appUser.getEmail())).willReturn(Optional.of(this.appUser));
        boolean userExists = appUserRepo.findByEmail(this.appUser.getEmail()).isPresent();
        assertTrue(userExists);
    }
    @Test
    public void getUser() {
        this.appUserRepo.save(this.appUser);
        given(this.appUserRepo.findByEmail(this.appUser.getEmail())).willReturn(Optional.of(this.appUser));
        given(this.appUserRepo.findById(this.appUser.getId())).willReturn(Optional.of(this.appUser));
        AppUser appUser2= appUserService.getUser(this.appUser.getEmail());
        AppUser appUser1= appUserService.getUser(1);
        assertThat(this.appUser,is(appUser2));
        assertThat(this.appUser,is(appUser1));
    }
    @Test
    public void isAdmin(){
        this.appUserRepo.save(this.appUser);
        given(this.appUserRepo.findByEmail(this.appUser.getEmail())).willReturn(Optional.of(this.appUser));
        given(this.appUserRepo.findById(this.appUser.getId())).willReturn(Optional.of(this.appUser));
       this.appUser.setRole(ADMIN);
       assertTrue(appUserService.isAdmin(this.appUser.getEmail()));
       assertTrue(appUserService.isAdmin(this.appUser.getId()));
    }

    /* Should return true if email is aviable*/
    @Test
    public void emailNotTakenTest(){
        boolean isAvailable= appUserService.emailNotTaken("itsNotTaken@domain.com");
        assertTrue(isAvailable);
    }
    @Test
    public void signUpTest(){
        given(this.appUserRepo.findByEmail(this.appUser.getEmail())).willReturn(Optional.of(this.appUser));
        appUserService.singUpUser(this.appUser);
        assertTrue(appUserRepo.findByEmail(this.appUser.getEmail()).isPresent());
    }
}
