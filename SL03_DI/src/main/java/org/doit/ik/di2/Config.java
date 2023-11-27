package org.doit.ik.di2;

import java.lang.annotation.Annotation;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바클래스 설정 파일 == application-context.xml 85p
@Configuration
public class Config {

	//RecordImpl recordImpl = new RecordImpl();
	// <bean id="recordImpl" class="org.doit.ik.di.RecordImpl">
	// bean 객체 생성
	@Bean
	public RecordImpl recordImpl() {
		return new RecordImpl();
	}
	
	// RecordViewImpl rvi = new RecordViewImpl();
	// <bean id="rvi" class="org.doit.ik.di.RecordViewImpl">
	
	//Setter
	@Bean(name = "rvi")
	public RecordViewImpl getRecordViewImpl() {
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(recordImpl());
		return rvi;
	}
	
	//생성자 DI
	/*
	 * @Bean
	 *  public RecordViewImpl rvi() { 
	 * return new RecordViewImpl( recordImpl());
	 *  }
	 */
	
	
}
