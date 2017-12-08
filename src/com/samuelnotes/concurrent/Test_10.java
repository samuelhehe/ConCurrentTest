package com.samuelnotes.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * 运行下面的程序，并分析结果
 * 
 */
public class Test_10 {

	volatile int count = 0;

	synchronized void m() {
		for (int i = 0; i < 10000; i++)
			count++;
	}

	public static void main(String[] args) {
		final Test_10 t = new Test_10();
		List<Thread> threads = new ArrayList<Thread>();
		// / 10 * 10000 = 100000 结果却只有51490(每次不固定)
		// /
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(new Runnable() {
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
