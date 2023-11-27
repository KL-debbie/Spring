package org.doit.ik.aop2.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;


public class LogPrintBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(
			Method method // add()
			, Object[] args // 3,4
			, Object target // 핵심 기능 실제 객체
			) throws Throwable {
		
		String methodName=	method.getName(); 
		Log log = LogFactory.getLog(this.getClass());
		log.info(">" + methodName + "() LogPrintBeforeAdvice 호출 ");
		
	}

}
