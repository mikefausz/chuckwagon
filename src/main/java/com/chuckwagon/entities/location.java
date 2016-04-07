package com.chuckwagon.entities;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by branden on 4/6/16 at 14:12.
 */

@Data
public class Location {

    @NotNull
    Long x;
    @NotNull
    Long y;

}