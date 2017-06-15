package io.myRetail;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



import org.junit.Before;

import org.junit.Test;

import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.myRetail.MyRetailApplication;
import io.myRetail.PriceRepository;


@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(classes = MyRetailApplication.class)

@SpringBootTest
public class ControllerTest {

	
	private static MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	// to test GET request for the product in external API
	@Test
	public void retrieveProductDetailsbyId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products/13860428").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.current_price").exists())
		.andExpect(jsonPath("$.id").value(13860428))
		.andExpect(jsonPath("$.name").value("The Big Lebowski (Blu-ray)"))
		.andExpect(jsonPath("$.current_price.value").value(priceRepository.findById(13860428).getValue()));
		
	}
	
	// to test GET request for the product not in external API
	@Test
	public void retrieveNoProductDetailsById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products/15117729").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").doesNotExist())
		.andExpect(jsonPath("$.current_price").exists())
		.andExpect(jsonPath("$.id").value(15117729))
		
		.andExpect(jsonPath("$.current_price").value("Product not found"));
		
	}
	

	//to test PUT Request by performing GET on the product whose pricing info is changed
	@Test
	public void retrieveUpdateProductDetailsById() throws Exception {
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/products/16696652").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.current_price").exists())
		.andExpect(jsonPath("$.id").value(16696652))
		
		.andExpect(jsonPath("$.name").value("Beats Solo 2 Wireless - Black (MHNG2AM/A)"))
		
		.andExpect(jsonPath("$.current_price.value").value(priceRepository.findById(16696652).getValue()));
		
	
	}
}

