package com.code.test.app.myretail.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.code.test.app.myretail.dto.CurrentPrice;
import com.code.test.app.myretail.dto.Product;
import com.code.test.app.myretail.exception.MyRetailException;
import com.code.test.app.myretail.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author naveen
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = { "product-name-api-endpoint = http://redsky.target.com" })
@WebMvcTest(value = ProductController.class)
public class ProductControllerTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MockMvc mockMvc;

	@MockBean
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
		String url = "/products/13860428";
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE);
		MvcResult actual = mockMvc.perform(reqBuilder).andReturn();
		JSONAssert.assertEquals(expected, actual.getResponse().getContentAsString(), false);
	}

	@Test
	public void getProductByInvalidIdTest() throws Exception {
		when(productService.getProductById(anyString()))
				.thenThrow(new MyRetailException(HttpStatus.NOT_FOUND.value(), "NotFound"));
		String url = "/products/15117729";
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE);
		MvcResult actual = mockMvc.perform(reqBuilder).andReturn();
		assertEquals("{\"statusCode\":404,\"errorMessage\":\"NotFound\"}", actual.getResponse().getContentAsString());
	}

	@Test
	public void updateProductPriceTest() throws Exception {
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setCurrencyCode("USD");
		currentPrice.setValue(134.99);
		Product product = new Product();
		product.setCurrentPrice(currentPrice);
		product.setId("13860428");
		product.setName("The Big Lebowski (Blu-ray) (Widescreen)");
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(product);
		doNothing().when(productService).updateProductById(any());
		String url = "/products/13860428";
		RequestBuilder reqBuilder = MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_STREAM_JSON_VALUE)
				.content(content);
		MvcResult actual = mockMvc.perform(reqBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), actual.getResponse().getStatus());

	}
	
	@Test
	public void updateProductPriceWithInValidIDTest() throws Exception {
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setCurrencyCode("USD");
		currentPrice.setValue(134.99);
		Product product = new Product();
		product.setCurrentPrice(currentPrice);
		product.setId("15117729");
		product.setName("The Big Lebowski (Blu-ray) (Widescreen)");
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(product);
		doNothing().when(productService).updateProductById(any());
		String url = "/products/13860428";
		RequestBuilder reqBuilder = MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_STREAM_JSON_VALUE)
				.content(content);
		MvcResult actual = mockMvc.perform(reqBuilder).andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getResponse().getStatus());

	}

}
