package com.samuelnotes.concurrent.threadpool;

/**
 * 认识Executor
 */

import java.util.concurrent.Executor;

public class T01_MyExecutor implements Executor {

	public static void main(String[] args) {
		new T01_MyExecutor().execute(new Runnable() {

			public void run() {
				System.out.println("hello executor");
			}
		});
	}

	public void execute(Runnable command) {
		// new Thread(command).run();
		command.run();
	}

}
