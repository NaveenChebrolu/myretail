package com.code.test.app.myretail.helper;

import org.springframework.stereotype.Component;

import com.code.test.app.myretail.dto.CurrentPrice;
import com.code.test.app.myretail.dto.Product;
import com.code.test.app.myretail.entity.CurrentPriceEntity;
import com.code.test.app.myretail.entity.ProductEntity;

@Component
public class ProductHeapler {

	public Product generateProductReponse(ProductEntity productEntity, String productName) {
		Product product = new Product();
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setCurrencyCode(productEntity.getCurrentPrice().getCurrencyCode());
		currentPrice.setValue(productEntity.getCurrentPrice().getValue());
		product.setId(productEntity.getId());
		product.setCurrentPrice(currentPrice);
		product.setName(productName);
		return product;
	}

	public ProductEntity getProductEntityObject(Product product) {
		CurrentPriceEntity currentPriceEntity = new CurrentPriceEntity(product.getCurrentPrice().getValue(),
				product.getCurrentPrice().getCurrencyCode());
		return new ProductEntity(product.getId(), currentPriceEntity);
	}

}
