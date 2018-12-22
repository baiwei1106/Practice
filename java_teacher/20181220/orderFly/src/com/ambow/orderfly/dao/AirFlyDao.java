package com.ambow.orderfly.dao;

import java.util.Scanner;

import com.ambow.orderfly.DataFactory.DataFactory;
import com.ambow.orderfly.javaBean.AirFly;
import com.ambow.orderfly.javaBean.AirLine;

public class AirFlyDao {
	AirLineDao airLineDao = new AirLineDao();

	public void findAllFly() {
		System.out
				.println("航班编号                    航班号                    起飞时间                    到达时间                    荷载人数                    起飞地                    目的地                    剩余票数");
		AirFly[] airFlys = DataFactory.airFlys;
		for (int i = 0; i < DataFactory.airFlyPoint; i++) {
			System.out.println((i + 1)
					+ "           "
					+ airFlys[i].getFlyNo()
					+ "       "
					+ airFlys[i].getFlyTime()
					+ "       "
					+ airFlys[i].getArriveTime()
					+ "       "
					+ airFlys[i].getPassengerNum()
					+ "       "
					+ airFlys[i].getAirLine().getFlyFrom()
					+ "       "
					+ airFlys[i].getAirLine().getFlyTO()
					+ "       "
					+ (airFlys[i].getPassengerNum() - airFlys[i]
							.getTicketPoint()));
		}
	}

	public boolean addAirFly() {
		Scanner scanner = new Scanner(System.in);
		if (DataFactory.airLinePoint <= 0) {
			System.out.println("目前没有航线，请先添加航线");
			return false;
		} else {
			airLineDao.findAllAirLine();
			System.out.println("请选择要添加航班的航线：");
			int chose = scanner.nextInt();
			AirLine airLine = DataFactory.airLines[chose - 1];// 这个航线就是用户选择的航线
			System.out.println("请输入航班号：");
			String flyNo = scanner.next();
			for (int i = 0; i < DataFactory.airFlyPoint; i++) {
				if (flyNo.equals(DataFactory.airFlys[i].getFlyNo())) {
					System.out.println("不能有相同航班号");
					return false;
				}
			}
			System.out.println("请输入起飞时间");
			String flyTime = scanner.next();
			System.out.println("请输入到达时间");
			String arriveTime = scanner.next();
			System.out.println("请输入荷载人数");
			int passengerNum = scanner.nextInt();
			AirFly airFly = new AirFly();
			airFly.setAirLine(airLine);
			airFly.setArriveTime(arriveTime);
			airFly.setFlyNo(flyNo);
			airFly.setFlyTime(flyTime);
			airFly.setPassengerNum(passengerNum);
			DataFactory.airFlys[DataFactory.airFlyPoint] = airFly;
			DataFactory.airFlyPoint++;
			return true;
		}

	}

	public boolean delAirFlyById() {
		Scanner scanner = new Scanner(System.in);
		if (DataFactory.airFlyPoint <= 0) {
			System.out.println("连航班都没有你删啥？");
			return false;
		} else {
			this.findAllFly();
			System.out.println("请输入要删除的航班");
			int chose = scanner.nextInt();
			AirFly airFly = DataFactory.airFlys[chose - 1];
			if (airFly.getTicketPoint() > 0) {
				System.out.println("此航班中有乘客，不能删除");
				return false;
			} else {
				if (chose == DataFactory.airFlyPoint) {
					DataFactory.airFlyPoint--;
					return true;
				}
				for (int i = chose - 1; i < DataFactory.airFlyPoint; i++) {
					DataFactory.airFlys[i] = DataFactory.airFlys[i + 1];
				}
				DataFactory.airFlyPoint--;
				return true;
			}
		}
	}

	public boolean delAirFlyByNo() {
		Scanner scanner = new Scanner(System.in);
		if (DataFactory.airFlyPoint <= 0) {
			System.out.println("连航班都没有你删啥？");
			return false;
		} else {
			int point = -1;
			System.out.println("请输入要删除的航班号");
			String flyNo = scanner.next();
			for (int i = 0; i < DataFactory.airFlyPoint; i++) {
				if (flyNo.equals(DataFactory.airFlys[i].getFlyNo())) {
					point = i;
					break;
				}
			}
			if (point < 0) {
				System.out.println("没有找到");
				return false;
			} else {
				if (point == DataFactory.airFlyPoint - 1) {
					DataFactory.airFlyPoint--;
					return true;
				}
				for (int i = point; i < DataFactory.airFlyPoint; i++) {
					DataFactory.airFlys[i] = DataFactory.airFlys[i + 1];
				}
				DataFactory.airFlyPoint--;
				return true;
			}
		}

	}

	public boolean updateAirFly() {
		Scanner scanner = new Scanner(System.in);
		this.findAllFly();
		System.out.println("请输入要修改的编号：");
		int chose = scanner.nextInt();
		AirFly airFly = DataFactory.airFlys[chose - 1];
		airLineDao.findAllAirLine();
		System.out.println("请选择要修改航班的航线：");
		int line = scanner.nextInt();
		AirLine airLine = DataFactory.airLines[line - 1];// 这个航线就是用户选择的航线
		System.out.println("请输入航班号：");
		String flyNo = scanner.next();
		for (int i = 0; i < DataFactory.airFlyPoint; i++) {
			if (flyNo.equals(DataFactory.airFlys[i].getFlyNo())
					&& !airFly.getFlyNo().equals(flyNo)) {
				System.out.println("不能有相同航班号");
				return false;
			}
		}
		System.out.println("请输入修改起飞时间");
		String flyTime = scanner.next();
		System.out.println("请输入修改到达时间");
		String arriveTime = scanner.next();
		System.out.println("请输入修改荷载人数");
		int passengerNum = scanner.nextInt();
		airFly.setAirLine(airLine);
		airFly.setArriveTime(arriveTime);
		airFly.setFlyNo(flyNo);
		airFly.setFlyTime(flyTime);
		airFly.setPassengerNum(passengerNum);
		return true;
	}
}
