<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="shortcut icon" href="http://localhost/images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
 span.material-symbols-outlined{
    vertical-align: text-bottom;
 }
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">ms Spring Home💜</a></h1>
  <ul>
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<h3>
  <span class="material-symbols-outlined">spring</span> 
</h3>
<div>
  <xmp class="code">
    N-tier 방식
    3-tier 방식
    웹 프로젝트 3-tier 방식
    1) 화면 계층 - Presentation Tier 
    2) 비지니스(로직) 계층 - Business Tier
    3) 데이터 계층 == 영속 계층 - Persistence Tier
    -- 스프링 MVC 패턴 개발 --
    
    -- 패키지 --
    org.doit.ik
     ㄴ config : 설정 관련 패키지
     ㄴ domain : VO, DTO
     ㄴ persistence : DAO , MyBatis 인터페이스, 클래스(mapper)
     ㄴ controller
     ㄴ service
     ㄴ exception
     ㄴ aop
     ㄴ util
     ㄴ security
     
     ===================================
           CREATE TABLE tbl_board
    (
      bno number(10)
      , title varchar2(200) not null
      , content varchar2(2000) not null
      , writer varchar2(50) not null
      , regdate date default sysdate
      , updatedate date default sysdate
    );
    
    alter table tbl_board add constraint pk_tblboard_bno primary key(bno);
    
     CREATE SEQUENCE seq_board; 
     ===================================
     
     1. DB 환경 구축 확인 + MyBatis
       ㄴ pom.xml  의존모듈 확인
       ㄴ root-context.xml DB 연동 모든 스프링 빈 객체 생성 + 등록 + 연결
          - 스프링, MyBatis 에서 DB 연동할 때 DataSource 객체 사용
     
     2. web.xml
     
     3. org.doit.ik.domain 패키지 추가
        ㄴ BoardVO 
     
     4. 게시글 목록
     org.doit.ik.mapper 패키지
       ㄴ BoardMapper.java 인터페이스
       
       resources  >  org.doit.ik.mapper  > BoardMapper.xml
       
     5. 컨트롤러 생성 > 컨트롤러메서드 생성 > 서비스.. > jsp  
     HomeController.java > BoardController.java
     
     6. org.doit.ik.service > BoardService 인터페이스, BoardServiceImpl 클래스
     
     7. servlet-context.xml
     <context:component-scan base-package="org.doit.ik" />
      org.doit.ik 패키지 및 하위 패키지 찾아 component 들을 자동적으로 스캔 후 스프링 빈 객체로 등록 + 연결
      @Component
      @Controller
      @Service
      @Repository
      
      8. ViewResolver 등록 : View 검색
      	<!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory -->
	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
	
	/WEB-INF/views/board/list.jsp 응답
	
	  9. webapp > board > list.txt (요청 url)
	  
	  10. 글쓰기
	  1) <a href="/board/register"> 글쓰기 응답
	  2) BoardController.java 컨트롤러메서드 추가
	  
	  1) BoardMapper.java 추가코딩, xml 수정
	  2) 
  </xmp> 
</div>
<script>
</script>
</body>
</html>