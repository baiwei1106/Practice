package com.ambow.orderfly.javaBean;

public class PassengerInfo {
	private String passengerNo;
	private String passengerName;
	private boolean pssengerSex;
	private int passengerAge;

	public PassengerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PassengerInfo(String passengerNo, String passengerName,
			boolean pssengerSex, int passengerAge) {
		super();
		this.passengerNo = passengerNo;
		this.passengerName = passengerName;
		this.pssengerSex = pssengerSex;
		this.passengerAge = passengerAge;
	}

	public String getPassengerNo() {
		return passengerNo;
	}

	public void setPassengerNo(String passengerNo) {
		this.passengerNo = passengerNo;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public boolean isPssengerSex() {
		return pssengerSex;
	}

	public void setPssengerSex(boolean pssengerSex) {
		this.pssengerSex = pssengerSex;
	}

	public int getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}

}
