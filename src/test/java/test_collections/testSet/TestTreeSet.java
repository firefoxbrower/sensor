package test_collections.testSet;
import java.util.Comparator;
import java.util.TreeSet;
import org.junit.Test;
/**
 *   使用TreeSet有一个要求：

   要求集合元素必须是可以比较大小。
   
   Java的比较大小有两种方式：
   A --- 自然排序。 所有集合元素实现Comparable接口。
   
                    集合元素实现了Comparable接口后，集合元素本身就是可排序的。
   
   B --- 定制排序。要求创建TreeSet时，提供一个Comparator对象。
                   Comparator对象可负责对元素进行比较大小。
				    集合元素无需实现Comparable接口，集合元素本身是无法排序的。
   
   TreeSet怎么才会认为两个对象是相等的？
      A。 只要两个对象通过compareTo比较返回0， 
	      TreeSet认为它们是相等的。
 * */

class Apple implements Comparable<Apple>{

	  private double weight ;
	  
	  public Apple(double weight){
		  
		  this.weight = weight ;
	  }
	
	  //实现Comparable接口中定义的方法
	@Override
	public int compareTo(Apple o) {
		// 现在认为: 苹果越重，苹果越"大"
		//其实可简化为如下写法
		return this.weight > o.weight ?
				1:this.weight == o.weight ? 0 : -1;
	}
	
	@Override
	public String toString(){
		return "Apple[weight]=" + this.weight;
		
		
	}
	
}

class Bird {

	  private String name ;
	  
	  public Bird(String name){
		  
		  this.name = name ;
	  }
	 
	  public String getName(){
		  return this.name;
	  }
	
	@Override
	public String toString(){
		return "Bird[name]=" + this.name;
		
		
	}
	@Override 
	public boolean equals(Object o){
		return false;
		
	}
	
}

public class TestTreeSet {

	// 使用默认比较大小的元素 加入到TreeSet中
	public void test1(){
		
     TreeSet<Integer> set = new TreeSet();
		
		set.add(100);
		set.add(13);
		set.add(30);
		System.out.println(set);
		
		TreeSet<String> set2 = new TreeSet();
		
		set2.add("ablkc");
		set2.add("Abc");
		set2.add("abc");
		
		// 字符串的大小，就是依次比较每个字符串unicode值。
		System.out.println(set2);
	};
	
	// 使用定制的比较大小规则的元素 加入到TreeSet中
	public void test2(){
      
		TreeSet<Apple> set = new TreeSet();
		
		set.add(new Apple(23));
		set.add(new Apple(267));
		set.add(new Apple(123));
		set.add(new Apple(123));
		System.out.println(set);
		       //向TreeSet中添加了3个元素。
				//但由于3个元素通过compareTo比较都返回0，
				//因此TreeSet认为它们是同一个元素。
		System.out.println("向TreeSet中添加了4个元素。但由于第4个元素通过compareTo比较与第3个元素相同，返回0,\n因此TreeSet认为它们是同一个元素。元素个数只有三个："+set.size());
		
	}
	
	//集合元素本身没有实现Comparable接口 强行加入TreeSet中
	
	public  void test3(){
		

		//TreeSet<Bird> set = new TreeSet<>();
		//如果集合元素本身没有实现Comparable接口。
		// 那就要求创建TreeSet时传入一个Comparator对象。
		// 此处创建一个Comparator的匿名实现类，而且会立即得到该匿名内部类的实例
		TreeSet<Bird> set = new TreeSet<Bird>(new Comparator<Bird>(){
			
			// 如果 o1大于o2 返回 整数， o1等于o2 返回 0 ， o1小于o2 返回 负数
			public  int compare(Bird o1, Bird o2){
				
			return  o1.getName().equals(o2.getName()) ? 
					0 : o1.getName().compareTo(o2.getName())> 0?
					1 : -1 ;
			} ;
		});
		
		set.add(new Bird("bird"));
		set.add(new Bird("airds")) ;
		set.add(new Bird("birds")) ;
		System.out.println(set);
	};
	
	@Test
	public  void test(){
	    //this.test1();
		//this.test2();
		this.test3();
	};
}
