package com.example.demo.model;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NoAuthorizedException extends Throwable {
    public NoAuthorizedException(String s) {
    }
}
