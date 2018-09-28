package test.query;

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

import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.Daos;

import com.sensor.utils.JSON;

public class GetLand {
	
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
 
	 @Test
	 public void test(){
		   // 创建一个数据源
	    SimpleDataSource dataSource = new SimpleDataSource();
	    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/nutz_sensor");
	    dataSource.setUsername("root");
	    dataSource.setPassword("123");
	    // 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
	    Dao dao = new NutDao(dataSource);
	    //目前自动建表
	    Daos.createTablesInPackage(dao, "com.sensor.bean", false); 

  	  String str_sprinkler="SELECT DISTINCT d.device_code, d.device_id, d.device_type, d.state, d.run_state , info.is_full_speed, info.worktime_len, info.resttime_len, info.start_type, info.stop_type , info.work_state, info.run_time AS run_time FROM waterdevice_extendinfo info, wartering_device d, (SELECT DISTINCT d.device_code, MAX(info.run_time) AS run_time FROM waterdevice_extendinfo info, wartering_device d WHERE d.device_code = info.device_code AND info.device_type = 2 GROUP BY info.device_code ORDER BY info.device_code ASC ) t WHERE d.device_code = info.device_code AND info.run_time = t.run_time AND info.device_code = t.device_code AND info.device_type = 2 GROUP BY info.device_code ORDER BY info.device_code ASC;";
		   Sql sql = Sqls.create(str_sprinkler);
		   
			//sql.setCallback(new SunHaoSqlCallback(sql)) ;
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
			 List list_sprinkler =sql.getList(HashMap.class);
			 HashMap return_map = new HashMap();
			
			
			 // 主水泵
			 // 主水泵 设备
			 // waterdevice_extendinfo info device_type 1 是水泵 
				String sql_pump="SELECT d.device_type AS pum_style, d.device_code AS device_code, d.device_id AS device_id, d.state AS state, d.pump_state AS pum_state, info.is_full_speed AS is_full_speed, info.worktime_len AS worktime_len, info.resttime_len AS resttime_len, info.start_type AS start_type, info.stop_type AS stop_type, info.work_state AS work_state, alarm.voltage1 AS voltage1, alarm.current1 AS current1, alarm.voltage2 AS voltage2, alarm.current2 AS current2, alarm.voltage3 AS voltage3, alarm.current3 AS current3, COUNT(*) FROM waterdevice_extendinfo info, wartering_device d, waterdevice_workinfo_alarm alarm,( SELECT DISTINCT d.device_code, MAX(info.run_time) AS run_time FROM waterdevice_extendinfo info, wartering_device d WHERE d.device_code = info.device_code AND info.device_type = 1 GROUP BY info.device_code ORDER BY info.device_code ASC) t WHERE d.device_code = info.device_code AND alarm.device_code = d.device_code AND info.device_type = 1 AND t.device_code = d.device_code AND info.run_time = t.run_time GROUP BY info.device_code ORDER BY info.device_code ASC; ";
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
				List list_pump =sql.getList(HashMap.class);
				 
			
	     		   // 地块信息
	     		      String sql_land="SELECT DISTINCT land.field_id, land.land_id, wartering_device.device_type, wartering_device.device_code, wartering_device.device_id , land.sub_device_code, land.sub_device_id, land.land_area, land.land_degree, land.land_pump_type , wartering_device.lon, wartering_device.lat, COUNT(*) FROM land, wartering_device WHERE 	land.field_id = wartering_device.field_id AND land.land_id = wartering_device.land_id  GROUP BY land.field_id,land.land_id ;" ;
	     		     sql = Sqls.create(sql_land);
	   			   
	  				//sql.setCallback(new SunHaoSqlCallback(sql)) ;
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
						             if((Integer)sub_map_sprinker.get("run_state")==0){
						            	 sub_map_sprinker.remove("is_full_speed");
						            	 sub_map_sprinker.put("is_full_speed", 0);
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
	 }
}
