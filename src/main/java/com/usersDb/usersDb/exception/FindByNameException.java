package com.usersDb.usersDb.exception;

import org.aspectj.lang.annotation.DeclareError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FindByNameException extends RuntimeException{
    public FindByNameException (){
        super("Invalid Name");
    }
}
