package com.chuckwagon.controllers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuckwagon.entities.Location;
import com.chuckwagon.entities.UserNotFoundException;
import com.chuckwagon.entities.Vendor;
import com.chuckwagon.services.VendorRepository;
import com.chuckwagon.utils.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        //run validates

        //hash password
        vendor.setPassword(PasswordStorage.createHash(vendor.getPassword()));

        Vendor result = vendorRepository.save(vendor);


        //set up a response entity
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri()); //keep an eye on this now.

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/vendor/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateVendor(@PathVariable Integer id, @RequestBody Vendor vendor) throws IOException {

        if (vendorRepository.findOne(id) != null) {

            if (vendor.getProfilePicture().getContentType().startsWith("image")) {  //check for a photo of some sort --MAY WANT TO TRY CATCH THIS?
                //all this creates a random file name
                File photoFile = File.createTempFile("image", vendor.getProfilePicture().getOriginalFilename(), new File("public/images" + vendor.getVendorName().toLowerCase().replace(" ", "")));
                FileOutputStream fos = new FileOutputStream(photoFile);
                fos.write(vendor.getProfilePicture().getBytes());

                vendor.setProfilePictureString(photoFile.getName());
            }

            vendorRepository.save(vendor);
            return new ResponseEntity<Object>("Updated", HttpStatus.ACCEPTED);
        }




//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vendor.getId()).toUri()); //keep an eye on this now.


        return new ResponseEntity<Object>("Vendor does not exist", HttpStatus.NOT_FOUND);
        //return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }



    @RequestMapping(value = "/vendor/{id}", method = RequestMethod.GET)
    Vendor getVendor(@PathVariable Integer id) {
        //validate
        return vendorRepository.findOne(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<?> login(@RequestBody Vendor vendor, HttpSession session) {
       Vendor result =  validateVendor(vendor.getVendorName());

        session.setAttribute("vendorName", vendor.getVendorName());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri()); //keep an eye on this now.

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/{Location}", method = RequestMethod.GET)
    List<Vendor> vendorByLocation(@PathVariable Location locaton) {
        return vendorRepository.findByIsActive(true);
    }




    //validation methods
    private Vendor validateVendor(String vendorName) {
        return vendorRepository.findByVendorName(vendorName).orElseThrow(
                () -> new UserNotFoundException(vendorName));
    }

}