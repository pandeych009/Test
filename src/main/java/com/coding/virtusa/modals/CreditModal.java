package com.coding.virtusa.modals;

public class CreditModal {
	
	private String product;
	private int value;
	
	
	public CreditModal(String product, int value) {
		super();
		this.product = product;
		this.value = value;
	}
	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CreditModal [product=" + product + ", value=" + value + "]";
	}
}
