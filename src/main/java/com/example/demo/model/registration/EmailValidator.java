package com.example.demo.model.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class EmailValidator{

    public boolean test(String email) {
      /* String regexPattern = "^(.+)@(\\S+) $.";
       return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();*/
        return true;
    }
}
