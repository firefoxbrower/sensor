package com.sensor.infoPro;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nutz.ioc.impl.PropertiesProxy;




public class InfoPro {

	//数据库配置信息
/*	public static String DBServerIP = null;
	public static String DBServerPORT = null;
	public static String DBUserName = null;
	public static String DBPasswrd = null;
	public static String DBSID = null;*/
	//土壤湿度服务端信息
	public static String ServerUrl = null;
	public static String SensorServer = null; //土壤传感器编号&密码
	
	//采集起始时间
	public static String CollectStartTime = null;
	
	public static String httpHeader_name = null ;
	public static String httpHeader_values[] ; 
	public static int count ;
		
		//读取配置文件信息

	//读取配置文件信息
	public static void getPropInfo() {
		
		// true 就是utf-8 编码格式
					PropertiesProxy prop= new PropertiesProxy(true);
					prop.setPaths("/info.properties");
	/*				InfoPro.DBServerIP = prop.get("dbserver_ip").trim();
					InfoPro.DBUserName = prop.get("db_username").trim();
					InfoPro.DBPasswrd = prop.get("db_password").trim();
					InfoPro.DBServerPORT = prop.get("dbserver_port").trim();
					InfoPro.DBSID = prop.get("db_sid").trim();*/
					
					// 请求头
					InfoPro.httpHeader_name=prop.get("httpHeader_name").trim();
					
					int count = new Integer(prop.get("httpHeader_count").trim()) ;
					InfoPro.count=count;
					InfoPro.httpHeader_values = new String[count] ;
					for(int i = 0 ; i < count ; i ++){
						InfoPro.httpHeader_values[i]= prop.get("httpHeader_values"+i);
					}
					//System.out.println(Arrays.toString(httpHeader_values));
					
					// 域名
					InfoPro.ServerUrl = prop.get("ServerUrl").trim();
					InfoPro.CollectStartTime = prop.get("collect_StartData").trim();
		}
}
