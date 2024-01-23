package com.usersDb.usersDb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlreadyExistEmailException extends RuntimeException {

    public AlreadyExistEmailException(String description) {
        super(description);
    }
}
