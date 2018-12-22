package com.ambow.orderfly.control;

import java.util.Scanner;

import com.ambow.orderfly.DataFactory.DataFactory;
import com.ambow.orderfly.javaBean.AirFly;
import com.ambow.orderfly.javaBean.PassengerInfo;
import com.ambow.orderfly.view.ShowMenu;

public class UserContorl {
	public void choseContor() {
		int[] flyPoints = new int[100];
		int flyPoint = 0;
		Scanner scanner = new Scanner(System.in);
		ShowMenu menu = new ShowMenu();
		menu.indexMenu();
		String chose = scanner.next();
		if (chose.equals("login")) {
			AdminControl adminControl = new AdminControl();
			if (adminControl.adminLogin()) {
				menu.mangerMenu();
				int adminChose = scanner.nextInt();
				adminControl.adminChoseControl(adminChose);
			} else {
				return;
			}
		} else {
			switch (chose) {
			case "1":
				System.out.println("�����������:");
				String flyFrom = scanner.next();
				System.out.println("������Ŀ�ĵ�");
				String flyTo = scanner.next();
				int point = -1;
				for (int i = 0; i < DataFactory.airLinePoint; i++) {
					if (DataFactory.airLines[i].getFlyFrom().equals(flyFrom)
							&& DataFactory.airLines[i].getFlyTO().equals(flyTo)) {
						point = i;
						break;
					}
				}
				if (point == -1) {
					System.out.println("û���ҵ�����ҵĺ���");
				} else {
					for (int i = 0; i < DataFactory.airFlyPoint; i++) {
						if (DataFactory.airLines[point].getFlyFrom().equals(
								DataFactory.airFlys[i].getAirLine()
										.getFlyFrom())
								&& DataFactory.airLines[point].getFlyTO()
										.equals(DataFactory.airFlys[i]
												.getAirLine().getFlyTO())) {
							flyPoints[flyPoint] = i;
							flyPoint++;
						}
					}
					System.out
							.println("���              �����              ��ɵ�             Ŀ�ĵ�               ���ʱ��               ����ʱ��              ʣ��Ʊ��");
					for (int i = 0; i < flyPoint; i++) {
						System.out
								.println((i + 1)
										+ "        "
										+ DataFactory.airFlys[flyPoints[i]]
												.getFlyNo()
										+ "        "
										+ flyFrom
										+ "        "
										+ flyTo
										+ "        "
										+ DataFactory.airFlys[flyPoints[i]]
												.getFlyTime()
										+ "        "
										+ DataFactory.airFlys[flyPoints[i]]
												.getArriveTime()
										+ "        "
										+ (DataFactory.airFlys[flyPoints[i]]
												.getPassengerNum() - DataFactory.airFlys[flyPoints[i]]
												.getTicketPoint()));
					}
					System.out.println("��ѡ��Ҫ��Ʊ����");
					int flyChose = scanner.nextInt();
					System.out.println("������Ҫ���������");
					int ticketNum = scanner.nextInt();
					if (ticketNum > DataFactory.airFlys[flyChose - 1]
							.getPassengerNum()
							- DataFactory.airFlys[flyChose - 1]
									.getTicketPoint()) {
                        System.out.println("��Ʊ����");
					}else{
						for (int i = 0; i < ticketNum; i++) {
							System.out.println("�������" + (i + 1) + "λ�˿���Ϣ");
							System.out.println("���������֤��");
							String passengerNo=scanner.next();
							System.out.println("����������");
							String passengerName=scanner.next();
							System.out.println("����������");
							int passengerAge=scanner.nextInt();
							System.out.println("1.��");
							System.out.println("2.Ů");
							System.out.println("��ѡ���Ա�");
							int sexChose=scanner.nextInt();
							boolean sex=true;
							if(sexChose==1){
								sex=true;
							}else if(sexChose==2){
								sex=false;
							}else{
								System.out.println("�������");
								return;
							}
							if(DataFactory.passengerPoint==0){
							PassengerInfo passengerInfo= new PassengerInfo();
							passengerInfo.setPassengerNo(passengerNo);
							passengerInfo.setPassengerName(passengerName);
							passengerInfo.setPassengerAge(passengerAge);
							passengerInfo.setPssengerSex(sex);
							AirFly[] airFlies=passengerInfo.getAirFly();
							airFlies[passengerInfo.getFlyPoint()]=DataFactory.airFlys[flyChose-1];
							passengerInfo.setAirFly(airFlies);
							passengerInfo.setFlyPoint(passengerInfo.getFlyPoint()+1);
							DataFactory.passengerInfos[DataFactory.passengerPoint]=passengerInfo;
							DataFactory.passengerPoint++;
						  }else{
							  int pasgPoint=-1;
							  for(int j=0;j<DataFactory.passengerPoint;j++){
								  if(passengerNo.equals(DataFactory.passengerInfos[j].getPassengerNo())){
									  pasgPoint=j;
								  }
							  }
							  if(pasgPoint==-1){
									PassengerInfo passengerInfo= new PassengerInfo();
									passengerInfo.setPassengerNo(passengerNo);
									passengerInfo.setPassengerName(passengerName);
									passengerInfo.setPassengerAge(passengerAge);
									passengerInfo.setPssengerSex(sex);
									AirFly[] airFlies=passengerInfo.getAirFly();
									airFlies[passengerInfo.getFlyPoint()]=DataFactory.airFlys[flyChose-1];
									passengerInfo.setAirFly(airFlies);
									passengerInfo.setFlyPoint(passengerInfo.getFlyPoint()+1);
									DataFactory.passengerInfos[DataFactory.passengerPoint]=passengerInfo;
									DataFactory.passengerPoint++;
								   
							  }else{
								  
							  }
						  }
						}
					}
					
				}
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				break;
			case "5":
				System.exit(0);
				break;
			default:
				break;
			}
		}

	}
}
