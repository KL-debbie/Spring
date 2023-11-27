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
    글 수정
  </xmp> 
  
     <form action="/board/modify" method="post">
     <table>  
       <tbody>
         <tr>
           <th>글번호</th>
           <td><input type="text" name="bno" class="full"  readonly="readonly"  value="${ board.bno }"></td>        
         </tr> 
         <tr>
           <th>제목</th>
           <td><input type="text" name="title" class="full"  autofocus="autofocus" value="${ board.title }"></td>        
         </tr> 
         <tr>
           <th>내용</th>
           <td><textarea  name="content" class="full" ><c:out value="${ board.content }"></c:out></textarea></td>        
         </tr> 
         <tr>
           <th>작성자</th>
           <td><input type="text" name="writer" class="short" readonly="readonly" value="${ board.writer }"></td>        
         </tr>  
       </tbody> 
       <tfoot>
         <tr>
           <td colspan="2">
             <button type="button"  data-oper="modify" class="edit">Modify</button>
             <button type="button" data-oper="list"  class="list">List</button>
           </td>
         </tr>
       </tfoot>
     </table>
     
  </form>
</div>
<script>

$(function() {
	
	var form = $("form");
	
	$("tfoot button").on("click",function(e){
		var operation = $(this).data("oper");
		if (operation == 'modify') {
			form.attr({
				"action":"/board/modify",
				"method":"post"
			}).submit();
		}else if (operation == 'list') {
			form.attr({
				"action":"/board/list",
				"method":"get"
			})	.empty()	.submit();
		}
	});
	
});
</script>
</body>
</html>