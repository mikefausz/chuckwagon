package com.chuckwagon.services;

import com.chuckwagon.entities.Location;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by branden on 4/11/16 at 11:27.
 */
public interface LocationRepository extends CrudRepository<Location, Integer> {
}
