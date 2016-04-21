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
            Location dashiLoc = new Location(32.8997592f, -79.9831028f, "48");
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
            Location baconLoc = new Location(32.787342f, -79.48267f, "48");
            baconLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            baconLoc = locationRepository.save(baconLoc);
            bacon.setLocation(baconLoc);
            vendorRepository.save(bacon);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(6), bacon));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(7), bacon));
            System.out.println("created pink");


            Vendor lobster = (new Vendor("lob@lob.com", "The Immortal Lobster", "password"));
            lobster.setBio("We're a family-owned food truck that uses delicious, sustainable Maine Lobster & fresh local produce in hopes to bring New England, down South!!");
            lobster.setPassword(PasswordStorage.createHash(lobster.getPassword()));
            Location lobLoc = new Location(32.7880982f, -79.9375969f, "48");
            lobLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            lobLoc = locationRepository.save(lobLoc);
            lobster.setLocation(lobLoc);
            vendorRepository.save(lobster);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(8), lobster));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(6), lobster));
            System.out.println("created lob");

            Vendor autob = (new Vendor("auto@auto.com", "Auto-bahn", "password"));
            autob.setBio("Auto-Banh Food Truck is slingin' deliciously addictive and creative Banh Mi (Vietnamese baguette sandwiches) all over Charleston and the surrounding areas! ");
            autob.setPassword(PasswordStorage.createHash(autob.getPassword()));
            Location autobLoc = new Location(32.7966824f, -79.9969835f, "48");
            autobLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            autobLoc = locationRepository.save(autobLoc);
            autob.setLocation(autobLoc);
            vendorRepository.save(autob);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(4), autob));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(5), autob));
            System.out.println("created autob");

            Vendor huevos = (new Vendor("outta@outta.com", "Outta My Huevos", "password"));
            huevos.setBio("A from- scratch food truck specializing in brunch fare. OMH works closely with local farmers in order to provide the highest quality ingredients possible for its customers.");
            huevos.setPassword(PasswordStorage.createHash(huevos.getPassword()));
            Location huevosLoc = new Location(32.78639043f, -79.93647125f, "48");
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
            Location pinkLoc = new Location(32.7854069f, -79.9381028f, "48");
            pinkLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            pinkLoc = locationRepository.save(pinkLoc);
            pink.setLocation(pinkLoc);
            vendorRepository.save(pink);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(1), pink));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(3), pink));
            System.out.println("created pink");

            Vendor cast = (new Vendor("cast@cast.com", "Cast Iron", "password"));
            cast.setBio("Best BBQ sandwich");
            cast.setPassword(PasswordStorage.createHash(cast.getPassword()));
            Location castLoc = new Location(32.840511f, -80.01388f, "48");
            castLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            castLoc = locationRepository.save(castLoc);
            cast.setLocation(castLoc);
            vendorRepository.save(cast);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(1), cast));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(7), cast));
            System.out.println("created cast");

            Vendor lunch = (new Vendor("lunch@lunch.com", "Lunchbox", "password"));
            lunch.setBio("Southern Gourmet Soul-food");
            lunch.setPassword(PasswordStorage.createHash(lunch.getPassword()));
            Location lunchLoc = new Location(32.86291f, -79.91477f, "48");
            lunchLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            lunchLoc = locationRepository.save(lunchLoc);
            lunch.setLocation(lunchLoc);
            vendorRepository.save(lunch);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(3), lunch));
            System.out.println("created lunch");

            Vendor king = (new Vendor("king@king.com", "King of Pops", "password"));
            king.setBio("We Have Pops");
            king.setPassword(PasswordStorage.createHash(king.getPassword()));
            Location kingLoc = new Location(32.86415364f, -79.91449641f, "48");
            kingLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            kingLoc = locationRepository.save(kingLoc);
            king.setLocation(kingLoc);
            vendorRepository.save(king);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(4), king));
            System.out.println("created king");

            Vendor juice = (new Vendor("juice@juice.com", "Juice Joint", "password"));
            juice.setBio("Juice. and Smoothies");
            juice.setPassword(PasswordStorage.createHash(juice.getPassword()));
            Location juiceLoc = new Location(32.65578969f, -79.94008672f, "48");
            juiceLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            juiceLoc = locationRepository.save(juiceLoc);
            juice.setLocation(juiceLoc);
            vendorRepository.save(juice);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(10), juice));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(9), juice));
            System.out.println("created juice");

            Vendor tokyo = (new Vendor("tokyo@tokyo.com", "Tokyo Crepes", "password"));
            tokyo.setBio("Get your Crepe on");
            tokyo.setPassword(PasswordStorage.createHash(tokyo.getPassword()));
            Location tokyoLoc = new Location(32.65610584f, -79.93931425f, "48");
            tokyoLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            tokyoLoc = locationRepository.save(tokyoLoc);
            tokyo.setLocation(tokyoLoc);
            vendorRepository.save(tokyo);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(9), tokyo));
            System.out.println("created Tokyo");

            Vendor mac = (new Vendor("mac@mac.com", "Mac Daddy", "password"));
            mac.setBio("Mac Daddy Macaroni");
            mac.setPassword(PasswordStorage.createHash(mac.getPassword()));
            Location macLoc = new Location(32.86465379f, -79.91207706f, "48");
            macLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            macLoc = locationRepository.save(macLoc);
            mac.setLocation(macLoc);
            vendorRepository.save(mac);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(2), mac));
            System.out.println("created Mac");

            Vendor grain = (new Vendor("grain@grain.com", "Short Grain", "password"));
            grain.setBio("We Sell Some Grains");
            grain.setPassword(PasswordStorage.createHash(grain.getPassword()));
            Location grainLoc = new Location(32.792677f, -79.941683f, "48");
            grainLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            grainLoc = locationRepository.save(grainLoc);
            grain.setLocation(grainLoc);
            vendorRepository.save(grain);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(3), grain));
            System.out.println("created Grain");

            Vendor tow = (new Vendor("tow@tow.com", "Towin' the Dough", "password"));
            tow.setBio("We Sling that Dough Yo");
            tow.setPassword(PasswordStorage.createHash(tow.getPassword()));
            Location towLoc = new Location(32.8216426f, -79.9515599f, "48");
            towLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            towLoc = locationRepository.save(towLoc);
            tow.setLocation(towLoc);
            vendorRepository.save(tow);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(3), tow));
            System.out.println("created Towin");
        }
    }
}