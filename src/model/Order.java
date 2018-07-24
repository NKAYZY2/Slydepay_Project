package model;

import java.util.ArrayList;

public class Order {

	private String item;
	private int quantity;
	private final int cost = 10;
	private String merchantEmail;
	private String merchantKey;
	private String orderName;
	private static ArrayList<Object> payoptions = new ArrayList<Object>();
	private static ArrayList<Object> payoptionsNames = new ArrayList<Object>();

	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCost() {

	return cost;

	}

	

	public String getItems() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public ArrayList<Object> getPayoptions() {
		return payoptions;
	}

	public void setPayoptions(ArrayList<Object> payoptions) {
		Order.payoptions = payoptions;
	}

	public ArrayList<Object> getPayoptionsNames() {
		return payoptionsNames;
	}

	public void setPayoptionsNames(ArrayList<Object> payoptionsNames) {
		Order.payoptionsNames = payoptionsNames;
	}

}
