package com.ambow.orderfly.dao;

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
		DataFactory.airLines[DataFactory.airLinePoint] = airLine;
		DataFactory.airLinePoint++;
		return true;
	}

	public boolean delAirLineById(int point) {
		boolean flag = false;
		AirLine airLine = DataFactory.airLines[point];
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
}
