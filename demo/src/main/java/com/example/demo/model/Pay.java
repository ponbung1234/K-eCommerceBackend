package com.example.demo.model;

public class Pay {
	private String data_name;
	private String data_currency;
	private String data_amount;
	private String data_payment_method;
	public String getData_name() {
		return data_name;
	}
	public void setData_name(String data_name) {
		this.data_name = data_name;
	}
	public String getData_currency() {
		return data_currency;
	}
	public void setData_currency(String data_currency) {
		this.data_currency = data_currency;
	}
	public String getData_amount() {
		return data_amount;
	}
	public void setData_amount(String data_amount) {
		this.data_amount = data_amount;
	}
	public String getData_payment_method() {
		return data_payment_method;
	}
	public void setData_payment_method(String data_payment_method) {
		this.data_payment_method = data_payment_method;
	}
	@Override
	public String toString() {
		return "Pay [data_name=" + data_name + ", data_currency=" + data_currency + ", data_amount=" + data_amount
				+ ", data_payment_method=" + data_payment_method + "]";
	}
	
	
}
