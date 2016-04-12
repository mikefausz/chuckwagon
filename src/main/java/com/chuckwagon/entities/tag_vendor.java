package com.chuckwagon.entities;

import javax.persistence.*;

/**
 * Created by branden on 4/12/16 at 14:18.
 */

@Entity
public class tag_vendor {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Vendor vendor;


    public tag_vendor() {
    }

    public tag_vendor(Tag tag, Vendor vendor) {
        this.tag = tag;
        this.vendor = vendor;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Integer getId() {
        return id;
    }
}