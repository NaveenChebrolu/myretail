package com.code.test.app.myretail.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentPrice {

	@JsonProperty(value = "value")
	private Double value;
	@JsonProperty(value = "currency_code")
	private String currencyCode;

	public CurrentPrice() {
		super();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(final Double value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(final String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "CurrentPrice [value=" + value + ", currencyCode=" + currencyCode + "]";
	}

}
