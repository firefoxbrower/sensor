package test_collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;


/**
 *        Java集合API
	   
	    Java Collection Framework API 
		
		集合 ， 也叫容器类。它的功能，就相当于容器。
		
		Java的集合（容器），它是用来"装对象的"。
		
		容器，应该有哪些方法供你使用？
		
		-把东西丢进容器中
		-把指定的东西"读取"出来，该东西依然在容器里。
		-把指定的"东西"从容器中删除。
		-"挨个"把每个东西数一次。(遍历)
		-把容器"倒空"
		-把A容器中东西，全部倒入B容器中。                    集合 + 集合
		-拿A容器包含的东西，减去B容器中包含的东西。          集合 - 集合
		-拿A容器包含的东西，与B容器中包含的东西 求交集。     集合 ∩ 集合
		
		Set集合，就是Collection。它们的行为完全相似。
		  遍历Set集合， 有两种方式：
		  
		  A。迭代器
		  
		  B。foreach循环
		  
		  注意点：a ，b集合取得补集时，先获取两个元素的交集，b集合遍历交集时，逐个删除b集合中交集的元素。这样就获得补集元素
 * 
 * */
public class TestCollection {

	 // 集合添加元素 
	public void test1(){
		//没有泛型限制的集合
		Collection c1 = new HashSet();
		c1.add("Hello");
		/**
		 * 本来20不是对象，无法装入集合，但由JDK1.5提供了自动装箱
		 * 自动装箱会将20包装成对象，实际上可以装入。
		 * */
		c1.add(20); 
		// 添加一个runtime 对象
		c1.add(Runtime.getRuntime());
		System.out.println(c1);
	}
	
	 // 集合添加元素 使用泛型
	public Collection<String> test2(){
		// 加入泛型限制的集合，意味着该集合中只能装"指定类型"的对象
		// Collection<String> c2 = new Hash<String>() ; jdk1.7之前。
		Collection <String> c2 = new HashSet<>(); //jdk 1.7之后: 菱形语法
		//加入泛型限制的集合，意味着该集合中只能装"指定类型"的对象。
		//Collection<String> c = new HashSet<>();
				c2.add("孙悟空");
				c2.add("白骨精");
				c2.add("牛魔王");
				//System.out.println(c2.contains("白骨精"));
				//System.out.println(c2);
				return c2;
	}
	
	// 集合元素的遍历
	public void test3(){
		
		// 加入泛型限制的集合，意味着该集合中只能装"指定类型"的对象
				// Collection<String> c2 = new Hash<String>() ; jdk1.7之前。
				Collection <String> c3 =this.test2(); //jdk 1.7之后: 菱形语法

						
						// 遍历Set集合 ，有两种方式。
						// A foreach循环
						
						for(String e : c3){
							
							System.out.println("foreach遍历："+e);
						}
					
						// B 用迭代器遍历
						Iterator <String> it = c3.iterator() ;
						
						c3.contains("白骨精");
						System.out.println(c3.contains("白骨精"));
						while(it.hasNext()){
							// 取出下一个
							String str =  it.next();
							 System.out.println("迭代器 遍历"+str);
						
						}

						
		
	}
	

	public void test4(){
		HashSet <String>  c2 = new HashSet<>();
		c2.add("孙悟空");
		c2.add("白骨精");
		c2.add("牛魔王");
		HashSet <String> c3 = new HashSet<>();
		c3.add("孙悟空");
		c3.add("白骨精");
		c3.add("刘备");
		
		// 取得交集
	  c2.retainAll(c3);  // c2∩c3 
	   System.out.println("c2交集c3 :"+c2);
		
		// 取得c2在c3的补集 B - A = { x| x∈B且x∉A
	    //用c2 - c3 直接移除元素没有效果  c2.remove(c3);
	    //用迭代器遍历
		Iterator <String> it = c2.iterator() ;
		while(it.hasNext()){
			// 取出下一个
			String str =  it.next();
			 System.out.println("迭代器 遍历"+str);
			 c3.remove(str);
		}
		System.out.println("c2在c3的补集 :"+c3);
		
		// 同理取得 并集
		c2.addAll(c3);
		System.out.println(c2);
	}
	@Test
	 public void test(){
		
		//this.test1();
		//this.test2();
		this.test3();
		//this.test4();
		   
	 }
}
