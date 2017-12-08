package com.samuelnotes.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 
 * 解决同样的问题的更高效的方法，使用AtomXXX类
 * 
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 * 
 * @author samuelwang
 *
 */
public class Test_11 {

//	 AtomicInteger extends Number 
	AtomicInteger count = new AtomicInteger(0); 

	/*synchronized*/ void m() { 
		for (int i = 0; i < 10000; i++)
			//if count.get() < 1000
			count.incrementAndGet(); //count++
	}

	public static void main(String[] args) {
		final Test_11 t = new Test_11();
		List<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(new Runnable(){
				public void run() {
					t.m();
				}
			}, "thread-" + i));
		}

		for (Thread th : threads) {
			th.start();
		}
		for (Thread th2 : threads) {
			try {
				th2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(t.count);
	}

}
