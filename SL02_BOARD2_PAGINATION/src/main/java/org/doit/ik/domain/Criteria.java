package org.doit.ik.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이징 처리 기준, 검색 기준
@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum; // 현재 페이지번호
	private int amount; // 한 페이지에 출력할 게시글 수
	
	public Criteria() {
		this(1,5);
	}
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// ?pageNum=1&amount=10&type=T&keyword=홍길동
	   public String getListLink() {
	      UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
	            .queryParam("pageNum", this.getPageNum())
	            .queryParam("amount", this.getAmount());
	      //.queryParam("type", this.getPageNum())
	      //.queryParam("keyword", this.getPageNum())
	      return builder.toUriString();
	   }
}
