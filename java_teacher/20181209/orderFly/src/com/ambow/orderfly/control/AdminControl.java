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
		System.out.println("�������û���");
		String name = scanner.next();
		System.out.println("����������");
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
				System.out.println("��ӳɹ�");
				this.managerMenu();
			} else {
				System.out.println("���ʧ��");
				this.managerMenu();
			}
			break;
		case 2:
			System.out.println("��������ɵأ�");
			AirLine airLine = new AirLine();
			String flyFrom = scanner.next();
			airLine.setFlyFrom(flyFrom);
			System.out.println("�����뵽��أ�");
			String flyTo = scanner.next();
			airLine.setFlyTO(flyTo);
			if (airLineDao.addAirLine(airLine)) {
				System.out.println("��ӳɹ�!");
				this.managerMenu();
			} else {
				System.out.println("���ʧ��");
				this.managerMenu();
			}
			break;
		case 3:
			if(airFlyDao.updateAirFly()){
				System.out.println("�����޸ĳɹ�");
				this.managerMenu();
			}else{
				System.out.println("�����޸�ʧ��");
				this.managerMenu();
			}
			break;
		case 4:
			System.out.println("1.ͨ������޸�");
			System.out.println("2.ͨ�������غ�Ŀ�ĵ��޸�");
			System.out.println("��ѡ���޸ķ�ʽ��");
			int updateChose = scanner.nextInt();
			switch (updateChose) {
			case 1:
				if (airLineDao.updateAirLineById()) {
					System.out.println("�޸ĳɹ�");
					this.managerMenu();
				} else {
					System.out.println("�޸�ʧ��");
					this.managerMenu();
				}
				break;
			case 2:
				if (airLineDao.updateAir()) {
					System.out.println("�޸ĳɹ�");
					this.managerMenu();
				} else {
					System.out.println("�޸�ʧ��");
					this.managerMenu();
				}
				break;
			}
			break;
		case 5:
			System.out.println("1.ͨ��ѡ��ɾ��");
			System.out.println("2.ͨ�������ɾ��");
			System.out.println("��ѡ��ɾ����ʽ");
			int airFlyDel=scanner.nextInt();
			if(airFlyDel==1){
				if(airFlyDao.delAirFlyById()){
					System.out.println("����ɾ���ɹ�");
					this.managerMenu();
				}else{
					System.out.println("����ɾ��ʧ��");
					this.managerMenu();
				}
			}else if(airFlyDel==2){
				   if(airFlyDao.delAirFlyByNo()){
						System.out.println("����ɾ���ɹ�");
						this.managerMenu(); 
				   }else{
						System.out.println("����ɾ��ʧ��");
						this.managerMenu();
				   }
			}
			break;
		case 6:
			System.out.println("1.ͨ�����ɾ��");
			System.out.println("2.��ɺ�Ŀ�ĵ�ɾ��");
			System.out.println("��ѡ��ɾ����ʽ��");
			int delChose = scanner.nextInt();
			if (delChose == 1) {
				airLineDao.findAllAirLine();
				System.out.println("��ѡ��ɾ���ı�ţ�");
				int delNo = scanner.nextInt();
				if (airLineDao.delAirLineById(delNo)) {
					System.out.println("ɾ���ɹ�");
					this.managerMenu();
				} else {
					System.out.println("ɾ��ʧ��");
					this.managerMenu();
				}
			} else if (delChose == 2) {
				AirLine line = new AirLine();
				System.out.println("������Ҫɾ������ʼ�أ�");
				String airLineFrom = scanner.next();
				System.out.println("������Ҫɾ����Ŀ�ĵأ�");
				String airLineTo = scanner.next();
				line.setFlyFrom(airLineFrom);
				line.setFlyTO(airLineTo);
				if (airLineDao.delAirLine(line)) {
					System.out.println("ɾ���ɹ�");
					this.managerMenu();
				} else {
					System.out.println("ɾ��ʧ��");
					this.managerMenu();
				}

			} else {
				System.out.println("����ò���ʹ����");
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
