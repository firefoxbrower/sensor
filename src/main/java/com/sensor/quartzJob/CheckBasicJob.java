package com.sensor.quartzJob;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.sensor.bean.Sensor_Basic;
import com.sensor.bean.Sensor_Basic_Details;
import com.sensor.infoPro.InfoPro;
import com.sensor.utils.HttpUtils;
import com.sensor.utils.JSONUtils;

@IocBean
public class CheckBasicJob  implements Job{

	 private static final Log log = Logs.get();

	    @Inject protected Dao dao;
	    
	   
	    /**所有检测点更新，并且根据检测点返回 一个序列号和监测点之间的List<Map>数据*/     
	    public ArrayList  getBasicMap(){
	    	

		    // 得到序列号与监测点的对应关系的List<Map >
		    HashMap basic_map = new HashMap<String ,String>();
		    ArrayList arrList = new ArrayList<HashMap<String,String>>();
	    	 // 得到info.properties 的所有信息
		     InfoPro.getPropInfo();
		     //  查询时间
			  Calendar calendar =Calendar.getInstance();
		      Date date = new Date();
		      SimpleDateFormat form = new  SimpleDateFormat("yyyyMMdd");
		    //System.out.println(end);
		    String end =form.format(date.getTime());
			// 获取检测点列表
		     String  url = InfoPro.ServerUrl+"/v2/poi/list?begin=20150202&end="+end+"&limit=10&page=1";
	           Sensor_Basic basic = new Sensor_Basic(); 
			
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
		        		 sub_map.put("id",object.get("id").toString() );
		        		 sub_map.put("head_value",InfoPro.httpHeader_values[i] );
		        		 basic.setLongitude(object.get("lng").toString());
		        		 basic.setLatitude(object.get("lat").toString());
		        		 basic.setType(1);
		        		 arrList.add(sub_map);
		        		 dao.insertOrUpdate(basic ,null , FieldFilter.create(Sensor_Basic.class,  "ip|id|type"));
		        		 sub_map=null;
		        		 
		        	 }
		        	 
		         }
		
		     }
               return arrList;
	    }
	    
	    /** 检测点详细表和监测点 一对一也需要更新数据*/
	    
	    public  void  getBasicDetails(ArrayList list){
	    	
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
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		  log.debug(" 检查 Basic , start");
		  // 监测点表的更新数据
         ArrayList list= this.getBasicMap();
          
          this.getBasicDetails(list);
          
	    
	}

}
