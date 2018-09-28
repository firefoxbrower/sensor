package test_collections.testMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * /**
 * 进入到HashSet源码中我们发现，所有数据存储在
 * private transient HashMap<E,Object> map;

private static final Object PRESENT = new Object();

HashMap 只考虑key的存储，它对key的存储的机制与HashSet机制几乎相同

意思就是HashSet的集合其实就是HashMap的key的集合，然后HashMap的val默认都是PRESENT。HashMap的定义即是key不重复的集合。使用HashMap实现，这样HashSet就不需要再实现一遍。

所以所有的add，remove等操作其实都是HashMap的add、remove操作。遍历操作其实就是HashMap的keySet的遍历
 * 
 * */

// HashMap 只考虑key的存储， 它对key的存储的机制与HashSet机制几乎相同

/*Set实现	 使用场景	                                                                                   数据结构
  HashSet	无序的、无重复的数据集合	                                                     基于HashMap
LinkedSet	维护次序的HashSet	                                                              基于LinkedHashMap
TreeSet	       保持元素大小次序的集合 元素需要实现Comparable接口	 基于TreeMap*/
public class TestHashMap {

	/**三种遍历map的方法 1、Set<K> keySet()
	 * 获取到所有key的Set集合后，由于Set是Collection类型的，所以可以通过Iterator去遍历所有的key，然后再通过get方法获取value
	 */
	public void test1(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("01", "zhangsan");
		map.put("02", "lisi");
		map.put("03", "wangwu");

		Set<String> keySet = map.keySet();//先获取map集合的所有键的Set集合
		Iterator<String> it = keySet.iterator();//有了Set集合，就可以获取其迭代器。

		while(it.hasNext()) {
		     String key = it.next();
		      String value = map.get(key);//有了键可以通过map集合的get方法获取其对应的值。
		     System.out.println("key: "+key+"-->value: "+value);//获得key和value值
		}
		
	};
	/**
	 * 三种遍历map的方法 2、 Collection<V> values()
	 * 直接获取values的集合，无法再获取到key。所以如果只需要value的场景可以用这个方法。获取到后使用Iterator去遍历所有的value
	 * */ 
	public void test2(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("01", "zhangsan");
		map.put("02", "lisi");
		map.put("03", "wangwu");

		Collection<String> collection = map.values();//返回值是个值的Collection集合
		System.out.println(collection);

		
		} ;
		
	/**
	 * 三种遍历map的方法 3、Set< Map.Entry< K, V>> entrySet()
	 * 是将整个Entry对象作为元素返回所有的数据。然后遍历Entry，分别再通过getKey和getValue获取key和value
	 * */
	public void test3(){
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("01", "zhangsan");
		map.put("02", "lisi");
		map.put("03", "wangwu");

		//通过entrySet()方法将map集合中的映射关系取出（这个关系就是Map.Entry类型）
		Set<Map.Entry<String, String>> entrySet = map.entrySet();
		//将关系集合entrySet进行迭代，存放到迭代器中                
		Iterator<Map.Entry<String, String>> it = entrySet.iterator();

		while(it.hasNext()) {
		     Map.Entry<String, String> me = it.next();//获取Map.Entry关系对象me
		      String key = me.getKey();//通过关系对象获取key
		      String value = me.getValue();//通过关系对象获取value
		      System.out.println("Map.Entry关系对象me中key-value:"+key+"-"+value);
		}


	};
	
	@Test
	public void test(){
		
		//this.test1();
        //this.test2();
        this.test3();
		
	};
}
