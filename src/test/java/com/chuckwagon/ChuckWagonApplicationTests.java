package com.chuckwagon;

import com.chuckwagon.entities.Vendor;
import com.chuckwagon.services.VendorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

//lot of this testing stuff comes from https://spring.io/guides/tutorials/bookmarks/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChuckWagonApplication.class)
@WebAppConfiguration
public class ChuckWagonApplicationTests {

	private MockMvc mockMvc;  //makes mock requests

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	WebApplicationContext wap;


	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();

		vendorRepository.deleteAll();
	}


	@Test
	public void createVendorTest() throws Exception {

		Vendor vendor = new Vendor();

        vendor.setVendorName("Auto Bahn Mi");
        vendor.setPassword("EatIt");

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
	public void contextLoads() {
	}

}
