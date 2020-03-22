package com.code.test.app.myretail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.code.test.app.myretail.dto.MyRetailResponse;
import com.code.test.app.myretail.dto.Product;
import com.code.test.app.myretail.exception.MyRetailException;
import com.code.test.app.myretail.service.ProductService;

/**
 * 
 * @author naveen
 *
 */

@RequestMapping("/products")
@RestController
public class ProductController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable("id") String productId) throws MyRetailException {
		logger.info("Inside getProductById: " + productId);
		Product productResponse = new Product();
		productResponse = productService.getProductById(productId);
		logger.debug("Response: " + productResponse);
		return new ResponseEntity<Product>(productResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public ResponseEntity<MyRetailResponse> updatePrice(@RequestBody Product product, @PathVariable("id") String id)
			throws MyRetailException {
		logger.info("Inside updatePrice ");
		if (!product.getId().equalsIgnoreCase(id)) {
			throw new MyRetailException(HttpStatus.BAD_REQUEST.value(),
					"Product Id in Url and Request body Product Id didn't match");
		}
		productService.updateProductById(product);
		return new ResponseEntity<MyRetailResponse>(HttpStatus.OK);
	}

}
