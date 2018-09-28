/*package test.building;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

import com.sensor.bean.Device;



public class Test {

	 public void test(String id){
		// 创建一个数据源
		    SimpleDataSource dataSource = new SimpleDataSource();
		
		    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/nutz_sensor");
		    dataSource.setUsername("root");
		    dataSource.setPassword("123");
			// 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
		    Dao dao = new NutDao(dataSource);
    	 Device device=deviceService.fetch(id);
    	 if(device!=null){
    	if(device.isHasChildren()){
    		deviceService.delete(id);
    		List<Device> list =deviceService.query(Cnd.NEW().where("parentId","=",id));
    		for(int i=0;i<list.size();i++){
    			deviceService.delete(new Long(list.get(i).getId()));
    			test(new Long(list.get(i).getId()).toString());
    		}
    	}else{
    		deviceService.delete(id);
    	}
    	};
    }
	@org.junit.Test
	public void test(){
		
		// 创建一个数据源
	    SimpleDataSource dataSource = new SimpleDataSource();
	
	    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/nutz_sensor");
	    dataSource.setUsername("root");
	    dataSource.setPassword("123");
		// 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
	    Dao dao = new NutDao(dataSource);
		
	}
}
*/