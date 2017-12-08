package com.samuelnotes.concurrent.threadpool;

/**
 * 认识future
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T06_Future {
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {

		FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
			public Integer call() throws Exception {
				TimeUnit.MILLISECONDS.sleep(500);
				return 1000;
			}
		}); // new Callable () { Integer call();}

		new Thread(task).start();

		System.out.println(task.get()); // 阻塞

		// *******************************
		ExecutorService service = Executors.newFixedThreadPool(5);
		Future<Integer> f = service.submit(new Callable<Integer>() {

			public Integer call() throws Exception {
				TimeUnit.MILLISECONDS.sleep(500);
				return 1;
			}
		});
		System.out.println(f.get());
		System.out.println(f.isDone());

	}
}
