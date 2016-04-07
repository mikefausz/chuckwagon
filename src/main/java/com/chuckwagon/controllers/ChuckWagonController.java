package com.chuckwagon.controllers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.chuckwagon.entities.Location;
import com.chuckwagon.entities.UserNotFoundException;
import com.chuckwagon.entities.Vendor;
import com.chuckwagon.services.VendorRepository;
import com.chuckwagon.utils.EmailUtils;
import com.chuckwagon.utils.PasswordStorage;
import org.aspectj.lang.annotation.Before;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by branden on 4/5/16 at 21:19.
 */

@CrossOrigin
@RestController
public class ChuckWagonController {


    //set up method wide vars
    private final VendorRepository vendorRepository;
    //Autowire in repos
    @Autowired
    ChuckWagonController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
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



    @RequestMapping(value = "/vendor", method = RequestMethod.POST)
    ResponseEntity<?> addVendor(@RequestBody Vendor vendor) throws PasswordStorage.CannotPerformOperationException {
        //We are expecting an email address(id) a vendor name, and a password
        if (!EmailUtils.isValidEmailAddress(vendor.getContactEmail())) return new ResponseEntity<Object>("invalid email", HttpStatus.BAD_REQUEST);
        String regex = ".[$&+,:;=?@#|'<>.-^*()%!]";
        if (vendor.getPassword().length() < 4 || !vendor.getPassword().matches(regex)) return new ResponseEntity<Object>("Invalid Password", HttpStatus.BAD_REQUEST);


        //hash password
        vendor.setPassword(PasswordStorage.createHash(vendor.getPassword()));
        Vendor result = vendorRepository.save(vendor);


        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/vendor/{id}", method = RequestMethod.POST)
    ResponseEntity<?> updateVendor(@PathVariable Integer id, @RequestBody Vendor vendor, MultipartFile profilePicture) throws IOException {

     //   if (vendorRepository.findOne(id) != null) {

          //  if (profilePicture.getContentType().startsWith("image")) {  //check for a photo of some sort --MAY WANT TO TRY/CATCH THIS?
                //ensure that the directory exists
                File dir = new File("public/images/" + vendor.getVendorName().toLowerCase().replace(" ", ""));
                dir.mkdirs();


                //all this creates a random file name
                File photoFile = File.createTempFile("image", profilePicture.getOriginalFilename(), dir);
                FileOutputStream fos = new FileOutputStream(photoFile);
                fos.write(profilePicture.getBytes());


                vendor.setProfilePictureString(photoFile.getName());
         //   }

            vendorRepository.save(vendor);
            return new ResponseEntity<Object>("Updated", HttpStatus.ACCEPTED);
   //     }




//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vendor.getId()).toUri()); //keep an eye on this now.


      //  return new ResponseEntity<Object>("Vendor does not exist", HttpStatus.NOT_FOUND);
        //return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }



    @RequestMapping(value = "/vendor/{id}", method = RequestMethod.GET)
    Vendor getVendor(@PathVariable Integer id) {
        //validate
        return vendorRepository.findOne(id);
    }

    @RequestMapping(value = "/vendor/login", method = RequestMethod.POST)
    ResponseEntity<?> login(@RequestBody Vendor vendor, HttpSession session) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {


        //Fake it till you make it

        if (vendorRepository.findByContactEmail("email") == null) {
            vendorRepository.save(new Vendor("email", PasswordStorage.createHash("password")));
        }

        Vendor result = vendorRepository.findByContactEmail((String) vendor.getContactEmail());

        if (result != null && PasswordStorage.verifyPassword((String) vendor.getContactEmail(), result.getPassword())) {
            session.setAttribute("email", result.getContactEmail());
            return new ResponseEntity<Object>("Accepted", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>("Password Mismatch", HttpStatus.UNAUTHORIZED);

        }
       // httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri()); //keep an eye on this now.

    }


    @RequestMapping(value = "/{Location}", method = RequestMethod.GET)
    List<Vendor> vendorByLocation(@PathVariable Location locaton) {
        return vendorRepository.findByIsActive(true);
    }




}