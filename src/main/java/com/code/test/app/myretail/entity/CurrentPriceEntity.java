package com.code.test.app.myretail.entity;

public class CurrentPriceEntity {

	private Double value;
	private String currencyCode;

	public CurrentPriceEntity() {
		super();
	}

	/**
	 * @param value
	 * @param currencyCode
	 */
	public CurrentPriceEntity(Double value, String currencyCode) {
		super();
		this.value = value;
		this.currencyCode = currencyCode;
	}

	/**
	 * @return the value
	 */
	public final Double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public final void setValue(final Double value) {
		this.value = value;
	}

	/**
	 * @return the currencyCode
	 */
	public final String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode the currencyCode to set
	 */
	public final void setCurrencyCode(final String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
