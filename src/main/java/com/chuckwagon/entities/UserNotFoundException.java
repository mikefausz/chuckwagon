package com.chuckwagon.entities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by branden on 4/6/16 at 16:23.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {


    public UserNotFoundException(String userName) {
        super("could not find user '" + userName + "'.");
    }


}