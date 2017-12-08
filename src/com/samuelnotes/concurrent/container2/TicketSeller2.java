package com.samuelnotes.concurrent.container2;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？
 *  
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 * 
 */
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {
	static Vector<String> tickets = new Vector<String>();

	static {
		for (int i = 0; i < 1000; i++)
			tickets.add("票 编号：" + i);
	}

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				public void run() {
					while (tickets.size() > 0) {

						try {
							TimeUnit.MILLISECONDS.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// //vector 的remove方法虽说是原子性的操作，但是和size
						// 函数结合操作并且sleep就不具备原子性操作，同样会出现并发问题，数组索引越界问题。。
						System.out.println("销售了--" + tickets.remove(0));
					}
				}
			}).start();
		}
	}
}
