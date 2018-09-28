package com.sensor.quartzJob;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.quartz.Job;
import com.sensor.infoPro.InfoPro;
import com.sensor.utils.HttpUtils;
import com.sensor.utils.JSONUtils;

public abstract class BasicJob  implements Job  {

	public ArrayList  getBasicMap(){
		// 得到info.properties 的所有信息
	     InfoPro.getPropInfo();
	     //  查询时间
		  Calendar calendar =Calendar.getInstance();
	      Date date = new Date();
	      SimpleDateFormat form = new  SimpleDateFormat("yyyyMMdd");
	
	    String end =form.format(date.getTime());
	 
	 
		
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
	        		 Map sub_map = new HashMap<>() ;
	        		 JSONObject object =arr.getJSONObject(j);
	        		 sub_map.put("id",object.get("id")); 
	        		 sub_map.put("sn",object.get("sn").toString() );
	        		 sub_map.put("head_value",InfoPro.httpHeader_values[i] );
	        		 System.out.println("id 的值"+sub_map.get("id"));
	        		 arrList.add(sub_map);
	        		
	        	 }
	        	 
	         }
	
		}
		return arrList;
		
	}

}
