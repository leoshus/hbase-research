package com.sdw.soft.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author shangyd
 * @date 2015年10月12日 下午6:07:00
 **/
public class Invoker implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("test proxy args=" + args[0]);
		return null;
	}

}
