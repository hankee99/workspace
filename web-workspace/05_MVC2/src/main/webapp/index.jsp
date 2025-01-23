<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>MVC2 - 전한길</h1>
	<!-- 
		전체회원조회
		1. DB작업 O
		2. query -> select * from member_tbl order by 1
		3. 사용자에게 입력받을 데이터 없음 -> Controller 호출(Servlet)
	 -->
	 <h3><a href="/allMember">1. 전체회원 조회</a></h3>
	 <!-- 
	 	insert into member_tbl values(member_seq.nextval,?,?,?,?,?,3,to_char(sysdate,'yyyy-mm-dd'))
	  -->
	  <h3><a href="/joinFrm">2. 회원가입</a></h3>
	  
	  <h3><a href="/searchIdFrm">3. 아이디 찾기</a></h3>
	  <h3><a href="/updateSearchFrm">4. 회원정보 수정</a></h3>
</body>
</html>