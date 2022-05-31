package com.example.demo;

import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.appuser.AppUserRepo;
import com.example.demo.model.appuser.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.assertTrue;
@DataJpaTest
public class JpaTest {
@Autowired
    AppUserRepo appUserRepo ;
    @Test
    public void savingUserAndGetTest(){
        AppUser  appUser =  new AppUser();
        appUser.setEmail("hello@web.de");
        appUser.setPassword("password");
        appUser.setRole(Role.NORMAL);
        appUserRepo.save(appUser);
        boolean userExists = appUserRepo.findByEmail("hello@web.de").isPresent();
        assertTrue(userExists);
    }

}
