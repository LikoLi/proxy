package org.liko.framework.service;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//注意, 使用Cglib动态代理, 要求目标类不能是final的
//因为final类是不能有子类的, 而Cglib动态代理的增强原理是, 子类增强父类
public class CglibFactory2 implements MethodInterceptor {

	private ISomeService target;
	
	public CglibFactory2(ISomeService target) {
		super();
		this.target = target;
	}

	public CglibFactory2() {
		super();
	}

	public ISomeService myCglibCreator() {
		Enhancer enhancer = new Enhancer();
		//指定父类, 即目标类, 因为Cglib动态代理增强的原理是: 子类增强父类
		enhancer.setSuperclass(ISomeService.class);
		
		//设置回掉接口
		enhancer.setCallback(this);
		
		// create() 方法用于创建Cglib动态代理对象, 即目标子类对象
		return (ISomeService) enhancer.create();
	}
	
	//回调接口的方法的执行条件: 代理对象执行目标方法时会触发此方法的执行
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Object invoke = method.invoke(target, args);
		if(invoke != null)
			invoke = ((String)invoke).toUpperCase();
		return invoke;
	}

}
