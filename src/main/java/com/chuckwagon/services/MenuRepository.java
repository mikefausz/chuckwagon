package com.chuckwagon.services;

import com.chuckwagon.entities.Menu;
import com.chuckwagon.entities.Vendor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by branden on 4/10/16 at 12:35.
 */
public interface MenuRepository extends CrudRepository<Menu, Integer> {
//    List<Menu> findByVendor(Vendor vendor);
}
