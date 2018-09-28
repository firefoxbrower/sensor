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

public class GetLand2 {

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
	 // 主水泵
		 // 主水泵 设备
		 // waterdevice_extendinfo info device_type 1 是水泵 
			String sql_pump="SELECT d.device_type AS pum_style, d.device_code AS device_code, d.device_id AS device_id, d.state AS state, d.pump_state AS pum_state, info.is_full_speed AS is_full_speed, info.worktime_len AS worktime_len, info.resttime_len AS resttime_len, info.start_type AS start_type, info.stop_type AS stop_type, info.work_state AS work_state, alarm.voltage1 AS voltage1, alarm.current1 AS current1, alarm.voltage2 AS voltage2, alarm.current2 AS current2, alarm.voltage3 AS voltage3, alarm.current3 AS current3, COUNT(*) FROM waterdevice_extendinfo info, wartering_device d, waterdevice_workinfo_alarm alarm,( SELECT DISTINCT d.device_code, MAX(info.run_time) AS run_time FROM waterdevice_extendinfo info, wartering_device d WHERE d.device_code = info.device_code AND info.device_type = 1 GROUP BY info.device_code ORDER BY info.device_code ASC) t WHERE d.device_code = info.device_code AND alarm.device_code = d.device_code AND info.device_type = 1 AND t.device_code = d.device_code AND info.run_time = t.run_time GROUP BY info.device_code ORDER BY info.device_code ASC; ";
			 Sql sql = Sqls.create(sql_pump);
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
		  		    System.out.println(list_sprinkler ); 
            // System.out.println(json);
	 }
}
