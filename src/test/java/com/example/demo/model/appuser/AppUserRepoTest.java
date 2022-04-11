package com.example.demo.model.appuser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepoTest {

    @Autowired
     AppUserRepo appUserRepos;
    @Test
    public void TestRepo(){
    AppUser appUser = new AppUser("NAme","name","email");
    appUserRepos.save(appUser);
    boolean exist  = appUserRepos.findByEmail("email").isPresent();
    assertTrue(exist);
}

}