package com.code.test.app.myretail.remoteHttp.redsky.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDescription {
	
	@JsonProperty(value = "title")
	private String title;

	/**
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public final void setTitle(String title) {
		this.title = title;
	}

}
