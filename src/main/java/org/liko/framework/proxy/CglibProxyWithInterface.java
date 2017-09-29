package org.liko.framework.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyWithInterface {
	public static void main(String[] args) {
		D d = new E();
		CglibProxy2 cglibProxy2 = new CglibProxy2(d);
		D proxy = cglibProxy2.getProxy();
		proxy.add();
	}
}

class CglibProxy2 implements MethodInterceptor {
	
	private D target;
	
	public CglibProxy2(D target) {
		super();
		this.target = target;
	}
	
	public D getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(D.class);
		enhancer.setCallback(this);
		return (D) enhancer.create();
	}

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("exec before...");
		Object invoke = method.invoke(target, args);
		System.out.println("exec after...");
		return invoke;
	}
	
}

interface D {
	void add();
}

class E implements D {
	public void add() {
		System.out.println("add");
	}
}