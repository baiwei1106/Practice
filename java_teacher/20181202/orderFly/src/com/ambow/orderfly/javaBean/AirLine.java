package com.ambow.orderfly.javaBean;

public class AirLine {
	private String flyFrom;
	private String flyTO;
	public AirLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AirLine(String flyFrom, String flyTO) {
		super();
		this.flyFrom = flyFrom;
		this.flyTO = flyTO;
	}

	public String getFlyFrom() {
		return flyFrom;
	}

	public void setFlyFrom(String flyFrom) {
		this.flyFrom = flyFrom;
	}

	public String getFlyTO() {
		return flyTO;
	}

	public void setFlyTO(String flyTO) {
		this.flyTO = flyTO;
	}

}
