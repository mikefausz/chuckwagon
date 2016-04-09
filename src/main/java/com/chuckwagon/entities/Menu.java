package com.chuckwagon.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by branden on 4/8/16 at 17:14.
 */
@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String photoLocation;


    public Menu(String name, String photoLocation) {
        this.name = name;
        this.photoLocation = photoLocation;
    }
}