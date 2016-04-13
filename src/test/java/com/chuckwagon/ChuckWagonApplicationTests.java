//package com.chuckwagon;
//
//import com.chuckwagon.entities.Location;
//import com.chuckwagon.entities.Menu;
//import com.chuckwagon.entities.Vendor;
//import com.chuckwagon.services.TagVendorRepository;
//import com.chuckwagon.services.VendorRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = ChuckWagonApplication.class)
//@WebAppConfiguration
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class ChuckWagonApplicationTests {
//
//    private MockMvc mockMvc;  //makes mock requests
//
//    @Autowired
//    private VendorRepository vendorRepository;
//    @Autowired
//    private TagVendorRepository tagVendorRepository;
//
//    @Autowired
//    WebApplicationContext wap;
//
//
//    @Before
//    public void before() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
//
//        //vendorRepository.deleteAll();
//    }
//
//
//    @Test
//    public void aCreateVendorTest() throws Exception {
//
//        Vendor vendor = new Vendor("mail@mail.com", "Auto Bahn Mi", "password");
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(vendor);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/vendor")
//                        .content(json)
//                        .contentType("application/json")
//        );
//        Assert.assertTrue(vendorRepository.findOne(5).getVendorName().equals("Auto Bahn Mi"));  //see if it saved properly by pulling a name back out
//        Assert.assertFalse(vendorRepository.findOne(5).getPassword().equals("password"));  //check to see if password got hashed.
//    }
//
//    @Test
//    public void bLoginTest() throws Exception {
//
//        HashMap m = new HashMap();
//        m.put("contactEmail", "mail@mail.com");
//        m.put("password", "password");
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(m);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/vendor/login")
//                        .content(json)
//                        .contentType("application/json")
//        ).andExpect(status().is(202));
//
//    }
//
//    @Test
//    public void cEditVendorFileUploadTest() throws Exception {
//        FileInputStream fis = new FileInputStream(new File("branden-small.jpg"));
//        MockMultipartFile image = new MockMultipartFile("profilePicture", "imageFromClient.jpg", "image/jpeg", fis);
//
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.fileUpload("/vendor/5/photo").file(image).sessionAttr("email", "mail@mail.com")
//        ).andExpect(status().is(202));
//    }
//
//    @Test
//    public void eCreateMenuTest() throws Exception {
//        FileInputStream fis = new FileInputStream(new File("branden-small.jpg"));
//        MockMultipartFile image = new MockMultipartFile("menuPicture", "imageFromClient.jpg", "image/jpeg", fis);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders
//                        .fileUpload("/vendor/5/menu").file(image)
//                        .param("menuName", "Monday")
//                        .sessionAttr("email", "mail@mail.com")
//
//        ).andExpect(status().is(202));
//    }
//
//    @Test
//    public void fAddLocationTest() throws Exception {
//        Location location = new Location( 274747.333F, 272674.33F, "2.5");
//        location.setVendor(vendorRepository.findOne(5));
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(location);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/vendor/5/location")
//                        .content(json)
//                        .contentType("application/json")
//        ).andExpect(status().is(202));
//
//    }
//
//    @Test
//    public void gEditLocationTest() throws Exception {
//        Location location = new Location(38.5848f, 483.3759f, "2.5");
//        location.setVendor(vendorRepository.findOne(5));
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(location);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/vendor/5/location")
//                        .content(json)
//                        .contentType("application/json")
//        ).andExpect(status().is(202));
//    }
//
//    @Test
//    public void hEditVendorTagTest() throws Exception {
//        HashMap m = new HashMap();
//        HashMap v = new HashMap();
//        String[] t = {"BBQ", "Pizza"};
//        v.put("bio", "This is the bio");
//        v.put("profilePictureURL", "http://www.google.com");
//        m.put("vendor", v);
//        m.put("tags", t);
//
//        System.out.println(m);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(m);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/vendor/5")
//                .content(json)
//                .contentType("application/json")
//        ).andExpect(status().is(202));
//
//        Assert.assertTrue(tagVendorRepository.count() == 2);
//    }
//
//    @Test
//    public void zDeleteVendorTest() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.delete("/vendor/5")
//                        .sessionAttr("email", "mail@mail.com")
//        ).andExpect(status().is(202));
//    }
//}
