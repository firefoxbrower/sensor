package test_collections.testList;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;
/**
 * Deque集合
 
  ---- 它是功能被限制了的线性表。
  (线性表分两种，一种是以数组的线性表叫线性表，一种以链表的实现叫链表。链表只是线性表的特例。线性表不等于链表)
  
      即是队列 ， 又是栈。
	
	
 * */
public class TestDeque {

	//堆是指程序运行时申请的动态内存，而栈只是指一种使用堆的方法(即先进后出)。
	// 把Deque当成栈用
	/**
	 * ①栈（stack）又名堆栈，它是一种运算受限的线性表。其限制是仅允许在表的一端进行插入和删除运算。这一端被称为栈顶，相对地，把另一端称为栈底。
       ②栈就是一个桶，后放进去的先拿出来，它下面本来有的东西要等它出来之后才能出来（先进后出）
	 * */
	public  void test1(){
		 //把Deque当成栈用
		 Deque  <String> deque= new ArrayDeque();
		 
		 //压入四个元素
		 deque.push("孙悟空1");
		 
		 deque.push("白骨精2");
		 deque.push("蜘蛛精3");
		 deque.push("牛魔王4");
		 //deque.push("牛魔王4");
		 System.out.println(deque.pop());
		 System.out.println(deque.pop());
		 System.out.println(deque.pop());
	     //peek 只是访问栈顶元素，并不弹出
	     System.out.println(deque.peek()+"\n用peek方法只是访问栈顶元素，并不弹出 。孙悟空获取到了，但是并没有移除栈顶，并没有弹出,所以栈的元素为:"+deque.size()+",而不是0");
	     
	}
	/**
     ④队列采用的FIFO(first in first out)，新元素（等待进入队列的元素）总是被插入到链表的尾部，而读取的时候总是从链表的头部开始读取。每次读取一个元素，释放一个元素。
            所谓的动态创建，动态释放。因而也不存在溢出等问题。由于链表由结构体间接而成，遍历也方便。（先进先出）
	 * */
	public  void test2(){
		//把Deque当成队用
		 Deque  <String> deque= new ArrayDeque();
		 
		 //从队列尾部，添加4个元素
		 deque.offer("孙悟空1");
		 deque.offer("白骨精2");
		 deque.offer("蜘蛛精3");
		 deque.offer("牛魔王4");
		  //从队列头部取出元素
		 System.out.println("取出:"+deque.poll());
		 System.out.println("取出："+deque.poll());
		  //peek 只是访问队顶元素，并不弹出
		 System.out.println("访问队顶，并不弹出:"+deque.peek());
	     System.out.println(deque);
	}
	
	@Test
	public void test(){
		 this.test1();
		//this.test2();
		
	};
}
