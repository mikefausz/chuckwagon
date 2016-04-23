package com.chuckwagon.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

/**
 * Created by branden on 4/8/16 at 17:14.
 *
 * Allows vendors to upload and name menus
 */
@Entity
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Integer id;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Vendor vendor;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String photoLocation;


    public Menu() {
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


    public Integer getId() {
        return id;
    }

}