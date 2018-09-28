package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

import com.sensor.bean.Sensor_Real_Time;

public class TestInser {

	 @Test
	public void test() throws ParseException{
		Double temp= 26.5 ;
		String str = "1500494400000";
		Long lon = new Long(str);
		//时间戳转化为Sting或Date  
	    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	
	    String d = format.format(lon);  
	 String str2 ="2017-07-20 04:00:00";
	 Date date = format.parse(str2); 
	    System.out.println(date);
	    // 创建一个数据源
	    SimpleDataSource dataSource = new SimpleDataSource();
	    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/nutz_sensor");
	    dataSource.setUsername("root");
	    dataSource.setPassword("123");
	    // 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
	    Dao dao = new NutDao(dataSource);
	    Sensor_Real_Time real_Time = new Sensor_Real_Time() ;
	    real_Time.setDevice_id(102);
	    real_Time.setCollect_time(date);
    	real_Time.setDepth(15+0.0);
    	real_Time.setTemp(temp);
    	real_Time.setWater(50.0);
    	dao.insert(real_Time);
    	 Sensor_Real_Time real_Time2 = new Sensor_Real_Time() ;
 	    real_Time2.setDevice_id(103);
 	    real_Time2.setCollect_time(date);
     	real_Time2.setDepth(15+0.0);
     	real_Time2.setTemp(temp);
     	real_Time2.setWater(50.0);
     	dao.insert(real_Time2);
	}
}
