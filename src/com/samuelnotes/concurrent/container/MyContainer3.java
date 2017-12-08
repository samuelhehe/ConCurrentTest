package com.samuelnotes.concurrent.container;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，该怎么做呢？
 * 
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 * 
 * 阅读下面的程序，并分析输出结果 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接收到通知而退出 想想这是为什么？
 * 
 * 
 * wait 是释放锁，放弃当前cpu 执行权限 处于挂起状态， t2接到执行权限后，将list size添加至5后，notify 唤醒 t1
 * 但是t1没有执行锁lock ，所以t2结束会在最后打印。
 * 
 * @author samuelwang
 * 
 */
public class MyContainer3 {

	volatile List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		final MyContainer3 c = new MyContainer3();

		final Object lock = new Object();

		new Thread(new Runnable() {
			public void run() {
				synchronized (lock) {
					System.out.println("t2启动");
					if (c.size() != 5) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("t2 结束");
				}
			}
		}, "t2").start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		new Thread(new Runnable() {
			public void run() {
				System.out.println("t1启动");
				synchronized (lock) {
					for (int i = 0; i < 10; i++) {
						c.add(new Object());
						System.out.println("add " + i);

						if (c.size() == 5) {
							lock.notify();
						}

						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, "t1").start();

	}
}
