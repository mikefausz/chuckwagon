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
        String[] tagStrings = { "BBQ", "Stir Fry", "Pizza", "Coffee", "Ice Cream", "Tacos", "Salads", "Seafood", "Breakfast", "Juice & Smoothies", "Burritos", "Omelettes", "Fritattas"};
        if (tagRepository.count() == 0) {
            for (String s : tagStrings) {
                Tag tag = new Tag(s);
                tagRepository.save(tag);
            }
        }

        if (vendorRepository.count() == 0) {
            Vendor dashi = (new Vendor("dashi@dashi.com", "Dashi", "password"));
            dashi.setPassword(PasswordStorage.createHash(dashi.getPassword()));
            dashi.setBio("Asian + Latin Helping people add a little Sriracha to their Sombreros! Dashi is a food truck combining Asian and Latin cuisines -- from Steamed Buns to Tacos, and more!");
            Location dashiLoc = new Location(32.788642f, -79.950876f, "48");
            dashiLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            dashiLoc = locationRepository.save(dashiLoc);
            dashi.setLocation(dashiLoc);
            vendorRepository.save(dashi);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(1), dashi));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(2), dashi));
            System.out.println("Created bon");


            Vendor bacon = (new Vendor("bacon@bacon.com", "Bac'n Me Crazy", "password"));
            bacon.setBio("Charlestons gourmet Bacon Food truck. Serving all types of Bacon sandwiches and crazy Bacon filled eats.");
            bacon.setPassword(PasswordStorage.createHash(bacon.getPassword()));
            Location baconLoc = new Location(32.785400f, -79.937957f, "48");
            baconLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            baconLoc = locationRepository.save(baconLoc);
            bacon.setLocation(baconLoc);
            vendorRepository.save(bacon);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(1), bacon));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(3), bacon));
            System.out.println("created pink");


            Vendor lobster = (new Vendor("lob@lob.com", "The Immortal Lobster", "password"));
            lobster.setBio("We're a family-owned food truck that uses delicious, sustainable Maine Lobster & fresh local produce in hopes to bring New England, down South!!");
            lobster.setPassword(PasswordStorage.createHash(lobster.getPassword()));
            Location lobLoc = new Location(32.786300f, -79.94384f, "48");
            lobLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            lobLoc = locationRepository.save(lobLoc);
            lobster.setLocation(lobLoc);
            vendorRepository.save(lobster);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(3), lobster));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(4), lobster));
            System.out.println("created lob");

            Vendor autob = (new Vendor("auto@auto.com", "Autobahn", "password"));
            autob.setBio("Auto-Banh Food Truck is slingin' deliciously addictive and creative Banh Mi (Vietnamese baguette sandwiches) all over Charleston and the surrounding areas! ");
            autob.setPassword(PasswordStorage.createHash(autob.getPassword()));
            Location autobLoc = new Location(32.796632f, -79.944514f, "48");
            autobLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            autobLoc = locationRepository.save(autobLoc);
            autob.setLocation(autobLoc);
            vendorRepository.save(autob);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(4), autob));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(5), autob));
            System.out.println("created autob");

            Vendor huevos = (new Vendor("huevos@huevos.com", "Outta My Huevos", "password"));
            huevos.setBio("A from- scratch food truck specializing in brunch fare. OMH works closely with local farmers in order to provide the highest quality ingredients possible for its customers.");
            huevos.setPassword(PasswordStorage.createHash(huevos.getPassword()));
            Location huevosLoc = new Location(32.86857473f, -79.92099364f, "48");
            huevosLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            huevosLoc = locationRepository.save(huevosLoc);
            huevos.setLocation(huevosLoc);
            vendorRepository.save(huevos);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(4), huevos));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(5), huevos));
            System.out.println("created Huevos");

            Vendor pink = (new Vendor("pink@pink.com", "Pink Bellies", "password"));
            pink.setBio("Delicious Vietnamese Food made with local ingredients");
            pink.setPassword(PasswordStorage.createHash(pink.getPassword()));
            Location pinkLoc = new Location(32.79515009f, -79.94321823f, "48");
            pinkLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            pinkLoc = locationRepository.save(pinkLoc);
            pink.setLocation(pinkLoc);
            vendorRepository.save(pink);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(1), pink));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(3), pink));
            System.out.println("created pink");


        }

    }

}