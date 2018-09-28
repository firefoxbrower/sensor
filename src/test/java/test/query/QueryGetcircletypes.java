package test.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

public class QueryGetcircletypes {

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

	}
}
