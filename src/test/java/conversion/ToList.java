package conversion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

public class ToList {

	public static List list;
	public static List toList(Object [] arr){
		if(list==null){
			list =new ArrayList<>();
		}else{
			list.clear();
		}
		CollectionUtils.addAll(list, arr);   
		
		return list;
	};
	
	public static List toSet(Set<?> set){
		if(list==null){
			list =new ArrayList<>();
		}else{
			list.clear();
		}
		// set集合转成数组，数组转成list
		
	    return	toList(set.toArray());
		
		
	}
	
	@Test
	public void test(){
		// 数组转成set
    	String[] strArray = {"aaa", "bbb","aaa", "ccc"};
    	
    	System.out.println(ToList.toList(strArray));
    	
    	 HashSet <String> set2 = new HashSet<>(3);// 底层数组自动会扩展到4
 		
 		set2.add("a");
 		set2.add("b");
 		set2.add("c");
 		set2.add("d");
 		set2.add("a");
 		System.out.println(ToList.toSet(set2));
    	
	}
}
