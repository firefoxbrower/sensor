package test_collections.testSet;

import java.util.HashSet;

import org.junit.Test;


/**
 *  HashSet的存储机制：
 
    HashSet的构造器： HashSet(int initialCapacity , float loadFactor)

      -initialCapcity: 控制底层数组的长度。默认值为16
	   如果传入数组长度不是2的N次方，HashSet会自动将它扩展到2个N次方。
	   
	  -loadFactor（0.75）： 当HashSet【感觉到底层快满】0.75的时候，创建新的数组是原来的

           两倍的数组，原来的数组就变成垃圾--并且要把原有数组的元素复制到新数组中。专业说法叫"rehash(重hash)"
	  
	  当loadFactory 为0.75时，75%的桶已满时，HashSet会分配新的数组。
	  当loadFactory为0.1时，1%的桶已满时，HashSet会分配新的数组。
	  当loadFactory为1时，100%的桶已满时，HashSet会分配新的数组。
	  
	  loadFactory越小，越耗内存， loadFactor越大，性能越低。
	  
	  --  loadFactory越大，代表当前数组很满时，才去"重hash"，
	     数组越慢，就越有可能出现链表。
	     
	     HashSet存入的机制：
          (1)  当有元素加进来时，HashSet会调用该对象的hashCode()方法，得到一个int值。
		  (2)  根据hashCode()返回的int值，计算出它在[底层数组]的存储位置(数组中索引)。
		  (3)  如果要加入的位置为空的，直接放入即可。
		  (4)  如果要加入的位置已经有元素，此处就会形成"链表"。
		  
        HashSet取元素的机制：
         (1)  当要取一个元素时，HashSet会调用该对象的hashCode()方法，得到一个int值。
		 (2)  根据hashCode()返回的int值，计算出它在[底层数组]的存储位置(数组中索引)。
		 (3)  如果要加入的位置为空的，直接取出即可。
		 (4)  如果该位置有链表，HashSet要"挨个"搜索链表里的元素。
		 
		最理想的情况下，HashSet的性能几乎可以数组匹敌，存、取的性能非常高。 
      
	     A。 两个对象的hashCode()返回值相等。
		 B。 两个对象通过equals比较也返回true。
	 
	 ---这就要求我们自定类的hashCode()与equals()方法是一致的。
	          要求程序重写equals()所用的关键属性，与计算hashCode()所用的关键属性要相同。
 * 
 * 
 * 
 * 
 * */

class A {
	 
	 private int  count ;
	 
	 private String name ;
	 
	 public A(int count , String name){
		 
		 this.count = count ;
		 
		 this.name  = name ;
	 }
	 public boolean equals(Object obj){
		 
		 // 传入的对象相同
		 if(this == obj){
			 
			 return true ;
		 }
		   // 传入对象类型一样并且不为空
		 if(obj != null && obj.getClass()== A.class){
			 
			 A targetObj = (A) obj ;
			 // 如果两个对象的关键属性相等。
			 if(this.count == targetObj.count && this.name == targetObj.name){
				 return true ;
			 }
		 }
		return false;
		 
	 }
	 // 当我们要把元素存入"hash表"(HashSet/HashMap)
	 // 此时该元素就应该重写hashCode(count 、 name)方法
	 public  int hashCode(){
		  // hashCode需要根据关键属性(name，count)属性来计算。
		 return name.hashCode()*7 + count ;
	 }
}


public class TestHashSet {
	
	// 底层数组自动扩展
	public void test1(){
		
    HashSet <String> set2 = new HashSet<>(3);// 底层数组自动会扩展到4
		
		set2.add("a");
		set2.add("b");
		set2.add("c");
		set2.add("d");
		set2.add("a");
		System.out.println(set2);
	}
	
	// 自定义  什么样的元素为重复元素的规则 添加到HashSet
	public void test2(){
		HashSet <String> set2 = new HashSet<>(3);// 底层数组自动会扩展到4
	     
		 HashSet<A> set = new HashSet<>();
		 
		 set.add(new A(2 , "sun"));
		 set.add(new A(2 ,"bai"));
		 set.add(new A(3 ,"bai"));
		 set.add(new A(2 ,"bai")); //它与第二个元素相等。
		 System.out.println(set.size());
		
	}
	

	@Test
	public void  test(){
		
      // this.test1();
		this.test2();
	}
	
	
}
