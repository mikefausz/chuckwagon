package com.chuckwagon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "tag")
    @JsonIgnore
    private List<TagVendor> tagVendorList;

    public Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag1 = (Tag) o;

        if (!id.equals(tag1.id)) return false;
        return tag.equals(tag1.tag);

    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }
}