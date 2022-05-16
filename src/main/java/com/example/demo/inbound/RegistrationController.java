package com.example.demo.inbound;

import com.example.demo.inbound.messaging.Config;
import com.example.demo.inbound.security.JwtUtil;
import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.appuser.AppUserRole;
import com.example.demo.model.appuser.AppUserService;
import com.example.demo.model.appuser.Role;
import com.example.demo.model.registration.RegistrationRequest;
import com.example.demo.model.registration.RegistrationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.stream.binder.rabbit.properties.RabbitBinderConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;

@RestController
@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private JwtUtil jwtTokenUtil;
    private final AppUserService appUserService;
    private AuthenticationManager authenticationManager;
    private RegistrationService registrationService;
    private final String REGISTER_SERVICE =  "register_service";
    private RabbitTemplate rabbitTemplate;
    private Config config ;
    @PostMapping
    public String createToken(@RequestBody RegistrationRequest request) throws Exception{
AppUser appUser = new AppUser(request.getName(),request.getUserName(),request.getEmail(),request.getPassword(),request.getAppUserZiele(),request.getGroesse(), AppUserRole.NORMAL,request.getGewicht(),request.getGeburtsdatum(), request.getGender(), Role.NORMAL);
/*config.publish(appUser, "amq.topic","sweng.routingKey");*/
String jo=registrationService.register(request);
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
           );
       } catch (BadCredentialsException e) {
           throw new Exception("Incorrect username or password", e);
       }
       final UserDetails userDetails = appUserService.loadUserByUsername(request.getEmail());
    final String jwt=jwtTokenUtil.generateToken(userDetails);
   return jwt;
    }

}
