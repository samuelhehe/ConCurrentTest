package com.samuelnotes.concurrent.threadlocal;

/**
 * ThreadLocal线程局部变量
 * 
 */
import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {
	volatile static Person p = new Person();

	public static void main(String[] args) {

		new Thread(new Runnable() {

			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(p.name);
			}
		}).start();

		new Thread(new Runnable() {

			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				p.name = "lisi";
			}
		}).start();
	}
}

class Person {
	String name = "zhangsan";
}
