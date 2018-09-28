package test.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.util.Daos;

import com.sensor.bean.Sensor_Basic_Details;

public class QueryDetails {

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
		    String sn="16032500028670";
		    int id =3018;
		    Sensor_Basic_Details  details = new Sensor_Basic_Details();
		
		
	}
}
