package com.code.test.app.myretail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

	@JsonProperty(value = "id")
	private String id;
	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value = "current_price")
	private CurrentPrice currentPrice;

	public Product() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public CurrentPrice getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(final CurrentPrice currentPrice) {
		this.currentPrice = currentPrice;
	}

}
