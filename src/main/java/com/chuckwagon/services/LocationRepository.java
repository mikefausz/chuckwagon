package com.chuckwagon.services;

import com.chuckwagon.entities.Location;
import com.chuckwagon.entities.Vendor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by branden on 4/11/16 at 11:27.
 */
public interface LocationRepository extends CrudRepository<Location, Integer> {
    Location findByVendor(Vendor vendor);


}
