package com.sensor.quartzJob;


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
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sensor.bean.Sensor_Basic_Details;
import com.sensor.bean.Sensor_Real_Time;
import com.sensor.infoPro.InfoPro;
import com.sensor.utils.HttpUtils;
import com.sensor.utils.JSONUtils;

@IocBean
public class AutoSenserDataJob extends BasicJob implements Job {
	
	@Inject protected Dao dao;
	
	
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
			public void handleResponse(String sn ,String response){
			
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
			
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// 得到info.properties 的所有信息
	     InfoPro.getPropInfo();
		ArrayList list= this.getBasicMap() ;
		 
		 Date date = new Date();
	      SimpleDateFormat form = new  SimpleDateFormat("yyyyMMdd");
	
	    String end = form.format(date.getTime()+1000*60*60*24);
	    
	   
	    //目前自动建表
	   // Daos.createTablesInPackage(dao, "com.sensor.bean", false); 
	    String begin =form.format(date.getTime());
	    
	    for(int i =0;i<list.size();i++){
	    	Map map =(Map) list.get(i);
	    	String sn =map.get("sn").toString();
	    	String device_id =map.get("id").toString();
	       String  url= InfoPro.ServerUrl+"/v2/poi/device/data?sn="+sn+"&nodes=1,2,3,4,5,6,7,8,9,10,11&params=34,82&begin="+begin+"&end="+end;
	       System.out.println(map);
	       String response = HttpUtils.getResponse(url, InfoPro.httpHeader_name, map.get("head_value").toString());
			this.handleResponse(device_id , response);
	    }

  list=null ;
  date =null ;
  form=null;
  begin =null ;
  end =null ;
}

}
