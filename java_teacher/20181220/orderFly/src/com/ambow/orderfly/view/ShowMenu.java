package com.ambow.orderfly.view;

import com.ambow.orderfly.DataFactory.DataFactory;

public class ShowMenu {
   public void indexMenu(){
	   System.out.println("*****��ӭʹ�÷ɻ���Ʊ*****");
	   System.out.println("**********1.��Ʊ*********");
	   System.out.println("**********2.��ѯ*********");
	   System.out.println("**********3.��Ʊ*********");
	   System.out.println("**********4.��ǩ*********");
	   System.out.println("**********5.�˳�*********");
	   System.out.println("��ѡ�������");
	   
   }
   public void mangerMenu(){
	   System.out.println("��ӭ"+DataFactory.adminInfo.getLoginName()+"��¼");
	   System.out.println("1.��Ӻ���");
	   System.out.println("2.��Ӻ���");
	   System.out.println("3.�޸ĺ���");
	   System.out.println("4.�޸ĺ���");
	   System.out.println("5.ɾ������");
	   System.out.println("6.ɾ������");
	   System.out.println("7.��ѯ����");
	   System.out.println("8.��ѯ����");
	   System.out.println("9.�˳���̨");
	   System.out.println("������ѡ��");
   }
}
