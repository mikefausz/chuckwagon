package com.chuckwagon.utils;

import com.chuckwagon.entities.Location;
import com.chuckwagon.entities.Tag;
import com.chuckwagon.entities.TagVendor;
import com.chuckwagon.entities.Vendor;
import com.chuckwagon.services.LocationRepository;
import com.chuckwagon.services.TagRepository;
import com.chuckwagon.services.TagVendorRepository;
import com.chuckwagon.services.VendorRepository;

import java.time.LocalDateTime;


/**
 * Created by branden on 4/12/16 at 11:14.
 */
public class PopulateDB {

    public static void populate(VendorRepository vendorRepository, LocationRepository locationRepository, TagRepository tagRepository, TagVendorRepository tagVendorRepository) throws PasswordStorage.CannotPerformOperationException {

        /** Hard Coded Tags */
        String[] tagStrings = { "BBQ", "Stir Fry", "Pizza", "Coffee", "Ice Cream", "Tacos", "Salads", "Seafood", "Breakfast", "Juice & Smoothies", "Burritos", "Omelettes", "Fritattas", "none"};
        if (tagRepository.count() == 0) {
            for (String s : tagStrings) {
                Tag tag = new Tag(s);
                tagRepository.save(tag);
            }
        }


        if (vendorRepository.count() <= 1) {
            Vendor bon = (new Vendor("bon@bon.com", "Bon Banh Mi", "password"));
            bon.setPassword(PasswordStorage.createHash(bon.getPassword()));
            bon.setBio("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            Location bonLoc = new Location(32.788642f, -79.950876f, "48");
            bonLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            bonLoc = locationRepository.save(bonLoc);
            bon.setLocation(bonLoc);
            vendorRepository.save(bon);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(1), bon));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(2), bon));
            System.out.println("Created bon");


            Vendor pink = (new Vendor("pink@pink.com", "Pink Bellies", "password"));
            pink.setBio("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            pink.setPassword(PasswordStorage.createHash(pink.getPassword()));
            Location pinkLoc = new Location(32.785400f, -79.937957f, "48");
            pinkLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            pinkLoc = locationRepository.save(pinkLoc);
            pink.setLocation(pinkLoc);
            vendorRepository.save(pink);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(1), pink));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(3), pink));
            System.out.println("created pink");


            Vendor lobster = (new Vendor("lob@lob.com", "The Immortal Lobster", "password"));
            lobster.setBio("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            lobster.setPassword(PasswordStorage.createHash(lobster.getPassword()));
            Location lobLoc = new Location(32.786300f, -79.94384f, "48");
            lobLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            lobLoc = locationRepository.save(lobLoc);
            lobster.setLocation(lobLoc);
            vendorRepository.save(lobster);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(3), lobster));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(4), lobster));
            System.out.println("created lob");

            Vendor autob = (new Vendor("auto@auto.com", "Autobahn", "password"));
            autob.setBio("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            autob.setPassword(PasswordStorage.createHash(autob.getPassword()));
            Location autobLoc = new Location(32.796632f, -79.944514f, "48");
            autobLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            autobLoc = locationRepository.save(autobLoc);
            autob.setLocation(autobLoc);
            vendorRepository.save(autob);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(4), autob));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(5), autob));
            System.out.println("created autob");
        }

    }

}