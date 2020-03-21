package com.code.test.app.myretail.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.code.test.app.myretail.dto.CurrentPrice;
import com.code.test.app.myretail.dto.Product;
import com.code.test.app.myretail.service.ProductService;

/**
 * 
 * @author naveen
 *
 */

public class ProductControllerTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MockMvc mockMvc;

	@Mock
	ProductService productService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getProductByIdTest() throws Exception {
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setCurrencyCode("USD");
		currentPrice.setValue(134.99);
		Product product = new Product();
		product.setCurrentPrice(currentPrice);
		product.setId("13860428");
		product.setName("The Big Lebowski (Blu-ray) (Widescreen)");
		String expected = "{\"id\":\"13860428\",\"name\":\"The Big Lebowski (Blu-ray) (Widescreen)\",\"current_price\":{\"value\":134.99,\"currency_code\":\"USD\"}}";
		doReturn(product).when(productService).getProductById(anyString());
		String url = "/products/15117729";
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE);
		MvcResult actual = mockMvc.perform(reqBuilder).andReturn();
		JSONAssert.assertEquals(expected, actual.getResponse().getContentAsString(), false);
	}

}
