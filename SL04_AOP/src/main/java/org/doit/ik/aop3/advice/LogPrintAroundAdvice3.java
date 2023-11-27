package org.doit.ik.aop3.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
@Component
public class LogPrintAroundAdvice3 implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		
		String methodName=	method.getMethod().getName(); 
		Log log = LogFactory.getLog(this.getClass());
		log.info(">" + methodName + "() start ");
		
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = method.proceed(); // 핵심기능 실행
		sw.stop();
		
		log.info(">" + methodName + "() end ");
		log.info(">" + methodName + "() 처리 시간 : " + sw.getTotalTimeMillis() + "ms");
		
		return result;
	}

}
