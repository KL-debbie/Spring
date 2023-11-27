<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<h3><a href="/scott/dept">dept</a></h3>
<xmp>
1. org.doit.ik.domain 패키지 추가
  ㄴ DeptDTO.java
  ㄴ EmpDTO.java 

2. home.jsp
  ㄴ <a href="/scott/dept">dept</a>

3. /scott/dept 요청 url  > 컨트롤러
  ㄴ  HomeController.java
  ㄴ  ScottController.java
  
 	ScottController.java method 생성
	@GetMapping("/scott/dept")
	public void dept(Local local, Model model)
	
4. views 
   ㄴ  scott폴더
     ㄴ dept.jsp

5. org.doit.ik.mapper.scott 패키지
   ㄴ DeptMapper.java
      ㄴ ArrayList<DeptDTO> selectDept();	추가
      
6. src/main/resources > org.doit.ik.mapper.scott 폴더 생성
      ㄴ DeptMapper.xml 추가 후
        <mapper namespace="org.doit.ik.mapper.scott.DeptMapper">

     <select id="selectDept" resultType="org.doit.ik.domain.DeptDTO">
       SELECT d.deptno, dname, loc, COUNT(e.empno) numberOfEmps    
        FROM dept d FULL JOIN emp e ON d.deptno = e.deptno
       GROUP BY d.deptno, dname, loc
       ORDER BY deptno ASC
     </select> 추가

** ScottController.java 스프링 빈 객체 생성 시기?
 ㄴ servlet-context.xml 안에 <context:component-scan base-package="org.doit.ik" /> 에 의해 생성

** org.doit.ik.mapper.scott.DeptMapper 스프링 빈 객체 생성 시기?
 ㄴ <mybatis-spring:scan base-package="org.doit.ik.mapper"/> 에 의해 하위 패키지들도 모두 스캔되어 생성
 
7. webapp/scott/dept 파일 추가 후 요청 url로 사용

8. 모달창 띄우기
  </xmp>      
</body>
</html>
