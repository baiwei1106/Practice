package com.ambow.orderfly.control;

import java.util.Scanner;

import com.ambow.orderfly.view.ShowMenu;

public class UserContorl {
	public void choseContor() {
		Scanner scanner = new Scanner(System.in);
		ShowMenu menu = new ShowMenu();
		menu.indexMenu();
		String chose = scanner.next();
		if (chose.equals("login")) {
			AdminControl adminControl = new AdminControl();
			if (adminControl.adminLogin()) {
				menu.mangerMenu();
				int adminChose=scanner.nextInt();
				adminControl.adminChoseControl(adminChose);	
			} else {
				return;
			}
		} else {
			switch (chose) {
			case "1":

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
