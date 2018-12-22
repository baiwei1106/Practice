package com.ambow.orderfly.dao;

import java.util.Scanner;

import com.ambow.orderfly.DataFactory.DataFactory;
import com.ambow.orderfly.javaBean.AirFly;
import com.ambow.orderfly.javaBean.AirLine;

public class AirFlyDao {
	AirLineDao airLineDao = new AirLineDao();

	public void findAllFly() {
		System.out
				.println("������                    �����                    ���ʱ��                    ����ʱ��                    ��������                    ��ɵ�                    Ŀ�ĵ�                    ʣ��Ʊ��");
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
			System.out.println("Ŀǰû�к��ߣ�������Ӻ���");
			return false;
		} else {
			airLineDao.findAllAirLine();
			System.out.println("��ѡ��Ҫ��Ӻ���ĺ��ߣ�");
			int chose = scanner.nextInt();
			AirLine airLine = DataFactory.airLines[chose - 1];// ������߾����û�ѡ��ĺ���
			System.out.println("�����뺽��ţ�");
			String flyNo = scanner.next();
			for (int i = 0; i < DataFactory.airFlyPoint; i++) {
				if (flyNo.equals(DataFactory.airFlys[i].getFlyNo())) {
					System.out.println("��������ͬ�����");
					return false;
				}
			}
			System.out.println("���������ʱ��");
			String flyTime = scanner.next();
			System.out.println("�����뵽��ʱ��");
			String arriveTime = scanner.next();
			System.out.println("�������������");
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
			System.out.println("�����඼û����ɾɶ��");
			return false;
		} else {
			this.findAllFly();
			System.out.println("������Ҫɾ���ĺ���");
			int chose = scanner.nextInt();
			AirFly airFly = DataFactory.airFlys[chose - 1];
			if (airFly.getTicketPoint() > 0) {
				System.out.println("�˺������г˿ͣ�����ɾ��");
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
			System.out.println("�����඼û����ɾɶ��");
			return false;
		} else {
			int point = -1;
			System.out.println("������Ҫɾ���ĺ����");
			String flyNo = scanner.next();
			for (int i = 0; i < DataFactory.airFlyPoint; i++) {
				if (flyNo.equals(DataFactory.airFlys[i].getFlyNo())) {
					point = i;
					break;
				}
			}
			if (point < 0) {
				System.out.println("û���ҵ�");
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
		System.out.println("������Ҫ�޸ĵı�ţ�");
		int chose = scanner.nextInt();
		AirFly airFly = DataFactory.airFlys[chose - 1];
		airLineDao.findAllAirLine();
		System.out.println("��ѡ��Ҫ�޸ĺ���ĺ��ߣ�");
		int line = scanner.nextInt();
		AirLine airLine = DataFactory.airLines[line - 1];// ������߾����û�ѡ��ĺ���
		System.out.println("�����뺽��ţ�");
		String flyNo = scanner.next();
		for (int i = 0; i < DataFactory.airFlyPoint; i++) {
			if (flyNo.equals(DataFactory.airFlys[i].getFlyNo())
					&& !airFly.getFlyNo().equals(flyNo)) {
				System.out.println("��������ͬ�����");
				return false;
			}
		}
		System.out.println("�������޸����ʱ��");
		String flyTime = scanner.next();
		System.out.println("�������޸ĵ���ʱ��");
		String arriveTime = scanner.next();
		System.out.println("�������޸ĺ�������");
		int passengerNum = scanner.nextInt();
		airFly.setAirLine(airLine);
		airFly.setArriveTime(arriveTime);
		airFly.setFlyNo(flyNo);
		airFly.setFlyTime(flyTime);
		airFly.setPassengerNum(passengerNum);
		return true;
	}
}
