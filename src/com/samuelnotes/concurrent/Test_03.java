package com.samuelnotes.concurrent;


/**
 * 简单同步问题 用 synchronized 来解决线程同步问题
 * 
 * @author samuelwang
 */
public class Test_03 implements Runnable {

	private int count = 10;

	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = "
				+ count);
	}

	public static void main(String[] args) {
		Test_03 t = new Test_03();
		for (int i = 0; i < 5; i++) {
			new Thread(t, "T" + i).start();
		}
	}
	

}
