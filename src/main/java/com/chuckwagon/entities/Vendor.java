package com.chuckwagon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by branden on 4/5/16 at 22:03.
 * This class is meant to contain a food truck
 *
 */
@Entity
public class Vendor {


    @Id
    @GeneratedValue
    @Column(name = "vendor_id")
    private Integer id;

    @Column(nullable = false, name = "vendor_name", unique = true)
    private String vendorName;

    @Column(nullable = false, name = "vendor_password_hashed")
    private String password;

    @Column( name = "contact_email", unique = true)
    private String contactEmail;

    @Column( name = "bio", length = 510)
    private String bio;

    @Column( name = "profile_picture")
    private String profilePictureLocation;

    @Transient
    @JsonIgnore
    private MultipartFile profilePicture;  //this stays out of DB & Json.

    @Column(name = "active")
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "vendor")
    @JsonIgnore
    private List<TagVendor> tags;

    //strings for FE to use.
    @Transient
    private List<String> tagsList;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Location location;


    public Vendor() {
    }

    public Vendor(String contactEmail, String vendorName, String password) {
        this.contactEmail = contactEmail;
        this.vendorName = vendorName;
        this.password = password;
    }


    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureLocation() {
        if (profilePictureLocation == null) {
            profilePictureLocation = "107.170.8.42/public/images/stock/logo-circle.png";
        }

        return profilePictureLocation;
    }

    public void setProfilePictureLocation(String profilePictureLocation) {
        this.profilePictureLocation = profilePictureLocation;
    }

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<TagVendor> getTags() {
        return tags;
    }

    public void setTags(List<TagVendor> tags) {
        this.tags = tags;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public List<String> getTagsList() {
        if (tags != null && !tags.isEmpty()) {
            List<String> tagsString = new ArrayList<>();
            for (TagVendor tv : tags) {
                tagsString.add(tv.getTag().getTag());
            }
            tagsList = tagsString;
        }
        return tagsList;
    }

    public void setTagsList(List<String> tagsList) {
        this.tagsList = tagsList;
    }



}