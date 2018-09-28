

import java.util.List;

import org.nutz.ioc.impl.PropertiesProxy;

import com.google.inject.internal.util.Strings;


public class Test {
	
	@org.junit.Test
	public void Test() {
		
		// true 就是utf-8 编码格式
		PropertiesProxy pro = new PropertiesProxy(true);
		pro.setPaths("info.properties");
	   List<String>	 list = pro.getKeys() ;
	 for(int i=0;i<list.size();i++){
		 // 所有的key
		 String key =list.get(i);
		 System.out.println(list.get(i));
		 pro.get(key);
		 // key 所对应value
		 System.out.println(pro.get(key));
	 }
		
		
	} 

}
