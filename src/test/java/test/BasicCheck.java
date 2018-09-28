package test;

import java.awt.List;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.json.Json;

import com.sensor.bean.Sensor_Basic;
import com.sensor.infoPro.InfoPro;
import com.sensor.utils.HttpUtils;
import com.sensor.utils.JSONUtils;

/** 自动更新监测点数据表*/
public class BasicCheck {

/*	public String getUrl(String ip){
		 GlobalInfo.getPropInfo() ;
		 //  查询时间
		  Calendar calendar =Calendar.getInstance();
	      Date date = new Date();
	      SimpleDateFormat form = new  SimpleDateFormat("yyyyMMdd");
	    String end = form.format(date.getTime()+1000*60*60*24);
	    //System.out.println(end);
	    String begin =form.format(date.getTime());
		 String  url=GlobalInfo.ServerUrl+"/v2/poi/device/data?sn="+ip+"&nodes=1,2,3,4,5,6,7,8,9,10,11&params=34,82&begin="+begin+"&end="+end;
	     System.out.println(url);
		 return url ;
	}*/
	

	@Test
	public void test(){
		
		
		// 得到info.properties 的所有信息
	     InfoPro.getPropInfo();
	     //  查询时间
		  Calendar calendar =Calendar.getInstance();
	      Date date = new Date();
	      SimpleDateFormat form = new  SimpleDateFormat("yyyyMMdd");
	    //System.out.println(end);
	    String end =form.format(date.getTime());
	 // 创建一个数据源
	    SimpleDataSource dataSource = new SimpleDataSource();
	    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/nutz_sensor");
	    dataSource.setUsername("root");
	    dataSource.setPassword("123");
           Sensor_Basic basic = new Sensor_Basic(); 
	 // 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
	    Dao dao = new NutDao(dataSource);
		
	    // 得到序列号与监测点的对应关系的Map 
	    HashMap basic_map = new HashMap<String ,String>();
	    
		// 获取检测点列表
	     String  url = InfoPro.ServerUrl+"/v2/poi/list?begin=20150202&end="+end+"&limit=10&page=1";
	     System.out.println(url);
	     int count = InfoPro.count;
	     for(int i=0;i<count;i++){
	    	String response = HttpUtils.getResponse(url, InfoPro.httpHeader_name, InfoPro.httpHeader_values[i]);
	    	System.out.println(response);
	    	
	    	
	    	JSONObject jsObject =JSONObject.fromObject(response);
	    	// 获得json字符串data的属性的所有value转成map
	    	Map map = JSONUtils.toHashMap(jsObject.get("data"));
	    	
	    	// map.get("data") 是一个序列号下所有检测点数组
	    	JSONArray arr = (JSONArray) map.get("data");
	         if(arr.size()>0){
	        	 for(int j=0;j<arr.size();j++){
	        		 JSONObject object =arr.getJSONObject(j);
	        		 
	        		 basic.setId(new Integer(object.get("id").toString()));
	        		 basic.setIp( object.get("sn").toString());
	        		 map.put("id",object.get("id").toString() );
	        		 map.put("head_value",InfoPro.httpHeader_values[i] );
	        		 basic.setLongitude(object.get("lng").toString());
	        		 basic.setLatitude(object.get("lat").toString());
	        		 basic.setType(1);
	        		/* Sensor_Basic result= dao.fetchx(Sensor_Basic.class ,basic ) ;
	        		
	        		 if(result==null){
	        			 dao.insert(basic);
	        		 }
	        		 else{
	        			dao.drop(Sensor_Basic.class, result);
	        			 dao.update(basic);
	        		 }*/
	        		 dao.insertOrUpdate(basic);
	        		 
	        	 }
	        	 
	         }
	     }
	    	 
	    	
	     }
	     
	        // 获取根系的url
	  			//String httpRootUrl=InfoPro.ServerUrl+"/v2/poi/detail?id="+list.get(i).get("device_id").toString() ;	
		
	}

