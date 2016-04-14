package com.chuckwagon.services;


import com.chuckwagon.entities.Tag;
import com.chuckwagon.entities.TagVendor;
import com.chuckwagon.entities.Vendor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by branden on 4/12/16 at 19:53.
 */
public interface TagVendorRepository extends CrudRepository<TagVendor, Integer> {
    @Modifying
    @Transactional
    @Query("delete from TagVendor tv where tv.vendor = ?1")
    void deleteByVendor(Vendor vendor);


    //@Query(value = "select tag from tag inner join tag_vendor on tag_id=tag_tag_id where vendor_vendor_id = ?1", nativeQuery = true)
    List<TagVendor> findByVendor(Vendor vendor);

}
