package com.chuckwagon.entities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by branden on 4/5/16 at 22:03.
 */
@Entity
@Data
public class Vendor {

    @GeneratedValue
    @Id
    @NonNull
    private Integer id;

    @NonNull
    @Column(nullable = false, name = "vendor_name")
    private String vendorName;

    @Column( name = "contact_first_name")
    private String contactFirstName;

    @Column( name = "contact_last_name")
    private String contactLastName;

    @Column( name = "contact_email")
    private String contactEmail;

    @Column( name = "bio")
    private String bio;

    @Column( name = "profile_picture")
    private String profilePicture;

    @Column(name = "active")
    private boolean isActive;


}