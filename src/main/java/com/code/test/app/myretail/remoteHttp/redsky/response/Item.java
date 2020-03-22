package com.code.test.app.myretail.remoteHttp.redsky.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	@JsonProperty(value = "product_description")
	private ProductDescription productDescription;

	/**
	 * @param productDescription
	 */
	public Item(ProductDescription productDescription) {
		super();
		this.productDescription = productDescription;
	}

	/**
	 * 
	 */
	public Item() {
		super();
	}

	/**
	 * @return the productDescription
	 */
	public final ProductDescription getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription the productDescription to set
	 */
	public final void setProductDescription(ProductDescription productDescription) {
		this.productDescription = productDescription;
	}

}
