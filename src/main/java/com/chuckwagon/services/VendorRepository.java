package com.chuckwagon.services;

import com.chuckwagon.entities.Tag;
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
    Vendor findByVendorName(String vendorName);
    List<Vendor> findByIsActive(Boolean isActive);
    Vendor findByContactEmail(String contactEmail);
    List<Vendor> findByVendorNameIgnoreCaseContaining(String search);

}
