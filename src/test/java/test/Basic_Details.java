package test;

import java.awt.List;
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
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.json.Json;

import com.sensor.bean.Sensor_Basic;
import com.sensor.bean.Sensor_Basic_Details;
import com.sensor.infoPro.InfoPro;
import com.sensor.utils.HttpUtils;
import com.sensor.utils.JSONUtils;
 // 更新 监测点详细表
public class Basic_Details {

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
	@org.junit.Test
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
	  ArrayList list = this.getBasicMap();
	  for(int i=0;i<list.size();i++){
		  Map map =(Map) list.get(i);
			String url = InfoPro.ServerUrl+"/v2/poi/detail?id="+map.get("id").toString();
			String response = HttpUtils.getResponse(url, InfoPro.httpHeader_name, map.get("head_value").toString());
			//System.out.println("response"+response);
			
			JSONObject jsObject =JSONObject.fromObject(response);
	    	// 获得json字符串data的属性的所有value转成map
	    	String jsonStr = jsObject.get("data").toString();
	    	System.out.println("jsonStr"+jsonStr);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	
		 
	}
}
