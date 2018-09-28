package test.query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.sensor.bean.Sensor_Real_Time;
import com.sensor.utils.JSON;


public class SensorTest2 {

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
		    // 查询单条数据
		    //Cnd cnd = Cnd.NEW();
		    Condition c = Cnd.where("id","=",3018);
		    //c.where("id", "=", 3018);
		    List<Sensor_Basic> list = dao.query(Sensor_Basic.class, c);
		    HashMap map = new HashMap();
		    List list1 = new ArrayList();
		   
		    System.out.println(list.size());   
	}
}
