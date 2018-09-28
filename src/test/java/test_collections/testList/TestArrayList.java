package test_collections.testList;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * ArrayList   ---基于数组实现。
	
	LinkedList  ---基于链表实现。
	(线性表分两种，一种是以数组的线性表叫线性表，一种以链表的实现叫链表。链表只是线性表的特例。线性表不等于链表)
 *  List集合
 
   它提供了大量的 “根据索引”来存 、取元素的方法。
   
   由于List可以“根据索引”来存、 取元素的方法。
   所以它多了一个遍历集合元素的方法。
   
   ArrayList与Vector的存储机制:
    
    它们底层完全基于数组————它对元素的存储机制，完全基于数组的，
    因此性能非常快。
 
   ArrayList与Vector的区别： Vector是一个JDK 1.0就有的集合。
	 
	   从JDK 1.2 之后，Sun重写设计了ArrayList，代替了原来的Vector。
	   ArrayList是线程不安全的，Vector是线程安全的。
	   ---ArrayList的性能比Vector要好。
	   即使在多线程环境下，可以使用Collections把ArrayList转换成线程安全的。
	   
	    LinkedList
                  即是线性表，又是队列，还是栈。
		
		LinkedList底层是基于链表实现的。通常认为它的性能比上ArrayList。
		
	    ArrayList：由于可以根据底层数组的索引存、取元素，所以性能非常快。
	          当插入元素、删除元素，后面的所有元素都要跟着"整体搬家"。

		LinkedList：由于底层采用了链表，双向链表，来存储元素，因此根据索引来存储
		             元素，较慢。当插入元素、删除元素，无需"整体搬家"，所以性能比较快。	
 * 
 * 
 * */
public class TestArrayList {

	
	// List 中“根据索引”存 、 取元素的方法
	@Test
	public  void test(){
     List <String> list = new ArrayList<>();
		
		list.add("孙悟空");
		list.add("白骨精");
        list.add("蜘蛛精");
        list.add("牛魔王");
        
        System.out.println(list);
        
        list.add("猪八戒");   // 在索引3处插入一个元素
        
        System.out.println(list);
        list.set(3, "唐僧"); //  在索引3处替换一个元素
        
        list.remove(2);    // 删除索引2处的元素
        System.out.println(list);
        
        for(int i = 0 ; i< list.size();i++){
        	
        	System.out.println(list.get(i)+ "  "); // 获取为i处的元素
        }
		
	};
}
