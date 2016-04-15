package com.chuckwagon.entities;

import java.util.HashMap;
import java.util.List;

/**
 * Created by branden on 4/13/16 at 11:57.
 *
 * This is an object made specifically to send to a front it. Contains only relevant fields for FE
 *
 */
public class VendorData {

    private Integer id;

    private String vendorName;

    private List<String> tags;

    private String bio;

    private HashMap<String, Float> location;

    private String profilePictureLocation;

    public VendorData() {
    }

    public VendorData(Integer id, String vendorName, List<String> tags, String bio, HashMap<String, Float> location, String profilePictureLocation) {
        this.id = id;
        this.vendorName = vendorName;
        this.tags = tags;
        this.bio = bio;
        this.location = location;
        this.profilePictureLocation = profilePictureLocation;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public HashMap<String, Float> getLocation() {
        return location;
    }

    public void setLocation(HashMap<String, Float> location) {
        this.location = location;
    }

    public String getProfilePictureLocation() {
        return profilePictureLocation;
    }

    public void setProfilePictureLocation(String profilePictureLocation) {
        this.profilePictureLocation = profilePictureLocation;
    }
}