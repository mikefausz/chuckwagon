package com.chuckwagon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * Created by branden on 4/6/16 at 14:12.
 *
 * This will store the location of all active vendors.
 * there is logic to remove the entry after a set time
 */

@Entity
public class Location {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(value = "vendor", name = "property"))
    @Column(name = "location_id", unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Long lat;

    @Column(nullable = false)
    private Long lng;

    @Column(nullable = false)
    @JsonIgnore
    private LocalDateTime expiresObject;

    @Transient
    private String expiresString;

    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "location_id")
    @PrimaryKeyJoinColumn
    private Vendor vendor;



    public Location() {
    }

    public Location(Long lat, Long lng, LocalDateTime expiresObject) {
        this.lat = lat;
        this.lng = lng;
        this.expiresObject = expiresObject;
        this.expiresString = expiresObject.toString();
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLng() {
        return lng;
    }

    public void setLng(Long lng) {
        this.lng = lng;
    }

    public LocalDateTime getExpiresObject() {
        return expiresObject;
    }

    public void setExpiresObject(LocalDateTime expiresObject) {
        this.expiresObject = expiresObject;
    }

    public String getExpiresString() {
        return expiresString;
    }

    public void setExpiresString(String expiresString) {
        this.expiresString = expiresString;
        this.expiresObject = LocalDateTime.parse(expiresString);
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

    public void setId(Integer id) {
        this.id = id;
    }


}