package com.ambow.orderfly.control;

import java.util.Scanner;

import com.ambow.orderfly.DataFactory.DataFactory;
import com.ambow.orderfly.dao.AirFlyDao;
import com.ambow.orderfly.dao.AirLineDao;
import com.ambow.orderfly.javaBean.AirLine;
import com.ambow.orderfly.view.ShowMenu;

public class AdminControl {
	public void managerMenu() {
		ShowMenu menu = new ShowMenu();
		Scanner scanner = new Scanner(System.in);
		menu.mangerMenu();
		int adminChose = scanner.nextInt();
		adminChoseControl(adminChose);
	}

	AirLineDao airLineDao = new AirLineDao();
	AirFlyDao airFlyDao = new AirFlyDao();

	public boolean adminLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名");
		String name = scanner.next();
		System.out.println("请输入密码");
		String pwd = scanner.next();
		if (name.equals(DataFactory.adminInfo.getLoginName())
				&& pwd.equals(DataFactory.adminInfo.getLoginPwd())) {
			return true;
		} else {
			return false;
		}

	}

	public void adminChoseControl(int chose) {
		Scanner scanner = new Scanner(System.in);
		switch (chose) {
		case 1:
			if (airFlyDao.addAirFly()) {
				System.out.println("添加成功");
				this.managerMenu();
			} else {
				System.out.println("添加失败");
				this.managerMenu();
			}
			break;
		case 2:
			System.out.println("请输入起飞地：");
			AirLine airLine = new AirLine();
			String flyFrom = scanner.next();
			airLine.setFlyFrom(flyFrom);
			System.out.println("请输入到达地：");
			String flyTo = scanner.next();
			airLine.setFlyTO(flyTo);
			if (airLineDao.addAirLine(airLine)) {
				System.out.println("添加成功!");
				this.managerMenu();
			} else {
				System.out.println("添加失败");
				this.managerMenu();
			}
			break;
		case 3:
			if(airFlyDao.updateAirFly()){
				System.out.println("航班修改成功");
				this.managerMenu();
			}else{
				System.out.println("航班修改失败");
				this.managerMenu();
			}
			break;
		case 4:
			System.out.println("1.通过编号修改");
			System.out.println("2.通过出发地和目的地修改");
			System.out.println("请选择修改方式：");
			int updateChose = scanner.nextInt();
			switch (updateChose) {
			case 1:
				if (airLineDao.updateAirLineById()) {
					System.out.println("修改成功");
					this.managerMenu();
				} else {
					System.out.println("修改失败");
					this.managerMenu();
				}
				break;
			case 2:
				if (airLineDao.updateAir()) {
					System.out.println("修改成功");
					this.managerMenu();
				} else {
					System.out.println("修改失败");
					this.managerMenu();
				}
				break;
			}
			break;
		case 5:
			System.out.println("1.通过选择删除");
			System.out.println("2.通过航班号删除");
			System.out.println("请选择删除方式");
			int airFlyDel=scanner.nextInt();
			if(airFlyDel==1){
				if(airFlyDao.delAirFlyById()){
					System.out.println("航班删除成功");
					this.managerMenu();
				}else{
					System.out.println("航班删除失败");
					this.managerMenu();
				}
			}else if(airFlyDel==2){
				   if(airFlyDao.delAirFlyByNo()){
						System.out.println("航班删除成功");
						this.managerMenu(); 
				   }else{
						System.out.println("航班删除失败");
						this.managerMenu();
				   }
			}
			break;
		case 6:
			System.out.println("1.通过编号删除");
			System.out.println("2.起飞和目的地删除");
			System.out.println("请选择删除方式：");
			int delChose = scanner.nextInt();
			if (delChose == 1) {
				airLineDao.findAllAirLine();
				System.out.println("请选择删除的编号：");
				int delNo = scanner.nextInt();
				if (airLineDao.delAirLineById(delNo)) {
					System.out.println("删除成功");
					this.managerMenu();
				} else {
					System.out.println("删除失败");
					this.managerMenu();
				}
			} else if (delChose == 2) {
				AirLine line = new AirLine();
				System.out.println("请输入要删除的起始地：");
				String airLineFrom = scanner.next();
				System.out.println("请输入要删除的目的地：");
				String airLineTo = scanner.next();
				line.setFlyFrom(airLineFrom);
				line.setFlyTO(airLineTo);
				if (airLineDao.delAirLine(line)) {
					System.out.println("删除成功");
					this.managerMenu();
				} else {
					System.out.println("删除失败");
					this.managerMenu();
				}

			} else {
				System.out.println("眼神好不好使？？");
			}
			break;
		case 7:
			airLineDao.findAllAirLine();
			this.managerMenu();
			break;
		case 8:
			airFlyDao.findAllFly();
			this.managerMenu();
			break;
		case 9:
			UserContorl contorl = new UserContorl();
			contorl.choseContor();
			break;
		}
	}
}
