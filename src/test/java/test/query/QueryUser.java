package test.query;

import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.util.Daos;
import org.nutz.json.Json;

import com.sensor.bean.User;

public class QueryUser {

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
	/*	User user =new User();
		user.setUsername("admin");
		user.setPassword("dcny8888");
		user.setPhone("12345678911");
		dao.insertOrUpdate(user);*/
		//获取所有
		List<User> list =dao.query(User.class, null);
		
          User user = new User();
          user.setUsername("111");
          user.setPassword("111");
          /**
           * 根据对象的主键(@Id/@Name/@Pk)先查询, 如果存在就更新, 不存在就插入
           * 
           * @param t
           *            对象
           * @param insertFieldFilter
           *            插入时的字段过滤, 可以是null
           * @param updateFieldFilter
           *            更新时的字段过滤,可以是null
           * @return 原对象
           */
         // <T> T insertOrUpdate(T t, FieldFilter insertFieldFilter, FieldFilter updateFieldFilter);
          user.setPhone("122333");
	   dao.insertOrUpdate(user , null , FieldFilter.create(User.class,  "phone|usertype|record_name"));
	 
	  //  System.out.println(map);

	}
}
