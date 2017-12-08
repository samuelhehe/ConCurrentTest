package com.samuelnotes.concurrent.container3;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_ConcurrentQueue {
	public static void main(String[] args) {
		
		Queue<String> strs = new ConcurrentLinkedQueue<String>();

		for (int i = 0; i < 10; i++) {
			strs.offer("a" + i); // add
		}

		System.out.println(strs);

		System.out.println(strs.size());
		// 取第一个出来并且删掉第一个
		System.out.println(strs.poll());
		System.out.println(strs.size());
		// 仅仅读取第一个数值，不删除
		System.out.println(strs.peek());
		System.out.println(strs.size());

		// 双向队列Deque
	}
}
