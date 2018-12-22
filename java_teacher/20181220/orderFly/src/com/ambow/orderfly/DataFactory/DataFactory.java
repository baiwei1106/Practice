package com.ambow.orderfly.DataFactory;

import com.ambow.orderfly.javaBean.AdminInfo;
import com.ambow.orderfly.javaBean.AirFly;
import com.ambow.orderfly.javaBean.AirLine;
import com.ambow.orderfly.javaBean.PassengerInfo;

public class DataFactory {
   public static AdminInfo adminInfo= new AdminInfo();
   public static int airFlyPoint=0;//
   public static int airLinePoint=0;
   public static int  passengerPoint=0;
   public static AirFly[] airFlys= new AirFly[300];
   public static AirLine[] airLines=new AirLine[30];
   public static PassengerInfo[] passengerInfos =new PassengerInfo[2000];
}
