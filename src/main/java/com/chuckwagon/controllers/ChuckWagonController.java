package com.chuckwagon.controllers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

import com.chuckwagon.entities.*;
import com.chuckwagon.services.*;
import com.chuckwagon.utils.EmailUtils;
import com.chuckwagon.utils.PasswordStorage;
import com.chuckwagon.utils.PopulateDB;
import com.sun.deploy.util.StringUtils;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by branden on 4/5/16 at 21:19.
 */

@CrossOrigin(origins = "*")
@RestController
public class ChuckWagonController {

    //set up method wide vars
    private final VendorRepository vendorRepository;
    private final MenuRepository menuRepository;
    private final LocationRepository locationRepository;
    private final TagRepository tagRepository;
    private final TagVendorRepository tagVendorRepository;

    //Autowire in repos
    @Autowired
    ChuckWagonController(VendorRepository vendorRepository, MenuRepository menuRepository,
                         LocationRepository locationRepository, TagRepository tagRepository,
                         TagVendorRepository tagVendorRepository) {
        this.vendorRepository = vendorRepository;
        this.menuRepository = menuRepository;
        this.locationRepository = locationRepository;
        this.tagRepository = tagRepository;
        this.tagVendorRepository = tagVendorRepository;
    }
    /** this area is for viewing DB in web browser */

    Server dbui = null;

    @PostConstruct
    public void init() throws SQLException, PasswordStorage.CannotPerformOperationException {
        dbui = Server.createWebServer().start();
        PopulateDB.populate(vendorRepository, locationRepository, tagRepository);
    }

    @PreDestroy
    public void preDestory() {
        dbui.stop();
    }


    /** end DB viewing code */

    /**
     * Route adds a menu to a vendor.
     * A vendor can have multiple menus.
     * Menus have names for toggle-ability
     * Menus are images
     *
     * @param id Path variable describing the vendor logged in
     * @param menuPicture Image of the menu. Image stored on DB, a link to image is stored in the Menu object
     * @param menuName Name to refer to the menu as
     * @return returns the status of the request
     */
    @RequestMapping(value = "/vendor/{id}/menu", method = RequestMethod.POST)
    public ResponseEntity<?> addMenuToVendor(@PathVariable("id") Integer id, @RequestParam(value = "menuPicture") MultipartFile menuPicture, @RequestParam(value = "menuName") String menuName) throws IOException {

        Menu menu = new Menu(vendorRepository.findOne(id), menuName);

            if (menuName != null && menuPicture != null) {
                photoUpload(menuPicture, vendorRepository.findOne(id), Optional.of(menu));
                menuRepository.save(menu);
                System.out.println(menu);
                return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Did not receive menu content",HttpStatus.NO_CONTENT);
            }
    }

    /**
     * Adds a new vendor into the DB
     * allows for minimal fields
     * other fields to be set in vendor PUT route
     *
     * @param vendor Vendor object with minimum required fields
     * @return the vendor and a status of the the request
     */
    @RequestMapping(value = "/vendor", method = RequestMethod.POST)
    public ResponseEntity<?> addVendor(@RequestBody Vendor vendor) throws PasswordStorage.CannotPerformOperationException {
        //We are expecting an email address(id) a vendor name, and a password
        if (!EmailUtils.isValidEmailAddress(vendor.getContactEmail())) return new ResponseEntity<Object>("invalid email", HttpStatus.BAD_REQUEST);
        //String regex = "[$&+,:;=?@#|'<>.-^*()%!]+";
        //if (vendor.getPassword().length() < 4 || !vendor.getPassword().matches(regex)) return new ResponseEntity<Object>("Invalid Password", HttpStatus.BAD_REQUEST);
        if (vendor.getPassword().length() < 4) return new ResponseEntity<Object>("Invalid Password", HttpStatus.BAD_REQUEST);

        //hash password
        vendor.setPassword(PasswordStorage.createHash(vendor.getPassword()));
        Vendor result = vendorRepository.save(vendor);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * Returns all details about all vendors in the DB
     *
     * @return all vendors and a response status
     */
    @RequestMapping(value = "/vendor", method = RequestMethod.GET)
    public ResponseEntity<?> getAllVendors() {
        return new ResponseEntity<Object>(vendorRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Returns all details about a single vendor
     *
     * @param id Path variable describing the vendor that's logged in/being manipulated
     * @return single vendor and a response status
     */
    @RequestMapping(value = "/vendor/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getVendor(@PathVariable("id") Integer id) {
        Vendor vendor = vendorRepository.findOne(id);
        if (vendor != null) {
            return new ResponseEntity<Object>(vendor, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Uploads a profile picture for a specific vendor
     *
     * @param id Path variable describing the vendor that's logged in/being manipulated
     * @param profilePicture MultiPartFile that is image being uploaded
     * @return string of status and response status
     */
    @RequestMapping(value = "/vendor/{id}/photo", method = RequestMethod.POST)
    public ResponseEntity<?> updateVendor(@PathVariable("id") Integer id, @RequestParam( value = "profilePicture", required = false) MultipartFile profilePicture) throws IOException {

        Vendor vendor = vendorRepository.findOne(id);
            if (profilePicture != null && profilePicture.getContentType().startsWith("image")) {  //check for a photo of some sort
                photoUpload(profilePicture, vendor, Optional.empty());
                vendorRepository.save(vendor);
                return new ResponseEntity<Object>("Updated", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<Object>("Not an image ", HttpStatus.NOT_ACCEPTABLE);
            }
        }


    /**
     * If first visit to route allows to set additional vendor profile information and tags
     *
     * If fields already set, allows for updating
     *
     * @param id Path variable describing the vendor that's logged in/being manipulated
     * @param data HashMap that contains a simple String Array of tags and a HashMap of Vendor fields to be updated
     * @return the manipulated vendor and a response status
     */
    @RequestMapping(value = "/vendor/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateVendor(@PathVariable("id") Integer id, @RequestBody HashMap data) {

        Vendor vendor = vendorRepository.findOne(id);  //reference to the vendor we are editing

        if (vendor != null) {
            Set<Tag> tagSet = new HashSet<>(); //empty hashset

            String[] tags = (data.get("tags").toString().split(","));  //grab the tags from client

            tagVendorRepository.deleteByVendor(vendor); //remove current tags from DB

            //add all the tags into the set and add to DB
            for (String t : tags) {
                Tag tag = tagRepository.findByTag(t);
                //check to see if tag does is not in DB at some point here.
                tagSet.add(tag);
                TagVendor tagVendor = new TagVendor(tag, vendor);
                tagVendorRepository.save(tagVendor);
            }

            //reset vendor fields
            HashMap vend = (HashMap) data.get("vendor"); //map of the vendor
            vendor.setBio((String) vend.get("bio"));
            vendor.setProfilePictureLocation((String) vend.get("profileImgURL"));
            vendor.setTags(tagSet);

            vendorRepository.save(vendor); //save vendor

            return new ResponseEntity<Object>(vendor, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>("Vendor not found", HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Removes a vendor from the DB and removes all associated tags, menus, and images
     *
     * @param id Path variable describing the vendor that's logged in/being manipulated
     * @return a response status
     */
    @RequestMapping(value = "/vendor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteVendor(@PathVariable("id") Integer id) {

        if (vendorRepository.findOne(id) != null) {

            File path = new File("public/images/" + vendorRepository.findOne(id).getVendorName().toLowerCase().replace(" ", ""));
            deleteFilesAndDirectory(path);

            vendorRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>("Vendor not found in DB", HttpStatus.NOT_FOUND);
        }
        }

    /**
     * Add a location with expiration to a vendor
     *
     * @param id Path variable describing the vendor that's logged in/being manipulated
     * @param location Location object that contains a lat and lng as well as a time to expire
     * @return a vendor and sucessful response or a string and a failure response
     */
    @RequestMapping(value = "/vendor/{id}/location", method = RequestMethod.POST)
    public ResponseEntity<?> addVendorLocation(@PathVariable("id") Integer id, @RequestBody Location location) {
        if (location != null && (location.getLat() >= 0 || location.getLat() <= 0) && (location.getLng() >= 0 || location.getLng() <= 0) && location.getExpiresString() != null) {
            Vendor vendor = vendorRepository.findOne(id); //vendor that is entering a location
            Location existingLocation = locationRepository.findByVendor(vendor); //possible preexisiting location set by vendor

            if (existingLocation != null) { locationRepository.delete(existingLocation); }

            location.setVendor(vendorRepository.findOne(id));
            double hours = Double.valueOf(location.getExpiresString()); //catch as double to preserve decimal
            hours = hours * 60 * 60; //convert hours to seconds
            location.setExpiresObject(LocalDateTime.now().plusSeconds((long) hours)); //add time to expire to the current time
            locationRepository.save(location);
            return new ResponseEntity<Object>(vendor, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>("Missing required fields", HttpStatus.PARTIAL_CONTENT);
        }
    }

    /**
     * Returns all vendors with locations attached
     *
     * @return
     */
    @RequestMapping(value = "/vendor/location", method = RequestMethod.GET)
    public ResponseEntity<?> getVendorLocations() {
        List<Location> locationList = (List<Location>) locationRepository.findAll();
        //runs through and deletes expired vendors
        if (locationList.size() > 0) {
            locationList.forEach(location -> {
                if (location.getExpiresObject().isBefore(LocalDateTime.now())) {
                    locationRepository.delete(location);
                }
            });
            //gets fresh list of locations
            locationList = (List<Location>) locationRepository.findAll();

            //list to hold special object that describes what the FE wants
            ArrayList<VendorData> vendorDataList = new ArrayList<>();

            //build each object
            for (Location l : locationList) {
                VendorData vendorData = new VendorData();
                vendorData.setId(l.getVendor().getId());
                vendorData.setName(l.getVendor().getVendorName());

                Set<Tag> tags = tagVendorRepository.findByVendor(l.getVendor());
                if (tags.size() > 0 ) {
                    vendorData.setTags(StringUtils.join(tags, ","));
                }
                HashMap<String, Float> location = new HashMap<>();
                location.put("lat", l.getLat());
                location.put("lng", l.getLng());
                vendorData.setLocation(location);
                vendorData.setProfileimgURL(l.getVendor().getProfilePictureLocation());
                vendorData.setBio(l.getVendor().getBio());

                vendorDataList.add(vendorData);
            }
            return new ResponseEntity<Object>(vendorDataList, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("No Wagons Rolling", HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Allows a vendor to log in where they will be able to update profiled and set locations
     *
     * @param data hashmap of email and password
     * @return vendor object and response status on success or string and response status if not found
     */
    @RequestMapping(value = "/vendor/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody HashMap data) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        //make connection to logging in vendor and vendors in DB
        Vendor vendor = vendorRepository.findByContactEmail((String) data.get("contactEmail"));

        //check to see if vendor exists in DB, and that password matches.
        if (vendor != null && PasswordStorage.verifyPassword((String) data.get("password"), vendor.getPassword())) {
            return new ResponseEntity<Object>(vendor, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>("Password Mismatch", HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{Location}", method = RequestMethod.GET)
    public List<Vendor> vendorByLocation(@PathVariable Location locaton) {
        return vendorRepository.findByIsActive(true);
    }

    @RequestMapping(value = "vendor/{id}/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(HttpSession session, @RequestParam("id") Integer id) {
            session.invalidate();
            return new ResponseEntity<Object>("logged out", HttpStatus.ACCEPTED);
        }

    /**
     * Processes an image and stores to server returning a link to where it was stored
     * Can accept a profile picture or a menu picture
     * Send Menu object if is menu picture
     *
     * @param photo MultiPartFile that contains image
     * @param vendor Vendor Object to associate image with
     * @param m Optionally, Menu Object to associate image with
     */
    public void photoUpload(MultipartFile photo, Vendor vendor, Optional<Menu> m) throws IOException {

        String filePath = (m.isPresent())
                ? "public/images/" + vendor.getVendorName().toLowerCase().replace(" ", "") + "/menus/"
                : "public/images/" + vendor.getVendorName().toLowerCase().replace(" ", "") + "/";

        //ensure that the directory exists
        File dir = new File(filePath);
        dir.mkdirs();

        //all this creates a random file name
        File photoFile = File.createTempFile("image", photo.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(photoFile);
        fos.write(photo.getBytes());

        if (m.isPresent()) {
            m.get().setPhotoLocation(filePath + photoFile.getName());
        } else {
            vendor.setProfilePictureLocation(filePath + photoFile.getName());
        }
    }

    /**
     *I was doing this manually previously but I found this walk through and decided to implement it.
     * this method seems to run a bit faster
     *http://www.mkyong.com/java/how-to-delete-directory-in-java/
     */
    public void deleteFilesAndDirectory(File path) {
        if(path.isDirectory()) { //if the file is a directory
            if(path.list().length == 0) { //if there are no files in the directory
                path.delete(); //this wil delete the directory
            } else { //if there are some files in the directory
                String files[] = path.list();
                for (String temp : files) {
                    //point to the file
                    File file = new File(path, temp);
                    deleteFilesAndDirectory(file);  //recursive call to method
                }
                //check directory again
                if (path.list().length == 0) {
                    path.delete();  //delete directory if no files
                }
            }
        } else { //if the file is not a directory and is a file
            path.delete();
        }
    }

    /**
     *
     *Catch all route for request method options
     */
    @RequestMapping(method = RequestMethod.OPTIONS, value = "/**")
    public void manageOptions(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
    }

}