package com.samuelnotes.concurrent.container3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T09_SynchronusQueue { // 容量为0
	public static void main(String[] args) throws InterruptedException {
//		 适用于得到消息之后，马上消费的场景下， 容量为0 
		final BlockingQueue<String> strs = new SynchronousQueue<String>();

		new Thread(new Runnable() {

			public void run() {
				try {
					System.out.println(strs.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
//
		strs.put("aaa"); // 阻塞等待消费者消费
		// strs.add("aaa");
		System.out.println(strs.size());
	}
}
