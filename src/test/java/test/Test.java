package test;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bytebuddy.asm.Advice.This;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.impl.PropertiesProxy;



public class Test {
	
	
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
 
	public List getSql(Dao dao , String query){
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
		dao.execute(reSql);
		return reSql.getList(HashMap.class);
		
	}
 
	@org.junit.Test
	public void Test() {
		
		   // 创建一个数据源
	    SimpleDataSource dataSource = new SimpleDataSource();
	    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/nutz_sensor");
	    dataSource.setUsername("root");
	    dataSource.setPassword("123");
	    // 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
	    Dao dao = new NutDao(dataSource);
	    // 地块信息
	      String sql_land= "SELECT DISTINCT land.field_id, land.land_id, wartering_device.device_type, wartering_device.device_code  as device_code, wartering_device.device_id , land.sub_device_code, land.sub_device_id, land.land_area, land.land_degree, land.land_pump_type , wartering_device.lon, wartering_device.lat, COUNT(*) FROM land, wartering_device WHERE 	land.field_id =1 AND land.land_id =2 GROUP BY land.field_id,land.land_id ;";
	      List list=this.getSql(dao, sql_land);
	  System.out.println( list);
		
	} 

}
