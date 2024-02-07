package com.usersDb.usersDb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserUpdateWithInvalidBodyException extends RuntimeException{

public  UserUpdateWithInvalidBodyException(){
    super("User Id user name and user email cannot be null or empty");
}
}
