package com.chuckwagon.services;

import com.chuckwagon.entities.Vendor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by branden on 4/5/16 at 22:37.
 */
public interface VendorRepository extends CrudRepository<Vendor, Integer> {
    Optional<Vendor> findByVendorName(String vendorName);
    List<Vendor> findByIsActive(Boolean isActive);

}
