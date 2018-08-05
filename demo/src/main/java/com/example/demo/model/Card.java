package com.example.demo.model;

public class Card {
	private String name;
	private String number;
	private String expmonth;
	private String expyear;
	private String cvv;
	
	
	public Card(String name, String number, String expmonth, String expyear, String cvv) {
		super();
		this.name = name;
		this.number = number;
		this.expmonth = expmonth;
		this.expyear = expyear;
		this.cvv = cvv;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getExpmonth() {
		return expmonth;
	}
	public void setExpmonth(String expmonth) {
		this.expmonth = expmonth;
	}
	public String getExpyear() {
		return expyear;
	}
	public void setExpyear(String expyear) {
		this.expyear = expyear;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	
}
