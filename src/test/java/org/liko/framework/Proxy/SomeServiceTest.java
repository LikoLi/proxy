package org.liko.framework.Proxy;

import org.junit.Test;
import org.liko.framework.service.ISomeService;
import org.liko.framework.service.SomeServiceImpl;
import org.liko.framework.service.proxy.ServiceStaticProxy;

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
}
