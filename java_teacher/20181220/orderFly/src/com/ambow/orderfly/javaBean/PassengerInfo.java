package com.ambow.orderfly.javaBean;

public class PassengerInfo {
	private String passengerNo;
	private String passengerName;
	private boolean pssengerSex;
	private int passengerAge;
	private int flyPoint = 0;
	private AirFly[] airFly = new AirFly[10];

	public PassengerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PassengerInfo(String passengerNo, String passengerName,
			boolean pssengerSex, int passengerAge, int flyPoint, AirFly[] airFly) {
		super();
		this.passengerNo = passengerNo;
		this.passengerName = passengerName;
		this.pssengerSex = pssengerSex;
		this.passengerAge = passengerAge;
		this.flyPoint = flyPoint;
		this.airFly = airFly;
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

	public int getFlyPoint() {
		return flyPoint;
	}

	public void setFlyPoint(int flyPoint) {
		this.flyPoint = flyPoint;
	}

	public AirFly[] getAirFly() {
		return airFly;
	}

	public void setAirFly(AirFly[] airFly) {
		this.airFly = airFly;
	}

}
