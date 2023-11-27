package org.doit.ik.aop4.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
@Component("logPrintProfiler4")
public class LogPrintProfiler4 {

	// 3. AfterAdvice 221p
	public void after(JoinPoint joinPoint ) {
		String methodName = joinPoint.getSignature().getName();
	      Log log = LogFactory.getLog(this.getClass());
	      log.info(">>> " + methodName + "() : LogPrintProfiler4.after 가 호출됨... ");
	}
	
	// 2.BeforeAdvice 217p
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
	      Log log = LogFactory.getLog(this.getClass());
	      log.info(">>> " + methodName + "() : LogPrintProfiler4.before 가 호출됨... ");
	}
	
	// 1. AroundAdvice 222p
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{

		String methodName = joinPoint.getSignature().toShortString();
		
		Log log =  LogFactory.getLog(this.getClass());
		
		log.info("> " + methodName +"() start.");
		StopWatch sw = new StopWatch();
		sw.start();       
		// 핵심 관심 사항.
		Object  result = joinPoint.proceed() ;  // calc.add()  
		
		sw.stop();
		log.info("> " + methodName +"() end.");
		log.info("> " + methodName +"() 처리 시간 :  " + sw.getTotalTimeMillis() +"ms");
		return result;

	}
}
