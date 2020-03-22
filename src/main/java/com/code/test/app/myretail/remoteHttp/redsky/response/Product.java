package com.code.test.app.myretail.remoteHttp.redsky.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

	@JsonProperty(value = "item")
	private Item item;

	/**
	 * @return the item
	 */
	public final Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public final void setItem(Item item) {
		this.item = item;
	}

}
