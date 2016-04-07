package com.chuckwagon;

import com.chuckwagon.entities.Vendor;
import com.chuckwagon.services.VendorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import com.chuckwagon.entities.WebConfig;

//lot of this testing stuff comes from https://spring.io/guides/tutorials/bookmarks/

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = WebConfig.class)
@SpringApplicationConfiguration(classes = ChuckWagonApplication.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChuckWagonApplicationTests {

	private MockMvc mockMvc;  //makes mock requests

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	WebApplicationContext wap;


	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();

		//vendorRepository.deleteAll();
	}


	@Test
	public void ACreateVendorTest() throws Exception {

		Vendor vendor = new Vendor();

        vendor.setVendorName("Auto Bahn Mi");
        vendor.setPassword("EatIt");
		vendor.setContactEmail("email");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(vendor);

        System.out.println(vendor);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/vendor")
						.content(json)
						.contentType("application/json")

		);
		Assert.assertTrue(vendorRepository.findOne(1).getVendorName().equals("Auto Bahn Mi"));  //see if it saved properly by pulling a name back out
        Assert.assertFalse(vendorRepository.findOne(1).getPassword().equals("EatIt"));  //check to see if password got hashed.
	}

	@Test
	public void CLoginTest() throws Exception {

		HashMap m = new HashMap();
		m.put("email", "email");
		m.put("password", "eatIt");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(m);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/vendor/login")
					.content(json)
					.contentType("application/json")
		).andExpect(status().is(202));

	}


	@Test
	public void BEditVendorFileUploadTest() throws Exception {
		MockMultipartFile image = new MockMultipartFile("profilePicture", "imageFromClient.jpg", "image", "a big image file of some stuff".getBytes());

		mockMvc.perform(
				MockMvcRequestBuilders.fileUpload("/vendor/1").file(image)
		).andExpect(status().is(202))
		.andExpect(content().string("success"));

	}

	@Test
	public void contextLoads() {
	}

}
