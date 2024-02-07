package com.usersDb.usersDb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException(){
        super("User not found");
    }
}
