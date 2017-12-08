package com.samuelnotes.concurrent;

/**
 * synchronized关键字
 * 对某个对象加锁
 * 
 * @author samuelwang
 */
public class Test_01 {

	public static void main(String[] args) {
		
		Test_01 t = new Test_01();
		t.m();
	}

	private int count = 10;
	private Object o = new Object();
	
	public void m() {
		synchronized(o) { // 任何线程要执行下面的代码，必须先拿到o的锁
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}
}
