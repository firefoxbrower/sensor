package test_collections.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 *    操作集合的工具类： Collections

 Arrays         -- 操作数组。
 Objects        -- 操作对象。
 Collections    -- 操作集合。
	
	synchronizedXxx -- 把原有的集合，包装成线程安全的集合。
	      
 shuffle(List<?> list) -- 将list集合方法进行随机排列。
 */

public class TestCollections {

	@Test
	public void test(){
		
		List <String>list = new ArrayList<>();
		list.add("张三");
		list.add("李四");
		list.add("王五");
		System.out.println(list);
		// 反转
		Collections.reverse(list);
		System.out.println(list);
		// 交换位置
		Collections.swap(list, 1, 2);
		System.out.println(list);
		
	};
}
