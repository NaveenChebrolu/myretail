package com.code.test.app.myretail.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
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

		try {
			CompletableFuture<String> productNameFuture = httpClient.getProductNameByRemoteHttpCall(productId);
			CompletableFuture<ProductEntity> productEntityFuture = getProductByIdAsync(productId);
			return helperObject.generateProductReponse(productEntityFuture.get(), productNameFuture.get());

		} catch (MyRetailException e) {
			throw e;
		} catch (InterruptedException | ExecutionException e) {
			throw new MyRetailException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"Server Threads are busy please try agian later");
		}

	}

	@Async
	public CompletableFuture<ProductEntity> getProductByIdAsync(final String id) throws MyRetailException {
		return CompletableFuture.completedFuture(getCachableProductById(id));

	}

	@Cacheable(value = "productPriceCache", key = "#id")
	public ProductEntity getCachableProductById(String id) throws MyRetailException {
		Optional<ProductEntity> productFromDb = Optional.empty();
		try {
			productFromDb = productRepoistory.findById(id);

			if (productFromDb.isEmpty()) {
				logger.debug("Product Not Found Exception in Db");
				throw new MyRetailException(HttpStatus.NOT_FOUND.value(), "Proudct Not Found in DB");
			}

		} catch (MyRetailException e) {
			throw e;

		}
		return productFromDb.get();
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
