package com.ambow.orderfly.javaBean;

public class TicketInfo {
	private PassengerInfo passengerInfo;
	private double price;

	public TicketInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketInfo(PassengerInfo passengerInfo, double price) {
		super();
		this.passengerInfo = passengerInfo;
		this.price = price;
	}

	public PassengerInfo getPassengerInfo() {
		return passengerInfo;
	}

	public void setPassengerInfo(PassengerInfo passengerInfo) {
		this.passengerInfo = passengerInfo;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
