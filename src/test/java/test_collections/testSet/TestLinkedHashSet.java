package test_collections.testSet;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

/**
 * 
 *  LinkedHashSet
    HashSet还有一个子类，LinkedHashSet。
          它与HashSet的存储机制相似，
	  但LinkedHashSet额外地维护一个链表，这个链表可以保证LinkedHashSet能记住元素添加顺序。
 * 
 * */
public class TestLinkedHashSet {

	// LinkedHashSet 能够记住元素添加顺序
	 @Test
	public void test(){
		 
		 LinkedHashSet <String> set = new LinkedHashSet<>();
			//HashSet <String> set = new HashSet<>();
		
			set.add("孙悟空");
			set.add("白骨精");
			set.add("牛魔王");
			for(String ele : set){
				System.out.println(ele);
			}
			System.out.println(set);
			 
	 }
	
}
