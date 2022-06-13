package com.example.demo.model.appuser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAllreadyExist extends RuntimeException {
    public UserAllreadyExist(String message) {
        super(message);
    }
}