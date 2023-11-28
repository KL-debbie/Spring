package org.doit.ik.aop5;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex05 {

	public static void main(String[] args) {

		// p 209 XML 스키마 기반의 AOP 구현
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("application-context5.xml");

		Calculator calc=ctx.getBean("calc5",Calculator.class);
		System.out.println(calc.add(3, 4));
//		System.out.println(calc.sub(3, 4));
		
		System.out.println("end");
	}

}

// 스프링 > 3가지 AOP 구현 방식 
// 1) 스프링 API 를 이용한 AOP 구현
// org.doit.ik.aop2
// org.doit.ik.aop2.advice 
//		ㄴ LogPrintAroundAdvice