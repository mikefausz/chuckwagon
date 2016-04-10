package com.chuckwagon.entities;

import javax.persistence.*;

/**
 * Created by branden on 4/8/16 at 17:14.
 */
@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @Column(nullable = false, name = "vendor_id")
    private Vendor vendor;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String photoLocation;


    public Menu() {
    }

    public Menu(Vendor vendor, String name, String photoLocation) {
        this.vendor = vendor;
        this.name = name;
        this.photoLocation = photoLocation;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }
}