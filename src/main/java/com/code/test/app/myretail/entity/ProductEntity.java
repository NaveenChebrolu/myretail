package com.code.test.app.myretail.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productPrice")
public class ProductEntity {

	@Id
	private String id;
	private CurrentPriceEntity currentPrice;

	public ProductEntity() {
		super();
	}

	public ProductEntity(final String id, final CurrentPriceEntity currentPrice) {
		super();
		this.id = id;
		this.currentPrice = currentPrice;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the currentPrice
	 */
	public final CurrentPriceEntity getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * @param currentPrice the currentPrice to set
	 */
	public final void setCurrentPrice(final CurrentPriceEntity currentPrice) {
		this.currentPrice = currentPrice;
	}

}
