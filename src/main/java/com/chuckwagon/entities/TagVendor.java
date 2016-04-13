package com.chuckwagon.entities;

import javax.persistence.*;

/**
 * Created by branden on 4/12/16 at 14:18.
 */

@Entity
@Table(name = "tag_vendor")
public class TagVendor {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Vendor vendor;


    public TagVendor() {
    }

    public TagVendor(Tag tag, Vendor vendor) {
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


    @Override
    public String toString() {
        return "TagVendor{" +
                "id=" + id +
                ", tag=" + tag +
                ", vendor=" + vendor +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagVendor tagVendor = (TagVendor) o;

        if (id != null ? !id.equals(tagVendor.id) : tagVendor.id != null) return false;
        if (tag != null ? !tag.equals(tagVendor.tag) : tagVendor.tag != null) return false;
        return vendor != null ? vendor.equals(tagVendor.vendor) : tagVendor.vendor == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (vendor != null ? vendor.hashCode() : 0);
        return result;
    }
}