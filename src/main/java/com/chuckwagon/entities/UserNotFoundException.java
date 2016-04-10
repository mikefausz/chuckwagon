package com.chuckwagon.entities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by branden on 4/6/16 at 16:23.
 *
 * was playing around with this. Not implemented currently
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {


    public UserNotFoundException(String contactEmail) {
        super("could not find user '" + contactEmail + "'.");
    }


}