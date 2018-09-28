package com.sensor;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.util.Daos;
import org.nutz.integration.quartz.NutQuartzCronJobFactory;
import org.nutz.ioc.Ioc;
import org.nutz.json.Json;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.quartz.Scheduler;
import com.sensor.bean.Sensor_Basic;
import com.sensor.bean.Sensor_Basic_Details;
import com.sensor.bean.Sensor_Real_Time;
import com.sensor.bean.User;
import com.sensor.infoPro.InfoPro;
import com.sensor.quartzJob.AutoSenserDataJob;
import com.sensor.utils.HttpUtils;
import com.sensor.utils.JSONUtils;

public class MainSetup implements Setup {
	
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

	public void handleResponse(Dao dao ,String sn ,String response){
		
		JSONObject jsObject =JSONObject.fromObject(response);
   	Map map =JSONUtils.toHashMap(response);
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
	        	
	        	System.out.println("时间点采集点过滤");
				HashMap <String, Map> depth_map =JSONUtils.toHashMap(value);
				// 采用 for-each 遍历map
		          Double tempSum = 0.0 ;
		          Double waterSum = 0.0 ;
		          int sum = depth_map.size() ;
		       // 插入根系值的判断
		      	for(Map.Entry<String, Map> entry : depth_map.entrySet()){
		      		Sensor_Real_Time real_Time = new Sensor_Real_Time();
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
					//List<Person> people = dao.query(Person.class, Cnd.where("name", "like", "P%"));
					List <Sensor_Real_Time> sensort_realList = dao.query(Sensor_Real_Time.class, Cnd.where("device_id","=",sn ).and("collect_time","=",collect_time).and("depth","=",depth ));
					real_Time.setDevice_id(new Integer(sn));
					
					/*Sensor_Basic_Details details=new Sensor_Basic_Details() ;
					
					details.setId(new Integer(sn));*/
					
					//System.out.println(details.getRoot().substring(0, 3));
		        	real_Time.setCollect_time(collect_time);
		        	
		        	real_Time.setDepth(depth+0.0);
		        	real_Time.setTemp(temp);
		        	real_Time.setWater(water);
		        	 // 设置根系
			 		  List<Sensor_Basic_Details > list=  dao.query(Sensor_Basic_Details.class, Cnd.where("id","=",sn));
			 		  System.out.println(list.size());
			 		  if(list.size()>0){
			 			 String s=list.get(0).getRoot().toString();
			 			  s=s.substring(0,s.length()-2);
			 			 String root =s;
			 			 System.out.println(s);
			 			 if(new Integer(root)-real_Time.getDepth()==0){
			 			 real_Time.setRoot_level(root);
			 			 }
			 		  }
		        	if(sensort_realList.size()<1){
		        	 	dao.insert(real_Time);
		        	 	real_Time=null;
		        	}else{
		        		
		        		System.out.println("查询有结果，不插入"+sensort_realList.size());
		        	}
		       
		      	}
		      	
		      	Sensor_Real_Time real_Time2 = new Sensor_Real_Time();
		      	 DecimalFormat df = new DecimalFormat("#.####");
				   Double tempAvg = Double.parseDouble(df.format(tempSum/sum));
				   Double  waterAvg = Double.parseDouble(df.format(waterSum/(sum-1)));
				       real_Time2.setDevice_id(new Integer(sn));
				     
				       real_Time2.setCollect_time(collect_time) ;
			 		   real_Time2.setDepth(1000.0);
			 		   real_Time2.setTemp(tempAvg);
			 		   real_Time2.setWater(waterAvg);
			 		  
			 		
			 		
						List <Sensor_Real_Time> sensort_realList2 = dao.query(Sensor_Real_Time.class, Cnd.where("device_id","=",sn ).and("collect_time","=",collect_time).and("depth","=",1000 ));
			        	if(sensort_realList2.size()<1){
			        	 	dao.insert(real_Time2);
			        	}else{
			        		
			        		System.out.println("查询有结果，不插入");
			        	}
			 		   real_Time2 =null;
			 		   
	        }
	   }
	
		
	}
	
	
	/**所有监测点检查*/
	public ArrayList checkBasic( Dao dao){
		Sensor_Basic basic = new Sensor_Basic(); 
		 // 所有监测点自动检查
	       // 获取检测点列表
		 ArrayList arrList = new ArrayList<HashMap<String,String>>();
    	 // 得到info.properties 的所有信息
		     InfoPro.getPropInfo();
		     //  查询时间
			  Calendar calendar =Calendar.getInstance();
		      Date date = new Date();
		      SimpleDateFormat form = new  SimpleDateFormat("yyyyMMdd");
		    String end =form.format(date.getTime());
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
		        		 Map sub_map = new HashMap<>() ;
		        		 basic.setId(new Integer(object.get("id").toString()));
		        		 basic.setIp( object.get("sn").toString());
		        		 basic.setLongitude(object.get("lng").toString());
		        		 basic.setLatitude(object.get("lat").toString());
		        		 basic.setType(0);
		        		 sub_map.put("id",object.get("id").toString() );
		        		 sub_map.put("sn",object.get("sn").toString() );
		        		 sub_map.put("head_value",InfoPro.httpHeader_values[i] );
		        		 arrList.add(sub_map);
		        		
		        		 dao.insertOrUpdate(basic ,null , FieldFilter.create(Sensor_Basic.class,  "ip|id|type"));
		        		 sub_map=null;
		        	 }
		        	 
		         }
		     }
		     return arrList;
	}
	
	public void  autoSensorData(Dao dao , List list){
		// 得到info.properties 的所有信息
	 System.out.println(InfoPro.httpHeader_name);
	  	 Date date = new Date();
	     SimpleDateFormat form = new  SimpleDateFormat("yyyyMMdd");

	    String end = form.format(date.getTime()+1000*60*60*24);

	    String begin =form.format(date.getTime());
		 for(int i =0;i<list.size();i++){
		    	Map map =(Map) list.get(i);
		    	String sn =map.get("sn").toString();
		    	String device_id =map.get("id").toString();
		       String  url= InfoPro.ServerUrl+"/v2/poi/device/data?sn="+sn+"&nodes=1,2,3,4,5,6,7,8,9,10,11&params=34,82&begin="+begin+"&end="+end;
		       System.out.println(map);
		       String response = HttpUtils.getResponse(url, InfoPro.httpHeader_name, map.get("head_value").toString());
				this.handleResponse(dao,device_id , response);
		    }
	}
	
    /** 检测点详细表和监测点 一对一也需要更新数据*/
    
    public  void  getBasicDetails(Dao dao ,ArrayList list){
    	
    	 for(int i=0;i<list.size();i++){
   		  Map map =(Map) list.get(i);
   			String url = InfoPro.ServerUrl+"/v2/poi/detail?id="+map.get("id").toString();
   			String response = HttpUtils.getResponse(url, InfoPro.httpHeader_name, map.get("head_value").toString());
   			JSONObject jsObject =JSONObject.fromObject(response);
   	    	// 获得json字符串data的属性的所有value转成map
   	    	String jsonStr = jsObject.get("data").toString();
   	    	Sensor_Basic_Details details = Json.fromJson(Sensor_Basic_Details.class,jsonStr);
   	    	
   	    	if(dao.exists(Sensor_Basic_Details.class)){
   	    		dao.insertOrUpdate(details);
   	    	}else{
   	    		dao.create(Sensor_Basic_Details.class, false);
   	    		dao.insertOrUpdate(details);
   	    	}
   	    	
   	    	// 上面大多字段可以插入，时间字段插入失败
   	    	jsObject =JSONObject.fromObject(jsonStr);
   	    	System.out.println(jsObject.get("create_time"));
   	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   	    	Date cdDate;
   	    	Date upDate ;
   			try {
   				String sn = jsObject.getString("sn");
   		    	Sensor_Basic_Details re = dao.fetch(Sensor_Basic_Details.class,sn);
   				cdDate = format.parse(jsObject.get("create_time").toString());
   				upDate = format.parse(jsObject.get("latest_collect_time").toString());
   				re.setCretate_time(cdDate);
   				re.setLastest_collect_time(upDate);
   				dao.insertOrUpdate(re);
   			} catch (ParseException e) {
   				
   				e.printStackTrace();
   			}

   	  }
    	
    }
	@Override
	public void init(NutConfig nc) {
		// TODO Auto-generated method stub
  	  Ioc ioc = nc.getIoc();
      Dao dao = ioc.get(Dao.class);
      // 如果没有createTablesInPackage,请检查nutz版本
	  //目前自动建表
      Daos.createTablesInPackage(dao, "com.sensor.bean", false);
   // 获取quartz的Scheduler,这样就自动触发了计划任务的启动
      //ioc.get(Scheduler.class);
      // 所有的监测点 插入和更新数据
  	ArrayList list= this.checkBasic(dao);
  	
  	// 所有监测点详情
  	//this.getBasicDetails(dao, list);
 
  	this.autoSensorData(dao, list);
 
    
      // 获取NutQuartzCronJobFactory从而触发计划任务的初始化与启动
      ioc.get(NutQuartzCronJobFactory.class);
	    	
      // 加载模板引擎
	    	
  
	}

	@Override
	public void destroy(NutConfig nc) {
		// TODO Auto-generated method stub
		
	}

  
}