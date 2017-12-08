package com.samuelnotes.concurrent;

/**
 * 同步和非同步方法是否可以同时调用?
 * 肯定可以的，互不干扰。 两个不同的线程，子线程sleep，并不是在主线程sleep了，所以第二个线程也会启动，执行。 
 * 
 * @author samuelwang
 *
 */
public class Test_05 {

	public synchronized void m1() {
		System.out.println(Thread.currentThread().getName() + " m1 start...");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m1 end");
	}

	public void m2() {
		System.out.println(Thread.currentThread().getName() + " m2  start");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m2  end");
	}

	public static void main(String[] args) {
		final Test_05 t = new Test_05();
		
		new Thread(new Runnable() {

			public void run() {
				t.m1();
			}
			
		},"t1").start();
		
		new Thread(new Runnable() {

			public void run() {
				t.m2();
			}
			
		},"t2").start();
	}
}
