package com.chuckwagon.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by branden on 4/14/16 at 15:41.
 */
public class Data {

    public Location location;

//    public Menu menu;

    public Tag tag;

    public TagVendor tagVendor;

    public Vendor vendor;

    public List<Tag> tags;

    public String keyword;


    @Override
    public String toString() {
        return "Data{" +
                "location=" + location +
                ", tag=" + tag +
                ", tagVendor=" + tagVendor +
                ", vendor=" + vendor +
                ", tags=" + tags +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}