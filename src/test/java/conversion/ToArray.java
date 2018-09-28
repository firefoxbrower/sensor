package conversion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ToArray {

	public static Object[] arr ;
	
	
	// set 转成 数组
	public static Object[] toArray(Collection con){
		if(arr==null){
			arr =new Object[con.size()];
		}
		 arr= con.toArray();
		return arr;
	}
	
	// 原打算是分别利用 Set 和list分别转换成数组。最后想到Collection有公用方法toArray。就统一用Collection进行转换了 这样一个方法 通用使用set和list两种接口
	@Test
	public void test(){
		   List <String> list = new ArrayList<>();
			
			list.add("孙悟空");
			list.add("白骨精");
	        list.add("蜘蛛精");
	        list.add("牛魔王");
	        list.add("牛魔王");
	        System.out.println(Arrays.toString(ToArray.toArray(list)));
	        
	}
}
