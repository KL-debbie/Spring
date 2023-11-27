package org.doit.ik.di3.test;

import org.doit.ik.di3.RecordViewImpl3;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {
	
	public static void main(String[] args) {
		
		// 1. 스프링 객체 생성 + 조립 ( DI 컨테이너== 스프링 컨테이너)
		// 2. 2가지 방법
		//		1) xml 파일
		//			ㄴ src/main/resources/application-context.xml
		//		2) 자바(class) 파일
		//			ㄴ org.doit.ik.di2.Config.java
		
		
		String resourcesLoc = "application-context3.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourcesLoc);
		
		RecordViewImpl3 rvi	=	(RecordViewImpl3) ctx.getBean("rvi");
		
		rvi.input();
		rvi.output();
		
		System.out.println("End");
		
	}

}
