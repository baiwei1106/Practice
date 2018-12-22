package com.ambow.orderfly.view;

import com.ambow.orderfly.DataFactory.DataFactory;

public class ShowMenu {
   public void indexMenu(){
	   System.out.println("*****欢迎使用飞机订票*****");
	   System.out.println("**********1.订票*********");
	   System.out.println("**********2.查询*********");
	   System.out.println("**********3.退票*********");
	   System.out.println("**********4.改签*********");
	   System.out.println("**********5.退出*********");
	   System.out.println("请选择操作：");
	   
   }
   public void mangerMenu(){
	   System.out.println("欢迎"+DataFactory.adminInfo.getLoginName()+"登录");
	   System.out.println("1.添加航班");
	   System.out.println("2.添加航线");
	   System.out.println("3.修改航班");
	   System.out.println("4.修改航线");
	   System.out.println("5.删除航班");
	   System.out.println("6.删除航线");
	   System.out.println("7.查询航线");
	   System.out.println("8.查询航班");
	   System.out.println("9.退出后台");
	   System.out.println("请输入选择：");
   }
}
