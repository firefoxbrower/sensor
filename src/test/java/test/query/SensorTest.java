package test.query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.Daos;
import com.sensor.bean.Sensor_Basic;
import com.sensor.utils.JSON;


public class SensorTest {

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
		    // 查询全部记录
		    
		    
		 // 查询单条数据
		    //Cnd cnd = Cnd.NEW();
		    Condition c = Cnd.where("id","=","3018");
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
		    System.out.println(list.size());

		 
	}
}
