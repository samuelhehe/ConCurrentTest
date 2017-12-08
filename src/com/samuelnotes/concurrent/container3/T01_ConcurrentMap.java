package com.samuelnotes.concurrent.container3;

/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913 
 * http://www.educity.cn/java/498061.html
 * 阅读concurrentskiplistmap
 */

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {
	public static void main(String[] args) {
		// 分段锁
		// Map<String, String> map = new ConcurrentHashMap<>();

		final Map<String, String> map = new ConcurrentSkipListMap<String, String>(); // 高并发并且排序

		// Map<String, String> map = new Hashtable<>();
		// Map<String, String> map = new HashMap<>();
		// //Collections.synchronizedXXX
		// TreeMap
		final Random r = new Random();
		Thread[] ths = new Thread[100];
		final CountDownLatch latch = new CountDownLatch(ths.length);
		long start = System.currentTimeMillis();
		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(new Runnable() {

				public void run() {
					for (int j = 0; j < 10000; j++)
						map.put("a" + r.nextInt(100000),
								"a" + r.nextInt(100000));
					latch.countDown();
				}
			});
		}

		List<Thread> asList = Arrays.asList(ths);
		for (Thread t : asList) {
			t.start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
