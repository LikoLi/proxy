package org.liko.framework.service;

//目标类: 代理类要增强的类
public class SomeServiceImpl implements ISomeService {

	public String doFirst() {
		System.out.println("doFirst...");
		return "abcde";
	}

	public void doSecond() {
		System.out.println("doSecond...");
	}

}
