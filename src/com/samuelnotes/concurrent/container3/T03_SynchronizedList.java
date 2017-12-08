package com.samuelnotes.concurrent.container3;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T03_SynchronizedList {
	public static void main(String[] args) {
		List<String> strs = new ArrayList<String>();
//		强转利用工具进行加锁
		List<String> strsSync = Collections.synchronizedList(strs);
	}
}
