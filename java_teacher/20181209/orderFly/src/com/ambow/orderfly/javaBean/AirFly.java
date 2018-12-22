package com.ambow.orderfly.javaBean;

public class AirFly {
	private String flyNo;
	private String flyTime;
	private String arriveTime;
	private int passengerNum;
	private AirLine airLine;
	private int ticketPoint = 0;
	private TicketInfo[] ticketInfo = new TicketInfo[passengerNum];

	public AirFly() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AirFly(String flyNo, String flyTime, String arriveTime,
			int passengerNum, AirLine airLine, int ticketPoint,
			TicketInfo[] ticketInfo) {
		super();
		this.flyNo = flyNo;
		this.flyTime = flyTime;
		this.arriveTime = arriveTime;
		this.passengerNum = passengerNum;
		this.airLine = airLine;
		this.ticketPoint = ticketPoint;
		this.ticketInfo = ticketInfo;
	}

	public String getFlyNo() {
		return flyNo;
	}

	public void setFlyNo(String flyNo) {
		this.flyNo = flyNo;
	}

	public String getFlyTime() {
		return flyTime;
	}

	public void setFlyTime(String flyTime) {
		this.flyTime = flyTime;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public int getPassengerNum() {
		return passengerNum;
	}

	public void setPassengerNum(int passengerNum) {
		this.passengerNum = passengerNum;
	}

	public AirLine getAirLine() {
		return airLine;
	}

	public void setAirLine(AirLine airLine) {
		this.airLine = airLine;
	}

	public int getTicketPoint() {
		return ticketPoint;
	}

	public void setTicketPoint(int ticketPoint) {
		this.ticketPoint = ticketPoint;
	}

	public TicketInfo[] getTicketInfo() {
		return ticketInfo;
	}

	public void setTicketInfo(TicketInfo[] ticketInfo) {
		this.ticketInfo = ticketInfo;
	}

}
