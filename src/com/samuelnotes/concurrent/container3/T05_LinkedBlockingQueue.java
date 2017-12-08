package com.samuelnotes.concurrent.container3;


import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class T05_LinkedBlockingQueue {
	/// 无界阻塞队列
	static BlockingQueue<String> strs = new LinkedBlockingQueue<String>();

	static Random r = new Random();

	public static void main(String[] args) {
		new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						strs.put("a" + i); // 如果满了，就会等待
						TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "p1").start();

		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {

				public void run() {
					for (;;) {
						try {
							System.out.println(Thread.currentThread().getName()
									+ " take -" + strs.take()); // 如果空了，就会等待
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}, "c" + i).start();

		}
	}
}
