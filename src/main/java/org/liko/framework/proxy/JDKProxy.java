package org.liko.framework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {
	public static void main(String[] args) {
		final A b = new B();
		A a = (A) Proxy.newProxyInstance(B.class.getClassLoader(), B.class.getInterfaces(), new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("do before...");
				Object result = method.invoke(b, args);
				System.out.println("do after...");
				return result;
			}
		});
		a.add();
	}
}

interface A {
	void add();
}

class B implements A {
	public void add() {
		System.out.println("exec add...");
	}
}
