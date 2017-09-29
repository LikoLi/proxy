package org.liko.framework.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;
import org.liko.framework.service.CglibFactory;
import org.liko.framework.service.CglibFactory2;
import org.liko.framework.service.ISomeService;
import org.liko.framework.service.SomeService;
import org.liko.framework.service.SomeServiceImpl;
import org.liko.framework.service.staticproxy.ServiceStaticProxy;

public class SomeServiceTest {
	@Test
	public void testSomeService() {
		ISomeService service = new SomeServiceImpl();
		String result = service.doFirst();
		System.out.println(result);
	}
	
	@Test
	public void testStaticProxy() {
		ISomeService target = new SomeServiceImpl();
		ISomeService service = new ServiceStaticProxy(target);
		String result = service.doFirst();
		System.out.println(result);
		service.doSecond();
		System.out.println("----------------------");
		ISomeService target1 = new SomeServiceImpl();
		target.doFirst();
		target.doSecond();
	}
	
	@Test
	public void testDynamicProxy() {
		final ISomeService target = new SomeServiceImpl();
		ISomeService service = (ISomeService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
			/*
			 * (non-Javadoc)
			 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
			 * proxy:代理对象
			 * method: 目标方法
			 * args： 目标方法的参数列表
			 */
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Object result = method.invoke(target, args);
				System.out.println("invoke");
				if (result != null) {
					result = ((String) result).toUpperCase();
				}
				return result;
			}
		});
		
		String result = service.doFirst();
		System.out.println(result);
		service.doSecond();
	}
	
	@Test
	public void testCglib() {
		SomeService target = new SomeService();
		CglibFactory cglibFactory = new CglibFactory(target);
		SomeService service = cglibFactory.myCglibCreator();
		String result = service.doFirst();
		System.out.println(result);
		service.doSecond();
	}
	
	@Test
	public void testCglib2() {
		ISomeService target = new SomeServiceImpl();
		CglibFactory2 cglibFactory = new CglibFactory2(target);
		ISomeService service = cglibFactory.myCglibCreator();
		String result = service.doFirst();
		System.out.println(result);
		service.doSecond();
	}
}
