package com.chuckwagon.utils;

import com.chuckwagon.entities.Location;
import com.chuckwagon.entities.Tag;
import com.chuckwagon.entities.Vendor;
import com.chuckwagon.services.LocationRepository;
import com.chuckwagon.services.TagRepository;
import com.chuckwagon.services.VendorRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by branden on 4/12/16 at 11:14.
 */
public class PopulateDB {

    public static void populate(VendorRepository vendorRepository, LocationRepository locationRepository, TagRepository tagRepository) throws PasswordStorage.CannotPerformOperationException {

        if (vendorRepository.count() <= 1) {
            Vendor bon = (new Vendor("bon@bon.com", "Bon Banh Mi", "password"));
            bon.setPassword(PasswordStorage.createHash(bon.getPassword()));
            bon.setBio("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            Location bonLoc = new Location(32.788642f, -79.950876f, "48");
            bonLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            bonLoc.setVendor(bon);
            vendorRepository.save(bon);
            locationRepository.save(bonLoc);

            Vendor pink = (new Vendor("pink@pink.com", "Pink Bellies", "password"));
            pink.setBio("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            pink.setPassword(PasswordStorage.createHash(pink.getPassword()));
            Location pinkLoc = new Location(32.785400f, -79.937957f, "48");
            pinkLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            pinkLoc.setVendor(pink);
            vendorRepository.save(pink);
            locationRepository.save(pinkLoc);

            Vendor lobster = (new Vendor("lob@lob.com", "The Immortal Lobster", "password"));
            lobster.setBio("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            lobster.setPassword(PasswordStorage.createHash(lobster.getPassword()));
            Location lobLoc = new Location(32.786300f, -79.94384f, "48");
            lobLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            lobLoc.setVendor(lobster);
            vendorRepository.save(lobster);
            locationRepository.save(lobLoc);

            Vendor autob = (new Vendor("auto@auto.com", "Autobahn", "password"));
            autob.setBio("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            autob.setPassword(PasswordStorage.createHash(autob.getPassword()));
            Location autobLoc = new Location(32.796632f, -79.944514f, "48");
            autobLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            autobLoc.setVendor(autob);
            vendorRepository.save(autob);
            locationRepository.save(autobLoc);
        }


        /** Hard Coded Tags */
        String[] tagStrings = { "BBQ", "Stir Fry", "Pizza", "Coffee", "Ice Cream", "Tacos", "Salads", "Seafood", "Breakfast", "Juice & Smoothies"};
        List<Tag> tags = null;
        if (tagRepository.count() > 0) {
            tags = (List<Tag>) tagRepository.findAll();
        }
        for (String s : tagStrings) {
            Tag tag = new Tag(s);
            if (tags != null) {
                if (!tags.contains(tag)) tagRepository.save(tag);
            } else {
                System.out.println(tag);
                tagRepository.save(tag);
            }
        }
    }

}