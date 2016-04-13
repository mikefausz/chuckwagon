package com.chuckwagon.entities;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by branden on 4/13/16 at 11:57.
 *
 * This is an object made specifically to send to a front it. Contains only relevant fields for FE
 *
 */
public class VendorData {

    private Integer id;

    private String name;

    private Set<Tag> tags;

    private String bio;

    private HashMap<String, Float> location;

    private String profileImgURL;

    public VendorData() {
    }

    public VendorData(Integer id, String name, Set<Tag> tags, String bio, HashMap<String, Float> location, String profileImgURL) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.bio = bio;
        this.location = location;
        this.profileImgURL = profileImgURL;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
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

    public String getProfileImgURL() {
        return profileImgURL;
    }

    public void setProfileImgURL(String profileImgURL) {
        this.profileImgURL = profileImgURL;
    }
}