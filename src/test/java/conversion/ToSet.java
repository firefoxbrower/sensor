package conversion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

/**
 * 转换的目的：set与list转换。比如说list中有重复的元素。想把重复的元素去掉放在set中。
 * 再比如说。set中不能放置重复的元素，想放置重复的元素。就要用list。
 * 还要，数组一旦声明了数组大小。放置发生越界就要用集合容器
 * */
public class ToSet {
	
	public static Set set ;
	
	// 数组转换成Set
	public static Set toSet(Object[] arr){
		if(set==null){
			set =new HashSet<>();
		}else{
			set.clear();
		}
		CollectionUtils.addAll(set, arr);   
		return set ;
	}
	// 数组转换成Set
	public static Set toSet(Set set,Object[] arr){
		if(set==null){
			set =new HashSet<>();
		}else{
			set.clear();
		}
		CollectionUtils.addAll(set, arr);   
		return set ;
	}
	
	// List 转换成Set
	public  static Set  toSet (List list) {
		
		 Object [] arr=  list.toArray();
		set = toSet(arr);
		 
		return set ;
	}
	// List 转换成Set
		public  static Set  ListtoSet (Set set ,List list) {
			if(set==null){
				set =new HashSet<>();
			}else{
				set.clear();
			}
			 set = (Set) Arrays.asList(list);
			return set ;
		}
    @Test
	public void  test(){
    	// 数组转成set
    	String[] strArray = {"aaa", "bbb", "ccc"};
    	Set set2=ToSet.toSet(new LinkedHashSet<> (),strArray);
    	
    	//List 转成 set 重复的去掉 逻辑是：把list转成数组，再把数组转成Set 其中 List list = new ArrayList(new Hashset());// Fixed-size list 
    	// 直接使用list的 Set set = new HashSet(Arrays.asList(array)); 只是把list所有元素当成set中一个元素了。并不是想要的结果
    List <String> list = new ArrayList<>();
		
		list.add("孙悟空");
		list.add("白骨精");
        list.add("蜘蛛精");
        list.add("牛魔王");
        list.add("牛魔王");
        System.out.println(ToSet.toSet(list));
        
    };
}
