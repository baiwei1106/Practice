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
				System.out.println("请输入出发地:");
				String flyFrom = scanner.next();
				System.out.println("请输入目的地");
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
					System.out.println("没有找到你查找的航线");
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
							.println("序号              航班号              起飞地             目的地               起飞时间               到达时间              剩余票数");
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
					System.out.println("请选择要订票航班");
					int flyChose = scanner.nextInt();
					System.out.println("请输入要购买的张数");
					int ticketNum = scanner.nextInt();
					if (ticketNum > DataFactory.airFlys[flyChose - 1]
							.getPassengerNum()
							- DataFactory.airFlys[flyChose - 1]
									.getTicketPoint()) {
                        System.out.println("余票不足");
					}else{
						for (int i = 0; i < ticketNum; i++) {
							System.out.println("请输入第" + (i + 1) + "位乘客信息");
							System.out.println("请输入身份证号");
							String passengerNo=scanner.next();
							System.out.println("请输入姓名");
							String passengerName=scanner.next();
							System.out.println("请输入年龄");
							int passengerAge=scanner.nextInt();
							System.out.println("1.男");
							System.out.println("2.女");
							System.out.println("请选择性别");
							int sexChose=scanner.nextInt();
							boolean sex=true;
							if(sexChose==1){
								sex=true;
							}else if(sexChose==2){
								sex=false;
							}else{
								System.out.println("输入错误");
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
