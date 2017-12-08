package com.samuelnotes.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象o，如果o的属性发生改变，不影响锁的使用 
 * 但是如果o变成另外一个对象，则锁定的对象发生改变 应该避免将锁定对象的引用变成另外的对象
 * 
 * 同时注意： 
 * 不要以字符串常量作为锁定对象
 * 在下面的例子中，m1和m2其实锁定的是同一个对象 指向的空间都是堆空间的同一块地址
 * 这种情况还会发生比较诡异的现象，比如你用到了一个类库，在该类库中代码锁定了字符串“Hello”，
 * 但是你读不到源码，所以你在自己的代码中也锁定了"Hello",这时候就有可能发生非常诡异的死锁阻塞，
 * 因为你的程序和你用到的类库不经意间使用了同一把锁
 * 
 * jetty 曾出现过这样的问题
 * 
 * 
 * @author samuelwang
 * 
 */
public class Test_13 {
	
	
	String s1 = "Hello";
	String s2 = "Hello";

	void m1() {
		synchronized(s1) {
			
		}
	}
	
	void m2() {
		synchronized(s2) {
			
		}
	}

	
	
	Object o = new Object();

	void m() {
		synchronized (o) {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}
	}

	public static void main(String[] args) {
		final Test_13 t = new Test_13();
		// 启动第一个线程
		new Thread(new Runnable() {
			public void run() {
				t.m();
			}
		}, "t1").start();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 创建第二个线程
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				t.m();
			}
		}, "t2");

		// t.o = new Object(); // 锁对象发生改变，所以t2线程得以执行，如果注释掉这句话，线程2将永远得不到执行机会
		// / 相当于线程2中的锁对象是在堆空间中新的对象。
		t2.start();

	}
}
