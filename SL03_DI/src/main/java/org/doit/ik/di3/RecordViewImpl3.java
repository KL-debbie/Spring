package org.doit.ik.di3;

import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordViewImpl3 implements RecordView3{

	// 생성자, 필드, Setter 에 가능
	@Autowired(required = false)
//	@Resource(name="record1") >> java 9부터 사용 X
//	@Inject
//	@Named(value = "recordImpl2")
	private RecordImpl3 record = null;
	
	@Override
	public void input() {
		try(Scanner sc = new Scanner(System.in)) {
			System.out.print("> kor, eng, mat input? ");
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int mat = sc.nextInt();
			
			this.record.setKor(kor);
			this.record.setEng(eng);
			this.record.setMat(mat);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void output() {
		System.out.printf(" kor = %d, eng = %d, mat = %d, tot = %d, avg = %.2f\n"
		,this.record.getKor()
		,this.record.getEng()
		,this.record.getMat()
		,this.record.total()
		,this.record.avg()
		);
	}
	
	

}
