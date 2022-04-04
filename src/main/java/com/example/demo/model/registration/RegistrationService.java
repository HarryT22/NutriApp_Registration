package com.example.demo.model.registration;

import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.appuser.AppUserRole;
import com.example.demo.model.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor //Alle Felder sind anzugeben um Konstruktor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request){
        boolean isValidEmail= emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not vail");
        }
        return appUserService.singUpUser(new AppUser(request.getName(),request.getUserName(),request.getEmail(),request.getPassword(),request.getAppUserZiele(),request.getGroesse(), AppUserRole.NORMAL,request.getGewicht()));
    }
}
