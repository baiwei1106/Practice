package com.ambow.orderfly.dao;

import java.util.Scanner;

import com.ambow.orderfly.DataFactory.DataFactory;
import com.ambow.orderfly.javaBean.AirLine;

public class AirLineDao {
	public void findAllAirLine() {
		System.out.println("航线编号                起始地                目的地");
		for (int i = 0; i < DataFactory.airLinePoint; i++) {
			System.out.println((i + 1) + "        "
					+ DataFactory.airLines[i].getFlyFrom() + "        "
					+ DataFactory.airLines[i].getFlyTO());
		}
	}

	public boolean addAirLine(AirLine airLine) {
		if (DataFactory.airLinePoint > 0) {
			for (int i = 0; i < DataFactory.airLinePoint; i++) {
				if (airLine.getFlyFrom().equals(
						DataFactory.airLines[i].getFlyFrom())
						&& airLine.getFlyTO().equals(
								DataFactory.airLines[i].getFlyTO())) {
                    System.out.println("航线已存在，别瞎折腾了");
                    return false;
				}
			}
			DataFactory.airLines[DataFactory.airLinePoint] = airLine;
			DataFactory.airLinePoint++;
			return true;

		} else {
			DataFactory.airLines[DataFactory.airLinePoint] = airLine;
			DataFactory.airLinePoint++;
			return true;
		}

	}

	public boolean delAirLineById(int point) {
		boolean flag = false;
		AirLine airLine = DataFactory.airLines[point-1];
		for (int i = 0; i < DataFactory.airFlyPoint; i++) {
			if (airLine.getFlyFrom().equals(
					DataFactory.airFlys[i].getAirLine().getFlyFrom())
					&& airLine.getFlyTO().equals(
							DataFactory.airFlys[i].getAirLine().getFlyTO())) {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println("该航线下有航班不能删除");
			return false;
		}
		if (point == DataFactory.airLinePoint) {
			DataFactory.airLinePoint--;
			return true;
		} else {
			for (int i = point - 1; i < DataFactory.airLinePoint; i++) {
				DataFactory.airLines[i] = DataFactory.airLines[i + 1];
			}
			DataFactory.airLinePoint = DataFactory.airLinePoint - 1;
			return true;
		}

	}

	public boolean delAirLine(AirLine airLine) {
		boolean flag = false;
		for (int i = 0; i < DataFactory.airFlyPoint; i++) {
			if (airLine.getFlyFrom().equals(
					DataFactory.airFlys[i].getAirLine().getFlyFrom())
					&& airLine.getFlyTO().equals(
							DataFactory.airFlys[i].getAirLine().getFlyTO())) {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println("该航线下有航班不能删除");
			return false;
		} else {
			int point = -1;
			for (int i = 0; i < DataFactory.airLinePoint; i++) {
				if (airLine.getFlyFrom().equals(
						DataFactory.airLines[i].getFlyFrom())
						&& airLine.getFlyTO().equals(
								DataFactory.airLines[i].getFlyTO())) {
					point = i;
					break;
				}

			}
			if (point < 0) {
				System.out.println("没有找到你输入的航线信息");
				return false;
			} else {
				if (point == DataFactory.airLinePoint - 1) {
					DataFactory.airLinePoint--;
					return true;
				} else {
					for (int i = point; i < DataFactory.airLinePoint; i++) {
						DataFactory.airLines[i] = DataFactory.airLines[i + 1];
					}
					DataFactory.airLinePoint = DataFactory.airLinePoint - 1;
					return true;
				}

			}
		}
	}

	public boolean updateAirLineById() {
		Scanner scanner = new Scanner(System.in);
		this.findAllAirLine();
		System.out.println("请选择要修改的航线：");
		int airLineId = scanner.nextInt();
		if (airLineId > DataFactory.airLinePoint || airLineId <= 0) {
			System.out.println("请正确输入航线编号");
			return false;
		} else {
			System.out.println("请输入修改后的起始地：");
			String flyFrom = scanner.next();
			System.out.println("请输入修改后的目的地：");
			String flyTo = scanner.next();
			AirLine airLine = new AirLine();
			airLine.setFlyFrom(flyFrom);
			airLine.setFlyTO(flyTo);
			for (int i = 0; i < DataFactory.airLinePoint; i++) {
				if (airLine.getFlyFrom().equals(
						DataFactory.airLines[i].getFlyFrom())
						&& airLine.getFlyTO().equals(
								DataFactory.airLines[i].getFlyTO())) {
                    System.out.println("航线已存在，别瞎折腾了");
                    return false;
				}
			}
			DataFactory.airLines[airLineId - 1] = airLine;
			return true;
		}

	}
	public boolean updateAir(){
		int point=-1;
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要修改的起始地：");
		String flyFrom = scanner.next();
		System.out.println("请输入要改的目的地：");
		String flyTo = scanner.next();
		AirLine airLine = new AirLine();
		airLine.setFlyFrom(flyFrom);
		airLine.setFlyTO(flyTo);
		for (int i = 0; i < DataFactory.airLinePoint; i++) {
			if (airLine.getFlyFrom().equals(
					DataFactory.airLines[i].getFlyFrom())
					&& airLine.getFlyTO().equals(
							DataFactory.airLines[i].getFlyTO())) {
                     point=i;
                 break;
			}
		}
		if(point<0){
			System.out.println("没有找到修改的值");
			return false;
		}else{
			System.out.println("请输入修改后的起始地：");
			String afterFlyFrom = scanner.next();
			System.out.println("请输入修改后的目的地：");
			String afterFlyTo = scanner.next();
			AirLine airLine2 = new AirLine();
			airLine2.setFlyFrom(afterFlyFrom);
			airLine2.setFlyTO(afterFlyTo);
			for (int i = 0; i < DataFactory.airLinePoint; i++) {
				if (airLine2.getFlyFrom().equals(
						DataFactory.airLines[i].getFlyFrom())
						&& airLine2.getFlyTO().equals(
								DataFactory.airLines[i].getFlyTO())) {
                    System.out.println("航线已存在，别瞎折腾了");
                    return false;
				}
			}
			DataFactory.airLines[point]=airLine2;
			return true;
		}
	}
}
