package test.query;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.nutz.json.Json;

import com.sensor.bean.User;
import com.sensor.utils.JSON;
import com.sensor.utils.SunHaoSqlCallback;

public class QueryGetDevice {

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
	     //运行状态
	    String sqls = "SELECT n.field_id, n.land_id, n.device_code, n.pump_state as pump_state, n.run_state, m.pump_state as sub_pump_state , n.lon, n.lat, n.device_type FROM wartering_device n LEFT JOIN ( SELECT pump_state, land_id, field_id FROM wartering_device WHERE device_type = 2 ) m ON m.land_id = n.land_id AND m.field_id = n.field_id WHERE n.device_type = 1 and (n.pump_state>0 or n.run_state>0 or m.pump_state>0) ;";

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
		    String sql3 = "SELECT DISTINCT  d.field_id , d.land_id  ,  d.lon ,d.lat , d.device_type , d.state as communication    FROM `wartering_device` d , `waterdevice_workinfo`  w  where d.device_code=w.device_code  and d.state=1  ";
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

	}
}
