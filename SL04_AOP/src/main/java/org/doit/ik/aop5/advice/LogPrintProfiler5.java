package org.doit.ik.aop5.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
@Component("logPrintProfiler5")
@Aspect //보조기능 ==  <aop:aspect id="traceAspect"  ref="logPrintProfiler5">
public class LogPrintProfiler5 {

	
	//<aop:pointcut expression="execution(* org.doit.ik.aop..*.*(*,*))" id="publicMethod" />
	@Pointcut("execution(* org.doit.ik.aop..*.*(*,*))")
	private void publicMethod() {}
	
	// 3. AfterAdvice 221p
	@After("publicMethod()")  
	public void after(JoinPoint joinPoint ) {
		String methodName = joinPoint.getSignature().getName();
	      Log log = LogFactory.getLog(this.getClass());
	      log.info(">>> " + methodName + "() : LogPrintProfiler5.after 가 호출됨... ");
	}
	
	// 2.BeforeAdvice 217p
	@Before("publicMethod()")  
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
	      Log log = LogFactory.getLog(this.getClass());
	      log.info(">>> " + methodName + "() : LogPrintProfiler5.before 가 호출됨... ");
	}
	
	// 1. AroundAdvice 222p
	@Around("publicMethod()")  
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
