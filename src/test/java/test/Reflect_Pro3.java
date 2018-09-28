package test;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.nutz.ioc.impl.PropertiesProxy;

import com.sensor.infoPro.DynamicCreateObject;






public class Reflect_Pro3 {

	

	 
	@Test
	public void test() throws Exception{
		
		  Map map = new HashMap<>();
			// true 就是utf-8 编码格式
			PropertiesProxy pro = new PropertiesProxy(true);
			pro.setPaths("info.properties");
		    List<String>	 list = pro.getKeys() ;
		    for(int i=0;i<list.size();i++){
			 // 所有的key
			 String key =list.get(i);
			 // key 所对应value
			 String value =pro.get(key);
            
			 map.put(key, value);
		    }

		     // 自定义的动态生成类 ，该类是从Map中key-value中生成任意类型数据类型类  详细用法参考Reflect_Pro2 和   dconamicCreateObject
		    DynamicCreateObject dco = new DynamicCreateObject();
		    
		    // 增加属性 ， 参数一是自定义类，参数二为map数据key-value对  key作为属性 ，vaule作为真实值赋予
		   Object stu = dco.addField("Student", map) ;
		    
		    Class c = Class.forName("Student");
	        Object s1 = c.newInstance();//创建Student类的对象
	       
			// 查看真实值
	        Field[] fields = stu.getClass().getDeclaredFields();
	        if(fields != null){
	           for(Field field:fields)
	         System.out.println(field.getName()+"=" +dco.getFieldValue(stu,field.getName()));
	        }
		    
	     
	        
	}
}
	
