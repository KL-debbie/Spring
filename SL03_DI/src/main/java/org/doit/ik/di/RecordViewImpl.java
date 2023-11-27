package org.doit.ik.di;

import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordViewImpl implements RecordView{

	private RecordImpl record = null;
//	private RecordImpl record =  new RecordImpl();
	
	@Override
	public void input() {
		try(Scanner sc = new Scanner(System.in)) {
			System.out.print(">kor, eng, mat input? ");
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
