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
        String[] tagStrings = { "BBQ", "Seafood", "Pizza", "Desert", "Soul Food", "Tacos", "Salads", "Asian", "Breakfast", "Juice & Smoothies", "Sandwiches"};
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
            dashiLoc.setImageUrl("https://scontent-atl3-1.xx.fbcdn.net/v/t1.0-0/p640x640/11141196_1766106836952532_7506791276925488937_n.jpg?oh=10ad25c67b80253691481d7e2cded537&oe=57A4119B ");
            dashiLoc = locationRepository.save(dashiLoc);
            dashi.setLocation(dashiLoc);
            dashi.setProfilePictureLocation("http://static1.squarespace.com/static/527065cae4b0aa61f1851f94/t/55424a2fe4b04eb8d8adc025/1430407728287/ ");
            vendorRepository.save(dashi);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(8), dashi));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(6), dashi));
            System.out.println("Created bon");

            Vendor bacon = (new Vendor("bacon@bacon.com", "Bac'n Me Crazy", "password"));
            bacon.setBio("Charlestons gourmet Bacon Food truck. Serving all types of Bacon sandwiches and crazy Bacon filled eats.");
            bacon.setPassword(PasswordStorage.createHash(bacon.getPassword()));
            Location baconLoc = new Location(32.787342f, -79.48267f, "48");
            baconLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            baconLoc.setImageUrl("https://scontent-atl3-1.xx.fbcdn.net/hphotos-xfp1/v/t1.0-9/13000085_1780770425484372_6017258864353769878_n.jpg?oh=06db5e6b23d71511c3dff80582370bc7&oe=57B0FFC8 ");
            baconLoc = locationRepository.save(baconLoc);
            bacon.setLocation(baconLoc);
            bacon.setProfilePictureLocation("http://www.bacnmecrazy.com/wp-content/uploads/2014/06/BacnMeCrazy_LogoFinal-e1402508228900.png");
            vendorRepository.save(bacon);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(9), bacon));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(5), bacon));
            System.out.println("created pink");

            Vendor lobster = (new Vendor("lob@lob.com", "The Immortal Lobster", "password"));
            lobster.setBio("We're a family-owned food truck that uses delicious, sustainable Maine Lobster & fresh local produce in hopes to bring New England, down South!!");
            lobster.setPassword(PasswordStorage.createHash(lobster.getPassword()));
            Location lobLoc = new Location(32.7880982f, -79.9375969f, "48");
            lobLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            lobLoc.setImageUrl("https://scontent.cdninstagram.com/hphotos-xap1/t51.2885-15/s640x640/sh0.08/e35/12519269_150627505312547_696791116_n.jpg");
            lobLoc = locationRepository.save(lobLoc);
            lobster.setLocation(lobLoc);
            lobster.setProfilePictureLocation("https://lh4.googleusercontent.com/-VkfY_yJeKuY/AAAAAAAAAAI/AAAAAAAAABs/4rlmgnHLyEE/photo.jpg");
            vendorRepository.save(lobster);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(2), lobster));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(11), lobster));
            System.out.println("created lob");

            Vendor autob = (new Vendor("auto@auto.com", "Auto-banh", "password"));
            autob.setBio("Auto-Banh Food Truck is slingin' deliciously addictive and creative Banh Mi (Vietnamese baguette sandwiches) all over Charleston and the surrounding areas! ");
            autob.setPassword(PasswordStorage.createHash(autob.getPassword()));
            Location autobLoc = new Location(32.7966824f, -79.9969835f, "48");
            autobLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            autobLoc = locationRepository.save(autobLoc);
            autob.setLocation(autobLoc);
            autob.setProfilePictureLocation("http://www.holycitybrewing.com/sites/default/files/imagecache/custom_1000px/autobanh_0.jpg");
            vendorRepository.save(autob);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(11), autob));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(8), autob));
            System.out.println("created autob");

            Vendor huevos = (new Vendor("outta@outta.com", "Outta My Huevos", "password"));
            huevos.setBio("A from-scratch food truck specializing in brunch fare. OMH works closely with local farmers in order to provide the highest quality ingredients possible for its customers.");
            huevos.setPassword(PasswordStorage.createHash(huevos.getPassword()));
            Location huevosLoc = new Location(32.78639043f, -79.93647125f, "48");
            huevosLoc.setExpiresObject(LocalDateTime.now().plusDays(5));
            huevosLoc.setImageUrl("http://2.bp.blogspot.com/-Uxcm7zsV6j4/UQWIV-ujBRI/AAAAAAAAEC8/6TW0l57YLi8/s1600/IMG_0746.JPG ");
            huevosLoc = locationRepository.save(huevosLoc);
            huevos.setLocation(huevosLoc);
            huevos.setProfilePictureLocation("https://pbs.twimg.com/profile_images/574347391244984320/PSmplEUe.jpeg");
            vendorRepository.save(huevos);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(9), huevos));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(6), huevos));
            System.out.println("created Huevos");

            Vendor pink = (new Vendor("pink@pink.com", "Pink Bellies", "password"));
            pink.setBio("Delicious Vietnamese Food made with local ingredients. We prepare our Vietnamese dishes with modern techniques, old family recipes, and local ingredients.");
            pink.setPassword(PasswordStorage.createHash(pink.getPassword()));
            Location pinkLoc = new Location(32.7854069f, -79.9381028f, "48");
            pinkLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            pinkLoc.setTweet("Come to Calhoun and St. Phillip for a banh mi study break");
            pinkLoc.setImageUrl("https://cdn3.vox-cdn.com/thumbor/VTLQKuFL8MpIzMyEtrQSruwfA2g=/0x0:4896x2754/1050x591/cdn0.vox-cdn.com/uploads/chorus_image/image/47495951/pinkbelliesam.0.0.0.0.jpg");
            pinkLoc = locationRepository.save(pinkLoc);
            pink.setLocation(pinkLoc);
            pink.setProfilePictureLocation("https://pbs.twimg.com/profile_images/686695485357330433/W5P4gigM.jpg");
            vendorRepository.save(pink);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(1), pink));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(11), pink));
            System.out.println("created pink");

            Vendor cast = (new Vendor("cast@cast.com", "Cast Iron", "password"));
            cast.setBio("Grandmas swear by it & Chefs depend on it... Cast Iron.");
            cast.setPassword(PasswordStorage.createHash(cast.getPassword()));
            Location castLoc = new Location(32.840511f, -80.01388f, "48");
            castLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            castLoc.setTweet("Charred Brussels with a Bacon Cheddar Burger!");
            castLoc.setImageUrl("https://scontent-atl3-1.xx.fbcdn.net/hphotos-xlf1/v/t1.0-0/p480x480/13083190_850295471762855_129006625686555498_n.jpg?oh=2d2f4b914cf9f6b7b442699f44bc9709&oe=5778ADC1 ");
            castLoc = locationRepository.save(castLoc);
            cast.setLocation(castLoc);
            cast.setProfilePictureLocation("https://s3-media4.fl.yelpcdn.com/bphoto/xlItJX-c6jeAsn3dU5FpjA/ls.jpg ");
            vendorRepository.save(cast);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(1), cast));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(5), cast));
            System.out.println("created cast");

            Vendor lunch = (new Vendor("lunch@lunch.com", "Lunchbox", "password"));
            lunch.setBio("Southern Gourmet Soul-food & BBQ with a Tex-Mex Flare.");
            lunch.setPassword(PasswordStorage.createHash(lunch.getPassword()));
            Location lunchLoc = new Location(32.86291f, -79.91477f, "48");
            lunchLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            lunchLoc.setTweet("Cloudy with a chance of nachos!!! Pulled pork, queso, pickled jalapeños, cilantro and BBQ sauce. Here till 1:30pm.");
            lunchLoc = locationRepository.save(lunchLoc);
            lunch.setLocation(lunchLoc);
            lunch.setProfilePictureLocation("https://pbs.twimg.com/profile_images/488768918601732096/t43gZSbV.jpeg");
            vendorRepository.save(lunch);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(11), lunch));
            System.out.println("created lunch");

            Vendor king = (new Vendor("king@king.com", "King of Pops", "password"));
            king.setBio("Providing all natural hand crafted pops in the low country.");
            king.setPassword(PasswordStorage.createHash(king.getPassword()));
            Location kingLoc = new Location(32.86415364f, -79.91449641f, "48");
            kingLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            kingLoc = locationRepository.save(kingLoc);
            king.setLocation(kingLoc);
            king.setProfilePictureLocation("http://academicdepartments.musc.edu/sebin/j/x/kingofpops%20logo.png");
            vendorRepository.save(king);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(4), king));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(10), king));
            System.out.println("created king");

            Vendor juice = (new Vendor("juice@juice.com", "Juice Joint", "password"));
            juice.setBio("The Juice Joint's mission is supremely healthy and nutrient-dense juices made from whole, organic, sustainable raw fruits and vegetables; whole fruit smoothies.");
            juice.setPassword(PasswordStorage.createHash(juice.getPassword()));
            Location juiceLoc = new Location(32.65578969f, -79.94008672f, "48");
            juiceLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            juiceLoc.setTweet("Come see us the weekend from 7-7 Saturday and Sunday for some sun and delicious healthy treats! Stop by post bridge run!");
            juiceLoc = locationRepository.save(juiceLoc);
            juice.setLocation(juiceLoc);
            juice.setProfilePictureLocation("https://pbs.twimg.com/profile_images/3757409917/b8c2eb3cccb9c44a41331334b7a5262f_400x400.jpeg");
            vendorRepository.save(juice);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(10), juice));
            System.out.println("created juice");

            Vendor tokyo = (new Vendor("tokyo@tokyo.com", "Tokyo Crepes", "password"));
            tokyo.setBio("The Only Japanese Style of Crepes you can find in South East! Gluten free, and Dairy free are available!");
            tokyo.setPassword(PasswordStorage.createHash(tokyo.getPassword()));
            Location tokyoLoc = new Location(32.65610584f, -79.93931425f, "48");
            tokyoLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            tokyoLoc.setTweet("We’ll be out here all day and night!");
            tokyoLoc.setImageUrl("https://media-cdn.tripadvisor.com/media/photo-s/08/62/42/01/tokyo-crepes.jpg ");
            tokyoLoc = locationRepository.save(tokyoLoc);
            tokyo.setLocation(tokyoLoc);
            tokyo.setProfilePictureLocation("http://41.media.tumblr.com/9ba5d1cbe019a1b39ba3e7462a84aa70/tumblr_n8079vVKZY1rcy0txo1_500.jpg");
            vendorRepository.save(tokyo);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(4), tokyo));
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(9), tokyo));
            System.out.println("created Tokyo");

            Vendor mac = (new Vendor("mac@mac.com", "Mac Daddy", "password"));
            mac.setBio("The Mac Daddy Food Truck offers include Chef Landen’s signature Mac & Cheese, must have toppings for your mac, and daily sandwich and salad specials.");
            mac.setPassword(PasswordStorage.createHash(mac.getPassword()));
            Location macLoc = new Location(32.86465379f, -79.91207706f, "48");
            macLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            macLoc.setImageUrl("http://cravekitchenandcocktails.com/wp-content/uploads/2015/09/mac3.jpg");
            macLoc = locationRepository.save(macLoc);
            mac.setLocation(macLoc);
            mac.setProfilePictureLocation("http://cravekitchenandcocktails.com/wp-content/uploads/2015/08/Mac-Daddy-Logo-Revised-1-300x300.png");
            vendorRepository.save(mac);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(5), mac));
            System.out.println("created Mac");

            Vendor grain = (new Vendor("grain@grain.com", "Short Grain", "password"));
            grain.setBio("Come along for the ride on the daily culinary adventures of the Short Grain food truck and its untraditional take on Japanese fare. Arigato ya'll.");
            grain.setPassword(PasswordStorage.createHash(grain.getPassword()));
            Location grainLoc = new Location(32.792677f, -79.941683f, "48");
            grainLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            grainLoc.setTweet("Owner Shuai Wang making the best Beet Salad in town!");
            grainLoc.setImageUrl("http://www.charlestonscene.com/storyimage/CP/20150603/CS/150609783/AR/0/AR-150609783.jpg&maxw=800&q=90 ");
            grainLoc = locationRepository.save(grainLoc);
            grain.setLocation(grainLoc);
            grain.setProfilePictureLocation("https://pbs.twimg.com/media/Cf7qUytUMAAElW6.jpg ");
            vendorRepository.save(grain);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(8), grain));
            System.out.println("created Grain");

            Vendor tow = (new Vendor("tow@tow.com", "Towin' the Dough", "password"));
            tow.setBio("A mobile woodfired pizza food truck located in beautiful Charleston, SC");
            tow.setPassword(PasswordStorage.createHash(tow.getPassword()));
            Location towLoc = new Location(32.8216426f, -79.9515599f, "48");
            towLoc.setExpiresObject(LocalDateTime.now().plusDays(2));
            towLoc.setTweet("It’s a perfect day for a margherita and a margarita");
            towLoc = locationRepository.save(towLoc);
            tow.setLocation(towLoc);
            tow.setProfilePictureLocation("https://www.foodtrucksin.com/sites/default/files/towin.jpg ");
            vendorRepository.save(tow);
            tagVendorRepository.save(new TagVendor(tagRepository.findOne(3), tow));
            System.out.println("created Towin");
        }
    }
}