package com.chuckwagon.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

/**
 * Created by branden on 4/8/16 at 17:14.
 */
@Entity
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Integer id;

    @ManyToOne
    private Vendor vendor;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String photoLocation;


    public Menu() {
    }

    public Menu(Vendor vendor, String name) {
        this.vendor = vendor;
        this.name = name;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", vendor=" + vendor +
                ", name='" + name + '\'' +
                ", photoLocation='" + photoLocation + '\'' +
                '}';
    }
}