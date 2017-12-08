package com.samuelnotes.concurrent;



import java.util.Arrays;
import java.util.List;

/**
 *  线程安全的单例模式：
 * 
 * 阅读文章：http://www.cnblogs.com/xudong-bupt/p/3433643.html
 * 
 * 更好的是采用下面的方式，既不用加锁，也能实现懒加载
 * 
 * @author samuelwang
 *
 */
public class Singleton {

	private Singleton() {
		System.out.println("single");
	}

	private static class Inner {
		private static Singleton s = new Singleton();
	}

	public static Singleton getSingle() {
		return Inner.s;
	}

	public static void main(String[] args) {
		Thread[] ths = new Thread[200];
		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(new Runnable() {

				public void run() {
					Singleton.getSingle();
				}
			});
		}

		List<Thread> asList = Arrays.asList(ths);
		for (Thread t : asList) {
			t.start();
		}
	}
}
