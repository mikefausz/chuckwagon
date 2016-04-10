package com.chuckwagon.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by branden on 4/10/16 at 13:17.
 */
@Entity
public class Tag {

    @GeneratedValue
    @Id
    @Column(name = "tag_id")
    private Integer id;

    @Column(nullable = false)
    private String tag;

}