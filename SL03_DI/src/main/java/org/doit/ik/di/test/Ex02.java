package org.doit.ik.di.test;

import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {

	public static void main(String[] args) {

		// 1. 스프링 객체 생성 + 조립 ( DI 컨테이너== 스프링 컨테이너)
		// 2. 2가지 방법
		//		1) xml 파일
		//			ㄴ src/main/resources/application-context.xml
		//		2) 자바(class) 파일
		//			ㄴ org.doit.ik.di2.Config.java
		
		String resourcesLoc = "application-context.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourcesLoc);
		
		RecordViewImpl rvi	=	(RecordViewImpl) ctx.getBean("rvi");
		
		rvi.input();
		rvi.output();
		
		System.out.println("End");
		
		
	}

}
