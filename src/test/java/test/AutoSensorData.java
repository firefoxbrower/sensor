package test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.json.Json;
import org.nutz.mvc.Mvcs;
import com.google.inject.internal.util.Strings;
import com.sensor.bean.Sensor_Basic;
import com.sensor.bean.Sensor_Basic_Details;
import com.sensor.bean.Sensor_Real_Time;
import com.sensor.infoPro.InfoPro;
import com.sensor.utils.HttpUtils;
import com.sensor.utils.JSONUtils;

public class AutoSensorData {

	public ArrayList  getBasicMap(){
		// 得到info.properties 的所有信息
	     InfoPro.getPropInfo();
	     //  查询时间
		  Calendar calendar =Calendar.getInstance();
	      Date date = new Date();
	      SimpleDateFormat form = new  SimpleDateFormat("yyyyMMdd");
	
	    String end =form.format(date.getTime());
	 // 创建一个数据源
	    SimpleDataSource dataSource = new SimpleDataSource();
	    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/nutz_sensor");
	    dataSource.setUsername("root");
	    dataSource.setPassword("123");
          Sensor_Basic basic = new Sensor_Basic(); 
	 // 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
	    Dao dao = new NutDao(dataSource);
		
	    // 得到序列号与监测点的对应关系的List<Map >
	    HashMap basic_map = new HashMap<String ,String>();
	    ArrayList arrList = new ArrayList<HashMap<String,String>>();
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
	        		 map.put("sn",object.get("sn").toString() );
	        		 map.put("head_value",InfoPro.httpHeader_values[i] );
	        		 basic.setLongitude(object.get("lng").toString());
	        		 basic.setLatitude(object.get("lat").toString());
	        		 basic.setType(1);
	        		 arrList.add(map);
	        		 dao.insertOrUpdate(basic);
	        		 
	        	 }
	        	 
	         }
	
		}
		return arrList;
		
	}
	
			public Date getDate(Long lon){
				Date date =null ;
			    try {
			    	//时间戳转化为Sting或Date  
				    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				    String d = format.format(lon);  
					 date=format.parse(d);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		      return  date;
				
			}
			public void handleResponse(String response){
				
				
			}
			
			
	 @org.junit.Test
	 public void Test(){
		 
		 ArrayList list= this.getBasicMap() ;
		 
		 Date date = new Date();
	      SimpleDateFormat form = new  SimpleDateFormat("yyyyMMdd");
	
	    String end = form.format(date.getTime()+1000*60*60*24);
	    
	    // 创建一个数据源
	    SimpleDataSource dataSource = new SimpleDataSource();
	    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/nutz_sensor");
	    dataSource.setUsername("root");
	    dataSource.setPassword("123");
	    // 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
	    Dao dao = new NutDao(dataSource);
	    String begin =form.format(date.getTime());
	    for(int i =0;i<list.size();i++){
	    	Map map =(Map) list.get(i);
	    	String sn =map.get("sn").toString();
	    	String device_id =map.get("id").toString();
	       String  url= InfoPro.ServerUrl+"/v2/poi/device/data?sn="+sn+"&nodes=1,2,3,4,5,6,7,8,9,10,11&params=34,82&begin="+begin+"&end="+end;
	       String response = HttpUtils.getResponse(url, InfoPro.httpHeader_name, map.get("head_value").toString());
			System.out.println(String.format("sn %s response %s ", map.get("sn").toString(),response));
	    }
/*	    for(int i =0;i<list.size();i++){
	    	Map map =(Map) list.get(i);
	    	String sn =map.get("sn").toString();
	    	String device_id =map.get("id").toString();
	       String  url= InfoPro.ServerUrl+"/v2/poi/device/data?sn="+sn+"&nodes=1,2,3,4,5,6,7,8,9,10,11&params=34,82&begin="+begin+"&end="+end;
	       String response = HttpUtils.getResponse(url, InfoPro.httpHeader_name, map.get("head_value").toString());
			System.out.println(String.format("sn %s response %s ", map.get("sn").toString(),response));
			JSONObject jsObject =JSONObject.fromObject(response);
	    	map =JSONUtils.toHashMap(response);
			// String root =map.get("root_level").toString();
			map =JSONUtils.toHashMap(map.get("data"));
			System.out.println(map.size());
			// 遍历 map 取出所有的时间
		   Iterator it = map.keySet().iterator();
		   while(it.hasNext()){
			     
			     
			   String key =  (String) it.next();
			 Long lon = new Long(key);
			  // 采集时间
			Date collect_time= this.getDate(lon);
			String value = map.get(key).toString();
			System.out.println(collect_time);
			// 时间过滤 

	        // 采集时间戳 lon 1483344001000
	        System.out.println("采集时间戳"+lon);
	      
	        // 现在时间戳
	        System.out.println("现在时间戳"+new Date().getTime());
	        if(new Date().getTime()>lon){
	        	
	        	Sensor_Real_Time real_Time = new Sensor_Real_Time();
	        	System.out.println("时间点采集点过滤");
				HashMap <String, Map> depth_map =JSONUtils.toHashMap(value);
				// 采用 for-each 遍历map
		          Double tempSum = 0.0 ;
		          Double waterSum = 0.0 ;
		          int sum = depth_map.size() ;
		       // 插入根系值的判断
					//int  root_level= new Integer(root);
					for(Map.Entry<String, Map> entry : depth_map.entrySet()){
						 // 深度 
						Integer depth =(new Integer(entry.getKey().toString())-1)*10;
						Map data_map = entry.getValue();
						// 土壤温度值
						Double temp =Double.valueOf( data_map.get("34").toString());
						tempSum+=temp ;
						// 土壤水分值
						Double water = 0.0;
						if(depth!=0){
							 water=Double.valueOf( data_map.get("82").toString());
							 waterSum+= water;
						}
						
						real_Time.setDevice_id(new Integer(device_id));
			        	real_Time.setCollect_time(collect_time);
			        	
			        	real_Time.setDepth(depth+0.0);
			        	real_Time.setTemp(temp);
			        	real_Time.setWater(water);
			        	
			        	dao.insert(real_Time);
						}
					
					
					 DecimalFormat df = new DecimalFormat("#.####");
					   Double tempAvg = Double.parseDouble(df.format(tempSum/sum));
					   Double  waterAvg = Double.parseDouble(df.format(waterSum/(sum-1)));
				 		   real_Time.setDepth(1000.0);
				 		   real_Time.setTemp(tempAvg);
				 		   real_Time.setWater(waterAvg);
				 		   dao.insert(real_Time);
				 		 
				 		 
				 		   real_Time=null;
				 		   tempAvg=null;
				 		   waterAvg=null ;
				 		   df=null ;
				 		   tempSum=null;
				 		   waterSum=null;
				 		  depth_map =null ;
				 		   
				 		 
	        }
	        
	        key =null ;
	        lon =null ;
	        collect_time =null ;
	        value =null ;
		   
	   }
	   
	   jsObject =null ;
	   response =null ;
	   sn=null ;
	   device_id =null ;
	   it=null;
	   url=null ;
   	
   }*/
   list=null ;
   date =null ;
   form=null;
   begin =null ;
   end =null ;
}
	 }

