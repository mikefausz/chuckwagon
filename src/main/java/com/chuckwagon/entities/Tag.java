package com.chuckwagon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by branden on 4/10/16 at 13:17.
 *
 * Will contain global tags that can be attached to trucks
 */
@Entity
public class Tag {

    @GeneratedValue
    @Id
    @Column(name = "tag_id")
    private Integer id;

    @Column(nullable = false)
    private String tag;

    @Transient
    @JsonIgnore
    private Set<Vendor> vendors;


    public Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(Set<Vendor> vendors) {
        this.vendors = vendors;
    }


}