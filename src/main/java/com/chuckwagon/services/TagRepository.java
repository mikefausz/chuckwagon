package com.chuckwagon.services;

import com.chuckwagon.entities.Tag;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by branden on 4/11/16 at 12:48.
 */
public interface TagRepository extends CrudRepository<Tag, Integer> {
    Tag findByTag(String tag);
}
