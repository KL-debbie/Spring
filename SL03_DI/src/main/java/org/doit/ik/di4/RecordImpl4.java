package org.doit.ik.di4;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Component // recordImpl4 빈 객체의 id(붙여야 스캔 대상이 됨)
public class RecordImpl4  implements Record4{

	private int kor;
	private int eng;
	private int mat;
	
	@Override
	public int total() {
		return kor + eng + mat;
	}
	@Override
	public double avg() {
		return total()/3.0;
	}
}
