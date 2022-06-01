package com.example.demo;


import com.example.demo.inbound.AppUserController;
import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.appuser.AppUserService;
import com.example.demo.model.appuser.Role;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.integration.support.json.JsonObjectMapper;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.integration.json.SimpleJsonSerializer.toJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppUserController.class)
public class LoginTest {
@Autowired
    private MockMvc mockMvc;
@MockBean
private AppUserService appUserService;
private AppUser appUser;
@BeforeEach
    public void loging() throws Exception{
    this.appUser = new AppUser();
    appUser.setEmail("ab@domain.de");
    appUser.setPassword("password");
    appUser.setRole(Role.NORMAL);
    appUserService.safeUser(appUser);
}

@Test
    public void logingTest() throws Exception {
    this.mockMvc.perform(post("/user/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(appUser)))
                    .andExpect(status().isOk());
}
}
*/