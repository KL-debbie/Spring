package org.doit.ik.di2.test;

import org.doit.ik.di.RecordViewImpl;
import org.doit.ik.di2.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex03 {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		 RecordViewImpl rvi	=	(RecordViewImpl) ctx.getBean("rvi");
		// RecordViewImpl rvi	=	(RecordViewImpl) ctx.getBean("getRecordViewImpl");
		
		rvi.input();
		rvi.output();
		
		System.out.println("End");
		
		
	}

}
