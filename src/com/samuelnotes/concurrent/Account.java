package com.samuelnotes.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读问题（dirtyRead）
 * 
 * @author samuelwang
 */
public class Account {
	String name;
	double balance;

	public synchronized void set(String name, double balance) {
		this.name = name;

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.balance = balance;
	}

//	Account 对象锁没有在set 所在线程执行完毕，释放锁， getBalance无法获取对象锁
	public /*synchronized*/ double getBalance(String name) {
		return this.balance;
	}

	public static void main(String[] args) {
			final Account a = new Account();
			new Thread(new Runnable(){
				public void run() {
					a.set("zhangsan", 100.0);
				}
			}).start();
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			System.out.println(a.getBalance("zhangsan"));
			
			System.out.println(a.getBalance("zhangsan"));
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(a.getBalance("zhangsan"));
		}
}
