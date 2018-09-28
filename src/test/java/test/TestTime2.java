package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javassist.expr.NewArray;

import org.junit.Test;

public class TestTime2 {

	@Test
	public void test() throws ParseException{
		String str = "1500494400000";
		Long lon = new Long(str);
		//时间戳转化为Sting或Date  
	    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	
	    String d = format.format(lon);  
	 String str2 ="2017-07-20 04:00:00";
	 Date date = format.parse(str2); 
	    System.out.println(date);
		
	}
}
