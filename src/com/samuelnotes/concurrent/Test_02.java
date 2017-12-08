package com.samuelnotes.concurrent;

/**
 * synchronized关键字 对某个对象加锁
 * @author samuelwang
 */
public class Test_02 {

	public static void main(String[] args) {
		new Test_02().m();
		// 同一种方法的不同写法
		new Test_02().m2();
	}

	private static int count = 100;

	public void m() {
		synchronized (this) { // 任何线程要执行下面的代码，必须先拿到this的锁
			count--;
			System.out.println(Thread.currentThread().getName() + " count = "+ count);
		}
	}

	public synchronized void m2() { // 等同于在方法的代码执行时要synchronized(this) m
		count--;
		System.out.println(Thread.currentThread().getName() + " count = "+ count);
	}
	
	/**
	 * 这里等同于synchronized(**.Test_02.class)
	 * 上锁对象是lang.Class obj 
	 */
	public synchronized static void m3() { 
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	/**
	 * 等效 m3
	 */
	public static void m4() {
		synchronized(Test_02.class) { 
			count --;
		}
	}
	
}
