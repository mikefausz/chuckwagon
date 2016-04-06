package com.chuckwagon.controllers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuckwagon.entities.Location;
import com.chuckwagon.entities.UserNotFoundException;
import com.chuckwagon.entities.Vendor;
import com.chuckwagon.services.VendorRepository;
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
    ResponseEntity<?> addVendor(@RequestBody Vendor vendor) {
        //run validates

        Vendor result = vendorRepository.save(vendor);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri()); //keep an eye on this now.

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
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