package com.chuckwagon.controllers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.chuckwagon.entities.Location;
import com.chuckwagon.entities.Vendor;
import com.chuckwagon.services.MenuRepository;
import com.chuckwagon.services.VendorRepository;
import com.chuckwagon.utils.EmailUtils;
import com.chuckwagon.utils.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by branden on 4/5/16 at 21:19.
 */

@CrossOrigin
@RestController
public class ChuckWagonController {

    //set up method wide vars
    private final VendorRepository vendorRepository;
    private final MenuRepository menuRepository;
    //Autowire in repos
    @Autowired
    ChuckWagonController(VendorRepository vendorRepository, MenuRepository menuRepository) {
        this.vendorRepository = vendorRepository;
        this.menuRepository = menuRepository;
    }

    /** this area is for viewing DB in web browser */

    Server dbui = null;

    @PostConstruct
    public void init() throws SQLException {
        dbui = Server.createWebServer().start();
    }

    @PreDestroy
    public void preDestory() {
        dbui.stop();
    }


    /** end DB viewing code */



    //add a new vendor to DB
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

    //request a single vendor.
    @RequestMapping(value = "/vendor/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getVendor(@PathVariable("id") Integer id) {
        Vendor vendor = vendorRepository.findOne(id);
        if (vendor != null) {
            return new ResponseEntity<Object>(vendor, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    //edit vendor in DB. currently just allows for a picture upload.
    @RequestMapping(value = "/vendor/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateVendor(@PathVariable("id") Integer id, @RequestParam( value = "profilePicture") MultipartFile profilePicture, HttpSession session) throws IOException {

        Vendor vendor = vendorRepository.findOne(id);
        //see if the vendor exists
        if (vendor != null && verifyVendor(session, vendor)) {
            /** Logic for file upload */
            if (profilePicture.getContentType().startsWith("image")) {  //check for a photo of some sort

                String filePath = "public/images/" + vendor.getVendorName().toLowerCase().replace(" ", "");

                //ensure that the directory exists
                File dir = new File(filePath);
                dir.mkdirs();

                //all this creates a random file name
                File photoFile = File.createTempFile("image", profilePicture.getOriginalFilename(), dir);
                FileOutputStream fos = new FileOutputStream(photoFile);
                fos.write(profilePicture.getBytes());

                vendor.setProfilePictureLocation(filePath + photoFile.getName());
                vendorRepository.save(vendor);
                return new ResponseEntity<Object>("Updated", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<Object>("Not an image ", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<Object>("Vendor not logged in", HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/vendor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteVendor(@PathVariable("id") Integer id, HttpSession session) {
        if (verifyVendor(session, vendorRepository.findOne(id))) {
            vendorRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>("A vendor can only be removed by the owning vendor", HttpStatus.UNAUTHORIZED);
        }
    }


    @RequestMapping(value = "/vendor/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody HashMap data, HttpSession session) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {

       //Fake it till you make it -- take this out in production
        if (vendorRepository.findByContactEmail("email") == null) {
            vendorRepository.save(new Vendor("email", "vendor",  PasswordStorage.createHash("password")));
        }

        //make connection to logging in vendor and vendors in DB
        Vendor vendor = vendorRepository.findByContactEmail((String) data.get("contactEmail"));

        //check to see if vendor exists in DB, and that password matches.
        if (vendor != null && PasswordStorage.verifyPassword((String) data.get("password"), vendor.getPassword())) {
            session.setAttribute("email", vendor.getContactEmail());
            return new ResponseEntity<Object>(vendor, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>("Password Mismatch", HttpStatus.UNAUTHORIZED);

        }
       // httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri()); //keep an eye on this now.
    }





    @RequestMapping(value = "/{Location}", method = RequestMethod.GET)
    public List<Vendor> vendorByLocation(@PathVariable Location locaton) {
        return vendorRepository.findByIsActive(true);
    }


    @RequestMapping(value = "vendor/{id}/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logout(HttpSession session, @RequestParam("id") Integer id) {

        if (verifyVendor(session, vendorRepository.findOne(id))) {
            session.invalidate();
            return new ResponseEntity<Object>("logged out", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>(null, HttpStatus.UNAUTHORIZED);
        }
    }



    public boolean verifyVendor(HttpSession session, Vendor vendor) {
        if (vendor.getContactEmail().equals(session.getAttribute("email"))) {
            return true;
        } else {
            return false; }
    }


}