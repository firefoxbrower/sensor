package test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sound.midi.MidiDevice.Info;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.Modifier;
import org.junit.Test;
import org.nutz.ioc.impl.PropertiesProxy;

import com.sensor.infoPro.DynamicCreateObject;
import com.sensor.infoPro.InfoPro;



public class Reflect_Pro2 {

	
	/**
	 * 从info.properties 中获取数据形成map结构
	 * */
	public Map getInfo(){
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
		 return map ;
	}
	/**利用Java字节码技术动态生成类*/
	@Test
	public void test() throws Exception{
		
		 // 利用javassist 从 info.properties 创建一个 InfoPro的类
		 String className = "com.sensor.infoPro.InfoPro" ;
		 
		    
		    
		    DynamicCreateObject  dco = new DynamicCreateObject();          
	        Object student1 = null, team = null;
	        Map fieldMap =this.getInfo();//属性-取值map
	       
	        student1 = dco.addField("Student",fieldMap);//创建一个名称为Student的类
	        Class c = Class.forName("Student");
	        Object s1 = c.newInstance();//创建Student类的对象
	        Object s2 = c.newInstance();
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
			 dco.setFieldValue(s1,key,value);
		    }
	        
	       /* dco.setFieldValue(s1,"name"," xiao ming ");//创建对象s1赋值
	        dco.setFieldValue(s2,"name","xiao zhang");*/
	        fieldMap.clear();
	        List<Object> students = new ArrayList<Object>();
	        students.add(s1);
	        students.add(s2);
	        fieldMap.put("students",students);
	        team = dco.addField("Team",fieldMap);// //创建一个名称为Team的类
	        Field[] fields = team.getClass().getDeclaredFields();
	        if(fields != null){
	           for(Field field:fields)
	         System.out.println(field.getName()+"=" +dco.getFieldValue(team,field.getName()));
	        }
	  

	        
	}
}
	
