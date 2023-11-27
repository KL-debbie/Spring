package org.doit.ik.di.test;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordView;
import org.doit.ik.di.RecordViewImpl;

public class Ex01 {

	public static void main(String[] args) {

		//p59 스프링 컨테이너
		/*
		 *1. org.doit.ik.di 
		 *  ㄴ Record.java 인터페이스
		 *  ㄴ RecordImpl.java 클래스
		 *  ㄴ RecordView.java 인터페이스
		 *  ㄴ RecordViewImpl.java 클래스
		 */
		RecordImpl recordImpl = new RecordImpl();
		
//		RecordViewImpl rvi = new RecordViewImpl(recordImpl); 생성자 통한 DI 68p
		
		// Setter 통한 DI
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(recordImpl);
		
		rvi.input();
		rvi.output();
		
		System.out.println("End");
		
		// 스프링> 설정 파일 + 스프링 컨텍스트 : 객체 생성 + 조립
	}

}
