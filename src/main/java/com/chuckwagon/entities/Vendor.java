package com.chuckwagon.entities;

import lombok.Data;

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
    private Integer id;

    @Column(nullable = false, name = "vendor_name")
    private String vendorName;

    @Column(nullable = false, name = "contact_first_name")
    private String contactFirstName;

    @Column(nullable = false, name = "contact_last_name")
    private String contactLastName;

    @Column(nullable = false, name = "contact_email")
    private String contactEmail;

    @Column(nullable = false, name = "bio")
    private String bio;

    @Column(nullable = false, name = "profile_picture")
    private String profilePicture;

}