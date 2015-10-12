package com.sdw.soft.proxy;

import java.lang.reflect.Proxy;

/**
 * @author shangyd
 * @date 2015年10月12日 下午6:07:43
 **/
public class ProxyTest {

	public static void main(String[] args){
		HRegionInterfaceTest test = (HRegionInterfaceTest) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{HRegionInterfaceTest.class}, new Invoker());
		test.get("-ROOT- .META.");
	}
}
