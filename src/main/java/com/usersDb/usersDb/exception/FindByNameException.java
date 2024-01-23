package com.usersDb.usersDb.exception;

public class FindByNameException extends RuntimeException{
    public FindByNameException (){
        super("Invalid Name");
    }
}
