package org.liko.framework.service.staticproxy;

import org.liko.framework.service.ISomeService;
import org.liko.framework.service.SomeServiceImpl;

//静态代理类
public class ServiceStaticProxy implements ISomeService {
	
	private ISomeService target;
	
	public ServiceStaticProxy(ISomeService target) {
		this.target = target;
	}

	public ServiceStaticProxy() {
		super();
	}



	public String doFirst() {
		//调用目标对象的目标方法, 该方法返回全小写字母
		String result = target.doFirst();
		//增强: 将目标方法返回的全小写字母变为全大写
		
		return result.toUpperCase();
		
	}

	public void doSecond() {
		target.doSecond();
	}
	
}
