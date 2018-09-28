package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TestTime {

	@Test
	public void test(){
		String timeStr = "2016-03-30 09:21:56";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Date date = null;
	       try {
	           date = format.parse(timeStr);
	       System.out.println(date.toLocaleString().split(" ")[0]);//切割掉不要的时分秒数据
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	}
	 
}

