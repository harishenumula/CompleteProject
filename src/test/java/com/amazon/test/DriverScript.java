package com.amazon.test;

import org.testng.annotations.Test;

import com.amazon.commonFunLibrary.FunctionLibrary;

public class DriverScript {
	@Test
	public void startTest() {
		FunctionLibrary fun = new FunctionLibrary();
		fun.fun();
	}
}
