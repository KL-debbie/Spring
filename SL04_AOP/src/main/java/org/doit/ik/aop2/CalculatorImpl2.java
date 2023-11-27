package org.doit.ik.aop2;

import org.doit.ik.aop.Calculator;

public class CalculatorImpl2 implements Calculator{

	@Override
	public int add(int x, int y) {
		// 전
		int result = x + y; // 핵심 기능
		// 후
		return result;
	}

	@Override
	public int sub(int x, int y) {
		
		int result = x - y;
	
		return result;
	}

	@Override
	public int mult(int x, int y) {
		
		int result = x * y;
	
		return result;
	}

	@Override
	public int div(int x, int y) {
		
		int result = x / y;
		
		return result;
	}

}
