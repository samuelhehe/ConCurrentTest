package com.samuelnotes.concurrent.container3;

/**
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 * 每次添加或者修改都会复制一份，所以不存在读并发问题。写效率低。
 */
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_CopyOnWriteList {
	public static void main(String[] args) {
		final List<String> lists =
		// new ArrayList<>(); //这个会出并发问题！
		// new Vector();
		new CopyOnWriteArrayList<String>();
		final Random r = new Random();
		Thread[] ths = new Thread[100];

		for (int i = 0; i < ths.length; i++) {
			Runnable task = new Runnable() {
				public void run() {
					for (int i = 0; i < 1000; i++) {
						boolean add = lists.add("a" + r.nextInt(10000));
					}
				}

			};
			ths[i] = new Thread(task);
		}

		runAndComputeTime(ths);

		System.out.println(lists.size());
	}

	static void runAndComputeTime(Thread[] ths) {
		long s1 = System.currentTimeMillis();
		List<Thread> asList = Arrays.asList(ths);
		for (Thread t : asList) {
			t.start();
		}
		for (Thread t : asList) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long s2 = System.currentTimeMillis();
		System.out.println(s2 - s1);

	}
}
