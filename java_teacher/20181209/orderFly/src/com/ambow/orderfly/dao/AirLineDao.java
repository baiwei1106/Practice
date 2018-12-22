package com.ambow.orderfly.dao;

import java.util.Scanner;

import com.ambow.orderfly.DataFactory.DataFactory;
import com.ambow.orderfly.javaBean.AirLine;

public class AirLineDao {
	public void findAllAirLine() {
		System.out.println("���߱��                ��ʼ��                Ŀ�ĵ�");
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
                    System.out.println("�����Ѵ��ڣ���Ϲ������");
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
			System.out.println("�ú������к��಻��ɾ��");
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
			System.out.println("�ú������к��಻��ɾ��");
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
				System.out.println("û���ҵ�������ĺ�����Ϣ");
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
		System.out.println("��ѡ��Ҫ�޸ĵĺ��ߣ�");
		int airLineId = scanner.nextInt();
		if (airLineId > DataFactory.airLinePoint || airLineId <= 0) {
			System.out.println("����ȷ���뺽�߱��");
			return false;
		} else {
			System.out.println("�������޸ĺ����ʼ�أ�");
			String flyFrom = scanner.next();
			System.out.println("�������޸ĺ��Ŀ�ĵأ�");
			String flyTo = scanner.next();
			AirLine airLine = new AirLine();
			airLine.setFlyFrom(flyFrom);
			airLine.setFlyTO(flyTo);
			for (int i = 0; i < DataFactory.airLinePoint; i++) {
				if (airLine.getFlyFrom().equals(
						DataFactory.airLines[i].getFlyFrom())
						&& airLine.getFlyTO().equals(
								DataFactory.airLines[i].getFlyTO())) {
                    System.out.println("�����Ѵ��ڣ���Ϲ������");
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
		System.out.println("������Ҫ�޸ĵ���ʼ�أ�");
		String flyFrom = scanner.next();
		System.out.println("������Ҫ�ĵ�Ŀ�ĵأ�");
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
			System.out.println("û���ҵ��޸ĵ�ֵ");
			return false;
		}else{
			System.out.println("�������޸ĺ����ʼ�أ�");
			String afterFlyFrom = scanner.next();
			System.out.println("�������޸ĺ��Ŀ�ĵأ�");
			String afterFlyTo = scanner.next();
			AirLine airLine2 = new AirLine();
			airLine2.setFlyFrom(afterFlyFrom);
			airLine2.setFlyTO(afterFlyTo);
			for (int i = 0; i < DataFactory.airLinePoint; i++) {
				if (airLine2.getFlyFrom().equals(
						DataFactory.airLines[i].getFlyFrom())
						&& airLine2.getFlyTO().equals(
								DataFactory.airLines[i].getFlyTO())) {
                    System.out.println("�����Ѵ��ڣ���Ϲ������");
                    return false;
				}
			}
			DataFactory.airLines[point]=airLine2;
			return true;
		}
	}
}
