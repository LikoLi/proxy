package org.liko.framework.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyNoInterface {
	public static void main(String[] args) {
		C target = new C();
		CglibProxy proxy = new CglibProxy(target );
		C proxyC = proxy.getProxy();
		proxyC.add();
	}
}

class CglibProxy implements MethodInterceptor{

	private C target;
	
	public CglibProxy(C target) {
		this.target = target;
	}
	
	public C getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(C.class);
		enhancer.setCallback(this);
		return (C) enhancer.create();
	}
	
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("do before");
		Object result = method.invoke(target, args);
		System.out.println("do after");
		return result;
	}
	
}

class C {
	public void add() {
		System.out.println("add...");
	}
}
