package com.code.test.app.myretail.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.code.test.app.myretail.dto.Product;
import com.code.test.app.myretail.entity.ProductEntity;
import com.code.test.app.myretail.exception.MyRetailException;
import com.code.test.app.myretail.helper.ProductHeapler;
import com.code.test.app.myretail.remoteHttp.RemoteHttpClient;
import com.code.test.app.myretail.repository.ProductRepository;

@Service
public class ProductService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRepository productRepoistory;

	@Autowired
	private RemoteHttpClient httpClient;

	@Autowired
	private ProductHeapler helperObject;

	public Product getProductById(final String productId) throws MyRetailException {
		logger.info("Inside ProuctService getProductById()" + productId);

		Optional<ProductEntity> productFromDb = Optional.empty();
		String productName = null;
		try {
			productFromDb = productRepoistory.findById(productId);

			if (productFromDb.isEmpty()) {
				logger.debug("Product Not Found Exception in Db");
				throw new MyRetailException(HttpStatus.NOT_FOUND.value(), "Proudct Not Found in DB");
			}

			productName = httpClient.getProductNameByRemoteHttpCall(productId);
		} catch (MyRetailException e) {
			throw e;

		}
		return helperObject.generateProductReponse(productFromDb.get(), productName);
	}

	public void updateProductById(Product product) throws MyRetailException {
		logger.info("Inside the Update ProductById ");

		try {

			ProductEntity productEntity = helperObject.getProductEntityObject(product);
			productRepoistory.save(productEntity);
		} catch (Exception ex) {
			logger.debug("product NotFound Exception while doing update :" + ex);
			throw new MyRetailException(HttpStatus.NOT_FOUND.value(), "product Not found for update");

		}
	}

}
