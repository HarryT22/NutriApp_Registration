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

import static com.example.demo.model.appuser.Role.ADMIN;
import static org.junit.jupiter.api.Assertions.assertTrue;
@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    private AppUser appUser;
    @Mock
    private AppUserService appUserService;

    @Autowired
    private AppUserRepo appUserRepo;

    public void setUp(){
        this.appUser = new AppUser();
        appUser.setEmail("ab@domain.de");
        appUser.setPassword("password");
        appUser.setRole(Role.NORMAL);

    }
    @Test
    public void safeUser() {
        this.appUser = new AppUser();
        appUser.setEmail("ab@domain.de");
        appUser.setPassword("password");
        appUser.setRole(Role.NORMAL);
        appUserService.safeUser(this.appUser);
        boolean userExists = appUserRepo.findByEmail("ab@domain.de").isPresent();
        assertTrue(userExists);
    }
    @Test
    public void getUser() {
        appUserRepo.save(this.appUser);
        AppUser appUser1 = appUserService.getUser(0);
        AppUser appUser2= appUserService.getUser(this.appUser.getEmail());
        assertThat(appUser1,is(this.appUser));
        assertThat(appUser2,is(this.appUser));

    }
    @Test
    public void isAdmin(){
       AppUser appUser = this.appUser;
       appUser.setRole(ADMIN);
       assertTrue(appUserService.isAdmin(this.appUser.getEmail()));
       assertTrue(appUserService.isAdmin(0));
    }

    /* Should return true if email is aviable*/
    @Test
    public void emailNotTakenTest(){
        boolean isAvailable= appUserService.emailNotTaken("itsNotTaken@domain.com");
        assertTrue(isAvailable);
    }
    @Test
    public void signUpTest(){
        appUserService.singUpUser(this.appUser);
        assertTrue(appUserRepo.findByEmail(this.appUser.getEmail()).isPresent());
    }
}
