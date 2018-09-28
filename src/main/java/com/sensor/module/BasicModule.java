package com.sensor.module;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.sensor.bean.Sensor_Basic;
import com.sensor.bean.User;
import com.sensor.service.BasicService;
import com.sensor.utils.JSON;
@IocBean
public class BasicModule  extends BaseModule  {

	public Sql getSql(String query){
		Sql sql = Sqls.create(query);
		Sql reSql= sql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				ResultSetMetaData md = rs.getMetaData();
			    int columnCount = md.getColumnCount();
			    ArrayList list = new ArrayList();

			    while (rs.next()) {
			      Map rowData = new HashMap(columnCount);
			      for (int i = 1; i <= columnCount; i++) {
			        Object v = rs.getObject(i);

			        if ((v != null) && ((v.getClass() == java.util.Date.class) || (v.getClass() == java.sql.Date.class))) {
			          Timestamp ts = rs.getTimestamp(i);
			          v = new java.util.Date(ts.getTime());
			        }
			        else if ((v != null) && (v.getClass() == Clob.class)) {
			        	Clob clob = (Clob)v;
			        	v =clob != null ? clob.getSubString(1L, (int)clob.length()) : null;
			      
			        }
			        rowData.put(md.getColumnName(i), v);
			      }
			      list.add(rowData);
			    }

			    return list;
			}
		}) ;
		return reSql ;
	}
	 @Inject protected BasicService basicService;
	 
	    @At
	    public int count() {
	        return dao.count(Sensor_Basic.class);
	    }
		
		
	    @At("getLandInfo")
	    @AdaptBy(type=JsonAdaptor.class)
	    @Ok("raw:js")
	    @GET
	    public String getLand(){
	    	   
	    	 // waterdevice_extendinfo info device_type 2 是水泵
	    	String str_sprinkler="SELECT DISTINCT d.device_code, d.device_id, d.device_type, d.state, d.run_state , info.is_full_speed, info.worktime_len, info.resttime_len, info.start_type, info.stop_type , info.work_state, info.run_time AS run_time FROM waterdevice_extendinfo info, wartering_device d, (SELECT DISTINCT d.device_code, MAX(info.run_time) AS run_time FROM waterdevice_extendinfo info, wartering_device d WHERE d.device_code = info.device_code AND info.device_type = 2 GROUP BY info.device_code ORDER BY info.device_code ASC ) t WHERE d.device_code = info.device_code AND info.run_time = t.run_time AND info.device_code = t.device_code AND info.device_type = 2 GROUP BY info.device_code ORDER BY info.device_code ASC;";
			 str_sprinkler="SELECT n.device_code as device_code, m.device_id AS device_id, n.device_type AS device_type, n.state AS state, m.run_state AS run_state, m.is_full_speed AS is_full_speed, m.worktime_len AS worktime_len, m.resttime_len AS resttime_len, m.start_type AS start_type, m.stop_type AS stop_type, m.work_state AS work_state, m.run_time AS run_time FROM wartering_device n LEFT JOIN( SELECT DISTINCT d.device_code, d.device_id, d.device_type, d.state, d.run_state, info.is_full_speed, info.worktime_len, info.resttime_len, info.start_type, info.stop_type, info.work_state, info.run_time AS run_time FROM waterdevice_extendinfo info, wartering_device d, ( SELECT DISTINCT d.device_code, MAX(info.run_time) AS run_time FROM waterdevice_extendinfo info, wartering_device d WHERE d.device_code = info.device_code AND info.device_type = 2 GROUP BY info.device_code ORDER BY info.device_code ASC) t WHERE d.device_code = info.device_code AND info.run_time = t.run_time AND info.device_code = t.device_code AND info.device_type = 2 GROUP BY info.device_code ORDER BY info.device_code ASC ) m ON n.device_code = m.device_code where n.device_type=1"; 
			 Sql sql = this.getSql(str_sprinkler);
			
				dao.execute(sql);
				 List list_sprinkler =sql.getList(HashMap.class);
				 HashMap return_map = new HashMap();
				
				
				 // 主水泵
				 // 主水泵 设备
				 // waterdevice_extendinfo info device_type 1 是水泵 
					String sql_pump=" select n.device_type AS pum_style, n.device_code , m.device_id AS device_id, n.state AS state, m.pum_state AS pum_state, m.is_full_speed AS is_full_speed, m.worktime_len AS worktime_len, m.resttime_len AS resttime_len, m.start_type AS start_type, m.stop_type AS stop_type, m.work_state AS work_state, m.voltage1 AS voltage1, m.current1 AS current1, m.voltage2 AS voltage2, m.current2 AS current2, m.voltage3 AS voltage3, m.current3 AS current3 from wartering_device n left join( SELECT d.device_type AS pum_style, d.device_code AS device_code, d.device_id AS device_id, d.state AS state, d.pump_state AS pum_state, info.is_full_speed AS is_full_speed, info.worktime_len AS worktime_len, info.resttime_len AS resttime_len, info.start_type AS start_type, info.stop_type AS stop_type, info.work_state AS work_state, alarm.voltage1 AS voltage1, alarm.current1 AS current1, alarm.voltage2 AS voltage2, alarm.current2 AS current2, alarm.voltage3 AS voltage3, alarm.current3 AS current3, COUNT(*) FROM waterdevice_extendinfo info, wartering_device d, waterdevice_workinfo_alarm alarm, ( SELECT DISTINCT d.device_code, MAX(info.run_time) AS run_time FROM waterdevice_extendinfo info, wartering_device d WHERE d.device_code = info.device_code AND info.device_type = 1 GROUP BY info.device_code ORDER BY info.device_code ASC) t WHERE d.device_code = info.device_code AND alarm.device_code = d.device_code AND info.device_type = 1 AND t.device_code = d.device_code AND info.run_time = t.run_time GROUP BY info.device_code ORDER BY info.device_code ASC) m on n.device_code = m.device_code ";
					sql = this.getSql(sql_pump);
				  dao.execute(sql);
					List list_pump =sql.getList(HashMap.class);
					 
				
		     		   // 地块信息
		     		      String sql_land="SELECT DISTINCT land.field_id, land.land_id, wartering_device.device_type, wartering_device.device_code, wartering_device.device_id , land.sub_device_code, land.sub_device_id, land.land_area, land.land_degree, land.land_pump_type , wartering_device.lon, wartering_device.lat, COUNT(*) FROM land, wartering_device WHERE 	land.field_id = wartering_device.field_id AND land.land_id = wartering_device.land_id  GROUP BY land.field_id,land.land_id ;" ;
		     		     sql = Sqls.create(sql_land);
		     		    sql = this.getSql(sql_land);
		  			  dao.execute(sql);
		     		      List  list_land=sql.getList(HashMap.class);

			     		   HashSet return_set =new HashSet<>() ;
			     		    Map sub_map_sprinker  =null ;
				    	    Map sub_map_pump =null  ;
				    	    Map sub_map_land = null ;
						    if (list_sprinkler.size() > 0) {
						    	 //喷灌机设备 对象属性数据
						    	for (int i = 0; i < list_sprinkler.size(); i++) {
						    		HashMap map = new HashMap();
							                sub_map_sprinker = (Map)list_sprinkler.get(i) ;
							                sub_map_sprinker.remove("COUNT(*)");
							             String device_code=(String) sub_map_sprinker.get("device_code");
							             String sub_device_code=(String) sub_map_sprinker.get("sub_device_code");
							            // System.out.println("apnd"+sub_map_sprinker.get("run_state"));
							             if(sub_map_sprinker.get("run_state")!=null){
							            	   if((Integer)sub_map_sprinker.get("run_state")==0){
							            	 sub_map_sprinker.remove("is_full_speed");
							            	 sub_map_sprinker.put("is_full_speed", 0);
							             } 
							             }
							          
							            		if(list_land.size()>0){
							            			 for(int j=0;j<list_land.size();j++){
							            				 sub_map_land = (HashMap) list_land.get(j) ;
							            				 sub_map_land.remove("COUNT(*)");
									                			if(sub_map_land.containsValue(device_code)){
									                			        map.put("land", sub_map_land);
									                			        sub_map_land=null ;
									                			};	
									                 }
							            		}
							             
							            	    for(int j=0;j<list_pump.size();j++){
									            	Map sub_map=(Map) list_pump.get(j);
									            	sub_map.remove("COUNT(*)");
									            	if(sub_map.get("device_code").toString().equals(device_code) || sub_map.get("device_code").toString().equals(sub_device_code)){
									            		// System.out.println(device_code);
									            		 sub_map_pump=sub_map;
									            		 sub_map=null;
									            		 //主水泵
							       		                 map.put("main_pum_device",sub_map_pump); 
									            		
									            	}
									                     
									            	 
									             }
							                 // 喷灌机添加
							                 map.put("pgjdevice", sub_map_sprinker);
							                          
							                 return_set.add(map) ;
							                 map=null;
							               
							                 
						    	}
						    	  // 喷灌圈  
					            return_map.put("cricledates", return_set);
					              // 状态
					            return_map.put("state",true);
						      
						    	
						    }else{
			     			  // 状态
			   	            return_map.put("state",false);
			     			   
			     		   }
			     		  JSONObject ob = new JSONObject();
			  		    String json = JSON.Encode(return_map);
			  		    System.out.println(json); 
			return json;
			
	    }
	   
	    @At("getDevices")
	    @AdaptBy(type=JsonAdaptor.class)
	    @Ok("raw:js")
	    public String getDevice(){
	    	 //运行状态
		    String sqls = "SELECT n.field_id, n.land_id, n.device_code, n.pump_state AS pump_state, n.run_state, CAST( CASE WHEN m.pump_state IS NULL THEN CONVERT (_utf8 '9' USING utf8) ELSE m.pump_state END AS CHAR (20) ) AS sub_pump_state, n.lon, n.lat, n.device_type FROM wartering_device n LEFT JOIN ( SELECT pump_state, land_id, field_id FROM wartering_device WHERE device_type = 2 ) m ON m.land_id = n.land_id AND m.field_id = n.field_id WHERE n.device_type = 1 AND ( n.pump_state > 0 OR n.run_state > 0 OR m.pump_state > 0 );";

		    Sql sql = Sqls.create(sqls);
			sql.setCallback(new SqlCallback() {
				
				@Override
				public Object invoke(Connection conn, ResultSet rs, Sql sql)
						throws SQLException {
				List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
				      while(rs.next()){
				    	  HashMap  map = new HashMap<>();
				    	  map.put("field_id", rs.getString("field_id"));
				    	  map.put("land_id",rs.getString("land_id") );
				    	  map.put("run_state", rs.getString("run_state"));
				    	  map.put("pump_state", rs.getString("pump_state"));
				    	  map.put("device_type",rs.getString("device_type") );
				    	  map.put("lon",rs.getString("lon") );
				    	  map.put("lat", rs.getString("lat"));
				    	  map.put("sub_pump_state", rs.getString("sub_pump_state"));
				    	  list.add(map);
				      }	
				return list;
				}
			}) ;
			dao.execute(sql);
		List relist =	sql.getList(HashMap.class); 
		    HashMap map = new HashMap();
		     map.put("runing", relist);
		    relist =null ;
		     
		     // 停止状态
		     String sqls2 = "SELECT * FROM ( SELECT n.field_id, n.land_id, n.device_code, n.pump_state AS pump_state, n.run_state, CAST( CASE WHEN m.pump_state IS NULL THEN CONVERT (_utf8 '9' USING utf8) ELSE m.pump_state END AS CHAR (20) ) sub_pump_state, n.lon, n.lat, n.device_type FROM wartering_device n LEFT JOIN ( SELECT pump_state, land_id, field_id FROM wartering_device WHERE device_type = 2 ) m ON m.land_id = n.land_id AND m.field_id = n.field_id WHERE n.device_type = 1 AND ( n.pump_state = 0 AND n.run_state = 0 ) ) AS mm WHERE sub_pump_state = 0 OR sub_pump_state = '9'";
		         sql = Sqls.create(sqls2);
		         
			sql.setCallback(new SqlCallback() {
					
					@Override
					public Object invoke(Connection conn, ResultSet rs, Sql sql)
							throws SQLException {
					List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
					      while(rs.next()){
					    	  HashMap  map = new HashMap<>();
					    	  map.put("field_id", rs.getString("field_id"));
					    	  map.put("land_id",rs.getString("land_id") );
					    	  map.put("run_state", rs.getString("run_state"));
					    	  map.put("pump_state", rs.getString("pump_state"));
					    	  map.put("device_type",rs.getString("device_type") );
					    	  map.put("lon",rs.getString("lon") );
					    	  map.put("lat", rs.getString("lat"));
					    	  map.put("sub_pump_state", rs.getString("sub_pump_state"));
					    	  list.add(map);
					      }	
					return list;
					}
				}) ;
		         
				
				dao.execute(sql);
				List relist2 =sql.getList(HashMap.class); 
				map.put("stop", relist2);
				
				
				
				// 通讯报警状态
			    String sql3 = "SELECT n.field_id AS field_id, n.land_id AS land_id, n.lon AS lon, n.lat AS lat, n.device_type AS device_type , n.state AS communication FROM wartering_device n LEFT JOIN (SELECT DISTINCT d.device_code, d.device_id, d.device_type, d.state, d.run_state , info.is_full_speed, info.worktime_len, info.resttime_len, info.start_type, info.stop_type , info.work_state, info.run_time AS run_time FROM waterdevice_extendinfo info, wartering_device d, (SELECT DISTINCT d.device_code, MAX(info.run_time) AS run_time FROM waterdevice_extendinfo info, wartering_device d WHERE d.device_code = info.device_code AND info.device_type = 2 GROUP BY info.device_code ORDER BY info.device_code ASC ) t WHERE d.device_code = info.device_code AND info.run_time = t.run_time AND info.device_code = t.device_code AND info.device_type = 2 GROUP BY info.device_code ORDER BY info.device_code ASC ) m ON n.device_code = m.device_code WHERE n.device_type = 1 AND n.state = 1";
			    sql = Sqls.create(sql3);
				sql.setCallback(new SqlCallback() {
					
					@Override
					public Object invoke(Connection conn, ResultSet rs, Sql sql)
							throws SQLException {
					List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
					      while(rs.next()){
					    	  HashMap  map = new HashMap<>();
					    	  map.put("field_id", rs.getString("field_id"));
					    	  map.put("land_id",rs.getString("land_id") );
					    	  map.put("lon",rs.getString("lon") );
					    	  map.put("lat", rs.getString("lat"));
					    	  map.put("device_type",rs.getString("device_type") );
					    	  map.put("communication", rs.getString("communication"));
					    	
					    	  list.add(map);
					      }	
					return list;
					}
				}) ;
				dao.execute(sql);
				relist =sql.getList(HashMap.class); 
				map.put("communcationWarnning", relist);
				relist=null;
				
				// 电压报警
			    String sql4 = "SELECT DISTINCT   d.field_id , d.land_id  ,  d.lon ,d.lat , d.device_type , w.voltage1 , w.voltage2 , w.voltage3   FROM `wartering_device` d , `waterdevice_workinfo_alarm`  w  where d.device_code=w.device_code  and ( w.voltage1>262 or w.voltage1<173  or w.voltage2>262 or w.voltage2<173 or w.voltage3>262 or w.voltage3<173)";
			    sql = Sqls.create(sql4);
				sql.setCallback(new SqlCallback() {
					
					@Override
					public Object invoke(Connection conn, ResultSet rs, Sql sql)
							throws SQLException {
						ResultSetMetaData md = rs.getMetaData();
					    int columnCount = md.getColumnCount();
					    ArrayList list = new ArrayList();

					    while (rs.next()) {
					      Map rowData = new HashMap(columnCount);
					      for (int i = 1; i <= columnCount; i++) {
					        Object v = rs.getObject(i);

					        if ((v != null) && ((v.getClass() == java.util.Date.class) || (v.getClass() == java.sql.Date.class))) {
					          Timestamp ts = rs.getTimestamp(i);
					          v = new java.util.Date(ts.getTime());
					        }
					        else if ((v != null) && (v.getClass() == Clob.class)) {
					        	Clob clob = (Clob)v;
					        	v =clob != null ? clob.getSubString(1L, (int)clob.length()) : null;
					      
					        }
					        rowData.put(md.getColumnName(i), v);
					      }
					      list.add(rowData);
					    }

					    return list;
					}
				}) ;
				dao.execute(sql);
				relist =sql.getList(HashMap.class); 
				map.put("voltage1Warnning", relist);
				relist=null;
		    System.out.println(map);
		    JSONObject ob = new JSONObject();
			    String json = JSON.Encode(map);
			    System.out.println(json); 

            return json ;
	    	
	    }
	    
	    @At("getcircletypes")
	    public Map getCircletypes(){
	    	
	    	String sqls = "select  circle_crop.FID,circle_crop.Id ,circle_crop.field_id ,wartering_device.land_id ,circle_crop.domain  ,circle_crop.work_kind ,circle_crop.plant_crop_type ,circle_crop.domain_color ,wartering_device.device_code,wartering_device.lon,wartering_device.lat  from circle_crop, wartering_device where circle_crop.field_id =Convert( wartering_device.field_id,SIGNED)  and circle_crop.id=wartering_device.land_id";
		    Sql sql = Sqls.create(sqls);
			sql.setCallback(new SqlCallback() {
				
				@Override
				public Object invoke(Connection conn, ResultSet rs, Sql sql)
						throws SQLException {
				List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
				      while(rs.next()){
				    	  HashMap  map = new HashMap<>();
				    	  map.put("FID", rs.getString("FID"));
				    	  map.put("Id",rs.getString("Id") );
				    	  map.put("field_id", rs.getString("field_id"));
				    	  map.put("land_id", rs.getString("land_id"));
				    	  map.put("domain",rs.getString("domain") );
				    	  map.put("work_kind", rs.getString("work_kind"));
				    	  map.put("plant_crop_type", rs.getString("plant_crop_type"));
				    	  map.put("domain_color", rs.getString("domain_color"));
				    	  map.put("device_code", rs.getString("device_code"));
				    	  map.put("lon",rs.getString("lon") );
				    	  map.put("lat", rs.getString("lat"));
				    	  list.add(map);
				      }	
				return list;
				}
			}) ;
			dao.execute(sql);
		List list =	sql.getList(HashMap.class); 
		    HashMap map = new HashMap();
		    map.put("state", Boolean.valueOf(true));
		    map.put("circletypes", list);
		    System.out.println(map);
		    return map;
	    }
	    @At("send")
	    public Map getUser(){
			//获取所有
			List<User> list =dao.query(User.class, null);
		    HashMap map = new HashMap();
		    map.put("state", Boolean.valueOf(true));
		    map.put("users",list);
		    return map;
	    	
	    }
	    
	    @At("getSensors")
	    @AdaptBy(type=JsonAdaptor.class)
	    @Ok("raw:js")
	    public String getRealTime(){
	    	
		    // 查询全部记录
		    List<Sensor_Basic> list = dao.query(Sensor_Basic.class, null);
		    HashMap map = new HashMap();
		    List list1 = new ArrayList();
		    if (list.size() > 0) {
		    	 
		    	for(int i=0;i<list.size();i++){
		    		HashMap map2 = new HashMap();
		    		Sensor_Basic basic =list.get(i);
		    		System.out.println(basic.getId());
		    		String sqls= "SELECT DISTINCT s.depth,s.collect_time,s.temp, s.water FROM sensor_real_time  s,\tsensor_basic_t t WHERE s.device_id = t.device_id AND TO_DAYS(NOW()) - TO_DAYS(s.collect_time) <= 6 and t.device_id=" + 
		    		          basic.getId() +" ORDER BY s.collect_time ASC";
		    		Sql sql = Sqls.create(sqls);
		    		sql.setCallback(new SqlCallback() {
						
						@Override
						public Object invoke(Connection conn, ResultSet rs, Sql sql)
								throws SQLException {
						List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
						      while(rs.next()){
						    	  HashMap  map = new HashMap<>();
						    	  map.put("depth", rs.getString("depth"));
						    	  map.put("collect_time",rs.getString("collect_time") );
						    	  map.put("temp", rs.getString("temp"));
						    	  map.put("water", rs.getString("water"));
						    	  list.add(map);
						      }	
						return list;
						}
					}) ;
		    		
		    		dao.execute(sql);
		    		List<HashMap> senTimes_list2 = sql.getList(HashMap.class); 
		    		 if (senTimes_list2.size() > 0) {
		    			 
		    			 
		    	          map2.put("depthes", senTimes_list2);
		    	        }
		    		    map2.put("device_id", basic.getId());
		    	        map2.put("longitude", basic.getLongitude());
		    	        map2.put("latitude",  basic.getLatitude());
		    	        list1.add(map2);
		    	        map2=null;
		    	}
		    	 map.put("senorsEntity", list1);
		    }
		    else {
		        map.put("state", Integer.valueOf(1));
		      }
		    JSONObject ob = new JSONObject();
		    String json = JSON.Encode(map);
		    System.out.println(json);  
		 
		    return  json ;
	}
	    
	    @At("getSensorsSingle")
	    @AdaptBy(type=JsonAdaptor.class)
	    @Ok("raw:js")
	    public String getRealTimeSingle(  @Param("shangqing_id") String shangqing_id ){
	    	
	    	// 查询单条数据
		    //Cnd cnd = Cnd.NEW();
		    Condition c = Cnd.where("id","=",shangqing_id);
		    //c.where("id", "=", 3018);
		    List<Sensor_Basic> list = dao.query(Sensor_Basic.class, c);
		    HashMap map = new HashMap();
		    List list1 = new ArrayList();
		    if (list.size() > 0) {
		    	
		    	
		    	for(int i=0;i<list.size();i++){
		    		HashMap map2 = new HashMap();
		    		Sensor_Basic basic =list.get(i);
		    		System.out.println(basic.getId());
		    		String sqls= "SELECT DISTINCT s.depth,s.collect_time,s.temp, s.water FROM sensor_real_time  s,\tsensor_basic_t t WHERE s.device_id = t.device_id AND TO_DAYS(NOW()) - TO_DAYS(s.collect_time) <= 6 and t.device_id=" + 
		    		          basic.getId() +" ORDER BY s.collect_time ASC";
		    		Sql sql = Sqls.create(sqls);
		    		sql.setCallback(new SqlCallback() {
						
						@Override
						public Object invoke(Connection conn, ResultSet rs, Sql sql)
								throws SQLException {
						List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
						      while(rs.next()){
						    	  HashMap  map = new HashMap<>();
						    	  map.put("depth", rs.getString("depth"));
						    	  map.put("collect_time",rs.getString("collect_time") );
						    	  map.put("temp", rs.getString("temp"));
						    	  map.put("water", rs.getString("water"));
						    	  list.add(map);
						      }	
						return list;
						}
					}) ;
		    		
		    		dao.execute(sql);
		    		List<HashMap> senTimes_list2 = sql.getList(HashMap.class); 
		    		 if (senTimes_list2.size() > 0) {
		    			 
		    			 
		    	          map2.put("depthes", senTimes_list2);
		    	        }
		    		    map2.put("device_id", basic.getId());
		    	        map2.put("longitude", basic.getLongitude());
		    	        map2.put("latitude",  basic.getLatitude());
		    	        list1.add(map2);
		    	        map2=null;
		    	}
		    	 map.put("senorsEntity", list1);
		    }
		    else {
		        map.put("state", Integer.valueOf(1));
		      }
		    JSONObject ob = new JSONObject();
		    String json = JSON.Encode(map);
		    System.out.println(json); 
		    
		    return  json ;
	}
}

